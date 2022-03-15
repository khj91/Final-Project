<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>   
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="EUC-KR">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>

<div class="list">
        <input type="text" name="query" value=""  placeholder="영화 제목을 입력하세요" >
        <input type="submit" id="searchBtn" value="검색">
    </div>
 <div id="main">
  <div class="movie">
     <img src="" alt=""
     >
     <div class="movie-info">
       <h3></h3>
     </div>
  </div>
 </div>
</body>

<script >
const main = document.getElementById('main');
let url = 'https://api.themoviedb.org/3/search/movie?api_key=c00b6bc84cbdb4e899121dcdfa60dac6&language=ko-KR&';
let query = document.querySelector('input[name="query"]');
async function getMovies(url){
	try {
		const res = fetch(url);
		const data = await res.json();
		
		showMovies(data.results)
	} catch(e){
		console.log(e);
	}
}
document.getElementById('searchBtn').addEventListener('click', (e) => {
	e.preventDefault();
	url = url + 'query='+query.value;
	main.innerText = url;
	getMovies(url);
})
function showMovies(movies){
	main.innerHTML = '';
	
	movies.forEach((movie) => {
		const title = movie.title;
		const poster_path = movie.poster_path;
		
		const movieEl = document.createElement('div');
		movieEl.classList('movie');
		
		movieEl.innerHTML = `
				<form action="/poster/register" method="post" id="regForm">
				<img src = "${IMG_PATH + poster_path}" alt="${title}" style="margin-top:5px;">
				<div class="movie-info">
				<h3>${title}</h3>
				<input type="checkbox" id="postervalue" value="${poster_path}">
				<button type="submit" id="regPoster">등록</button>
				<input type="hidden" name="sname" value="${title}">
				<input type="hidden" name="value" value="${poster_path}">
				</form>
				</div>
				`;
				main.appendChild(movieEl);
	})
}

</script>