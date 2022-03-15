<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
<style>
.content_wrap {
	width: 80%;
	float: left;
	min-height:700px;
}
.content_subject {
	font-size: 40px;
	font-weight: bolder;
	padding-left: 15px;
	background-color: gray;
	height: 80px;
	line-height: 80px;
	
}
.form_section {
	width: 95%;
	margin-left: 2%;
	margin-top: 20px;
	border: 1px soid #dbdde2;
	background-color: #efefef;
}
.form_section_title {
	padding: 20px 35px;
}
.form_section_title label {
	display: inline;
	font-size: 20px;
	font-weight: 800
}
.form_section_content input {
	font-size: 20px;
	margin: 10px 0;
	padding: 10px 50%;
}
#reg {
	
	margin: 5px 0;
	margin-left: 16px;
}

</style>
<div class="content_wrap">
    <div class="content_subject"><span>상품 등록</span></div>
    <div class="main">
        <form action="/color/register" method="post" id="enrollForm">
          <div class="form_section">
            <div class="form_section_title">
	    <label for="sname" class="form-label">상품이름</label>  
	    <input type="text" class="form-control" name="sname"
               id="sname" placeholder="상품명을 입력해주세요">
            </div>
            <div class="form_section_content">
              
            </div>
          </div>
        
          <div class="form_section">
            <div class="form_section_title">
	    <label for="category" class="form-label">카테고리</label>  
	    <input type="text" class="form-control" name="category"
               id="category" placeholder="카테고리를 입력해주세요">
            </div>
            <div class="form_section_content">
              
            </div>
          </div>
          
          <div class="form_section">
            <div class="form_section_title">
	    <label for="price" class="form-label">가격</label> 
	    <input type="text" class="form-control" name="price"
               id="price" placeholder="가격을 입력해주세요">
	     
            </div>
            <div class="form_section_content">
            </div>
          </div>
          
          <div class="form_section">
            <div class="form_section_title">
	    <label for="value" class="form-label">상품정보</label>  
	    <input type="color" class="form-control" name="value"
               id="value">
            </div>
            <div class="form_section_content">
              
            </div>
          </div>
         <div class="btn_section">
           <button id="reg" class="btn" type="submit">등록</button>
         </div>
        </form> 
    </div>
</div>
<script>

</script>