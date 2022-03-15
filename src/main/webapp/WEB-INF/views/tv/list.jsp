<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<sec:authorize access="isAuthenticated()" >
       	 <sec:authentication property="principal.uvo.email" var="authEmail" />
	          <sec:authentication property="principal.uvo.nickName" var="authNick" />
	          <sec:authentication property="principal.uvo.authList" var="auths" />
	          <script type="text/javascript">
				const email = "${authEmail}";
			  </script>
	    </sec:authorize>
<link rel="stylesheet" href="/resources/css/movieList.css" />
<div class="container">
      <div class="optionContainer">
       <div id="wrapper">
        <div class="btnContainer">
          <button type="button" data-genre="10759" class="btn btn-outline-secondary genreBtn">
            액션/어드벤쳐
          </button>
          <button type="button" data-genre="16" class="btn btn-outline-secondary genreBtn">애니메이션</button>
          <button type="button" data-genre="35" class="btn btn-outline-secondary genreBtn">코미디</button>
          <button type="button" data-genre="80" class="btn btn-outline-secondary genreBtn">범죄</button>
          <button type="button" data-genre="99" class="btn btn-outline-secondary genreBtn">다큐</button>
          <button type="button" data-genre="18" class="btn btn-outline-secondary genreBtn">드라마</button>
          <button type="button" data-genre="10751" class="btn btn-outline-secondary genreBtn">가족</button>
          <button type="button" data-genre="10762" class="btn btn-outline-secondary genreBtn">kids</button>
          <button type="button" data-genre="9648" class="btn btn-outline-secondary genreBtn">미스테리</button>
          <button type="button" data-genre="10763" class="btn btn-outline-secondary genreBtn">뉴스</button>
          <button type="button" data-genre="10764" class="btn btn-outline-secondary genreBtn">
            리얼리티
          </button>
          <button type="button" data-genre="10765" class="btn btn-outline-secondary genreBtn">
            SF/판타지
          </button>
          <button type="button" data-genre="10766" class="btn btn-outline-secondary genreBtn">soap</button>
          <button type="button" data-genre="10767" class="btn btn-outline-secondary genreBtn">토크</button>
          <button type="button" data-genre="10768" class="btn btn-outline-secondary genreBtn">
            전쟁/정치
          </button>
          <button type="button" data-genre="37" class="btn btn-outline-secondary genreBtn">서부</button>
        </div>
         <div class="selectContainer">
          <select name="orderBy" id="orderBy">
            <option value="popularity.desc">인기도 내림차순</option>
            <option value="popularity.asc">인기도 오름차순</option>
            <option value="vote_average.desc">평점 내림차순</option>
            <option value="vote_average.asc">평점 오름차순</option>
            <option value="release_date.desc">상영일 내림차순</option>
            <option value="release_date.asc">상영일 오름차순</option>
            <option value="original_title.asc">제목순</option>
          </select>
        </div>
        </div>
      </div>
      <div id="cardContainer" class="row"></div>
      <button id="moreBtn">more</button>
    </div>
    <button id="searchBtn" style="visibility: hidden">검색</button>
  </body>
  <!-- 모달 -->
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
let platform = `<c:out value="${platform}"/>`
let isAdult = `<c:out value="${isAdult}" />`
if(isAdult == null || isAdult == ""){
	isAdult = false;
}
console.log(isAdult);
console.log(platform);
</script>
<script src="/resources/js/tv.list.js"></script>


