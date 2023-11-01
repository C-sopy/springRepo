<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--include file="../temp/header.jsp"--%>
<article>
	<header style="color: white">upBoard</header>
	<ul class="list-unstyled">
		<li class="border-top my-3">ICT No1 Detail 페이지 입니다.</li>
	</ul>
	<div class="container pt-3">
		<div class="row">
			<h2>UpBoard Detail</h2>
			<div class="row mb-3">
				<label for="subject" class="col-sm-2 col-form-label">제목</label>
				<div class="col-sm-10">
					<input type="text" name="subject" class="form-control" id="subject"
						value="${b.title}" readonly="readonly">
				</div>
			</div>
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-10">
					<input type="text" name="writer" class="form-control" id="writer"
						value="${b.writer}" readonly="readonly">
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<textarea name="content" rows="10" cols="50" id="content"
						readonly="readonly">${b.content}</textarea>
						
				</div>
			</div>
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">이미지</label>
				<div class="col-sm-10">
					<img src="${rPath}/imgfile/${b.imgn}"
						style="width: 200px; border: dotted 1px; cursor: pointer;">
				</div>
			</div>
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">아이피</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${b.reip}"
						readonly="readonly">
				</div>
			</div>
			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">날짜</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${b.bdate}"
						readonly="readonly">
				</div>
			</div>
			<div class="container text-center" role="group">
				<form action="upboardDelete" method="post">
					<input type="hidden" name="num" value="${b.num}">
					<%-- chkpwdForm을 화면출력하고, sysout으로 2파라미터값을 출력 해보기 --%>
					<button class="btn btn-primary" type="button"
						onclick="location='upboardEdit?num=${b.num}'">수정</button>
					<input class="btn btn-primary" type="submit" value="삭제">
					<button class="btn btn-danger" type="button"
						onclick="location='uplist'">리스트</button>
				</form>
			</div>
		</div>
		<h3>Commentaire</h3>
		<%-- comment commencer --%>
		<div class="container pt-4">
			<form action="bcominsert" method="post">
				<input class="form-control" type="hidden" id="reip" name="reip" value="<%=request.getRemoteAddr()%>">
				<input class="form-control" type="hidden" id="ucode" name="ucode" value="${b.num}">
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-3">
					<input type="text" name="uwriter" class="form-control" id="uwriter">
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<input type="text" name="ucontent" class="form-control" id="ucontent">
				</div>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary">등록</button>
			</div>
			<div class="container pt-2">
				<table class="table">
						<thead>
							<tr>
								<th>no</th>
								<th>작성자</th>
								<th>내용</th>
								<th>ip</th>
								<th>날짜</th>
							</tr>
						</thead>
						<c:forEach var="cm" items="${listcomm}">
						<tbody>
							<tr>
								<td>${cm.num}</td>
								<td>${cm.uwriter}</td>
								<td>${cm.ucontent}</td>
								<td>${cm.reip}</td>
								<td>${cm.uregdate}</td>
							</tr>
						</tbody>
						</c:forEach>
					</table>
				
				</div>
			</form>
		</div>
		<%-- comment fini --%>
		<%-- comment paging --%>
		<%@include file="../temp/cmtPageProcess.jsp" %>
	</div>
</article>
<%--include file="../temp/footer.jsp"--%>