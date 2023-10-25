<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color:white">UpForm Page</header>
	<ul class="list-unstyled">
	<li class="border-top my-3">ICT No1 UpForm 페이지 입니다.</li>
	</ul>
	<div class="container">
	<div class="row">
		<h2>FreeBoard UpForm</h2>
		<%--
		update fboard set subject=? , writer=?,content=?,pwd=?,reip=?
		where num=?
		 --%>
		<form method="post" action="fboardUpdate" autocomplete="off">
			<input type="hidden" name="num" value="${v.num }">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">

			<div class="row mb-3">
				<label for="subject" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" name="subject" class="form-control" id="subject"
					value="${v.subject}"
					>
				</div>
			</div>
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-10">
					<input type="text" name="writer" class="form-control" id="writer"
					value="${v.writer}"
					>
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
				<textarea name="content" rows="10" cols="50" id="content">${v.content}</textarea>
				</div>
			</div>
			<div  class="container text-center" role="group">

			<button class="custom-btn btn-3" type="submit"><span>수정</span></button>
            <button class="custom-btn btn-danger btn-3" 
            type="button" onclick="location='tboardList'"><span>리스트</span></button>
			</div>
		</form>
		</div>
	</div>
</article>
<%@include file="../temp/footer.jsp"%>
