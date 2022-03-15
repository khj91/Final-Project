<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="content">
	<h2>통계</h2>
	<div class="stats">
		<div class="rateStats">
			<h4>평점 수</h4>
			<p class="statCnt">${mRatedCnt + tRatedCnt}</p>
		</div>
		<div class="revStats">
			<h4>리뷰 수</h4>
			<p class="statCnt">${mReviewedCnt + tReviewedCnt}</p>
		</div>
		<div class="likeStats">
			<h4>즐겨찾기 수</h4>
			<p class="statCnt">${mLikedCnt + tLikedCnt }</p>
		</div>
		
		<div class="rateDist">
			<h4>평점 분포</h4>
			<div>
				<div id="graph">
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.one + tRateData.one)*10}px;background-color: red;"></div></div>
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.two + tRateData.two)*10}px;background-color: red;"></div></div>
					<div class="colorDiv"><div class="bar" style="width: 10px;height:${(mRateData.three + tRateData.three)*10}px;background-color: red;"></div></div>
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.four + tRateData.four)*10}px;background-color: red;"></div></div>
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.five + tRateData.five)*10}px;background-color: red;"></div></div>
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.six + tRateData.six)*10}px;background-color: red;"></div> </div>
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.seven + tRateData.seven)*10}px;background-color: red;"></div></div>
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.eight + tRateData.eight)*10}px;background-color: red;"></div></div>
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.nine + tRateData.nine)*10}px;background-color: red;"></div></div>
					<div class="colorDiv" ><div class="bar" style="width: 10px;height:${(mRateData.ten + tRateData.ten)*10}px;background-color: red;"></div></div>
					
				</div>
				<div id="params">
					<p>1</p>
					<p>2</p>
					<p>3</p>
					<p>4</p>
					<p>5</p>
					<p>6</p>
					<p>7</p>
					<p>8</p>
					<p>9</p>
					<p>10</p>
					
					
				</div>
			</div>
			
		</div>
		<div class="favoriteMGenre">
			<h4>선호하는 영화 장르</h4>
			
		</div>
		<div class="favoriteTGenre">
			<h4>선호하는 tv 장르</h4>
	
		</div>
	</div>
</div>
<div class="content">
<hr/>
	<h2>포인트</h2>
	<div class="stats">
		<div class="currPoints">
			<h4>잔여 포인트</h4>
			<p class="statCnt">${uvo.currPoints}</p>
		</div>
		<div class="totalPoints">
			<h4>누적 포인트</h4>
			<p class="statCnt">${uvo.totalPoints}</p>
		</div>
	</div>
</div>
<script src="/resources/js/user.detail.main.js"></script>