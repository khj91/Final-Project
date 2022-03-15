<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>    
    <div class="list">
        <input type="text" name="search" placeholder="tv 제목을 입력하세요" >
        <button type="button" id="searchBtn">검색</button>
    </div>
    <main id="main">
        <div class="movie">
            <img src="" alt=
            "">
            <div class="movie-info">
                <h3></h3>
            </div>
        </div>
    </main>
<script>
const list = document.getElementById('list');
const API_KEY = "c00b6bc84cbdb4e899121dcdfa60dac6";
const IMG_PATH = 'https://image.tmdb.org/t/p/w1280';
const url = "https://api.themoviedb.org/3/search/tv?api_key="+API_KEY+"&language=ko-KR&query=";

let query = '';
const main = document.getElementById('main');
let div = document.querySelector(".div");
let button = document.getElementById('searchBtn');
let input = document.querySelector('input[name="search"]');


async function getMovies(url){
   
    try{
        const res = await fetch(url);
        const data = await res.json();
        showMovies(data.results)
    } catch (e){
        console.log(e);
    }
  
  
}

document.addEventListener('click', (e) => {
    if (e.target.id == "searchBtn") {
        e.preventDefault();
        
        if (input.value && input.value !== '') {
            query = input.value;
            getMovies(url + query);
        } else {
            window.location.reload();
        }
   }
});

function showMovies(movies){
    main.innerHTML = '';

     movies.forEach((movie) => {
     const title = movie.title;
     const poster_path = movie.poster_path;

     const movieEl = document.createElement('div');
     movieEl.classList.add('movie');

     movieEl.innerHTML = `
                        <form action="/poster/register" method="post" id="regForm">
                        <img src ="${IMG_PATH + poster_path}" alt="${title}" style="margin-top:5px;">
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
});
}
</script>
