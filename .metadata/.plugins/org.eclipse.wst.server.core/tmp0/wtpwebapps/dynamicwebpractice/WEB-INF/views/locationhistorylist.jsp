<%@page import="java.util.*" %>
<%@page import="dynamicwebpractice.test.openapi.dto.MyLocHistory" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	List<MyLocHistory> myHistoryList = (List<MyLocHistory>) request.getAttribute("myHistoryList");
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

	<table id="wifiinfolist">
		<tbody>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
			<%if (myHistoryList != null) {%>
				<% for (MyLocHistory myHis : myHistoryList) { %>
				<tr>
					<td><%= myHis.getId() %></td>
					<td><%= myHis.getLat() %></td>
					<td><%= myHis.getLnt() %></td>
					<td><%= myHis.getUseDttm() %></td>
					<td>
						<form action="/wfinfo-myhistory">
							<button type="submit" formmethod="GET">삭제</button>
							<input type="hidden" name="historyId" value="<%= myHis.getId() %>" />
						</form>
					</td>
				</tr>
				<% } %>
			<% } %>
			
		</tbody>
	</table>


<script>

	
	
</script>

</body>
</html>