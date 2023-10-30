<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/admin_header.jsp"%>
<div class="container">
	<div class="row">
		<h1>Title : ${title }</h1>
		<p>Message : ${message }</p>
		<div style="width: 750px; margin: auto">
<table class="table">
		<caption> ID : ${sessionScope.sessionID}</caption>
		<thead>
		
			<tr>
				<th>No</th>
				<th>제목</th>
				<th>항목</th>
				<th>투표수</th>
				<th>Date</th>
			</tr>
		</thead>
		<tbody>
		<%-- 반복시작 --%>
		<c:forEach var="e" items="${list}">
			<tr>
				<td>${e.num}</td>
				<td><a href="surveyAdminDetail?num=${e.num}">${e.sub}</a></td>
				<td>${e.code}</td>
				<td>${e.surveytotal}</td>
				<td>${e.sdate}</td>
			</tr>
		</c:forEach>
		<%-- 반복 끝 --%>
		</tbody>
		<tfoot>
			<tr>
				<th colspan="5">
				<input type="button" value="등록폼" id="writeBtn"
					onclick="location='surveyForm'">
					<input type="button" value="설문참여하기" id="surveyClient"></th>
			</tr>
		</tfoot>
</table>
</div>
 
</div>
</div>