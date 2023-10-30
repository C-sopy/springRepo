<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@include file="../temp/admin_header.jsp"%>
<div class="container">
   <div class="row">
      <h1>Title : ${title }</h1>
      <p>Message : ${message }</p>
<div style="width: 750px; margin: auto">
<%-- form start --%>
 <table class="table">
      <thead>
         <tr>
            <th colspan="2">SurveyDetail</th>
         </tr>
      </thead>
      <tbody>
         <tr>
         <th>제목</th>
         <td>
         <input type="text" name="sub" id="sub" value="${vo.sub }"
                     readonly="readonly"> 
         <input type="hidden" name="num"   value="${vo.num }" id="num">
         </td>
         </tr>
         <c:forEach var="e" items="${vo.survey}" varStatus="i">
            <tr>
               <th>${i.index+1}번설문문항 ${e.subtype }</th>
               <td>${e.surveytitle } => ${e.surveycnt }</td>
            </tr>
         </c:forEach>
      </tbody>
      <tfoot>
         <tr>
            <th colspan="2"><input type="button" value="list"
               onclick="location='surveylist'"> 
               <input type="button" value="delete" id="delete"></th>
         </tr>
      </tfoot>
   </table> 
</div>
 
</div>
</div>