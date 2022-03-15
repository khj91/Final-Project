<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/board.detail.css" />

<div class="container-fluid">
	<div class="wrapper">
		<div class="title">공지사항 디테일</div>
		<div class="card" style="max-width: 740px;">
			<div class="card-body">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
						<label for="title">&nbsp&nbsp제목</label>
						<input type="text" id="title" class="titAlign" name="title" value="${nvo.title}" readOnly>
					</li>
					<li class="list-group-item">
						<label for="writer">&nbsp작성자</label>
						<input type="email" id="email" class="witAlign" name="email" value="${nvo.nickName }" readOnly>
					</li>
					<li class="list-group-item">
						<label for="regAt">등록일자</label>
						<input type="text" id="regAt" class="detAlign" name="regAt" value="${nvo.regAt }" readOnly>
					</li>
					<li class="list-group-item">
						<label for="modAt">수정일자</label>
						<input type="text" id="modAt" class="detAlign" name="modAt" value="${nvo.modAt }" readOnly>
					</li>
					<li class="list-group-item">
						<label for="readCount">&nbsp조회수</label>
						<input type="text" name="readCount" class="witAlign" id="readCount" value="${nvo.readCount }" readOnly>
					</li>
				</ul>
			</div>
		</div>
		<div class="card mb-3" style="max-width: 740px;">
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<label for="cont">내용</label>
					<textarea name="content" id="content" readOnly>${nvo.content }</textarea>
				</li>
			</ul>
		</div>
		<div class="btnBox mb-3">
			<a class="btn btn-outline-info" href="/notice/list?pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&type=${pgvo.type}&keyword=${pgvo.keyword}">목록</a>
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.uvo.authList" var="auths"/>
				<c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
					<div class="editBox">
						<a class="btn btn-outline-warning" href="/notice/modify?nid=${nvo.nid }&pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&type=${pgvo.type}&keyword=${pgvo.keyword}">수정</a>
						<button type="button" class="btn btn-outline-danger" id="delBtn">삭제</button>
					</div>
				</c:if>
			</sec:authorize>
		</div>
	</div>
</div>

<form action="/notice/remove" method="post" id="delForm">
	<input type="hidden" name="nid" value="${nvo.nid }">
	<input type="hidden" name="pageNo" value="${pgvo.pageNo }">
    <input type="hidden" name="qty" value="${pgvo.qty }">
    <input type="hidden" name="type" value="${pgvo.type }">
    <input type="hidden" name="keyword" value="${pgvo.keyword }">
</form>

<script src="/resources/js/notice.detail.js"></script>
<jsp:include page="../common/footer.jsp" />