<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/board.register.css" />

<sec:authentication property="principal.uvo.email" var="authEmail" />
<div class="container-fluid">
	<div class="wrapper">
		<div class="title">영화 감상평 등록</div>
		<form action="/mBoard/register" method="post">
			<div class="card" style="max-width: 740px;">
				<div class="row g-0">
					<div class="col-md-4">
						<img src="${poster }" class="img-fluid rounded-start posterSize">
					</div>
					<div class="col-md-8">
						<div class="card-body">
							<ul class="list-group list-group-flush">
								<li class="list-group-item">
									재미 여부
									<div class="funColor">&nbsp&nbsp
										<button type="button" class="LHBtn" id="likeBtn">재미있어요</button>
										/
										<button type="button" class="LHBtn" id="hateBtn">재미없어요</button>
										<input type="hidden" name="likeHate" id="likeHate" required>
										<input type="hidden" name="poster" id="poster" value="${poster }">
										<input type="hidden" name="mid" id="mid" value="${mid }">
									</div>
								</li>
								<li class="list-group-item">
									<label for="movieTitle">영화제목</label>
									<input type="text" id="movieTitle" class="regAlign" name="movieTitle" value="${movieTitle}" readOnly>
								</li>
								<li class="list-group-item">
									<label for="regDate">개봉날짜</label>
									<input type="text" id="regDate" class="regAlign" name="regDate" value="${regDate}" readOnly>
								</li>
								<li class="list-group-item">
									<label for="writer">&nbsp작성자</label>
									<input type="email" id="writer" class="witAlign" name="writer" value=" ${authEmail }" readOnly>
								</li>
								<li class="list-group-item">
									<label for="title">&nbsp&nbsp제목</label>
									<input type="text" name="title" class="titAlign" id="title" placeholder="제목을 적으세요." required>
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
						<textarea name="content" id="content" placeholder="내용을 적으세요." required></textarea>
					</li>
					<li class="list-group-item text-end">
						<button id="regBtn" type="submit" class="btn btn-outline-info" disabled>등록</button>
					</li>
				</ul>
			</div>
		</form>
	</div>
</div>
<script src="/resources/js/mBoard.register.js"></script>
<jsp:include page="../common/footer.jsp" />