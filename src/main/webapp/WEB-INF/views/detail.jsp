<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="common/header.jsp" />

<jsp:include page="common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/movieDetail.css" />

    <!-- <div class="menuContainer">
      <input type="text" id="search" name="searchQuery" placeholder="Search..." />
      <button type="button" id="searchBtn" name="searchBtn">search</button>
    </div> -->

    <header style="">
      <div class="shadow">
        <div class="imageContainer">
          <div class="image">
            <img id="poster" src="" alt="" />
          </div>
        </div>
        <div class="headerContent">
          <div class="headerTitle" id="movieTitle">영화 제목</div>
          <div class="headerRatingDiv">
          	<div class="headerRating">
          	평균점수: &nbsp;<i><span id="rt">NoData</span></i>&nbsp;|&nbsp; tmdb 점수: &nbsp; <i><span id="dbRating"></span></i>
          	</div>
<!--           	// 좋아요시 색변 -->
			<sec:authorize access="isAuthenticated()">
    	    	<sec:authentication property="principal.uvo.email" var="authEmail" />
	         	<sec:authentication property="principal.uvo.nickName" var="authNick" />
	          	<sec:authentication property="principal.uvo.authList" var="auths" />
	          	<c:set value="${authEmail }" var="email" />
          		<a class="btn headerRateBtn headerBtn"  data-bs-toggle="modal" data-bs-target="#ratingModal" data-email="${authEmail }"><i class="bi bi-star-fill"></i></a>
          		<a class="btn headerLikeBtn headerBtn" data-email="${authEmail }"><i class="bi bi-heart-fill"></i></a>
          	</sec:authorize>
          </div>
          <div class="tagLine"><i class="tLine">태그라인</i></div>
          <div class="overview">
            <h3 class="overviewTitle">개요</h3>
            <p class="overviewSub">줄거리</p>
          </div>
        </div>
      </div>
    </header>
    <main>
    	<sec:authorize access="isAuthenticated()">
    	    	<sec:authentication property="principal.uvo.email" var="authEmail" />
	          <sec:authentication property="principal.uvo.nickName" var="authNick" />
	          <sec:authentication property="principal.uvo.authList" var="auths" />
	          <sec:authentication property="principal.uvo.profileImg" var="profileImg"/>
    			<c:set value="${profileImg }" var="profileImg"/>
    			<c:set value="${authEmail}" var="loggedInEmail" />
    		
    	</sec:authorize>
    	<script>
    		let loggedInEmail = `<c:out value="${loggedInEmail}" />`;
    		let profileImg = `<c:out value="${profileImg}" />`;
    	</script>
    	<div class="mainTitle">시리즈 출연진</div>
		<div id="castDiv">
		</div>
		<hr>
		        <div id="reviewDiv">
          <h4>사용자 리뷰</h4>

          <ul class="list-group" id="reviewWrapper" style="padding: 20px">
          <!--  -->
          <h4>등록된 리뷰가 없습니다. 리뷰를 등록해보세요!</h4>
            <!--  -->
          </ul>
		
		<sec:authorize access="isAuthenticated()">
    	    	<sec:authentication property="principal.uvo.email" var="authEmail" />
	         	<sec:authentication property="principal.uvo.nickName" var="authNick" />
	          	<sec:authentication property="principal.uvo.authList" var="auths" />
          <div class="input-group mb-3">
            <span class="input-group-text">${authEmail }</span>
            <input type="hidden" name="writer" value="${authEmail }">
            <input type="text" class="form-control" name="content" id="revInput" placeholder="리뷰를 남겨주세요!" />
            <button class="btn btn-outline-secondary reviewRegBtn" type="button">add</button>
          </div>
          </sec:authorize>
        </div>
    </main>
  </body>
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
</html>
<script>
let detailId = `<c:out value="${id}" />`;
let category = `<c:out value="${category}" />`
</script>

<script src="/resources/js/${category }.detail.js"></script>




<%-- <jsp:include page="../common/footer.jsp" /> --%>