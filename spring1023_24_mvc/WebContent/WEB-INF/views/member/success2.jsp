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
<h4>�ݰ����ϴ� <span>${vo.uname}</span>(${vo.id}) ��</h4>
<p>���Գ�¥ : ${vo.mdate}</p>

</body>
</html>