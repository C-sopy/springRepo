<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>        
<c:if test="${today !=null }">    
<li class="border-top my-3">오늘의 날짜는
 <b style="color:orange">${today }</b> 이고, 당신 접속한 
	아이디피는 ${reip } 입니다.
</li>
</c:if>