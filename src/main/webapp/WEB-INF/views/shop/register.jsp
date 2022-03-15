<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<div class="container">
    <div class="row">
        <h1 class="jumbotron" style="text-align: center; margin: 50px 0;">구매등록</h1>
    </div>
    <div class="form-group shopInfo">
       <form action="/shop/register" method="post" id="enrollForm">
            <div>
                 <label for="sid">상품번호</label> 
                 <input type="text" class="form-control" name="sid"
               id="sid" value="${svo.sid }">
            </div>
            <div>
                 <label for="email">이메일</label> 
                 <input type="email" class="form-control" name="email"
               id="email" value="${svo.email }">
            </div>
            <div>
                 <label for="sid">상품이름</label> 
                 <input type="text" class="form-control" name="sname"
               id="sname" value="${svo.sname }">
            </div>
            <div>
                 <label for="category">카테고리</label> 
                 <input type="text" class="form-control" name="category"
               id="category" value="${svo.category }">
            </div>
            <div>
                 <label for="price">가격</label> 
                 <input type="text" class="form-control" name="price"
               id="price" value="${svo.price }">
            </div>
            <div>
                 <label for="value">상품정보</label> 
                 <input type="text" class="form-control" name="value"
               id="value" value="${svo.value }">
            </div>
            <div class="btn_section">
                <button id="reg" class="btn" type="submit">등록</button>
            </div>
       </form>
    </div>
</div>