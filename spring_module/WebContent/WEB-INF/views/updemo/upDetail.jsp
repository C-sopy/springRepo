<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color: white">FileUpLoad Demo</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
	
	<div class="container-fluid px-4">
		<h1 class="mt-4">Board Detail</h1>
		<div class="card mb-4">
			<div class="card-body">
				<form>
					<div class="mb-3 mt-3">
						<label  class="form-label">번호</label> 
						 ${vo.num }
					</div>
					<div class="mb-3">
						<label  class="form-label">제목</label>
						  <input
							type="text" class="form-control" id="title" name="title"
							value="${vo.title}" disabled>
					</div>
					<div class="mb-3">
						<label  class="form-label">내용</label>
						<textarea class="form-control" id="content" name="content"
							disabled>${vo.content}</textarea>
					</div>
					<div class="mb-3">
						<label  class="form-label">작성날짜</label> 
						${vo.bdate }
					</div>
					<div class="mb-3">
						<label class="form-label">작성자</label>
						<input
							type="text" class="form-control" id="writer" name="writer"
							value="${vo.writer}" disabled>
					</div>
					<div class="mb-3">
						<label class="form-label">이미지</label> 
						<img src="${rPath }/imgfile/${vo.imgn}" 
						style="width: 80px; border: dotted 1px;
						cursor: pointer; 
						" id="imgn">
					</div>
					<div class="mb-3">
						<label class="form-label">아이피</label> ${vo.reip }
					</div>
					<a href="upList" class="btn btn-outline-secondary">list</a> <a
						href="modify?num=${vo.num }" class="btn btn-outline-warning">modify</a>
					<a href="remove?num=${vo.num }" class="btn btn-outline-danger"
						onclick="return confirm('삭제하시겠습니까?')">remove</a>
				</form>
			</div>
		</div>
		<%-- 댓글 영역 시작 --%>
		<div class="card mb-4">
			<div class="card-body">
			<fieldset>
 	<legend>MyBoard Detail</legend>
   	
   <form method="post" action="bcominsert">
    <input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
	<input type="hidden" name="ucode" value="${vo.num }">
		 <div class="row mb-3">
		 <div class="col-sm-10">
		 <label class="col-sm-2 col-form-label">writer</label> 
		 <input
		 type="text" name="uwriter" id="uwriter"
		 placeholder="writer를 입력하세요." />
		 <textarea name="ucontent" class="form-control" id="ucontent"
		 rows="3"></textarea>
		 </div>
		 <div class="mt-2 col-sm-10 d-flex justify-content-end">
		 <input type="submit" value="등록" id="btn1" class="btn btn-info" />
		 </div>
		 <div class="col-sm-10">
		 <table class="table">
		 <thead>
			 <tr>
			 <td>no</td>
			 <td>writer</td>
			 <td>content</td>
			 <td>reip</td>
			 <td>date</td>
			 </tr>
		 </thead>
		 <tbody id="comm">
			 <c:forEach var="e" items="${listcomm}">
			 <tr>
			 <td>${e.num }</td>
			 <td><a href="" >${e.uwriter }</a></td>
			 <td>${e.ucontent }</td>
			 <td>${e.reip }</td>
			 <td>${e.uregdate}</td>
			 </tr>
			 </c:forEach> 
		 </tbody>
		 
		 </table>
		 </div>
		 </div>
		 </form>
 		</fieldset>
			</div>
		</div>	
		
		<%-- 댓글 영역 끝 --%>
		
	</div>
	
</div>
</article>
<%@include file="../temp/footer.jsp"%>