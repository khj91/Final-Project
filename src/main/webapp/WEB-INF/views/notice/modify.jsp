<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/board.detail.css" />

<div class="container-fluid">
	<div class="wrapper">
		<form action="/notice/modify" method="post">
			<div class="title">공지사항 수정</div>
			<div class="card" style="max-width: 740px;">
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<label for="title">&nbsp&nbsp제목</label>
							<input type="text" id="title" class="modTitle" name="title" value="${nvo.title}">
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
						<textarea name="content" id="content">${nvo.content }</textarea>
					</li>
					<li class="list-group-item text-end">
							<button type="submit" class="btn btn-outline-info" id="delBtn">수정</button>
							<a href="/notice/list" class="btn btn-outline-warning" id="delBtn">취소</a>
					</li>
				</ul>
			</div>
			<input type="hidden" name="nid" id="nid" value="${nvo.nid }">
		</form>
	</div>
</div>
<jsp:include page="../common/footer.jsp" />