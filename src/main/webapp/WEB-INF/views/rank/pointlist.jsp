<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<link rel="stylesheet" href="/resources/css/rank.css" />
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<div class="content_wrap">
    <div class="content_subject"> 
    <h4 class="title">포인트왕</h4></div>
<select name="list" onchange="location.href=this.value">
<option>선택</option>
<option value="/rank/pointlist">포인트왕</option>
<option value="/rank/boardlist">게시판왕</option>
<option value="/rank/commentlist">댓글왕</option>
</select>
    <div class="comment">가장 많은 포인트를 모은 사용자</div>
    <div class="table_wrap">
    <h4>best</h4>
    <table class="leaderboard">
      <tbody>
	   <c:forEach items="${list }" var="prvo">
	   <tr>
	           <td class="con1">${prvo.totalPoints}</td>
	           <td>
	             <span class="content">
	              <a href="/user/detail.jsp"><img src="/fileUpload/${prvo.profileImg}"></a>
	             </span>
	             <span class="detail">
	              <h3><a href="#"  >${prvo.nickName}</a></h3>
	              <p>가입 : ${prvo.regAt}</p>
	             </span> 
	             <div style="clear:both; padding-top: 10px;"></div>
	           </td>   
	     </tr> 
	   </c:forEach>  
	</tbody>   
       </table>	     
       </div>
         
       <div class="table_wrap"> 
       <h4>worst</h4>
       <table class="leaderboard">
         <tbody>
	   <c:forEach items="${lowlist }" var="prvo">
	      <tr>
	           <td class="con1">${prvo.totalPoints}</td>
	           <td>
	             <span class="content">
	              <a href="detail.jsp"><img src="/fileUpload/${prvo.profileImg}"></a>
	             </span>
	             <span class="detail">
	              <h3><a href="#"  >${prvo.nickName}</a></h3>
	              <p>가입 : ${prvo.regAt}</p>
	             </span> 
	             <div style="clear:both; padding-top:10px"></div>
	           </td>   
	     </tr> 
	   </c:forEach> 
	 </tbody>  
        </table>
        </div>
</div>  
<jsp:include page="../common/footer.jsp" />