<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%--upList.jsp --%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/header.jsp"%>
<article>
	<header style="color: white">Fileupload Demo</header>
	<ul class="list-unstyled">
		<li class="border-top my-3">FileList</li>
	</ul>
	<div class="container">
		<table class="table table-bordered" id="upboardTable">
		
			<thead>
				<tr><th colspan="6">
					<select id="sv" name="sv">
						<option value="0">Search</option>
						<option value="1">hit</option>
						<option value="2">bdate</option>
					</select>
				</th></tr>
				<tr>
					<th>번호</th>
					<th>이미지</th>
					<th>제목</th>
					<th>작성자</th>
					<th>조회수</th>
					<th>작성날짜</th>
				</tr>
			</thead>
			<tbody>
				<%-- for start 
				${vo.속성 } , ${map.key }
				List<BoardVO> list => BoardVO => e
				--%>
				<c:forEach var="list" items="${boardList}">
					<tr>
						<td>${list.num}</td>
						<td><a href="boardHit?num=${list.num}">
							<img src ="${rPath}/imgfile/${list.imgn}"
							style="width: 80px; border: dotted 1px; cursor: pointer;"></a></td>
						<td>${list.title} <span style="color:red;">[${list.cnt}]</span></td>
						<td>${list.writer}</td>
						<td>${list.hit}</td>
						<td>${list.bdate}</td>
				</c:forEach>
				<%-- for end --%>
			</tbody>
			<tfoot>
				<%-- page영역 --%>
				<%@include file="../temp/pageProcess.jsp" %>
				<%-- 검색영역 --%>
<%-- 				<tr>
					<th colspan="6">
						기존의 리스트 모델로 요청을 보낸다.
						<form class="d-flex" method="post" action="upList">
							<select class="form-control btn-mini" 
							    name="searchType" id="searchType"
								style="margin-left: 120px;">
								<option value="">검색</option>
								<option value="1">제목</option>
								<option value="2">이름</option>
								<option value="3">내용</option>
							</select> <input class="form-control me-2" type="text"
								placeholder="Search.." 
								name="searchValue" id="searchValue"
								style="width: 300px">
							<button class="btn btn-outline-secondary" type="submit">Search</button>
						</form>
					</th>
				</tr> --%>
				<tr>
					<td colspan="6" style="text-align: right;">
						<button type="button" class="btn btn-outline-secondary"
						onclick="location='upform'"
						>글작성</button>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>
</article>
<style>
	.searchCls{
		color: orange;
		font-weith: bold;
	}
</style>
<%@include file="../temp/footer.jsp"%>