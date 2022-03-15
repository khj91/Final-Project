<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
   <nav>
      <div id="left">
        <div id="logo"><a href="/home">TMDB</a></div>
        <div class="leftMenu">
        	<div class="dropdown dropdownNav menu">
  				<button class="btn " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    			영화
  				</button>
  				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				    <li><a class="dropdown-item" href="/movie/popular">인기</a></li>
				    <li><a class="dropdown-item" href="/movie/now-playing">상영중</a></li>
				    <li><a class="dropdown-item" href="/movie/up-coming">개봉 예정</a></li>
				    <li><a class="dropdown-item" href="/movie/rating">평점순</a></li>
  				</ul>
			</div>
			<div class="dropdown dropdownNav menu">
  				<button class="btn " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    			TV
  				</button>
  				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				    <li><a class="dropdown-item" href="/tv/netflix">넷플릭스</a></li>
				    <li><a class="dropdown-item" href="/tv/amazon">아마존</a></li>
				    <li><a class="dropdown-item" href="/tv/watcha">왓챠</a></li>
				    <li><a class="dropdown-item" href="/tv/waave">웨이브</a></li>
  				</ul>
			</div>
          
          <div class="dropdown dropdownNav menu">
  				<button class="btn" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    			영화 게시판
  				</button>
  				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				    <li><a class="dropdown-item" href="/mBoard/likeList">좋아요</a></li>
				    <li><a class="dropdown-item" href="/mBoard/hateList">싫어요</a></li>
  				</ul>
			</div>
        <div class="dropdown dropdownNav menu">
  				<button class="btn " type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
    			tv 게시판
  				</button>
  				<ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
				    <li><a class="dropdown-item" href="/tvBoard/likeList">좋아요</a></li>
				    <li><a class="dropdown-item" href="/tvBoard/hateList">싫어요</a></li>
  				</ul>
			</div>
        </div>
        <div class="menu">
        	<a class="btn" style="color: #fff;" href="/notice/list">공지사항</a>
        </div>
        <div class="menu">
        	<a class="btn" style="color: #fff;" href="/rank/pointlist">랭킹</a>
        </div>
        <div class="menu">
        	<a class="btn" style="color: #fff;" href="/purchase/font">shop</a>
        </div>
       </div>
        
    
      <div id="right">
        <div class="rightMenu">
       
       	<sec:authorize access="isAuthenticated()" >
       	 <sec:authentication property="principal.uvo.email" var="authEmail" />
	          <sec:authentication property="principal.uvo.nickName" var="authNick" />
	          <sec:authentication property="principal.uvo.authList" var="auths" />
	          <c:if test="${ auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
	          	<div class="menu">
       				<a href="/user/userList">user list</a>
       			</div>
	          </c:if>
       		<div class="menu">
       			<form action="/user/logout" method="post">
       				<input type="hidden" value="${authEmail }">
       				<button class="nav-btn btn" id="logOutBtn" type="submit">Log Out</button>
       			</form>
       			
       		</div>
          	<div class="menu">
          	<%-- <form action="/user/detail/${ authEmail}" method="get" id="myPageForm"> --%>
          		
          	<a class="nav-btn" href="/user/${ authEmail}" >${authNick }(${authEmail })</a>
          	<!-- </form> -->
          	</div>
       	</sec:authorize>
       	<sec:authorize access="isAnonymous()">
          <div class="menu"><a href="/user/login">Login</a></div>
          <div class="menu"><a href="/user/register">Sign in</a></div>
       	</sec:authorize>
        </div>
      </div>
    </nav>