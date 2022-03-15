<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/userDetail.css" />
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css">
<%-- <jsp:include page="userDetailHeader.jsp" /> --%>
	<div id="header">
      <div >
      	<img id="profileImg" src="/fileUpload/og_${uvo.profileImg }">
      </div>
      <div id="headerInfo">
        <div id="profileEmailDiv"><span id="profileEmail" style="color: ${uvo.fontColor}">${uvo.nickName}</span>
        <span>
        ${fn:split(uvo.regAt, "-")[1] }월
        ${fn:split(uvo.regAt, "-")[0] }년
        부터 회원 
        </span>
        </div>
        <div id="scoreDiv">
          <div class="scoreBlock">
            <div class="score">
            <c:choose>
            	<c:when test="${movieAvg > 0 }">
            		<fmt:formatNumber type="number" pattern="0" value="${movieAvg }" />
            	</c:when>
            	<c:otherwise>
            		<span style="font-size: 11px;">평점없음</span>
            	</c:otherwise>
            </c:choose>
            		</div>
            <span class="scoreText">
            평균
            <br />
            영화 점수
            </span>
          </div>
          <div class="scoreBlock">
            <div class="score">
            <c:choose>
            	<c:when test="${tvAvg > 0 }">
           			<fmt:formatNumber type="number" pattern="0" value="${tvAvg }" />
            	</c:when>
            	<c:otherwise>
            		<span style="font-size: 11px;">평점없음</span>
            	</c:otherwise>
            </c:choose>
            </div>
            <span class="scoreText">
            평균
            <br />
            tv 점수
            </span>
          </div>
        </div>
      </div>
    </div>
    
    <!-- 메뉴 -->
    
    <div class="detailMenu">
      <div class="btn-group">
        <button
          class="menuBtn dropdown-toggle"
          type="button"
          data-bs-toggle="dropdown"
          aria-expanded="false"
        >
          개요
        </button>
        <ul class="dropdown-menu">
          <li><a class="dropdown-item" href="/user/${uvo.email }">메인</a></li>
          <sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.uvo.email" var="authEmail"/>  
			<c:set value="${authEmail }" var="authEmail" /> 
			<c:if test="${authEmail eq uvo.email }">
          <li><a class="dropdown-item" href="/user/${authEmail }/modify">프로필 수정</a></li>
			</c:if>
          </sec:authorize>
        </ul>
      </div>
      <div class="btn-group">
        <a class="menuBtn" style="display: flex;align-items:center;" type="button" href="/user/${uvo.email }/poster">포스터</a> <!-- 디테일 메인페이지에서 보여줄	건지? -->
      </div>
      <sec:authorize access="isAuthenticated()">
			<sec:authentication property="principal.uvo.email" var="authEmail"/>  
			<c:if test="${uvo.email eq authEmail }">
		      <div class="btn-group">
		        <button
		          class="menuBtn dropdown dropdown-toggle"
		          type="button"
		          data-bs-toggle="dropdown"
		          aria-expanded="false"
		        >
		          평가
		        </button>
		        <ul class="dropdown-menu">
		          <li><a class="dropdown-item" href="/user/${uvo.email }/ratedList">영화</a></li>
		          <li><a class="dropdown-item" href="/user/${uvo.email }/ratedList/tv">tv 프로그램</a></li>
		        </ul>
		      </div>
		
		      <div class="btn-group">
		        <button
		          class="menuBtn dropdown dropdown-toggle"
		          type="button"
		          data-bs-toggle="dropdown"
		          aria-expanded="false"
		        >
		          즐겨찾기
		        </button>
		        <ul class="dropdown-menu">
		          <li><a class="dropdown-item" href="/user/${uvo.email }/likedList">영화</a></li>
		          <li><a class="dropdown-item" href="/user/${uvo.email }/likedList/tv">tv 프로그램</a></li>
		        </ul>
		      </div>
		      <div class="btn-group">
		        <button
		          class="menuBtn dropdown dropdown-toggle"
		          type="button"
		          data-bs-toggle="dropdown"
		          aria-expanded="false"
		        >
		          리뷰
		        </button>
		        <ul class="dropdown-menu">
		          <li><a class="dropdown-item" href="/user/${uvo.email }/reviewedList">영화</a></li>
		          <li><a class="dropdown-item" href="/user/${uvo.email }/reviewedList/tv">tv 프로그램</a></li>
		        </ul>
		      </div>
			</c:if>
    </sec:authorize>
    </div>
    <script>
    	let email = `<c:out value="${email}" />`;
    	let platform = `<c:out value="${platform}" />`;
    	let list = `<c:out value="${list}" />`;
    	let moviesData = ${moviesData};
    	let tvsData = ${tvsData};
    	let mLikedCnt = `<c:out value="${mLikedCnt}" />`
   		let tLikedCnt = `<c:out value="${tLikedCnt}" />`
   		let mRatedCnt = `<c:out value="${mRatedCnt}" />`
		let tRatedCnt = `<c:out value="${tRatedCnt}" />`
		let mReviewedCnt = `<c:out value="${mReviewedCnt}" />` 
		let tReviewedCnt = `<c:out value="${tReviewedCnt}" />` 
    </script>
    <c:if test="${!empty movieGenres }">
    	<script>
		let movieGenres = ${movieGenres};
    	</script>
    </c:if>
   	<c:if test="${!empty tvGenres  }">
   		<script>
   		
		let tvGenres = ${tvGenres};
   		</script>
   	</c:if>
      <c:choose>
      	<c:when test="${list eq 'liked' }">
      		 <jsp:include page="list/likedList.jsp" />
      	</c:when>
      	<c:when test="${list eq 'rated'}">
      		 <jsp:include page="list/ratedList.jsp" />
      	</c:when>
      	<c:when test="${list eq 'reviewed'}">
      		<jsp:include page="list/reviewedList.jsp" />
      	</c:when>
      	<c:when test="${list eq 'main'}">
      		 <jsp:include page="list/main.jsp" /> 
      	</c:when>
      	<c:when test="${list eq 'poster' }">
      		<jsp:include page="list/posterList.jsp" />
      	</c:when>
      	<c:otherwise>
      		??
      	</c:otherwise>
      </c:choose>
        <!-- rating modal -->
<div class="modal fade" id="ratingModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">평점을 남겨주세요!</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <span class="star">
        ★★★★★
        <span>★★★★★</span>
        <input
          type="range"
          id="ratingStar"
          name="rating"
          oninput="drawStar(this)"
          value="1"
          step="1"
          min="0"
          max="10"
        />
      </span>
      </div>
      <div class="modal-footer">
        <button id="modalCloseBtn" type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
        <button id="deleteRatingBtn" type="button" class="btn btn-danger" data-bs-dismiss="modal">Delete Rating</button>
      </div>
    </div>
  </div>
</div>
<script>
	let msg = `<c:out value="${msg}" />`;
	if(msg != null && msg != ""){
		alert(msg);
	}
</script>
<script src="/resources/js/user.detail.js"></script>