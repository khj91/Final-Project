<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<div id="header">
      <div id="profileImg"></div>
      <div id="headerInfo">
        <div id="profileEmailDiv"><span id="profileEmail">${uvo.email }</span> 00년 00월 부터 회원</div>
        <div id="scoreDiv">
          <div class="scoreBlock">
            <div class="score"></div>
            <span class="scoreText">
            평균
            
            영화 점수
            </span>
          </div>
          <div class="scoreBlock">
            <div class="score"></div>
            <span class="scoreText">
            평균
            <br />
            tv 점수
            </span>
          </div>
        </div>
      </div>
    </div>