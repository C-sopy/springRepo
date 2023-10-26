<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header>
		<h1 style="color:white;">My Page</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<h3>My login log list</h3>
		<table class="table table-hover">
				<thead>
					<tr class="table-secondary">
						<th scope="col">번호</th>
						<c:if test="${sessionScope.sessionID == 'admin'}">
						<th scope="col">ID</th>
						</c:if>						
						<th scope="col">IP</th>
						<th scope="col">기기</th>
						<th scope="col">status</th>
						<th scope="col">날짜</th>
					</tr>
				</thead>
				<tbody>
				<%-- 반복시작  --%>
				<c:forEach var="list" items="${list}">
					<tr>
						<td scope="col">${list.num}</td>
						<c:if test="${sessionScope.sessionID == 'admin'}">
						<td scope="col">${list.idn}</td>
						</c:if>
						<td scope="col">${list.reip}</td>
						<td scope="col">${list.uagent}</td>
						<td scope="col">${list.status}</td>
						<td scope="col">${list.logtime}</td>
					</tr>
				</c:forEach>
				<%-- 반복끝  --%>	
				</tbody>
			</table>
	</div>
	<div class="container">
		<h3>시각화 영역 </h3>
		<div id="chart1"></div>
		
	</div>
</article>
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>

$.ajaxSetup({cache : false});
$.ajax({
	url : "${cPath}/jsonTest1",
	success : function(jsondata) {
		console.log(jsondata[0].sub);
		console.log(jsondata[1]);
		console.log("---------------------");
		var chart = c3.generate({
			bindto : '#chart1',
			data : {
				json : [ jsondata[1] ],
				keys : {
					value : Object.keys(jsondata[1]),
				},
				type : 'donut'
			},
			donut : {
				title : jsondata[0].sub,
			},
		});
	},
	error : function(e) {
		console.log("error:" + e);
	}
})

$.ajax({
	url: "${cPath}/member/myloglist"
})



</script>
<%@include file="../temp/footer.jsp"%>