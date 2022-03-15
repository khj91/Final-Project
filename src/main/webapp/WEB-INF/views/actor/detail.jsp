<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- <jsp:include page="../common/header.jsp" /> --%>

<jsp:include page="../common/header.jsp" />
   
<jsp:include page="../common/nav.jsp" />
<link rel="stylesheet" href="/resources/css/actor.css" >
   <div id="profileDiv">
      <img id="profileImg" src="" alt="" />
      <div id="descDiv">
        <h3 id="name"></h3>
        <div id="desc">
          <p>참여한 작품 수: <span id="castCnt">200</span></p>
          <p>생일: <span id="bDay"></span> (<span id="age"></span>세)</p>
          <p>출생지: <span id="bPlace"></span></p>
          <p>tmdb내 인기도지수: <span id="popularity"></span></p>
        </div>
      </div>
    </div>
    <main>
      <div id="mainTitle">출연한 영화</div>
      <div id="cardContainer">
      
      </div>
    </main>
  </body>
</html>
<script>
let id = `<c:out value="${id}" />`
console.log(id);
</script>
<script src="/resources/js/actor.detail.js"></script>




<%-- <jsp:include page="../common/footer.jsp" /> --%>