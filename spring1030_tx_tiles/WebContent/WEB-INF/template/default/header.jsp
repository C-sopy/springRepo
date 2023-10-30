<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <c:set var="cPath" value="${pageContext.request.contextPath}/web/" scope="application"/> --%>
	<header class="text-white text-center">
		<div class="d-flex flex-row-reverse mybgColor">
			
			<%-- avant login --%>
			<c:choose>
				<c:when test="${sessionScope.sessionID == null}">
					<div class="p-2 bg-warning">
						<a href="${cPath}/login/loginForm" class="nav-link text-white" id="item1">�α���</a>
					</div>
					<div class="p-2 bg-primary">
						<a href="${cPath}/member/memberForm" class="nav-link text-white" id="item2">ȸ������</a>
					</div>
					<div class="p-2 bg-info">
						<a href="#" class="nav-link text-white" id="item3">item</a>
					</div>
				</c:when>
					<%-- apres login --%>
				<c:when test="${sessionScope.sessionID != null}">
					<div class="p-2 bg-warning">
						<a href="${cPath}/login/logout" class="nav-link text-white" id="item1">�α׾ƿ�</a>
					</div>
					<div class="p-2 bg-primary">
						<a href="${cPath}/member/mypage" class="nav-link text-white" id="item2">����������</a>
					</div>
					<div class="p-2 bg-info">
						<a href="${cPath}/chatdemo/chat" class="nav-link text-white" id="item3">Chatdemo</a>
					</div>
				</c:when>
			</c:choose>
		</div>
		<!-- Carousel -->
		<div id="demo" class="carousel slide" data-bs-ride="carousel">
			<!-- Indicators/dots -->
			<div class="carousel-indicators">
				<button type="button" data-bs-target="#demo" data-bs-slide-to="0"
					class="active"></button>
				<button type="button" data-bs-target="#demo" data-bs-slide-to="1"></button>
				<button type="button" data-bs-target="#demo" data-bs-slide-to="2"></button>
			</div>
			<c:choose>
				<c:when test="${sessionScope.sessionID == null}">
					<c:set var="carouselMsg" value="jQuery �� �̿��Ͽ� �������� �������Ͽ��� �Ʒ� �䱸���״�� �����մϴ�."></c:set>
				</c:when>
				<c:when test="${sessionScope.sessionID != null}">
					<c:set var="carouselMsg" value="${sessionScope.sessionName}, bon courage ~!!"></c:set>
				</c:when>
			</c:choose>
			<!-- The slideshow/carousel -->
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img src="${rPath}/image/d1.jpg" alt="Kosmo113" class="d-block"
						style="width: 100%">
					<div class="carousel-caption">
						<h3>UI �䱸���� Ȯ���ϱ�,UI �����ϱ�</h3>
						<p>${carouselMsg}</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="${rPath}/image/d2.jpg" alt="Carousel" class="d-block"
						style="width: 100%">
					<div class="carousel-caption">
						<h3>Carousel ����� CSS3��!</h3>
						<p>Thank you, CSS3!</p>
					</div>
				</div>
				<div class="carousel-item">
					<img src="${rPath}/image/d3.jpg" alt="JSP" class="d-block"
						style="width: 100%">
					<div class="carousel-caption">
						<h3>UI �䱸���� Ȯ���ϱ�,UI �����ϱ�</h3>
						<p>${carouselMsg}<br/>���ϰ��־��!</p>
					</div>
				</div>
			</div>

			<!-- Left and right controls/icons -->
			<button class="carousel-control-prev" type="button"
				data-bs-target="#demo" data-bs-slide="prev">
				<span class="carousel-control-prev-icon"></span>
			</button>
			<button class="carousel-control-next" type="button"
				data-bs-target="#demo" data-bs-slide="next">
				<span class="carousel-control-next-icon"></span>
			</button>
		</div>
	</header>