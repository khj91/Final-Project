<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/board.list.css" />

<div class="container-fluid">
	<div class="wrapper">
		<div class="title align-middle">공지사항 목록
			<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.uvo.authList" var="auths"/>
				<c:if test="${auths.stream().anyMatch(authVO -> authVO.auth.equals('ROLE_ADMIN')).get() }">
					<a href="/notice/register" class="btn btn-outline-info">등록</a>
				</c:if>
			</sec:authorize>
		</div>
		<table class="table table-hover text-center fs-5 align-middle">
		  <thead>
		    <tr class="boardTr">
		      <th scope="col">#</th>
		      <th scope="col">제목</th>
		      <th scope="col">작성자</th>
		      <th scope="col">조회수</th>
		      <th scope="col">날짜</th>
		    </tr>
		  </thead>
		  <tbody>
		  	<c:forEach items="${list }" var="nvo">
		    <tr class="tableText">
		      <th scope="row">${nvo.nid }</th>
		      <td><a href="/notice/detail?nid=${nvo.nid }&pageNo=${pgn.pgvo.pageNo}&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${nvo.title }</a></td>
		      <td>${nvo.nickName }</td>
		      <td>${nvo.readCount }</td>
		      <td>${nvo.modAt }</td>
		    </tr>
		    </c:forEach>    
		  </tbody>
		</table>
		<ul class="pagination justify-content-center">
			<c:if test="${pgn.prev }">
				<li class="page-item">
					<a  class="page-link" href="/notice/list?pageNo=${pgn.startPage - 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${pgn.startPage }" end="${pgn.endPage }" var="i">
				<li class="page-item ${pgn.pgvo.pageNo == i ? 'active':''}" aria-current="page">
					<a class="page-link" href="/notice/list?pageNo=${i }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${i }</a>
				</li>
			</c:forEach>
			<c:if test="${pgn.next }">
				<li class="page-item">
					<a class="page-link" href="/notice/list?pageNo=${pgn.endPage + 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">다음</a>
				</li>
			</c:if>
		</ul>
	</div>
</div>
<script>
	let isUp = '<c:out value="${isUp}"/>';
	let isDel = '<c:out value="${isDel}"/>';
	if (parseInt(isUp)) {
		alert('게시글 등록 성공~');
	}
	if (parseInt(isDel)) {
		alert('게시글 삭제 성공~');
	}
</script>
<jsp:include page="../common/footer.jsp" />