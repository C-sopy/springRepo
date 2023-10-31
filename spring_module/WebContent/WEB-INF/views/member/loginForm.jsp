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
<%--contents --%>	
	<form method="post" action="loginProcess" id="loginInfo">
	<div class="form-group">
      <label for="id">ID</label>
      <input type="text" class="form-control" id="id"
       placeholder="아이디 입력" name="id"
       maxlength="10" required="required"
       pattern=".{2,10}">
    </div>
    <div class="form-group">
      <label for="pwd">PWD</label>
      <input type="password" class="form-control" id="pwd"
       placeholder="******" name="pwd"
       maxlength="10" 
      >
    </div>
    <div class="form-group" style="text-align: right; margin-top: 10px;">
      <button type="submit" class="btn btn-primary">등록</button>&nbsp;
      <button type="button" class="btn btn-primary">아이디/비번찾기</button>
    </div>
	</form>
	</div>

</article>	
<%@include file="../temp/footer.jsp"%>