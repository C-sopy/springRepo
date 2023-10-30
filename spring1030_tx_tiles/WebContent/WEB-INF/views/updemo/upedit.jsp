<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header>
		<h1>FileUpload Demo</h1>
	</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div class="container">
		<%-- boardForm 들어 갈 자리. --%>
		<form action="upboardUpdate" method="post" enctype="multipart/form-data"  autocomplete="off">
			<input type="hidden" name="num" value="${b.num }">
			<input type="hidden" name="reip" value="<%=request.getRemoteAddr()%>">
			<div class="form-group">
				<label for="title">제목</label> <input type="text"
					class="form-control" id="title" value="${b.title}" placeholder="제목 입력(5-100)"
					name="title" maxlength="100" required="required" pattern=".{5,100}">
			</div>
			<div class="form-group">
				<label for="content">내용</label>
				<textarea class="form-control" rows="5" id="content" name="content"
					placeholder="내용 작성">${b.content}</textarea>
			</div>
			<div class="form-group">
				<label for="writer">작성자</label> <input type="text"
					class="form-control" id="writer" value="${b.writer}" placeholder="작성자(2자-30자)"
					name="writer" pattern=".{2,30}">
			</div>
			<div class="form-group">
				<label for="title">이미지</label>
				<div class="col-sm-10">
					<input type="file" class="form-control" id="mfile" name="mfile"  value="${b.imgn}" >
				</div>
				<div class="col-sm-10">
					<img src="${rPath}/imgfile/${b.imgn}" id="imgx"
						style="width: 210px; border: dotted 1px; margin: 5px 5px">

				</div>
			</div>
			<div class="form-group" style="text-align: right; margin-top: 10px;">
				<button type="submit" class="btn btn-primary">수정</button>
				<input type="button" value="리스트" class="btn btn-danger"
					onclick="location='uplist'" />
			</div>
		</form>

	</div>

</article>
<script>
	function readURL(input){
		// input type="file"의 주소를 가져온다.
		if(input.files && input.files[0]){
			//javascriptI/O
			var reader = new FileReader();
			//리스너
			reader.onload = function(e){
				console.log("이벤트 발생 : "+e.target.result);
				//<img src>선택자, attr("속성명", "값") -><ing src="">
				$('#imgx').attr('src', e.target.result);
			}
			reader.readAsDataURL(input.files[0]);
		}
	}
	$(function(){
		$('#mfile').change(function(){
			console.log($(this).val());
			if($(this).val().length > 0){
				readURL(this);
			}else{
				console.log("이미지 없음");
			}
		});
		
	});
</script>
<%@include file="../temp/footer.jsp"%>