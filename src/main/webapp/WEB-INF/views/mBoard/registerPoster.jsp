<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />

<div>
	<input type="text" placeholder="search" id="search">
	<button type="button" id="searchBtn">검색</button>
</div>

<div id="movieArea"></div>

<a href="/mBoard/register?${imgUrl + poster_path}">다음</a>
<script src="/resources/js/mBoard.register.js"></script>
<jsp:include page="../common/footer.jsp" />