<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>success.jsp</title>
</head>
<body>
<h1>${vo.uname }님 가입을 축하드립니다. 당신의 아이디는</h1>
<span>${vo.id } 입니다.</span>
<p>가입날짜 : ${vo.mdate }</p>
</body>
</html>