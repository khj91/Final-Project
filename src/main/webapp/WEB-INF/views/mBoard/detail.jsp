<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<script src="https://kit.fontawesome.com/58e52d7ffb.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/board.detail.css" />

<div class="container-fluid">
	<div class="wrapper">
		<div class="title">영화 게시판 디테일</div>
		<c:set var="mbvo" value="${mbdto.mbvo }" />
		<div class="card" style="max-width: 740px;">
			<div class="row g-0">
				<div class="col-md-4">
					<img src="${mbvo.poster }" class="img-fluid rounded-start posterSize">
				</div>
				<div class="col-md-8">
					<div class="card-body">
						<ul class="list-group list-group-flush">
							<li class="list-group-item">
								<label for="movieTitle">영화제목</label>
								<input type="text" id="movieTitle" class="detAlign" name="movieTitle" value="${mbvo.movieTitle}" readOnly>
							</li>
							<li class="list-group-item">
								<label for="regDate">개봉날짜</label>
								<input type="text" id="regDate" class="detAlign" name="regDate" value="${fn:substring(mbvo.regDate, 0, 10)}" readOnly>
							</li>
							<li class="list-group-item">
								<label for="writer">&nbsp작성자</label>
								<input type="email" id="writer" class="witAlign" name="writer" value="${mbvo.nickName }" readOnly>
							</li>
							<li class="list-group-item">
								<label for="modAt">등록일자</label>
								<input type="text" id="modAt" class="detAlign" name="modAt" value="${mbvo.modAt }" readOnly>
							</li>
							<li class="list-group-item">
								<label for="title">&nbsp&nbsp제목</label>
								<input type="text" name="title" class="titAlign" id="title" value="${mbvo.title }" readOnly>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div class="card mb-3" style="max-width: 740px;">
			<ul class="list-group list-group-flush">
				<li class="list-group-item">
					<label for="cont">내용</label>
					<textarea name="content" id="content" readOnly>${mbvo.content }</textarea>
				</li>
				<li class="list-group-item text-end">
					<sec:authorize access="isAuthenticated()">
				<sec:authentication property="principal.uvo.email" var="authEmail"/>
				<input type="hidden" name="heartCheck" id="heartCheck" value="${mbdto.check }">
				<input type="hidden" name="authEmail" id="authEmail" value="${authEmail }">
					<c:choose>
						<c:when test="${authEmail == mbvo.writer }">
							<button type="button" class="hBtn" id="heartList" data-bs-toggle="modal" data-bs-target="#myModalHeart">♡</button>
						</c:when>
						<c:when test="${authEmail != null && mbdto.check == 1 }">
							<button type="button" class="hBtn" id="heartBtn">♥</button>
						</c:when>
						<c:otherwise>
							<button type="button" class="hBtn" id="heartBtn">♡</button>
						</c:otherwise>
					</c:choose>
			</sec:authorize>
			
			<sec:authorize access="isAnonymous()">
				<span>♡</span>
			</sec:authorize>
				</li>
			</ul>
		</div>
		<div class="btnBox mb-3">
			<a class="btn btn-outline-info" href="/mBoard/${mbvo.likeHate == 1 ? 'like' : 'hate' }List?pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&type=${pgvo.type}&keyword=${pgvo.keyword}">목록</a>
			<c:if test="${mbvo.writer eq authEmail }">
				<div class="editBox">
					<a class="btn btn-outline-warning" href="/mBoard/modify?mbId=${mbvo.mbId }&authEmail=${authEmail }&pageNo=${pgvo.pageNo }&qty=${pgvo.qty}&type=${pgvo.type}&keyword=${pgvo.keyword}">수정</a>
					<button type="button" class="btn btn-outline-danger" id="delBtn">삭제</button>
				</div>
			</c:if>
		</div>
		
		<p class="fs-5">댓글</p>
			<c:choose>
				<c:when test="${authEmail ne null }">
					<div class="input-group mb-2">
						<input type="text" class="form-control" id="cmtWriter" name="cmtWriter" value="${authEmail }" readOnly>				
						<div class="form-outline">
							<input type="text" class="form-control" id="cmtText" placeholder="댓글을 입력해주세요.">
						</div>
							<button type="button" class="btn btn-info" id="cmtPostBtn">게시</button>
					</div>
				</c:when>
				<c:otherwise>
					<div class="input-group mb-2">
						<input type="text" class="form-control" id="cmtWriter" name="cmtWriter" value="" disabled>
						<div class="form-outline">
							<input type="text" class="form-control" id="cmtText" placeholder="로그인 후 이용 가능합니다." disabled>
						</div>
							<button type="button" class="btn btn-secondary" id="cmtPostBtn" disabled>게시</button>
					</div>
				</c:otherwise>
			</c:choose>
		
		<div id="cmtListArea" class="bg-light"></div>
		
		<div class="row" id="cmtPaging">
			<ul class="pagination justify-content-center">
				<li class="page-item">
					<a href="" class="page-link">이전</a>
				</li>
				<li class="page-item">
					<a href="" class="page-link"></a>
				</li>
				<li class="page-item">
					<a href="" class="page-link">다음</a>
				</li>
			</ul>
		</div>
		
		<div class="modal" id="myModal">
	  		<div class="modal-dialog">
			    <div class="modal-content">
				    <div class="modal-header">
				        <h4 class="modal-title">${authEmail }</h4>
				        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				    </div>
				
				    <div class="modal-body">
				        <div class="input-group my-3">
							<input type="text" class="form-control" id="cmtTextMod">
							<button class="btn btn-success" id="cmtModBtn" type="button">수정</button>
						</div>
				    </div>
				
				    <div class="modal-footer">
				        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
				    </div>
				</div>
	    	</div>
	  	</div>
	  	<div class="modal" id="myModalHeart">
	  		<div class="modal-dialog">
			    <div class="modal-content">
				    <div class="modal-header">
				        <h4 class="modal-title">좋아요한 사람</h4>
				        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
				    </div>
				
				    <div class="modal-body">
				        <div class="input-group my-3">
							<div class="likeListArea">
							
							</div>
							<div class="likeTotalCnt">
							
							</div>
						</div>
				    </div>
				
				    <div class="modal-footer">
				        <button type="button" class="btn btn-danger" data-bs-dismiss="modal">닫기</button>
				    </div>
				</div>
	    	</div>
	  	</div>
  	</div>
</div>


<form action="/mBoard/remove" method="post" id="delForm">
	<input type="hidden" name="mbId" value="${mbvo.mbId }">
	<input type="hidden" name="likeHate" value="${mbvo.likeHate }">
	<input type="hidden" name="pageNo" value="${pgvo.pageNo }">
    <input type="hidden" name="qty" value="${pgvo.qty }">
    <input type="hidden" name="type" value="${pgvo.type }">
    <input type="hidden" name="keyword" value="${pgvo.keyword }">
</form>
<script>
	const mbIdVal = document.querySelector("input[name=mbId]").value;
	const heartCheck = document.querySelector("input[name=heartCheck]").value;
	const authEmail = document.querySelector("input[name=authEmail]").value;
</script>
<script src="/resources/js/mBoard.comment.js"></script>
<script src="/resources/js/mBoard.detail.js"></script>
<jsp:include page="../common/footer.jsp" />