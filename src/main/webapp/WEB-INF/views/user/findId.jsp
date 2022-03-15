<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />

<jsp:include page="../common/nav.jsp" />
   <link rel="stylesheet" href="/resources/css/userLogin.css" />
    <main>
      <form action="/user/findId" method="post">
        <div class="title">
          <h4>이메일 찾기</h4>
        </div>
        <label for="email">이름</label>
        <br />
        <input type="text" name="name" id="name" />
        <br />
        <label for="nickName">닉네임</label>
        <br />
        <input type="text" name="nickName" id="nickName" />
        <br />
        <div class="btnDiv">
          <button class="btn" id="loginBtn" type="submit">이메일 찾기</button>
          
        </div>
      </form>
    </main>
  </body>
  <script>
  let isUp = `<c:out value="${isUp}" />`;
	if(isUp > 0){
		alert("정보 수정 완료");
	}
  </script>
</html>

