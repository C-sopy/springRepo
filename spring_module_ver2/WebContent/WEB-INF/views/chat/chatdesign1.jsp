<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<input type="hidden" id="username" value="${sessionScope.sessionName}">
<section style="background-color: #eee;">
	<div class="container py-5">

		<div class="row d-flex justify-content-center">
			<div class="col-md-8 col-lg-6 col-xl-4" style="width: 80%">

				<div class="card" id="chat1" style="border-radius: 15px;" data-bs-spy="scroll" data-bs-offset="0" tabindex="0">
					<div
						class="card-header d-flex justify-content-between align-items-center p-3 bg-info text-white border-bottom-0"
						style="border-top-left-radius: 15px; border-top-right-radius: 15px;">
						<i class="fas fa-angle-left"></i>
						<p class="mb-0 fw-bold">${sessionScope.sessionName}</p>
						<i class="fas fa-times"></i>
					</div>
					<div class="card-body" id="messageTextArea">
						<%-- 다른 사람  채팅 --%>
	
						<%-- 나의 채팅 --%>

					</div>
					<div class="form-outline">
						<input type="text" class="form-control" id="textMessage" />
						<button id="sendBtn">sendBtn</button>
					</div>
				</div>

			</div>
		</div>

	</div>
</section>
<script>
	
	$(function() {
		let webSocket = null;
		let cnt = 0;
		function connect() {
			webSocket = new WebSocket("ws://localhost:8099/spring1025_26_27AapLogSocket/chat");
			webSocket.onopen = function(message) {
				$(".chat-package").attr("disabled", false);
				let key = {
					id : $("#username").val(),
					state : 0
				};
				// 접속 메시지 보내기
				webSocket.send(JSON.stringify(key));
				cnt++;
			};
			webSocket.onclose = function(message) {
			};
			webSocket.onerror = function(message) {
			};
			
			
			
			
			
			let messageTextArea = "";
			webSocket.onmessage = function(message) {
				var arr = message.data.split(":");
				if (arr[0] == $("#username").val()) { // 접속자 
					messageTextArea +=" <div class=\"d-flex flex-row justify-content-end mb-4\"> ";
					messageTextArea +=" <div class=\"p-3 me-3 border\" ";
					messageTextArea +="	style=\"border-radius: 15px; background-color: #fbfbfb;\"> ";
					messageTextArea +="	<p class=\"small mb-0\">"+ message.data+"</p> ";
					messageTextArea +="</div> ";
					messageTextArea +="<img src=\"<%=application.getContextPath()%>/resources/image/${cimg}\" ";
					messageTextArea +="	alt=\"avatar 1\" style=\"width: 45px; height: 100%;\"> ";
					messageTextArea +="</div> ";
					
				} else { // 다른 사람 
				messageTextArea +=" <div class=\"d-flex flex-row justify-content-start mb-4\" ";
				messageTextArea +="	style=\"border: 1px solid red;\"> ";
				messageTextArea +="	<img src=\"<%=application.getContextPath()%>/resources/image/${cimg}\" ";
				messageTextArea +="		alt=\"avatar 1\" style=\"width: 45px; height: 100%;\"> ";
				messageTextArea +="	<div class=\"p-3 ms-3\" ";
				messageTextArea +="		style=\"border-radius: 15px; background-color: rgba(57, 192, 237, .2);\"> ";
				messageTextArea +="<p class=\"small mb-0\">";
				messageTextArea +=message.data;
				messageTextArea +="</p>	</div> ";
				messageTextArea +=" </div> ";
				}
				$('#messageTextArea').html(messageTextArea);
			};
		}
		if(cnt == 0){
			connect();
		}
		// 메시지 보내기 함수
		function sendMessage() {
			// 텍스트 박스 객체 취득
			let message = document.getElementById("textMessage");
			// 메시지 전송 객체 만들기
			let key = {
				id : $("#username").val(),
				state : 1, // state는 1
				value : message.value
			// 메시지 내용
			}
			// 메시지 보내기
			webSocket.send(JSON.stringify(key));
			message.value = "";
		}
		/*
		$("#loginBtn").on("click", function() {
		  // 아이디 텍스트 박스에 내용이 없으면
		    if ($.trim($("#username").val()) === '') {
		      // 에러 표시
		      $(".error-message").html("에러 메세지");
		      // 함수 종료
		      return false;
		    }
		    // 에러 메시지 삭제
		    $(".error-message").html("");
		    // 로그인 텍스트 박스와 버튼 disabled
		    $(".login-package").attr("disabled", "disabled");
		    // 접속
		    connect();
		  });
		 */
		// 채팅 버튼 누르면 메시지 보내기
		$("#sendBtn").on("click", sendMessage);

		// 텍스트 박스에 키가 눌리면 enter함수 실행
		// $("#textMessage").on("keydown", enter);

	});
</script>