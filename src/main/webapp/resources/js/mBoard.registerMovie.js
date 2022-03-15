// 98f72697bdec70505dd54384d7d53a89

const apiKey = 'api_key=98f72697bdec70505dd54384d7d53a89';
const baseUrl = 'https://api.themoviedb.org/3';
const apiUrl = baseUrl + '/discover/movie?sort_by=popularity.desc&' + apiKey;
const imgUrl = 'https://image.tmdb.org/t/p/w500';
const searchUrl = baseUrl + '/search/movie?' + apiKey;

const movieArea = document.getElementById('movieArea');

function getMovies(url){
    fetch(url).then(res => res.json()).then(result => {
        console.log(result.results);

        showMovies(result.results);
    })
}

function showMovies(result){
    movieArea.innerHTML = '';

    result.forEach(movie => {
        const {adult, id, title, poster_path, release_date, vote_average} = movie;
        const movieEl = document.createElement('div');
        movieEl.classList.add('movie');
        movieEl.innerHTML = `
            <form action="/mBoard/registerMovie" method="post">
            <input type="hidden" id="mid" name="mid" value="${id}">
            <input type="hidden" id="regDate" name="regDate" value="${release_date}">
            <input type="hidden" id="movieTitle" name="movieTitle" value="${title}">
            <input type="hidden" id="poster" name="poster" value="${imgUrl + poster_path}">
            <div class="card movieCard bg-light">
            <img src="${imgUrl + poster_path}" class="card-img-top moivePoster" alt="">
            <div class="card-body">
            <div class="card-title cTitle">${title}</div>
            <div class="card-text rDate">평점 <span class="vote${rateColor(vote_average)}">${vote_average}</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${release_date}</div>
            <div class="btnAlign"><button type="submit" class="btn btn-outline-info" id="movieSelctBtn">선택</button></div>
            </div>
            </div>
            </form>`;
        movieArea.appendChild(movieEl);
    });
}

function rateColor(rate) {
    if(rate >= 8){
        return 'Green';
    }else if(rate >= 5){
        return 'Orange';
    }else{
        return 'Red';
    }
}

document.getElementById('searchBtn').addEventListener('click',(e)=>{
    e.preventDefault();

    const searchTerm = document.getElementById('search').value;

    if(searchTerm){
        getMovies(searchUrl + '&query=' + searchTerm);
    }else{
        getMovies(apiUrl);
    }
});