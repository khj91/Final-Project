<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/userSetting.css" />


    <div id="emailDiv">
      <div >
      	<img class="profileImg" src="/fileUpload/og_${uvo.profileImg }">
      </div>
      <span style="color: ${uvo.fontColor}"><c:out value="${uvo.nickName }"/></span>
    </div>
    <div id="settingContainer">
      <div id="leftMenu">
        <div id="settingTitle">Setting</div>
        <div class="settingMenu">
          <a href="/user/${uvo.email }/modify" id="currMenu"> 프로필 편집 </a>
        </div>
        <div class="settingMenu">
          <a href="/user/${uvo.email }/setting"> 계정 설정 </a>
        </div>
      </div>
      <div id="settingMain">
        <h4>프로필 편집</h4>
        <div class="setting">
          <a href="/user/modify/pwd" class="btn btn-secondary">비밀번호 변경</a>
          <a href="/user/modify/nickName" class="btn btn-secondary">닉네임 변경</a>
        </div>
        <div class="setting">
          <label for="emailInput">이메일</label>
          <input id="emailInput" type="text" class="form-control" readonly value="${uvo.email }" />
        </div>
        <div class="setting">
          <label for="imgUploadModalBtn">현재 아바타</label>
          <br />
          <c:choose>
          	<c:when test="${uvo.profileImg eq 'default_img.jpg' }">
          	아바타 없음
          <a id="imgUploadModalBtn" data-bs-toggle="modal" data-bs-target="#exampleModal"
            >아바타를 불러오시겠습니까?</a
          >
          	</c:when>
          	<c:otherwise>
          		<img class="profileImg" src="/fileUpload/og_${uvo.profileImg }">
          		<a id="imgUploadModalBtn" data-bs-toggle="modal" data-bs-target="#exampleModal"
            >아바타를 변경하시겠습니까?</a
          >
          	</c:otherwise>
          </c:choose>
        </div>
        <div class="setting">폰트 색 변경 <br />
        	<div id="fontColorDiv">
        		<div class="fontColor ${uvo.fontColor eq 'black' ? '' : 'buyAble'}" id="black" style="background-color: black">
          	 	 ${uvo.fontColor eq 'black' ? 'applied' : ''}
          	  	</div>
          <c:forEach items="${fontList }" var="color">
          	 	 <div class="fontColor ${uvo.fontColor eq color.value ? '' : 'buyAble'}" id="${color.sname }" style="background-color: ${color.value}">
          	  		${uvo.fontColor eq color.value ? 'applied' : ''}
          		 </div>
          </c:forEach>
          	</div>
         	<form action="/user/modify/fontColor" method="post" id="fontForm" > 
	            <input type="hidden" name="email" value="${uvo.email }" />
	            <input type="hidden" name="color" value="" />
	            <button class="btn" id="fontBtn" type="submit">설정</button>
          	</form>
        </div>
        
      </div>
    </div>
  </body>
  <!-- Modal -->
  <div
    class="modal fade"
    id="exampleModal"
    tabindex="-1"
    aria-labelledby="exampleModalLabel"
    aria-hidden="true"
  >
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <h5 class="modal-title" id="exampleModalLabel">아바타 추가</h5>
          <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
        </div>
        <div class="modal-body">
          <form action="/user/modify/profileImg" id="profileImgForm" method="post" enctype="multipart/form-data">
          	<input type="hidden" name="email" value="${uvo.email }">
            <input type="file" name="file" />
          </form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
          <button type="button" id="profileImgSubmitBtn" class="btn btn-primary">Save</button>
        </div>
      </div>
    </div>
  </div>
  <script src="/resources/js/user.setting.js"></script>
  <script>
  	let wrongPwd = `<c:out value="${wrongPwd}" />`;
  	let isUp = `<c:out value="${isUp}" />`;
  	if(isUp > 0){
  		alert("정보 수정 완료");
  	}
  	if(parseInt(wrongPwd) > 0){
  		alert("비밀번호가 일치하지 않습니다.");
  	}
  </script>
</html>

