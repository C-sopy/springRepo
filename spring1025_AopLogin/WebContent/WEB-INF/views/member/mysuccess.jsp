<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<style>
nav, h3, h4, p {
	padding: 30px;
}

nav a {
	font-weight: bold;
	color: blue;
}
</style>
<article>
	<header>
		<h1>Member Login</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<h3>Bienvenu !</h3>
		<br>
		<h4>
			�ݰ����ϴ� <span>${name}</span>(${id}) ��
		</h4>
		<p>���Գ�¥ : ${mdate}</p>

	</div>
</article>
<%@include file="../temp/footer.jsp"%>