<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/mBoard.registerMovie.css" />

<div class="container">
	<div class="searchBox">
		<div class="searchPos">
			<div class="fs-4">영화 검색</div>
			<div class="explain">(영화 선택 후 다음으로 넘어갑니다.)</div>
			<div class="input-group">
				<div class="form-outline">
					<input type="text" class="form-control" placeholder="search" id="search">
				</div>
				<button type="button" class="btn btn-info" id="searchBtn">검색</button>
			</div>
		</div>
	</div>
</div>
<div class="container-fluid">
	<div class="wrapper">
		<div id="movieArea"></div>
	</div>
</div>
<script src="/resources/js/mBoard.registerMovie.js"></script>
<jsp:include page="../common/footer.jsp" />