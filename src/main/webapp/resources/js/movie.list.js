const API_KEY = "6e6b78d7518e1d61e33e6121c3d5e62d";
// 카테고리별 검색, 페이징에 이용
let page = 1;

let genreQuery = [];
let sortQuery = "";
let url = `https://api.themoviedb.org/3/movie/${sortBy}?api_key=${API_KEY}&language=ko-KR&region=KR&include_adult=${isAdult}&page=`;
const searchBtn = document.getElementById("searchBtn");

// post시에 전송할 영화 데이터 변수
let movieData = {};

// 사용자의 좋아요/평점/리뷰 리스트 받아올 변수
let likedList = null;
let ratedList = null;
let reviewedList = null;

const gainPoints = async (email, point) => {
  try {
    const data = { point };
    const config = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/user/${email}/gainPoints`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const changeUrl = () => {
  url = `https://api.themoviedb.org/3/discover/movie?api_key=${API_KEY}&language=ko-KR&region=KR&sort_by=${sortQuery}&include_adult=false&include_video=false&include_adult=${isAdult}&with_genres=${genreQuery.join(
    ","
  )}&with_watch_monetization_types=flatrate&page=`;
};
console.log(url);
const getJson = async (page = 1) => {
  try {
    const res = await fetch(url + page);
    console.log(url);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const getUserLikedList = async () => {
  try {
    const res = await fetch(`/movie/${email}/liked`);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log();
  }
};

const getUserRatedList = async () => {
  try {
    const res = await fetch(`/movie/${email}/rated`);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const getUserReviewdList = async () => {
  try {
    const res = await fetch(`/movie/${email}/reviewed`);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const getUserData = async () => {
  console.log("fetching...");
  const likedList_ = await getUserLikedList();
  const reviewedList_ = await getUserReviewdList();
  const ratedList_ = await getUserRatedList();
  likedList = likedList_;
  reviewedList = reviewedList_;
  ratedList = ratedList_;
};

const drawStar = (target) => {
  document.querySelector(`.star span`).style.width = `${target.value * 10}%`;
};

const addLike = async (mid, title, poster, genres) => {
  try {
    const data = {
      mvvo: { mid, title, poster, genres },
      lvo: { mid, email },
    };
    const config = {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(data),
    };
    const res = await fetch(`/movie/like/${mid}`, config);
    const result = await res.text();
    if (result == 1) {
      alert("즐겨찾기 추가 성공");
      likedList.push({ mid, title, poster });
      // 즐겨찾기 추가 -> 삭제 구현
      let likeBtn = document.querySelector(`a[data-likeId="${mid}"]`);
      likeBtn.outerHTML = `<a onclick="removeLike('${mid}')" class="dropdown-item" href="#" data-likeId="${mid}">즐겨찾기 해제</a>`;
    }
  } catch (e) {
    alert("즐겨찾기 추가 실패..");
    console.log(e);
  }
};

const removeLike = async (mid, title, poster, genres) => {
  try {
    const data = {
      mid,
      email,
    };
    const config = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/movie/like/${mid}`, config);
    const result = await res.text();
    if (result) {
      alert("즐겨찾기 제거 성공");
      likedList = likedList.filter((each) => each.mid == mid);
      // 즐겨찾기 추가 -> 삭제 구현
      let likeBtn = document.querySelector(`a[data-likeId="${mid}"]`);
      likeBtn.outerHTML = `<a onclick="addLike('${mid}','${title}','${poster}', ${genres})" data-likeId="${mid}" class="dropdown-item" href="#">즐겨찾기 추가</a>`;
    } else {
      alert("즐겨찾기 제거 실패..");
    }
  } catch (e) {
    console.log(e);
  }
};

const setData = (rating, mid, title, poster, genres) => {
  let ratingStar = document.getElementById("ratingStar");
  if (rating != null) {
    ratingStar.value = rating;
    document.querySelector(`.star span`).style.width = `${rating * 10}%`;
    ratingStar.dataset.status = "mod";
  } else {
    ratingStar.value = 0;
    document.querySelector(`.star span`).style.width = `${0}%`;
    ratingStar.dataset.status = "reg";
  }
  movieData = { mid, title, poster, genres };
  ratingStar.dataset.mid = mid;
};

const addRating = async (mid, email, rating) => {
  try {
    const data = {
      mvvo: movieData,
      rtvo: { mid, email, rating },
    };
    const config = {
      headers: { "Content-Type": "application/json" },
      method: "POST",
      body: JSON.stringify(data),
    };
    const res = await fetch(`/movie/rating/${mid}`, config);
    const result = await res.text();
    let rateBtn = document.querySelector(`a[data-ratingId="${mid}"]`);

    if (result > 0) {
      // 평점 남기기 -> 평점 수정 구현
      rateBtn.outerHTML = `<a class="dropdown-item" onclick="setData(${rating}, '${movieData.mid}', '${movieData.title}', '${movieData.poster}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${movieData.id}" data-status="mod" href="#">평점 수정하기</a>`;
      document.getElementById("modalCloseBtn").click();
      alert("평점 등록 성공, 1포인트 획득!");
      gainPoints(email, 1);
    } else {
      alert("평점 등록 실패..");
    }
  } catch (e) {
    console.log(e);
  }
};

const modifyRating = async (mid, email, rating) => {
  try {
    const data = {
      mid,
      email,
      rating,
    };
    const config = {
      method: "PATCH",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/movie/rating/${mid}`, config);
    const result = await res.text();
    if (result != null || result != "NoData") {
      let rateBtn = document.querySelector(`a[data-ratingId="${mid}"]`);
      rateBtn.outerHTML = `<a class="dropdown-item" onclick="setData(${rating}, '${movieData.mid}', '${
        movieData.title
      }', '${movieData.poster}','${movieData.genres
        .map((each) => each.id)
        .join(",")}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${
        movieData.mid
      }" data-status="mod" href="#">평점 수정하기</a>`;
      document.getElementById("modalCloseBtn").click();
      alert("평점 수정 성공");
    } else {
      alert("평점 수정 실패..");
    }
  } catch (e) {
    alert("평점 수정 실패..");
    console.log(e);
  }
};

const removeRating = async (mid, email) => {
  try {
    const data = {
      mid,
      email,
    };
    const config = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/movie/rating/${mid}`, config);
    const result = await res.text();
    if (result != null || result != "NoData") {
      let rateBtn = document.querySelector(`a[data-ratingId="${mid}"]`);
      rateBtn.outerHTML = `<a class="dropdown-item" onclick="setData(${null}, '${movieData.id}', '${
        movieData.title
      }', '${movieData.poster}','${movieData.genres
        .map((each) => each.id)
        .join(",")}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${
        movieData.id
      }" data-status="reg" href="#">평점 남기기</a>`;
      alert("평점 삭제 성공");
    } else {
      alert("평점 삭제 실패..");
    }
  } catch (e) {
    console.log(e);
  }
};

const renderMovies = async (json, page = 1) => {
  console.log(json);
  let cardContainer = document.getElementById("cardContainer");

  if (page == 1) {
    cardContainer.innerHTML = "";
  }
  if (json == null || json.length == 0) {
    cardContainer.innerHTML = `<div style="text-align: center; padding: 100px 0px;"><h5 class="noDataMsg">정보가 없습니다.</h5></div>`;
    console.log(document.getElementById("moreBtn"));
    document.getElementById("moreBtn").style.display = "none";
  } else if (json.length < 20) {
    document.getElementById("moreBtn").style.display = "none";
  }
  json.forEach((movie, i) => {
    let isLiked = 0;
    let isRated = null;
    likedList?.forEach((liked) => {
      if (movie.id == liked.mid) {
        isLiked = 1;
      }
    });
    ratedList?.forEach((rated) => {
      if (movie.id == rated.mid) {
        isRated = rated.rating;
      }
    });

    const card = `
    <div class="cards col-lg-3">
      <a href="/movie/detail/${movie.id}">
        <img id="listImg"
        src="https://www.themoviedb.org/t/p/w440_and_h660_face${movie.poster_path}"
        alt=""
        />
        
        <div class="dropdown menu">
          <a class="moreBtn" href="#" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
            ···
          </a>

          <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
            <li>
            ${
              isLiked
                ? `<a onclick="removeLike('${movie.id}','${movie.title}','${
                    movie.poster_path
                  }','${movie.genre_ids.join(",")}')" class="dropdown-item" href="#" data-likeId="${
                    movie.id
                  }">즐겨찾기 해제</a>`
                : `<a onclick="addLike('${movie.id}','${movie.title}','${
                    movie.poster_path
                  }','${movie.genre_ids.join(",")}')" data-likeId="${
                    movie.id
                  }" class="dropdown-item" href="#">즐겨찾기 추가</a>`
            }
            
            </li>
            <li>
            ${
              isRated != null
                ? `<a class="dropdown-item" onclick="setData(${isRated}, '${movie.id}', '${movie.title}', '${
                    movie.poster_path
                  }','${movie.genre_ids.join(
                    ","
                  )}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${
                    movie.id
                  }" data-status="mod" href="#">평점 수정하기</a>`
                : `<a class="dropdown-item" onclick="setData(${isRated}, '${movie.id}', '${movie.title}', '${
                    movie.poster_path
                  }','${movie.genre_ids.join(
                    ","
                  )}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${
                    movie.id
                  }" data-status="reg" href="#">평점 남기기</a>`
            }
            
            </li>
          </ul>
        </div>
        <div class="cardContent">
          <div class="cardTitle">${movie.title}</div>
          <div class="cardSub">${movie.release_date}</div>
        </div>
      </a>
    </div>
        `;
    cardContainer.innerHTML += card;
  });
};

document.addEventListener("DOMContentLoaded", () => {
  getUserData().then(() => {
    getJson().then((result) => renderMovies(result.results));
  });
});

document.addEventListener("click", (e) => {
  if (e.target.id == "moreBtn") {
    page++;
    getJson(page).then((result) => renderMovies(result.results, page));
  } else if (e.target.classList.contains("genreBtn")) {
    if (genreQuery.includes(e.target.dataset.genre)) {
      genreQuery = genreQuery.filter((query) => query != e.target.dataset.genre);
      e.target.classList.remove("btn-secondary");
      e.target.classList.add("btn-outline-secondary");
    } else {
      genreQuery.push(e.target.dataset.genre);
      e.target.classList.remove("btn-outline-secondary");
      e.target.classList.add("btn-secondary");
    }
    searchBtn.style.visibility = "";
  } else if (e.target.id == "searchBtn") {
    changeUrl();
    getJson().then((result) => renderMovies(result.results));
    e.target.style.visibility = "hidden";
  } else if (e.target.id == "deleteRatingBtn") {
    const mid = document.getElementById("ratingStar").dataset.mid;
    removeRating(mid, email);
  }
});

document.addEventListener("change", (e) => {
  if (e.target.id == "orderBy") {
    sortQuery = e.target.value;
    searchBtn.style.visibility = "";
  } else if (e.target.id == "ratingStar") {
    const status = e.target.dataset.status;
    const mid = e.target.dataset.mid;
    const rating = e.target.value;
    if (status == "reg") {
      addRating(mid, email, rating);
    } else {
      console.log("변경");
      modifyRating(mid, email, rating);
    }
  }
});
