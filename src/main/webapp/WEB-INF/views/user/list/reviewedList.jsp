<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<div id="movieContainer">
      <!--  -->
      <sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.uvo.email" var="authEmail"/>  
			<c:set value="${authEmail }" var="authEmail" /> 
			
          </sec:authorize>
      <div class="listTitle">
       <div class="left">
        <span id="listTitleText">${authEmail eq email ? "나" : email }의 리뷰</span>
         <a ${platform eq "movie" ? 'style="border-bottom: 5px solid #dd54be;"' : '' } href="/user/${email }/reviewedList"><span class="platformBtn" >영화</span> <span class="platformCnt">${mReviewedCnt}</span></a>
        <a ${platform eq "tv" ? 'style="border-bottom: 5px solid #dd54be;"' : '' } href="/user/${email }/reviewedList/tv"><span class="platformBtn">TV</span> <span class="platformCnt">${tReviewedCnt }</span></a>
       </div>
        
      
      </div>
      <div id="cardContainer"></div>
      <!--  -->
    </div>
