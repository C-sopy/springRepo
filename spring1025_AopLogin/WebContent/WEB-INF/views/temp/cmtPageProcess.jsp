<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- temp/pageProcess.jsp --%>
<c:set var="cmturl" value="?num=${num}&cPage"/>
<tr>
	<th colspan="6" style="text-align: center;">
		<ul class="pagination" style="margin: 0 auto; width: 35%;">
			<%-- 이전 페이지 --%>
			<c:choose>
				<c:when test="${startPage <= page.pagePerBlock}">
					<li class="page-item disabled"><a class="page-link" href="#">Previous</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class="page-link" href="${cmturl}=${startPage -1}">Previous</a></li>
				</c:otherwise>
			</c:choose>
			
			<%-- 현재 페이지 startPage, endPage --%>
			<%-- nowPage에 해당되지 않으면 링크를 걸어서 페이지 메뉴 선택 제공 --%>
			<c:forEach varStatus="i" begin="${startPage}" end="${endPage}">
				<%-- 현재 페이지면 링크를 해제하고 아니면 링크를 지정 --%>
				<c:choose>
				<c:when test="${i.index == page.nowPage}">
					<li class="page-item active">
					<a class="page-link" href="#">${i.index}</a>
					</li>
				</c:when>
				<c:otherwise>
					<li class="page-item">
					<a class="page-link" href="${cmturl}=${i.index}">${i.index}</a>
					</li>
				</c:otherwise>
				</c:choose>
			</c:forEach>
			<%-- 다음 페이지 --%>
			<c:choose>
				<c:when test="${endPage >= page.totalPage}">
					<li class="page-item disabled"><a class="page-link" href="#">Next</a></li>
				</c:when>
				<c:otherwise>
					<li class="page-item active"><a class="page-link" href="${cmturl}=${endPage +1}">Next</a></li>
				</c:otherwise>
			</c:choose>
			
		</ul>
	</th>
</tr>