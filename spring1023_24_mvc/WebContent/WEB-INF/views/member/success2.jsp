<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>success.jsp</title>
<style>
nav, h3, h4, p{
	padding:30px;
}
nav a {
	font-weight: bold;
	color: blue;
}
</style>
</head>
<body>
<%@include file="../main/menu.jsp" %>
<h3>Bienvenu !</h3>
<br>
<h4>반갑습니다 <span>${vo.uname}</span>(${vo.id}) 님</h4>
<p>가입날짜 : ${vo.mdate}</p>

</body>
</html>