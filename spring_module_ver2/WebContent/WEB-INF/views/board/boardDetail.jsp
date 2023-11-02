<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/header.jsp"%>
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
						value="${vo.title}" readonly="readonly">
				</div>
			</div>
			<div class="row mb-3">
				<label for="writer" class="col-sm-2 col-form-label">작성자</label>
				<div class="col-sm-10">
					<input type="text" name="writer" class="form-control" id="writer"
						value="${vo.writer}" readonly="readonly">
				</div>
				<label for="reip" class="col-sm-2 col-form-label">아이피</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${vo.reip}"
						readonly="readonly">
				</div>
			</div>
			<div class="row">
				<label for="content" class="col-sm-2 col-form-label">내용</label>
				<div class="col-sm-10">
					<textarea name="content" rows="10" cols="50" id="content"
						readonly="readonly">${vo.content}</textarea>

				</div>
			</div>

			<div class="mb-3">
				<label class="form-label">이미지</label>

				<c:forEach var="e" items="${vo.imglist}">
					<img src="${rPath}/imgfile/${e}"
						style="width: 200px; border: dotted 1px; cursor: pointer;">
				</c:forEach>

			</div>
			<div class="mb-3">
				<label class="form-label">영상</label>
				<video muted autoplay loop style="width: 500px; cursor: pointer;">
					<source src="${rPath}/videofile/${vo.vidn}" type="video/mp4"
					>
				</video>
			</div>

			<div class="row mb-3">
				<label for="pwd" class="col-sm-2 col-form-label">날짜</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" value="${vo.bdate}"
						readonly="readonly">
				</div>
			</div>
			<div class="container text-center" role="group">
				<form action="boardDelete" method="post">
					<input type="hidden" name="num" value="${vo.num}">
					<%-- chkpwdForm을 화면출력하고, sysout으로 2파라미터값을 출력 해보기 --%>
					<button class="btn btn-primary" type="button"
						onclick="location='boardModify?num=${vo.num}'">수정</button>
					<input class="btn btn-primary" type="submit" value="삭제">
					<button class="btn btn-danger" type="button"
						onclick="location='boardList'">리스트</button>
				</form>
			</div>
		</div>
		<h3>Commentaire</h3>
		<%-- comment commencer --%>
		<div class="container pt-4">
			<form action="comminsert" method="post">
				<input class="form-control" type="hidden" id="reip" name="reip"
					value="<%=request.getRemoteAddr()%>"> <input
					class="form-control" type="hidden" id="cnum" name="cnum"
					value="${vo.num}">
				<div class="row mb-3">
					<label for="writer" class="col-sm-2 col-form-label">작성자</label>
					<div class="col-sm-3">
						<input type="text" name="cwriter" class="form-control"
							id="cwriter">
					</div>
				</div>
				<div class="row">
					<label for="content" class="col-sm-2 col-form-label">내용</label>
					<div class="col-sm-10">
						<input type="text" name="ccontent" class="form-control"
							id="ccontent">
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
							<tbody id="comm">
								<!-- commentaire -->
							</tbody>
					</table>

				</div>
			</form>
		</div>
		<%-- comment fini --%>
		<%-- comment paging --%>
		<%@include file="../temp/cmtPageProcess.jsp"%>
	</div>
</article>
<script>
    var page = 1;
    var num = ${vo.num};
    var isLoading = false;

    function fetchData() {
    	
        if (isLoading) return;
		//로딩을 보여주고 
        isLoading = true;
        $('#loading').show();

        setTimeout(function () {
            $.ajax({
                url: '/spring_module_ver2/api/board/boardcommlist',
                type: 'GET',
                data: {
                    page: page,
                    num: num,
                },
                success: function (data) {
                	console.log(data);
                    // ajax로 json 데이터를  성공적으로 데이터를 받아온 경우 처리
                    console.log(data.commList.length)
                    if (data.commList.length > 0) {
                        for (var i = 0; i < data.commList.length; i++) {
                            // Create a new table row for each item in data.upboardList
                            var newRow = '<tr>' +
                                '<td>' + data.commList[i].num + '</td>' +
                                '<td>' + data.commList[i].cwriter + '</td>' +
                                '<td>' + data.commList[i].ccontent + '</td>' +
                                '<td>' + data.commList[i].reip + '</td>' +
                                '<td>' + data.commList[i].cdate + '</td>' +
                                '</tr>';
                            // Append the new row to your table
                            $('#comm').append(newRow);
                        }
                        page++;
                    }
                },
                complete: function () {
            		//완료된 후에는 로딩을 하이드함.
                	isLoading = false;
            
                    $('#loading').hide();
                }
            });
        }, 500); 
    }

    // 초기에 json으로 받아온 데이터 뿌리기 
    fetchData();

    // 스크롤 이벤트 감지하는 핸들러 
    $(window).scroll(function () {
    	  // 스크롤이 아래로 50px 이하로 남았을 때 fetchData 함수 호출
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 50) {
          
            fetchData();
        }
    });
</script>
<%@include file="../temp/footer.jsp"%>