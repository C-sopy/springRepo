<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header>
		<h1>FileUpload Demo</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3">loginForm</li>
	</ul>
	<div class="container">
		<h2>시각화 영역</h2>
		<div id="chart1"></div>
	</div>
	<div class="container">
		<div class="card">
			<div class="card-header">
				<h2>${sessionScope.sessionName }님의LogList</h2>
			</div>
			<div class="card-body">

				<table class="table table-striped">
					<thead>
						<tr>
							<th scope="col">Num</th>
							<c:if test="${sessionScope.sessionID eq 'admin'}">
								<th scope="col">아이디</th>
							</c:if>
							<th scope="col">아이피</th>
							<th scope="col">UAgent</th>
							<th scope="col">Status</th>
							<th scope="col">Time</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="e" items="${logList }" varStatus="i">
							<c:choose>
								<c:when test="${i.index == 0}">
									<tr style="background-color: orange;">
										<th scope="row">${e.num }◀</th>
										<c:if test="${sessionScope.sessionID eq 'admin'}">
											<th scope="col">${e.idn }</th>
										</c:if>
										<td>${e.reip }</td>
										<td>${e.uagent }</td>
										<td>${e.status }</td>
										<td>${e.logtime }</td>
									</tr>
								</c:when>
								<c:otherwise>
									<tr style="background-color: white;">
										<th scope="row">${e.num }</th>
										<c:if test="${sessionScope.sessionID eq 'admin'}">
											<th scope="col">${e.idn }</th>
										</c:if>
										<td>${e.reip }</td>
										<td>${e.uagent }</td>
										<td>${e.status }</td>
										<td>${e.logtime }</td>
									</tr>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</tbody>
				</table>

			</div>
		</div>
	</div>

</article>
<script src="https://d3js.org/d3.v3.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/c3/0.4.11/c3.min.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<script>
	$.ajaxSetup({
		cache : false
	});
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
	});
</script>
<%@include file="../temp/footer.jsp"%>
