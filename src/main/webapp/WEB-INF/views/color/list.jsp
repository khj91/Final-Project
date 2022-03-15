<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<style>
.content_wrap {
	   width: 80%;
	   float:left;
	   min-height:700px;
}
.content_subject {
	   font-size: 40px;
	   font-weight: bolder;
	   padding-left: 15px;
	   background-color: gray;
	   height: 80px;
	   line-height: 80px;
	   color: white;	
}
.table_wrap {
    	padding: 20px 35px;
}
.table {
	width: 100%;
   	border: 1px solid #d3d8e1;
    	text-align: center;
    	border-collapse: collapse;
}
.table td {
	padding: 10px 5px;
	border : 1px solid #e9ebf0;
}
.table thead {
	background-color: #f8f9fd;	
	font-weight: 600;
}
.th_column_1{
	width:120px;
}
.th_column_3{
	width:110px;
}
.th_column_4{
	width:140px;
}
.th_column_5{
	width:140px;
}
.th_column_6{
	width:50px;
}
a {
	text-decoration: none;
}
ul {
	list-style: none;
	text-align: center;
}
</style>
<div class="content_wrap">
    <div class="content_subject"><h4>상품 목록</h4></div>
    <div class="table_wrap">
         <table class="table">
	   <thead>
	       <tr>
	            <td class="th_column_1">상품번호</td>
	            <td class="th_column_2">상품이름</td>
	            <td class="th_column_3">카테고리</td>
	            <td class="th_column_4">가격</td>
	            <td class="th_column_5">상품설명</td>
	            <td class="th_column_6"></td>
	            <td class="th_column_7"></td>
	       </tr>	   
	   </thead>
	   <tbody>
	   <c:forEach items="${list }" var="cvo">
	       <tr>
	           <td>${cvo.sid}</td>
	           <td>${cvo.sname}</td>
	           <td>${cvo.category}</td>
	           <td>${cvo.price}</td>
	           <td style="color:${cvo.value}">${cvo.value}</td>
	           <td> 
	              <form action='<c:url value="/shop/register"/>' method="post" id="regForm">
	              <input type="hidden" name="sid" value="${cvo.sid }">
	              <input type="hidden" name="sid" value="${cvo.sname}">
	              <input type="hidden" name="sid" value="${cvo.category}">
	              <input type="hidden" name="sid" value="${cvo.price}">
	              <input type="hidden" name="sid" value="${cvo.value}">
	              <button type="submit" id="regBtn" class="btn btn-outline-danger" value=${cvo.sid }>REG</button>
	              </form>
	           </td>
	           <td> 
	              <form action="/color/remove" method="post" id="delForm">
	              <button type="submit" id="delBtn" class="btn btn-outline-danger" value=${cvo.sid }>DEL</button>
		    <input type="hidden" name="sid" value="${cvo.sid }">
		    <input type="hidden" name="pageNo" value="${pgvo.pageNo }">
	              <input type="hidden" name="qty" value="${pgvo.qty }">
	              </form>
	           </td>
	           
	       </tr>
	   </c:forEach>    
	   </tbody>      
         </table>
       
 	
         
         <!-- paging -->
         <ul class="pagination justify-content-center">
	<c:if test="${pgn.prev }">
    	  <li class="page-item">
      	  <a href="/color/list?pageNo=${pgn.startPage - 1 }&qty=${pgn.pgvo.qty}" class="page-link">Prev</a>
    	  </li>
          </c:if>
          <c:forEach begin="${pgn.startPage }" end="${pgn.endPage }" var="i">
            <li class="page-item ${pgn.pgvo.pageNo == i ? 'active':''}"
            aria-current="page">
            <a class="page-link" href="/color/list?pageNo=${i }&qty=${pgn.pgvo.qty}">${i }</a>
            </li>
          </c:forEach>
          <c:if test="${pgn.next }">
            <li class="page-item">
            <a class="page-link" href="/color/list?pageNo=${pgn.endPage + 1 }&qty=${pgn.pgvo.qty}">Next</a>
            </li>
         </c:if>
        </ul>
    </div>
</div>
<script>
let isUp = '<c:out value="${isUp}"/>';
let isDel = '<c:out value="${isDel}"/>';
if (parseInt(isUp)) {
	alert('게시물 등록 성공~');
}
if (parseInt(isDel)) {
	alert('게시물 삭제 성공~');
}
function getCheckboxValue(){

	const check = 'input[name="color"]:checked';
	const selectedCheck = document.querySelectorAll(check);
}
const form = {
		sid : '${cvo.sid }',
		category : '${cvo.category }',
		sname : '${cvo.sname }',
		value : '${cvo.value }',
		price : '${cvo.price }'
		
}
document.addEventListener("click", (e) => {   
    if(e.target.id == 'regBtn') {
        e.preventDefault();
        document.querySelector("#regForm").submit();
    }
});
</script>