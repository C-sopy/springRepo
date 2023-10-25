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



</script>
<%@include file="../temp/footer.jsp"%>