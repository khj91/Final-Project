// 98f72697bdec70505dd54384d7d53a89

const apiKey = 'api_key=98f72697bdec70505dd54384d7d53a89';
const baseUrl = 'https://api.themoviedb.org/3';
const apiUrl = baseUrl + '/discover/tv?sort_by=popularity.desc&' + apiKey;
const imgUrl = 'https://image.tmdb.org/t/p/w500';
const searchUrl = baseUrl + '/search/tv?' + apiKey;

const tvArea = document.getElementById('tvArea');

function getTvs(url){
    fetch(url).then(res => res.json()).then(result => {
        console.log(result.results);

        showTvs(result.results);
    })
}

function showTvs(result){
    tvArea.innerHTML = '';

    result.forEach(tv => {
        const {id, name, poster_path, first_air_date, vote_average} = tv;
        const tvEl = document.createElement('div');
        tvEl.classList.add('tv');
        tvEl.innerHTML = `
            <form action="/tvBoard/registerTv" method="post">
            <input type="hidden" id="tvid" name="tvid" value="${id}">
            <input type="hidden" id="regDate" name="regDate" value="${first_air_date}">
            <input type="hidden" id="tvTitle" name="tvTitle" value="${name}">
            <input type="hidden" id="poster" name="poster" value="${imgUrl + poster_path}">
            <div class="card tvCard bg-light">
            <img src="${imgUrl + poster_path}" class="card-img-top tvPoster" alt="">
            <div class="card-body">
            <div class="card-title cTitle">${name}</div>
            <div class="card-text rDate">평점 <span class="vote${rateColor(vote_average)}">${vote_average}</span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp${first_air_date}</div>
            <div class="btnAlign"><button type="submit" class="btn btn-outline-info" id="tvSelctBtn">선택</button></div>
            </div>
            </div>
            </form>`;
        tvArea.appendChild(tvEl);
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
        getTvs(searchUrl + '&query=' + searchTerm);
    }else{
        getTvs(apiUrl);
    }
});