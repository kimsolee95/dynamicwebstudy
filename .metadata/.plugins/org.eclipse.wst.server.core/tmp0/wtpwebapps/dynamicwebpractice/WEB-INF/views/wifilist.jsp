<%@page import="java.util.*" %>
<%@page import="dynamicwebpractice.test.openapi.dto.Row" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<Row> wifiInfoList = (List<Row>) request.getAttribute("wifiInfolist");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>와이파이 정보 구하기</title>
	<style>
		
		#menu-tab {
			width: 25%;
			text-align: center;
		}
		
		#menu-tab th {
			padding: 2px;
		}
	
		#mylocation-section {
			width: 40%;
		}
	
		#wifiinfolist {
			width: 100%;
			border-collapse: collapse;
		}
		
		#wifiinfolist td, #wifiinfolist th {
			border: 1px solid #ddd;
			padding: 8px;
		}
		
		#wifiinfolist th {
			padding-top: 10px;
			padding-bottom: 12px;
			text-align: left;
			background-color: #04AA6D;
			color: white;
		}
		
		.btnFrm {
			float: left;
			margin: 0;
		}
	</style>
</head>
<body>
	
	
	<h1>와이파이 정보 구하기</h1>
	
	<table id="menu-tab">
		<tbody>
			<tr>
				<th><A href="/wfinfo">홈</A></th>
				<th><A href="/wfinfo-myhistory">위치 히스토리 목록</A></th>
				<th><form name="frmApi" id="frmApi" method="post" action="callapi" class="btnFrm">Open API 와이파이 정보 가져오기</form></th>
			</tr>
		</tbody>
	</table>
	
	<!-- 
	<p>LAT: <input id="myLat">, LNT: <input id="myLnt"> <button id="getMyLocation">내 위치 가져오기</button>
	<form name="frmGetNearWifi" id="frmGetNearWifi" method="post" class="btnFrm">
		<button>근처 WIPI 정보 보기</button>
		<input type="hidden" name="lat" id ="lat" value="" />
		<input type="hidden" name="lnt" id ="lnt" value="" />
	</form>
	 </p>
	  -->
	  
	 <table id="mylocation-section">
	 	<tbody>
	 		<tr>
	 			<th>LAT: <input id="myLat">,</th>
	 			<th>LNT: <input id="myLnt"></th>
	 			<th><button id="getMyLocation">내 위치 가져오기</button></th>
	 			<th>
					<form name="frmGetNearWifi" id="frmGetNearWifi" method="post" class="btnFrm">
						<button>근처 WIPI 정보 보기</button>
						<input type="hidden" name="lat" id ="lat" value="" />
						<input type="hidden" name="lnt" id ="lnt" value="" />
					</form>
	 			</th>
	 		</tr>
	 	</tbody>
	 </table>
	
	<table id="wifiinfolist">
		<tbody>
			<tr>
				<th>거리(Km)</th>
				<th>관리번호</th>
				<th>자치구</th>
				<th>와이파이명</th>
				<th>도로명주소</th>
				<th>상세주소</th>
				<th>설치위치(층)</th>
				<th>설치유형</th>
				<th>설치기관</th>
				<th>서비스구분</th>
				<th>망종류</th>
				<th>설치년도</th>
				<th>실내외구분</th>
				<th>WIFI접속환경</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>작업일자</th>
			</tr>
			<% if (wifiInfoList == null) { %>
			<tr>
				<td colspan="17" align="center">위치 정보를 입력한 후 조회해주세요</td>
			</tr>
			<% } else { %>
				<% for (Row row : wifiInfoList) {%>
					<tr>
						<td><%=row.getDistance() %></td>
						<td><%=row.getXSwifiMgrNo() %></td>
						<td><%=row.getXSwifiWrdofc() %></td>
						<td><%=row.getXSwifiMainNm() %></td>
						<td><%=row.getXSwifiAdres1() %></td>
						<td><%=row.getXSwifiAdres2() %></td>
						<td><%=row.getXSwifiInstlFloor() %></td>
						<td><%=row.getXSwifiInstlTy() %></td>
						<td><%=row.getXSwifiInstlMby() %></td>
						<td><%=row.getXSwifiSvcSe() %></td>
						<td><%=row.getXSwifiCmcwr() %></td>
						<td><%=row.getXSwifiCnstcYear() %></td>
						<td><%=row.getXSwifiInoutDoor() %></td>
						<td><%=row.getXSwifiRemars3() %></td>
						<td><%=row.getLat() %></td>
						<td><%=row.getLnt() %></td>
						<td><%=row.getWorkDttm() %></td>
					</tr>
				<% } %>
			<% } %>
		</tbody>
	</table>
	
<script>
	
	const frmApi = document.getElementById('frmApi'); // API 통신 위한 폼
	const getMyLocationButton = document.getElementById('getMyLocation'); //내위치 가져오기 버튼
	const frmGetNearWifi = document.getElementById('frmGetNearWifi'); // 내 위치(위도, 경도)와 가까운 와이파이 정보 찾기 버튼
	
	var latitude;
	var longitude;
	
	//WIFI info 전체 저장 요청하기
	frmApi.addEventListener('click', event => {
		frmApi.method = "post";
		frmApi.action = "wfinfo-load";
		frmApi.submit();
	});
	
	//내 위치 좌표 가져오기
	getMyLocationButton.addEventListener('click', event => {
		navigator.geolocation.getCurrentPosition(function(pos) {
		    console.log(pos);
		    latitude = pos.coords.latitude;
		    longitude = pos.coords.longitude;
		    
		    document.getElementById('myLat').value = latitude;
		    document.getElementById('myLnt').value = longitude;
		    
		});
	});
	
	//내 위치와 가까운 와이파이 정보 가져오기
	frmGetNearWifi.addEventListener('click', event => {
		
		//const lat = document.getElementById('myLat').value;
		//const lnt = document.getElementById('myLnt').value;
		
		document.getElementById('lat').value = latitude;
		document.getElementById('lnt').value = longitude;
		
		console.log('확인');
		console.log(latitude);
		console.log(longitude);
		
		frmGetNearWifi.method = 'post';
		frmGetNearWifi.action = "/wfinfo";
		frmGetNearWifi.submit();
	});
	
	
</script>
	
</body>
</html>