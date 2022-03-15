<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />

<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/userLogin.css" />
    <main>
      <form action="/user/login" method="post">
        <div class="title">
          <h4>로그인</h4>
        </div>
        <label for="email">이메일</label>
        <br />
        <input type="text" name="email" id="email" />
        <br />
        <label for="pwd">비밀번호</label>
        <br />
        <input type="password" name="pwd" id="pwd" />
        <br />
        <a href="/user/findId">이메일을 잊으셨나요?</a>
        <br />
        <div class="btnDiv">
          <button class="btn" id="loginBtn" type="submit">로그인</button>
          <a href="/user/findPwd">비밀번호 초기화</a>
        </div>
      </form>
    </main>
  </body>
  <script>
  let email = `<c:out value="${email}" />`;
  let isUp = `<c:out value="${isUp}" />`;
	if(isUp > 0){
		alert("정보 수정 완료");
	}
	if(email != null && email != ""){
		alert(`회원님의 이메일은 ${email} 입니다.`);
	}
  let errMsg = `<c:out value="${errMsg}" />`;
	if(errMsg != null && errMsg != ''){
		alert(errMsg);
	}
  </script>
</html>

