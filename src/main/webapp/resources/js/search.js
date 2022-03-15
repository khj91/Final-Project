const API_KEY = "6e6b78d7518e1d61e33e6121c3d5e62d";

let movieData = null;
let tvData = null;

const searchMovieData = async (query, page) => {
  try {
    const url = `https://api.themoviedb.org/3/search/movie?api_key=${API_KEY}&language=ko-KR&query=${query}&include_adult=${isAdult}&page=${page}`;
    const res = await fetch(url);
    const result = res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const searchTVData = async (query, page) => {
  try {
    const url = `https://api.themoviedb.org/3/search/tv?api_key=${API_KEY}&language=ko-KR&query=${query}&include_adult=${isAdult}&page=${page}`;
    const res = await fetch(url);
    const result = res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const searchData = () => {
  searchMovieData(query).then((result) => {
    document.querySelector(".moviePill").innerText = result.total_results;
    movieData = result;
  });
  searchTVData(query).then((result) => {
    document.querySelector(".tvPill").innerText = result.total_results;
    tvData = result;
  });
};

const renderMovies = (data) => {
  let cardContainer = document.getElementById("cardContainer");
  if (data.results.length != 0) {
    cardContainer.innerHTML = "";
  } else {
    cardContainer.innerText = "일치하는 영화 결과가 없습니다";
  }

  data.results.forEach((movie) => {
    const card = `
    <div class="searchCard">
          <div class="imgDiv">
            ${
              movie.poster_path != null
                ? `<img
              src="https://www.themoviedb.org/t/p/w94_and_h141_bestv2/${movie.poster_path}"
              alt=""
            />`
                : `<img src="?" style="width:94px;height:141px;" />`
            }
          </div>
          <div class="contentDiv">
            <div class="title">
              <p><a href="/movie/detail/${movie.id}">${movie.title}</a></p>
              <p>${movie.release_date}</p>
            </div>
            <div class="desc">
              ${movie.overview}
            </div>
          </div>
        </div>
    `;
    cardContainer.innerHTML = cardContainer.innerHTML + card;
  });
  cardContainer.innerHTML = cardContainer.innerHTML + renderPager(data.total_pages, data.page, "movie");
  console.log(renderPager(data.total_pages, data.page, "movie"));
};

const renderTVs = (data) => {
  let cardContainer = document.getElementById("cardContainer");
  if (data.results.length != 0) {
    cardContainer.innerHTML = "";
  } else {
    cardContainer.innerText = "일치하는 tv 결과가 없습니다";
  }

  data.results.forEach((tv) => {
    const card = `
   <div class="searchCard">
          <div class="imgDiv">
          ${
            tv.poster_path != null
              ? `<img
              src="https://www.themoviedb.org/t/p/w94_and_h141_bestv2/${tv.poster_path}"
              alt=""
            />`
              : `<img src="?" style="width:94px;height:141px;" />`
          }
          </div>
          <div class="contentDiv">
            <div class="title">
              <p><a href="/tv/detail/${tv.id}">${tv.name}</a></p>
              <p>${tv.first_air_date}</p>
            </div>
            <div class="desc">
              ${tv.overview}
            </div>
          </div>
        </div>
    `;
    cardContainer.innerHTML = cardContainer.innerHTML + card;
  });
  cardContainer.innerHTML = cardContainer.innerHTML + renderPager(data.total_pages, data.page, "tv");
  console.log(renderPager(data.total_pages, data.page, "tv"));
};

const renderPager = (totalPage, page, platform) => {
  let pagingDiv = document.getElementById("pagingDiv");
  page--;
  pagingDiv.innerHTML = "";
  let startPage = page - (page % 10) + 1;
  let endPage = totalPage < startPage + 9 ? totalPage : startPage + 9;
  console.log(startPage, endPage);
  let aList = `<div class="pagingDiv">`;
  if (startPage != 1) {
    aList += `<a class="paging" href="/search/${platform}?query=${query}&page=${startPage - 1}">이전</a>`;
  }
  for (let i = startPage; i <= endPage; i++) {
    let a;
    if (i == page + 1) {
      a = `<a class="paging" style="font-weight: bold;" href="/search/${platform}?query=${query}&page=${i}">${i}</a>`;
    } else {
      a = `<a class="paging" href="/search/${platform}?query=${query}&page=${i}">${i}</a>`;
    }
    aList += a;
  }
  if (totalPage > endPage) {
    aList += `<a class="paging" href="/search/${platform}?query=${query}&page=${endPage + 1}">다음</a>`;
  }
  aList += `</div>`;
  return aList;
};

document.addEventListener("DOMContentLoaded", () => {
  if (platform == "tv") {
    document.getElementById("tvData").style.backgroundColor = "#e6e6e6";

    searchTVData(query, page).then((result) => {
      renderTVs(result);
      document.querySelector(".tvPill").innerText = result.total_results;
      searchMovieData(query).then((result) => {
        document.querySelector(".moviePill").innerText = result.total_results;
      });
    });
  } else if (platform == "movie") {
    document.getElementById("movieData").style.backgroundColor = "#e6e6e6";
    searchMovieData(query, page).then((result) => {
      renderMovies(result);
      document.querySelector(".moviePill").innerText = result.total_results;
      searchTVData(query).then((result) => {
        document.querySelector(".tvPill").innerText = result.total_results;
      });
    });
  }
});

const getRequest = async (query) => {
  try {
    fetch(`/search/${platform}?query=${query}`);
  } catch (e) {
    console.log(e);
  }
};

document.getElementById("searchInput").addEventListener("keydown", (e) => {
  if (e.key == "Enter") {
    location.replace(`/search/${platform}?query=${e.target.value}`);
  }
});
