package dynamicwebpractice.test.openapi.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dynamicwebpractice.test.openapi.dto.WifiInfo;

public class CallApiService {
	
	private DBService dbService = new DBService();
	
	//1. 총 data total list get
	public long callTotalNumWifiInfo() throws IOException {
		
    	// 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/59534c59416168683631667a765a51/json/TbPublicWifiInfo/1/1/"); /*URL*/
        // 3. URL 객체 생성.
        URL url = new URL(urlBuilder.toString());
        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 5. 통신을 위한 메소드 SET.
        conn.setRequestMethod("GET");
        // 6. 통신을 위한 Content-type SET. 
        conn.setRequestProperty("Content-type", "application/json");
        // 7. 통신 응답 코드 확인.
        System.out.println("Response code: " + conn.getResponseCode());
        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        rd.close();
        conn.disconnect();
        // 11. 전달받은 데이터 확인.
        System.out.println(sb.toString());
        
        //12. 자바 object 변환(추가)
        //Gson gson = new Gson();
        //Gson gson = new GsonBuilder().setExclusionStrategies(new AnnotationBasedExclusionStrategy()).create()
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        
        WifiInfo wifiInfo = gson.fromJson(sb.toString(), WifiInfo.class);

        
        return wifiInfo.getTbPublicWifiInfo().getListTotalCount();
	}
	
	
	//2. data list 1000개 단위로 쪼개서 url 호출 후, dto 담기
	public long saveWifiInfo() throws IOException {
		
		long result = 0;
		
		long totalNum = callTotalNumWifiInfo(); // 총 data 수
		long dataChunk = 999; //한번에 요청가능한 data 수
		
		long mod = totalNum % 999; //나머지 숫자
		long cnt = totalNum / 999; //API 호출 카운트
		
		long startIdx = 1;
		long endIdx = startIdx + dataChunk;
		
	
		for (int i=1; i<=cnt; i++) {
			//지정한 인덱스에 맞는 api data dto set
			WifiInfo wifiInfo = callWifiInfoApiWithPaging(startIdx, endIdx);
			
			//DB insert
			result += dbService.dbInsert(wifiInfo);
			
			startIdx = endIdx + 1;
			endIdx += dataChunk;
		}
		
		if (mod > 0) {
		
			endIdx = totalNum;
			//지정한 인덱스에 맞는 api data dto set
			WifiInfo wifiInfo = callWifiInfoApiWithPaging(startIdx, endIdx);
			
			//DB insert
			result += dbService.dbInsert(wifiInfo);
		}
	
		return result;
	}
	
	
	//서울시 공공 와이파이 데이터 지정한 인덱스에 맞는 api call
	public WifiInfo callWifiInfoApiWithPaging(long startIdx, long endIdx) throws IOException {
    	// 1. URL을 만들기 위한 StringBuilder.
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/59534c59416168683631667a765a51/json/TbPublicWifiInfo"); /*URL*/
		
        // 2. 오픈 API의요청 규격에 맞는 파라미터 생성, 페이징 처리 (total count가 1000이 넘을 경우, Open API는 1회 1000건을 넘을 수 없음.)
        urlBuilder.append("/" + startIdx);
        urlBuilder.append("/" + endIdx + "/");
        
        // 3. URL 객체 생성.
        URL url = new URL(urlBuilder.toString());
        // 4. 요청하고자 하는 URL과 통신하기 위한 Connection 객체 생성.
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        // 5. 통신을 위한 메소드 SET.
        conn.setRequestMethod("GET");
        // 6. 통신을 위한 Content-type SET. 
        conn.setRequestProperty("Content-type", "application/json");
        // 7. 통신 응답 코드 확인.
        System.out.println("Response code: " + conn.getResponseCode());
        // 8. 전달받은 데이터를 BufferedReader 객체로 저장.
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        // 9. 저장된 데이터를 라인별로 읽어 StringBuilder 객체로 저장.
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        // 10. 객체 해제.
        rd.close();
        conn.disconnect();
        
        //12. 자바 object 변환(추가)
        //Gson gson = new Gson();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.excludeFieldsWithoutExposeAnnotation().create();
        
        WifiInfo wifiInfo = gson.fromJson(sb.toString(), WifiInfo.class);
        
        return wifiInfo;
	}
	
	
	
}
