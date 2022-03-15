<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />

<jsp:include page="../common/nav.jsp" />
   <link rel="stylesheet" href="/resources/css/userLogin.css" />
    <main>
      <form action="/user/findPwd" id="findPwdForm" method="post">
        <div class="title">
          <h4>비밀번호 초기화</h4>
        </div>
        <label for="email">이메일</label>
        <br />
        <input type="text" name="email" id="email" />
        <br />
        <label for="email">이름</label>
        <br />
        <input type="text" name="name" id="name" />
        <br />
        <label for="nickName">닉네임</label>
        <br />
        <input type="text" name="nickName" id="nickName" />
        <br />
         <label for="pwd">비밀번호</label>
        <br />
        <input type="password" name="pwd" id="pwd" />
        <br />
         <label for="pwdChk">비밀번호 확인</label>
        <br />
        <input type="password" name="pwdChk" id="pwdChk" />
        <div class="btnDiv">
          <button class="btn" id="loginBtn" type="button">비밀번호 초기화</button>
          
        </div>
      </form>
    </main>
  </body>
  <script>
  document.getElementById("loginBtn").addEventListener("click", (e) => {
	  let pwd = document.getElementById("pwd").value;
	  let pwdChk = document.getElementById("pwdChk").value;
	  if(pwd == pwdChk){
		  document.getElementById("findPwdForm").submit();
	  } else {
		  alert("비밀번호가 일치하지 않습니다");
	  }
  })
  let isUp = `<c:out value="${isUp}" />`;
	if(isUp > 0){
		alert("정보 수정 완료");
	}
  </script>
</html>

