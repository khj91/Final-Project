<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/purchase.css">
 <div id="settingContainer">
      <div id="leftMenu">
        <div id="settingTitle">Shop</div>
        <div class="settingMenu">
          <a href="" id="currMenu"> 폰트 컬러 구매 </a>
        </div>
        <div class="settingMenu">
          <a href="/purchase/poster"> 영화 포스터 구매 </a>
        </div>
        
      </div>
      <div id="settingMain">
        <h4>폰트컬러 구매</h4>
        <div class="setting" style="text-align: end">
        	잔여 포인트: ${currPoints }
        </div>
        <div class="setting">
          폰트 컬러(40포인트)
          <div id="fontColorDiv">
            <div class="fontColor ${fn:contains( fontCsv, 'margenta') ? '' : 'buyAble' }" id="margenta" style="background-color: #bd43bd">${fn:contains( fontCsv, "margenta") ? '구매한 폰트' : '' }</div>
            <div class="fontColor ${fn:contains( fontCsv, 'red') ? '' : 'buyAble' }" id="red" style="background-color: red">${fn:contains( fontCsv, "red" )? '구매한 폰트' : '' }</div>
            <div class="fontColor ${fn:contains( fontCsv, 'yellow') ? '' : 'buyAble' }" id="yellow" style="background-color: #dfdf7e">${fn:contains( fontCsv, "yellow") ? '구매한 폰트' : '' }</div>
            <div class="fontColor ${fn:contains( fontCsv, 'blue') ? '' : 'buyAble' }" id="blue" style="background-color: #002ec4">${fn:contains(fontCsv, "blue" )? '구매한 폰트' : '' }</div>
            <div class="fontColor ${fn:contains( fontCsv, 'green') ? '' : 'buyAble' }" id="green" style="background-color: green">${fn:contains( fontCsv, "green") ? '구매한 폰트' : '' }</div>
            <div class="fontColor ${fn:contains( fontCsv, 'cyan') ? '' : 'buyAble' }" id="cyan" style="background-color: cyan">${fn:contains( fontCsv, "cyan") ? '구매한 폰트' : '' }</div>
          </div>
          <form action="/purchase/register" method="post" id="buyForm"> 
            <input type="hidden" name="category" value="1" />
            <input type="hidden" name="email" value="${email }" />
            <input type="hidden" name="sname" value="" />
            <input type="hidden" name="price" value="40" />
            <input type="hidden" name="value" value="" />
            <button class="btn" id="buyBtn" type="button">구매</button>
          </form>
        </div>

       
      </div>
    </div>
  </body>
  <!-- Modal -->
<script src="/resources/js/purchase.font.js" ></script>
<jsp:include page="../common/footer.jsp" />