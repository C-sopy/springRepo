<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color:white">TodayProfile Page</header>
	<ul class="list-unstyled">
	<li class="border-top my-3">kosmo132기 TodayProfile 페이지 입니다.</li>
	<%@include file="todayAdviceView.jsp" %>
	</ul>
	<div class="container">
	   <h2>${profile }</h2>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>