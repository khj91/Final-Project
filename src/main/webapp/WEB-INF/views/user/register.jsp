<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />

<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/userRegister.css"/>
    <div id="container">
      <div id="leftMenu">
        <div id="menuTitle">가입 회원 혜택</div>
       	<div class="menuText"> 감상한 영화 & TV 프로그램 기록</div>
       	<div class="menuText"> 좋아하는 영화와 TV 프로그램을 <br> 기록하고 추천을 받으세요</div>
       	
      </div>
      <div id="rightMenu">
        <div id="rightTitle">회원가입</div>
        <form action="/user/register" id="registerForm" method="post">
          <label for="email">이메일</label>
          <div class="wrapper">
            <input type="text" id="email" name="email" />
            <button type="button" class="btn" id="validEmailChk">중복 확인</button>
          </div>
          <label for="name">이름</label>
          <br />
          <input type="text" name="name" id="name" />
          <br />
          <label for="nickName">닉네임</label>
          <div class="wrapper">
            <input type="text" name="nickName" id="nickName" />
            <button type="button" class="btn" id="validNickNameChk">중복 확인</button>
          </div>
          <label for="pwd">비밀번호</label>
          <br />
          <input type="password" name="pwd" id="pwd" />
          <br />
          <label for="pwdChk">비밀번호 확인</label>
          <br />
          <input type="password" name="pwdChk" id="pwdChk" />
        </form>
        <div id="btnContainer">
          <button class="btn" type="button" id="regBtn">회원가입</button>
          <a href="/home">취소</a>
        </div>
      </div>
    </div>
  </body>
  <script src="/resources/js/userRegister.js"></script>
</html>
