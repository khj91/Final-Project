<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="common/header.jsp" />
<jsp:include page="common/nav.jsp" />

<link rel="stylesheet" href="/resources/css/search.css" />
    <div id="searchDiv">
      <input type="text" id="searchInput" placeholder="검색" />
    </div>
<!--    	<div class="list-group" id="searchList">
	  <a href="/search/movie?query=ddd" class="list-group-item list-group-item-action">A second link item</a>
	  <a href="#" class="list-group-item list-group-item-action">A third link item</a>
	  <a href="#" class="list-group-item list-group-item-action">A fourth link item</a>
	</div> -->
    <div id="mainContainer">
      <div id="leftMenu">
        <div class="menuTitle">Search Results</div>
        
        <a href="/search/movie?query=${query }" id="movieData" class="searchMenu">
          <div>영화</div>
          <div class="moviePill">10</div>
        </a>
        <a href="/search/tv?query=${query }" id="tvData" class="searchMenu">
          <div>tv</div>
          <div class="tvPill">10</div>
        </a>
      </div>
      <div id="cardContainer">
       
        
      </div>
    </div>
    <div id="pagingDiv">
    
    </div>
  </body>

<script>
	let query = `<c:out value="${query}" />`;
	let isAdult = `<c:out value="${isAdult}" />`;
	let page = `<c:out value="${page}" />`;
	let platform = `<c:out value="${platform}"/>`;
</script>
<script src="/resources/js/search.js"></script>
  
</html>

