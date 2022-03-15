<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/userSetting.css" />


    <div id="emailDiv">
      <div >
      	<img class="profileImg" src="/fileUpload/${uvo.profileImg }">
      </div>
      <span><c:out value="${uvo.nickName }"/></span>
    </div>
    <div id="settingContainer">
      <div id="leftMenu">
        <div id="settingTitle">Setting</div>
        <div class="settingMenu">
          <a href="/user/${uvo.email}/modify" > 프로필 편집 </a>
        </div>
        <div class="settingMenu">
          <a href="/user/${uvo.email }/setting"> 계정 설정 </a>
        </div>
      </div>
      
      <div id="settingMain">
        <h3>이메일 변경</h3>
        <p>회원님의 이메일을 변경하길 원하시면, 하단 영역에 새로운 이메일을 입력해주세요.</p>
       
        <form method="POST" id="changeNickNameForm" action="/user/modify/nickName">
          	<input type="hidden" name="email" value="${uvo.email }">
          <div class="setting">
	          <label for="pwd">비밀번호</label>
	          <br>
	          <input id="pwd" type="password" value="password" name="pwd">
          </div>
          <div class="setting">
	          <label for="nickName">닉네임</label>
	          <br>
	          <input id="nickName" type="text" value="${uvo.nickName }" name="nickName">
          </div>
          <div class="setting">
	          <label for="nickNameMatch">새 닉네임 확인</label>
	          <br>
	          <input id="nickNameMatch" type="text" name="nickNameMatch">
          </div>
          <button id="changeEmailSubmitBtn" class="btn rounded-pill" type="button">저장</button>
        </form>
      </div>
      
    </div>
  </body>
  <script src="/resources/js/user.setting.js"></script>
</html>


<%-- 
<c:out value="${uvo }" />
<c:out value="${purchased }"></c:out> --%>