<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color: white">UpForm Page</header>
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
			<form method="post" action="boardEdit" autocomplete="off"
				enctype="multipart/form-data" id="updateForm">
				<input type="hidden" name="num" value="${vo.num}">
				<input
					type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">

				<div class="row mb-3">
					<label for="subject" class="col-sm-2 col-form-label">제목</label>
					<div class="col-sm-10">
						<input type="text" name="title" class="form-control" id="title"
							value="${vo.title}">
					</div>
				</div>
				<div class="row mb-3">
					<label for="writer" class="col-sm-2 col-form-label">작성자</label>
					<div class="col-sm-10">
						<input type="text" name="writer" class="form-control" id="writer"
							value="${vo.writer}">
					</div>
				</div>
				<div class="row">
					<label for="content" class="col-sm-2 col-form-label">내용</label>
					<div class="col-sm-10">
						<textarea name="content" rows="10" cols="50" id="content">${vo.content}</textarea>
					</div>
				</div>
				
				삭제할 이미지 선택
				<c:forEach var="e" items="${vo.imglist}">
					<div>
						<input type="checkbox" id="img" name="deletelist" value="${e}" />
						<label for="img"><img src="${rPath}/imgfile/${e}"
							id="imgx"
							style="width: 210px; border: dotted 1px; margin: 5px 5px"></label>
					</div>
				</c:forEach>
				<div class="form-group">
					<label for="image">이미지 </label>
					<div class="col-sm-10">
						<input type="file" accept="image/*" class="form-control"
							id="mflist" name="mflist" multiple="multiple">
					</div>
					<div class="col-sm-10">
						<img src="${rPath}/image/noimage.jpg" id="imgx"
							style="width: 210px; border: dotted 1px; margin: 5px 5px">

					</div>
				</div>
				<div class="form-group">
					<label for="video">영상</label>
					<div class="col-sm-10">
						<input type="file" accept="video/*" class="form-control"
							id="mfile" name="mfile" value="${vo.vidn}">
					</div>
					<div class="col-sm-10">
						<video muted autoplay loop>
							<source src="${rPath}/videofile/${vo.vidn}" type="video/mp4">
						</video>
					</div>
				</div>
				<div class="container text-center" role="group">

					<button class="custom-btn btn-3" type="submit">
						<span>수정</span>
					</button>
					<button class="custom-btn btn-danger btn-3" type="button"
						onclick="location='boardList'">
						<span>리스트</span>
					</button>
				</div>
			</form>
		</div>
	</div>
</article>
<script>
	function readURL(input) {
		if (input.files && input.files[0]) {
			//js I/O
			var reader = new FileReader();
			//listener
			reader.onload = function(e) {
				console.log("event occured" + e.target.result);
				//<img src> 선택자, attr("속성명", "값") -> (img src="")
				$('#imgx').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	$(function() {
		$('#mfile').change(function() {
			console.log($(this).val());
			if ($(this).val().length > 0) {
				readURL(this);
			} else {
				console.log("No Image")
			}
		})
	})
</script>

<%@include file="../temp/footer.jsp"%>