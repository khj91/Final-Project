<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<style>
.content_wrap {
	   margin: auto;
	   width: 85%;
	   min-height:700px;
	   border:1px solid black;
	 
}
.content_subject {
	   font-size: 40px;
	   font-weight: bolder;
	   padding-left: 15px;
	   background-color: rgb(91, 189, 250);
	   height: 80px;
	   line-height: 80px;
	   color: white;	
}
span.content a img {        
             width: 45px;
             height: 45px;
             border-radius: 50%;
             border: none;
             margin: 0;
             padding: 0;
             
  }
td { 
	   color: #000; 
             font : 16px "source Sans Pro", Arial, sans-serif;
}
.detail {
	   position: absolute;
	   margin-left:4px;
}
.detail h3 {
	font-weight: 900;
}
p {
	font: 13px "source Sans Pro", Arial, sans-serif;
}	
.leaderboard td h3 a {
	font: 19.2px "source Sans Pro", Arial, sans-serif;
	color: #000;
	text-decoration: none;
	font-weight: 600px;
	cursor: pointer;
}
ul {
	list-style: none;
	text-align: center;
}
.table_wrap {
	text-align: center;
	margin-bottom: 40px;
}
h4 {
	margin-right: 20px;
	margin-bottom: 20px;
	font-weight: bold;
	color: #082546;
	font-size: 16px;
}
.leaderboard {
   width: 240px;
   margin: 40 auto 0 auto;
}
.title {
	color: #fff;
}
.con1 {
	font-size: 16px;
}
.comment {
	font: 24px "source Sans Pro", Arial, sans-serif;
	color: #000;
	margin: 50px 0px 30px;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
}
</style>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<div class="content_wrap">
    <div class="content_subject"><h4 class="title">´ñ±Û¿Õ</h4></div>
<select name="list" onchange="location.href=this.value">
<option>¼±ÅÃ</option>
<option value="/rank/pointlist">Æ÷ÀÎÆ®¿Õ</option>
<option value="/rank/boardlist">°Ô½ÃÆÇ¿Õ</option>
<option value="/rank/commentlist">´ñ±Û¿Õ</option>
</select>
    <div class="comment">¿µÈ­/tv °Ô½ÃÆÇ ´ñ±Û ±â¿©ÀÚ</div>
    <div class="table_wrap">
    <h4>¿µÈ­´ñ±Û°Ô½ÃÆÇ</h4>
      <table class="leaderboard">
      <tbody>
	   <c:forEach items="${cmlist }" var="crvo">
	   <tr>
	           <td class="con1">${crvo.commentQty}</td>
	           <td>
	             <span class="content">
	              <a href="/user/detail.jsp"><img src="/fileUpload/${crvo.profileImg}"></a>
	             </span>
	             <span class="detail">
	              <h3><a href="#">${crvo.nickName}</a></h3>
	              <p>°¡ÀÔ : ${crvo.regAt}</p>
	             </span> 
	             <div style="clear:both; padding-top: 10px;"></div>
	           </td>   
	     </tr> 
	   </c:forEach>    
	  </tbody>
	  </table>
       </div>
    <div class="table_wrap">
         <h4>tv´ñ±Û°Ô½ÃÆÇ</h4>
         <table class="leaderboard">
           <tbody>
	   <c:forEach items="${ctlist }" var="crvo">
	   <tr>
	          <td class="con1">${crvo.commentQty}</td>
	           <td>
	             <span class="content">
	              <a href="detail.jsp"><img src="/fileUpload/${crvo.profileImg}"></a>
	             </span>
	             <span class="detail">
	              <h3><a href="#">${crvo.nickName}</a></h3>
	              <p>°¡ÀÔ : ${crvo.regAt}</p>
	             </span> 
	             <div style="clear:both; padding-top:10px"></div>
	           </td>   
	     </tr> 
	   </c:forEach> 
	   </tbody></table>   
       </div>
</div> 
<jsp:include page="../common/footer.jsp" />