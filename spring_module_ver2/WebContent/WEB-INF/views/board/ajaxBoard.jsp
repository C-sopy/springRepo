<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color: white">Board Ajax Demo</header>
	<ul class="list-unstyled">
		<li class="border-top my-3"></li>
	</ul>
	<div>
		<%-- 기존 form에서 아래처럼 복사 해서 편집 해놓기  --%>
		<input type="hidden" name="reip" id="reip"
			value="<%=request.getRemoteAddr()%>">
		<p>
			<input type="text" class="form-control" id="title"
				placeholder="제목 입력(4-100)" name="title" maxlength="100">
		</p>
		<p>
			<textarea class="form-control" rows="3" id="content" name="content"
				placeholder="내용 작성"></textarea>
		</p>
		<p>
			<input type="text" class="form-control" id="writer"
				placeholder="작성자(2자-10자)" name="writer">
		</p>
		<%-- 기존 form에서 아래처럼 복사 해서 편집 해놓기 끝 --%>
		<button type="button" class="btn btn-outline-secondary" id="load1">AjaxListLoad</button>
		<button type="button" class="btn btn-outline-secondary" id="sendAjax">데이터전송</button>
	</div>
	<div class="container">
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>번호</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			<tbody id="target">

			</tbody>
			<tfoot>
				<%-- page영역 --%>
				<tr>
					<th colspan="5" style="text-align: center;">
						<ul class="pagination" style="margin: 0 auto; width: 35%">
							<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
							<li class="page-item active"><a class="page-link" href="#">1</a></li>
							<li class="page-item"><a class="page-link" href="#">2</a></li>
							<li class="page-item"><a class="page-link" href="#">3</a></li>
							<li class="page-item"><a class="page-link" href="#">4</a></li>
							<li class="page-item"><a class="page-link" href="#">5</a></li>
							<li class="page-item"><a class="page-link" href="#">Next</a></li>
						</ul>
					</th>
				</tr>
				<%-- 검색영역 --%>
				<tr>
					<th colspan="5">
						<form class="d-flex">
							<select class="form-control btn-mini" id="sel1"
								style="margin-left: 120px;">
								<option>1</option>
								<option>2</option>
								<option>3</option>
								<option>4</option>
							</select> <input class="form-control me-2" type="text"
								placeholder="Search.." style="width: 300px">
							<button class="btn btn-outline-secondary" type="button">Search</button>
						</form>
					</th>
				</tr>
				<tr>
					<td colspan="5" style="text-align: right;">
						<button type="button" class="btn btn-outline-secondary" id="wbtn">글작성</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</article>

<script>
window.onload = function() {
	let number = 1;
	document.querySelector("#load1").onclick = function(e) {
		e.preventDefault();
		let param = null;
		sendRequest("boardJsonList", param, res, "get");
	}
	document.querySelector("#sendAjax").onclick = function(e) {
		e.preventDefault();
		//동적으로 전달할 객체
		/*
		{
		num:1,
		title:"오늘은 왠지 ㅎㅎㅎㅎㅎ0",
		writer:"김길동",
		content:"내용1",
		reip:"192.168.0.113",
		bdate:"2023-06-14"
		}
		*/
		 //
		const data = {}
		data.num=number;
		data.title=document.getElementById("title").value;
		data.content=document.getElementById("content").value;
		data.writer=document.getElementById("writer").value;
		data.reip=document.getElementById("reip").value;
		data.bdate="2023-06-14";
		let param = JSON.stringify(data); //json으로 변환
		let type = "json";
		//alert(data.bdate);
		sendRequest("RequestParamDemo", param, res2, "post", type);
		number++;
	}
	//callback
	function res(){
		if(xhr.readyState === 4){
			if (xhr.status === 200) {
				let res = xhr.responseText;
				//console.log("res =>"+res);
			}
		}
	}
	function res2() {
		if(xhr.readyState === 4){
			if (xhr.status === 200) {
				let res = xhr.responseText;
				console.log("res2 =>"+res);
			}
		}
	}
}
</script>









<%@include file="../temp/footer.jsp"%>