<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

  <p>
    <span class="error-message"></span>
  </p>

  <p>
    login : <input type="text" id="username" name="username" class="login-package">
    <button id="loginBtn" class="login-package">login</button>
  </p>

  <p>
    <input id="textMessage" l,'L.type="text" disabled="disabled" class="chat-package">
    <button id="sendBtn" disabled="disabled" class="chat-package">Send</button>
  </p>

  <p>
    <textarea id="messageTextArea" rows="10" cols="50" disabled="disabled"></textarea>
  </p>
  <script>
  	$(function() {
	  // WebSocket 변수
      let webSocket = null;
      // WebSocket 접속 함수
            // WebSocket 접속 함수
      function connect() {
        // 웹 소켓 객체 생성
        webSocket = new WebSocket("ws://localhost:8099/spring1025_26_27AapLogSocket/chat");
        // 웹 소켓이 open되면 실행되는 이벤트
        webSocket.onopen = function(message) {
          // 채팅 textbox와 버튼의 disabled 해제
          $(".chat-package").attr("disabled", false);
          // 접속 초기 데이터 작성
          let key = {
            id : $("#username").val(),
            state : 0 // state는 0
          };
          // 접속 메시지 보내기
          webSocket.send(JSON.stringify(key));
        };
        webSocket.onclose = function(message) {
        };
        webSocket.onerror = function(message) {
        };
        // 서버로 부터 메시지가 오면 실행되는 이벤트
        webSocket.onmessage = function(message) {
          console.log("Me:"+$("#username").val());	
       		// 채팅 내용 박스 객체 취득
          let messageTextArea = document.getElementById("messageTextArea");
          var arr = message.data.split(":");
          if(arr[0] == $("#username").val()){
        	  console.log("같은 사람 "+$("#username").val()+"Message:"+message.data);
        	// 데이터 작성
              messageTextArea.value += "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+message.data;
          }else{
        	  console.log("다른 사람"+arr[0]+"Message:"+message.data);	
        	// 데이터 작성
              messageTextArea.value += message.data;
          }
        };
      }

      $("#loginBtn").click(function() {
    	  // 아이디 텍스트 박스에 내용이 없으면
          if ($.trim($("#username").val()) === '') {
            // 에러 표시
            $(".error-message").html("Please input the name textbox.");
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
      // 채팅 버튼 누르면 메시지 보내기
      $("#sendBtn").click(function(){
    	// 텍스트 박스 객체 취득
          let message = document.getElementById("textMessage");
          // 메시지 전송 객체 만들기
          let key = {
            id : $("#username").val(),
            state : 1, // state는 1
            value : message.value // 메시지 내용
          }
          // 메시지 보내기
          webSocket.send(JSON.stringify(key));
          // 텍스트 박스 초기화
          message.value = "";
    	  
      });
      
     // 텍스트 박스에 키가 눌리면 enter함수 실행
     // $("#textMessage").on("keydown", enter);
   
      
	});
  </script>