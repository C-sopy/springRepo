<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:set var="cPath" value="${pageContext.request.contextPath}/web/" scope="application"/> --%>
	<%--bg-dark navbar-dark --%>
	<nav class="navbar navbar-expand-sm mybgColor">
		<div class="container-fluid">
			<ul class="navbar-nav">
				<li class="nav-item"><a href="${cPath}/main" class="nav-link active">Home</a></li>
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-bs-toggle="dropdown">Board</a>
					<ul class="dropdown-menu">
						<li><a class="dropdown-item" href="${cPath}fboard/fboardList">FreeBoard</a></li>
						<li><a class="dropdown-item" href="${cPath}board/ajaxBoard">AjaxBoard</a></li>
						<li><a class="dropdown-item" href="${cPath}board/uplist">uploadBoard</a></li>
					</ul>
				</li>
				<li class="nav-item"><a href="#" class="nav-link">Profile</a></li>
				<li class="nav-item"><a href="#" class="nav-link">Contact</a></li>
			</ul>
			<form class="d-flex">
				<input class="form-control me-2" type="text" placeholder="Search"
					name="searchv" id="searchv">
				<button class="btn btn-primary" type="button">Search</button>
			</form>
		</div>
	</nav>