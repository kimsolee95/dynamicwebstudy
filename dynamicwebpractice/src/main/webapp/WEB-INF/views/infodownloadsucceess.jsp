<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	Long totalSaveedInfo = (Long) request.getAttribute("totalSaveedInfo");
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h5>
		<% if (totalSaveedInfo != null) { %> <%= totalSaveedInfo %> <%} %>개의 WIFI 정보를 정상적으로 저장하였습니다.
	</h5>
	<A href="/wfinfo">홈으로 가기</A>

</body>
</html>