<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%-- 경로 설정 --%>
<c:set var="rPath" value="${pageContext.request.contextPath}/resources" scope="application"/>
<c:set var="cPath" value="${pageContext.request.contextPath}/web/" scope="application"/>
<!DOCTYPE html>
<html>
<head>
<TITLE> New Document </TITLE>
  <meta charset="euc-kr">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet">
  <link rel="stylesheet" href="${rPath}/css/style.css"/>
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css"/>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
 <!-- ajax를 링크 걸기 -->
 <script src="${rPath}/js/ajaxdemo1.js"></script>
<style>
a {
	text-decoration: none;
}

select.btn-mini {
	height: auto;
	width: 200px;
	line-height: 14px;
}

.card {
	border: none;
	box-shadow: 5px 6px 6px 2px #e9ecef;
	border-radius: 4px;
}

.dots {
	height: 4px;
	width: 4px;
	margin-bottom: 2px;
	background-color: #bbb;
	border-radius: 50%;
	display: inline-block;
}

.badge {
	padding: 7px;
	padding-right: 9px;
	padding-left: 16px;
	box-shadow: 5px 6px 6px 2px #e9ecef;
}

.user-img {
	margin-top: 4px;
}

.check-icon {
	font-size: 17px;
	color: #c3bfbf;
	top: 1px;
	position: relative;
	margin-left: 3px;
}

.form-check-input {
	margin-top: 6px;
	margin-left: -24px !important;
	cursor: pointer;
}

.form-check-input:focus {
	box-shadow: none;
}

.icons i {
	margin-left: 8px;
}

.reply {
	margin-left: 12px;
}

.reply small {
	color: #b7b4b4;
}

.reply small:hover {
	color: green;
	cursor: pointer;
}

.bgGr1 {
	background-color: #0d6efd
}

.bgGr2 {
	background-color: #ffc107
}

.bgGr3 {
	background-color: #0dcaf0
}

#colTitle {
	background-color: #000000;
}

.mytable-condensed {
	font-size: 11px;
}
</style>
<script>
	$(function() {
		$('#searchv').focusin(function() {
			$(this).val("검색어 입력")
		});
		$('#searchv').focusout(function() {
			$(this).val("")
		});

		//let firstColor ="d-flex flex-row-reverse mybgColor";
		//console.log("log00 => "+$('.mybgColor').attr('class'));
		$('#item1').click(function() {
			//$(this).attr('class',firstColor)
			console.log("log => " + $('.mybgColor').attr('class'));
			$('.mybgColor').attr('class', function() {
				$(this).attr('style', 'background-color:#0dcaf0');
				/*
				let changeClass = $(this).attr('class');
				let class_sttr_arr = changeClass.split(" ");
				$.each(class_sttr_arr,function(e,val){
					console.log(e+"::::::::::::::::::"+val)
					if(val.includes("bg-")){
						console.log("================>"+e+":"+val);
						$('.mybgColor').attr('class').replace(val,'bg-info');
					}else{
						changeClass +=" bg-info";
					}
					$('.mybgColor').attr('class',changeClass)
				});
				 */
			});
		});
		$('#item2').click(
				function() {
					console.log("log => " + $('.mybgColor').attr('class'));
					$('.mybgColor').attr('class', function() {
						$(this).attr('style', 'background-color:#ffc107');
					});
					//이미지 세팅하기
					let imgArr = [ 'd1.jpg', 'd2.jpg', 'd3.jpg' ];
					$('.carousel-item > img').each(
							function(idx) {
								console
										.log("바뀔 이미지 :" + imgArr[idx]
												+ ", log Img => "
												+ $(this).attr('src'));
								$(this).attr('src',
										'resources/image/' + imgArr[idx]);
							});

				});
		$('#item3').click(
				function() {
					$('.mybgColor').attr('class', function() {
						$(this).attr('style', 'background-color:#0d6efd');
					});
					//이미지 세팅하기
					let imgArr = [ 'c1.jpg', 'c2.jpg', 'c3.jpg' ];
					$('.carousel-item > img').each(
							function(idx) {
								console
										.log("바뀔 이미지 :" + imgArr[idx]
												+ ", log Img => "
												+ $(this).attr('src'));
								$(this).attr('src',
										'resources/image/' + imgArr[idx]);
							});
				});
	});
</script>
</head>
<body>
<!--  header.jsp -->
<tiles:insertAttribute name="header"/>
<!-- menu.jsp -->		
<tiles:insertAttribute name="menu"/>

<!-- body -->
<tiles:insertAttribute name="body"/>

  <aside>
        <header>
             <span class="fs-4">핵심 교과목</span>
        </header>
      <ul class="list-unstyled ">
      <li class="mb-1" >
        <button class="bg-white btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed " data-bs-toggle="collapse" data-bs-target="#home-collapse" aria-expanded="true">
          Java
        </button>
        <div class="collapse show" id="home-collapse">
          <ul class="btn-toggle-nav list-unstyled">
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">클래스</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">인터페이스</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">NetWork</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="bg-white btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#dashboard-collapse" aria-expanded="false">
                  빅데이터 
        </button>
        <div class="collapse" id="dashboard-collapse">
          <ul class="btn-toggle-nav list-unstyled">
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Python</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">NumPy</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Pandas</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Tensorflow</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="bg-white btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#orders-collapse" aria-expanded="false">
          Orders
        </button>
        <div class="collapse" id="orders-collapse">
          <ul class="btn-toggle-nav list-unstyled">
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">New</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Processed</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Shipped</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Returned</a></li>
          </ul>
        </div>
      </li>
      <li class="border-top my-3"></li>
      <li class="mb-1">
        <button class="bg-white btn btn-toggle d-inline-flex align-items-center rounded border-0 collapsed" data-bs-toggle="collapse" data-bs-target="#account-collapse" aria-expanded="false">
          Account
        </button>
        <div class="collapse" id="account-collapse">
          <ul class="btn-toggle-nav list-unstyled">
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">New...</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Profile</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Settings</a></li>
            <li><a href="#" class="link-dark d-inline-flex text-decoration-none rounded">Sign out</a></li>
          </ul>
        </div>
      </li>
    </ul>

    </aside>    
    
 <!-- footer.jsp -->
<tiles:insertAttribute name="footer"/>

<script>
	$(function(){
		$('#wbtn').click(function(){
			location ="boardForm";
		});
		
	});
</script>
 </body>
</html>
