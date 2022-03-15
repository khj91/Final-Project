<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<div class="form-group">
	<div class="row" style="text-align: center;">
	   <h1 class="page-header" style="margin-bottom: 50px;">나의 주문 내역</h1>
	   <table class="table table-hover" style="margin: auto; border-bottom: 1px solid #D5D5D5;">
	   <thead>
	      <tr>
	         <th colspan="2" style="text-align: center;">상품번호</th>
	         <th>주문인</th>
	         <th>카테고리</th>
	         <th>상품명</th>
	         <th>상품설명</th>
	         <th>가격</th>
	         <th>결제일</th>
	     </tr>
	  </thead>
            <tbody style="text-align: left">
<c:set var="svo" value="${svo }" />           
	     <tr>
	        <td style="text-align:center; padding-right:20px;">${svo.sid }</td>
	        <td style="margin-left:10px">${svo.email }</td>
	        <td>${svo.category }</td>
	        <td>${svo.sname }</td>
	        <td>${svo.value }</td>
	        <td>${svo.price }</td>
	        <td>${svo.purchDate }</td>
	        <td>
	           <button type="button" onclick="location.href='/shop/list'">주문목록</button>
	        	 <br>
		 <button type="button" id="delBtn" class="btn btn-default delBtn">주문취소</button>
	       </td>
	  </tr>	
	</tbody>
	</table>
	</div>
</div>

	        	 <form action="/shop/remove" method="post" id="delForm">
	       	    <input type="hidden" name="sid" value="${svo.sid }">
		    <input type="hidden" name="pageNo" value="${pgvo.pageNo }">
	              <input type="hidden" name="qty" value="${pgvo.qty }">
	       	 </form>


