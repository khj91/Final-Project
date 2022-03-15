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
      <span style="color: ${uvo.fontColor}"><c:out value="${uvo.nickName }"/></span>
    </div>
    <div id="settingContainer">
      <div id="leftMenu">
        <div id="settingTitle">Setting</div>
        <div class="settingMenu">
          <a href="/user/${uvo.email}/modify" > 프로필 편집 </a>
        </div>
        <div class="settingMenu">
          <a href="/user/${uvo.email }/setting" id="currMenu"> 계정 설정 </a>
        </div>
      </div>
      
     
      
      <!--  -->
      <div id="settingMain">
        <h4>계정 설정</h4>
       
        <form method="POST" action="/user/${uvo.email }/setting">
          	
          <input type="hidden" value="${uvo.email }" name="email">
          <div class="setting">
          
            <label for="adultSel">검색에 성인용을 포함 시키겠습니까?</label>
            <select name="adult" id="adultSel">
              <option value="false" ${uvo.adult ? "" : "selected" } >아니오</option>
              <option value="true" ${!uvo.adult ? "" : "selected" } >예</option>
            </select>
          </div>
          <button id="settingSubmitBtn" class="btn rounded-pill" type="submit">저장</button>
        </form>
      </div> 
      <!--  -->
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
</html>


<%-- 
<c:out value="${uvo }" />
<c:out value="${purchased }"></c:out> --%>