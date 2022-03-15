<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<script src="https://kit.fontawesome.com/58e52d7ffb.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/board.list.css" />
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal.uvo.email" var="authEmail"/>
</sec:authorize>

<div class="container-fluid">
	<div class="wrapper">
		<div class="title">TV 감상평
			<c:if test="${authEmail != null }">
				<a href="/tvBoard/registerTv" class="btn btn-outline-info">등록</a>
			</c:if>
		</div>
		<div class="searchBar">
			<form action="/tvBoard/likeList" method="get">
				<div class="input-group">
					<c:set value="${pgn.pgvo.type }" var="typed" />
					<div class="form-outline">
						<select name="type" class="form-select form-select-sm searchSelc">
							<option ${typed == null ? 'selected':'' }>선택</option>
							<option value="t" ${typed eq 't' ? 'selected':'' }>제목</option>
							<option value="v" ${typed eq 'v' ? 'selected':'' }>TV제목</option>
							<option value="w" ${typed eq 'w' ? 'selected':'' }>작성자</option>
							<option value="tv" ${typed eq 'tv' ? 'selected':'' }>제목 또는 TV제목</option>
							<option value="tw" ${typed eq 'tw' ? 'selected':'' }>제목 또는 작성자</option>
							<option value="vw" ${typed eq 'vw' ? 'selected':'' }>TV제목 또는 작성자</option>
						</select>
					</div>
					<div class="form-outline">
						<input type="text" name="keyword" class="form-control searchInpt" placeholder="검색어를 입력하세요." value="${pgn.pgvo.keyword }">
						<input type="hidden" name="pageNo" value="1">
						<input type="hidden" name="qty" value="${pgn.pgvo.qty }">
					</div>
					<button type="submit" class="btn btn-info"><i class="fas fa-search"></i></button>
				</div>
			</form>
		</div>
	
		<div class="likeHateText">
			<a href="/tvBoard/likeList" style="color: rgb(91, 189, 250); text-decoration: underline; font-weight: bold;">재밌어요</a>
			/
			<a href="/tvBoard/hateList">재미없어요</a>
		</div>
	
		<table class="table table-hover text-center fs-5 align-middle">
			<thead>
				<tr class="boardTr">
					<th scope="col">포스터</th>
					<th scope="col">제목</th>
					<th scope="col">좋아요</th>
					<th scope="col">작성자</th>
					<th scope="col">조회수</th>
					<th scope="col">날짜</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${list }" var="tvbvo">
					<tr class="tableText">
						<th scope="row"><img src="${tvbvo.poster }" class="listPoster"></th>
						<td>
							<a href="/tvBoard/detail?tvbId=${tvbvo.tvbId }&authEmail=${authEmail }&pageNo=${pgn.pgvo.pageNo}&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">
								${tvbvo.title }
							</a>
						</td>
						<td><span class="heartColor"><i class="far fa-heart"></i></span>${tvbvo.heart }</td>
						<td>
							<div class="dropdown">
							  <a href="" id="uDetail" data-bs-toggle="dropdown" aria-expanded="false">
							    ${tvbvo.nickName }
							  </a>
							  <ul class="dropdown-menu" aria-labelledby="uDetail">
							    <li><a href="/user/${tvbvo.writer }" class="dropdown-item">정보 보기</a></li>
							  </ul>
							</div>
						</td>
						<td>${tvbvo.readCount }</td>
						<td>${tvbvo.modAt }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<ul class="pagination justify-content-center">
			<c:if test="${pgn.prev }">
				<li class="page-item">
					<a  class="page-link" href="/tvBoard/likeList?pageNo=${pgn.startPage - 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">이전</a>
				</li>
			</c:if>
			<c:forEach begin="${pgn.startPage }" end="${pgn.endPage }" var="i">
				<li class="page-item ${pgn.pgvo.pageNo == i ? 'active':''}" aria-current="page">
					<a class="page-link" href="/tvBoard/likeList?pageNo=${i }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">${i }</a>
				</li>
			</c:forEach>
			<c:if test="${pgn.next }">
				<li class="page-item">
					<a class="page-link" href="/tvBoard/likeList?pageNo=${pgn.endPage + 1 }&qty=${pgn.pgvo.qty}&type=${pgn.pgvo.type}&keyword=${pgn.pgvo.keyword}">다음</a>
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