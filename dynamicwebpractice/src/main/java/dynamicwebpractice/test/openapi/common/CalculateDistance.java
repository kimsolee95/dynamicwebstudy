package dynamicwebpractice.test.openapi.common;

import java.util.List;

import dynamicwebpractice.test.openapi.dto.Row;

public class CalculateDistance {
	
	//거리 계산하기
	public void getDistance(String lat, String lnt, List<Row> wifiInfolist) {
		
		double lat1 = Double.parseDouble(lat);
		double lnt1 = Double.parseDouble(lnt);
		
		for (int i=0; i<wifiInfolist.size(); i++) {
			
			double lnt2 = Double.parseDouble(wifiInfolist.get(i).getLnt());
			double lat2 = Double.parseDouble(wifiInfolist.get(i).getLat());
			
		    double theta = lnt1 - lnt2;
		    double dist = Math.sin(deg2rad(lat1))* Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1))*Math.cos(deg2rad(lat2))*Math.cos(deg2rad(theta));
		    dist = Math.acos(dist);
		    dist = rad2deg(dist);
		    dist = dist * 60*1.1515*1609.344;
		    
		    dist = dist / 1000;
		    dist = (Math.round(dist*10000)/10000.0); //소수 4자리까지 반올림한 값으로 변환
			
		    wifiInfolist.get(i).setDistance(dist);
		}
	
	}
	
	//10진수를 radian(라디안)으로 변환하기
	public static double deg2rad(double deg){
	    return (deg * Math.PI/180.0);
	}
	
	//radian(라디안)을 10진수로 변환하기
	public static double rad2deg(double rad){
	    return (rad * 180 / Math.PI);
	}

}
