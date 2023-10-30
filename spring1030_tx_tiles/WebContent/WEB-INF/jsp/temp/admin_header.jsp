<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="rPath" value="${pageContext.request.contextPath}/resources" />
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>temp/admin_header</title>
<meta charset="euc-kr">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" />

<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script>
	$(function() {
		$('.nav-link').mousemove(function() {
			// Class를 추가해주는 함수
			$(this).addClass("active");
		});
		$('.nav-link').mouseleave(function() {
			// Class를 추가해주는 함수
			$(this).removeClass("active");
		})
	});
</script>
</head>
<body>
	<div class="container">
		<ul class="nav nav-pills">
			<li class="nav-item"><a class="nav-link" aria-current="page"
				href="${pageContext.request.contextPath}/admin/welcome">Home</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/admin/userInfo">User
					Info</a></li>
			<li class="nav-item"><a class="nav-link"
				href="${pageContext.request.contextPath}/admin/admin">Admin</a></li>
			<li class="nav-item "><a class="nav-link"
				href="${pageContext.request.contextPath}/admin/survey/surveyForm">설문관리</a>
			</li>
			<c:if test="${pageContext.request.userPrincipal.name != null}">
				<li class="nav-item "><a class="nav-link"
					href="${pageContext.request.contextPath}/admin/logout">Logout</a></li>

			</c:if>
		</ul>
	</div>