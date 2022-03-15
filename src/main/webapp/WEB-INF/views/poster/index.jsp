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
</style>
<div class="content_wrap">
    <div class="content_subject"><span>카테고리 선택</span></div>
    <div class="main">
        <p>카테고리를 선택해 주세요</p>
        <select name="select" onchange="window.open(value, '_self');">
           <option>선택</option>
           <option value="/poster/movie">movie</option>
           <option value="/poster/tv">tv</option>
        </select>
    </div>
</div>
