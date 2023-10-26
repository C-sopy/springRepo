<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script src="<%=application.getContextPath()%>/resources/js/ajaxdemo1.js"></script>
<style>
nav{
	padding:30px;
}
nav a {
	font-weight: bold;
	color: blue;
}
</style>
</head>
<body>
<%@include file="../main/menu.jsp" %>
	<form method="post" action="memberIn">
		<table style="width: 80%; margin: auto">
			<tr>
				<td>아이디</td>
				<td><input type="text" name="id" id="id" maxlength="10"
					style="width: 130px"> <input type="button" value="중복확인"
					id="idChkBtn" style="width: 150px; font-size: 10px; height: 30px;">
				</td>
			</tr>
			<tr>
				<td colspan="2" id="target"></td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pwd" maxlength="10"></td>
			</tr>
			<tr>
				<td>이름</td>
				<td><input type="text" name="uname"></td>
			</tr>
			<%-- 배열타입의 파라미터 처리하기  --%>
			<tr>
				<td>체크</td>
				<td><input type="checkbox" name="chk" value="1"> 1 <input
					type="checkbox" name="chk" value="2"> 2 <input
					type="checkbox" name="chk" value="3"> 3</td>
			</tr>

			<tr>
				<td colspan="2"><input type="submit" value="가입"></td>
			</tr>
		</table>
	</form>
	<script>
		window.onload=function(){
			document.querySelector("#idChkBtn").onclick=function(e){
				//폼전송을 막는 기능 
				e.preventDefault();
				let param = "id="+document.getElementById("id").value;
				sendRequest("idcheck", param, res, "get");
			};
			function res(){
				if(xhr.readyState === 4){
					if(xhr.status === 200){
						let idCnt = parseInt(xhr.responseText);
						console.log(idCnt+":"+typeof(idCnt));
						let msg ="";
						if(idCnt === 0){
							msg ="사용가능한 아이디 입니다.";
							document.getElementById("target").style.backgroundColor="orange";
						}else{
							msg ="사용중인 아이디 입니다.";
							document.getElementById("target").style.backgroundColor="red";
							document.getElementById("target").style.color='white';
						}
						document.getElementById("target").innerHTML=msg;
					}
				}
			}
		}
	</script>
</body>
</html>