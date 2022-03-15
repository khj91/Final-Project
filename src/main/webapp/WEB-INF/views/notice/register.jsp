<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/board.register.css" />

<div class="container-fluid">
	<div class="wrapper">
		<div class="title">공지사항 등록</div>
		<form action="/notice/register" method="post">
			<div class="card" style="max-width: 740px;">
				<div class="card-body">
					<ul class="list-group list-group-flush">
						<li class="list-group-item">
							<label for="title">&nbsp&nbsp제목</label>
							<input type="text" id="title" class="notTit" name="title" placeholder="제목을 적으세요." required>
						</li>
						<li class="list-group-item">
							<label for="email">&nbsp작성자</label>
							<input type="text" id="email" class="witAlign" name="email" value="admin@admin.com" readOnly>
						</li>
					</ul>
				</div>
			</div>
			<div class="card mb-3" style="max-width: 740px;">
				<ul class="list-group list-group-flush">
					<li class="list-group-item">
						<label for="cont">내용</label>
						<textarea name="content" id="content" placeholder="내용을 적으세요." required></textarea>
					</li>
					<li class="list-group-item text-end">
						<button type="submit" class="btn btn-outline-info">등록</button>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
<jsp:include page="../common/footer.jsp" />