<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	hahaha
	
	<button onclick="getLocation()">Show my location</button>

	<p id="pos"></p>

	<script>
		
	navigator.geolocation.getCurrentPosition(function(pos) {
	    console.log(pos);
	    var latitude = pos.coords.latitude;
	    var longitude = pos.coords.longitude;
	    alert("현재 위치는 : " + latitude + ", "+ longitude);
	});
	
	</script>

</body>
</html>