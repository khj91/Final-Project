<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/userSetting.css" />
<div id="changePwdDiv">
<h3>비밀번호 초기화</h3>
<form action="/user/modify/pwd" method="post" id="changePwdForm" >
	<label for="email" >이메일</label>
	<br>
	<input type="text" id="email"  name="email" value="${email }" readonly style="width: 200px;">
	<br>
	<label for="pwd">기존 비밀번호</label>
	<br>
	<input type="password" name="pwd" style="width: 200px;" >
	<br>
	<label for="pwd">새 비밀번호</label>
	<br>
	<input type="password" id="newPwd" name="newPwd" id="newPwd" style="width: 200px;">
	<br>
	<label for="pwdChk">새 비밀번호 확인</label>
	<br>
	<input type="password" id="pwdChk" name="pwdChk" id="pwdChk" style="width: 200px;">
	<br>
	<button type="button" class="btn" id="changePwdSubmitBtn">저장</button>
</form>

<script src="/resources/js/user.setting.js"></script>
</div>
</body>
</html>
