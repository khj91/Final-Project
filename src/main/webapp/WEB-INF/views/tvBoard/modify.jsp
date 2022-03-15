<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<script src="https://kit.fontawesome.com/58e52d7ffb.js" crossorigin="anonymous"></script>
<link rel="stylesheet" href="/resources/css/board.detail.css" />

<sec:authentication property="principal.uvo.email" var="authEmail"/>
<div class="container-fluid">
	<div class="wrapper">
		<form action="/tvBoard/modify" method="post">
			<div class="title">TV 감상평 수정</div>
			<c:set var="tvbvo" value="${tvbdto.tvbvo }" />
			<div class="card" style="max-width: 740px;">
				<div class="row g-0">
					<div class="col-md-4">
						<img src="${tvbvo.poster }" class="img-fluid rounded-start posterSize">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									<label for="tvTitle">TV제목</label>
									<input type="text" id="tvTitle" class="detAlign" name="tvTitle" value="${tvbvo.tvTitle}" readOnly>
								</li>
								<li class="list-group-item">
									<label for="regDate">방영일자</label>
									<input type="text" id="regDate" class="detAlign" name="regDate" value="${fn:substring(tvbvo.regDate, 0, 10)}" readOnly>
								</li>
								<li class="list-group-item">
									<label for="writer">&nbsp작성자</label>
									<input type="email" id="writer" class="witAlign" name="writer" value="${tvbvo.nickName }" readOnly>
								</li>
								<li class="list-group-item">
									<label for="modAt">등록일자</label>
									<input type="text" id="modAt" class="detAlign" name="modAt" value="${tvbvo.modAt }" readOnly>
								</li>
								<li class="list-group-item">
									<label for="title">&nbsp&nbsp제목</label>
									<input type="text" name="title" class="modTitle" id="title" value="${tvbvo.title }">
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
						<textarea name="content" id="content">${tvbvo.content }</textarea>
					</li>
					<li class="list-group-item text-end">
						<button type="submit" class="btn btn-outline-info">수정</button>
						<a href="/tvBoard/detail?tvbId=${tvbvo.tvbId }&authEmail=${authEmail }&pageNo=${pgvo.pageNo}&qty=${pgvo.qty}&type=${pgvo.type}&keyword=${pgvo.keyword}" class="btn btn-outline-warning">취소</a>
					</li>
				</ul>
			</div>
			<input type="hidden" name="authEmail" id="authEmail" value="${authEmail }">
			<input type="hidden" name="tvbId" id="tvbId" value="${tvbvo.tvbId }">
		</form>
  	</div>
</div>

<jsp:include page="../common/footer.jsp" />