const API_KEY = "6e6b78d7518e1d61e33e6121c3d5e62d";
let page = 1;
let genreQuery = [];
let sortQuery = "popularity.desc";
let url = `https://api.themoviedb.org/3/discover/tv?api_key=${API_KEY}&lair_date.gte=&air_date.lte=2022-07-24&include_adult=${isAdult}&certification=&certification_country=KR&debug=&first_air_date.gte=&first_air_date.lte=&ott_region=KR&primary_release_date.gte=&primary_release_date.lte=&region=&release_date.gte=&release_date.lte=2022-07-24&show_me=0&sort_by=popularity.desc&vote_average.gte=0&vote_average.lte=10&vote_count.gte=0&with_genres=&with_keywords=&with_networks=&with_origin_country=&with_original_language=&with_ott_monetization_types=&with_ott_providers=${platform}&with_release_type=&with_runtime.gte=0&with_runtime.lte=400&language=ko&page=`;

const searchBtn = document.getElementById("searchBtn");

//post 시에 전송할 tv 데이터 변수
let tvData = {};

// 사용자의 좋아요/평점/리뷰 리스트 받아올 변수
let likedList = null;
let ratedList = null;
let reviewedList = null;
const changeUrl = () => {
  url = `https://api.themoviedb.org/3/discover/tv?api_key=${API_KEY}&lair_date.gte=&air_date.lte=2022-07-24&certification=&certification_country=KR&debug=&first_air_date.gte=&first_air_date.lte=&ott_region=KR&primary_release_date.gte=&primary_release_date.lte=&include_adult=${isAdult}&region=&release_date.gte=&release_date.lte=2022-07-24&show_me=0&sort_by=${sortQuery}&vote_average.gte=0&vote_average.lte=10&vote_count.gte=0&with_genres=${genreQuery.join(
    ","
  )}&with_keywords=&with_networks=&with_origin_country=&with_original_language=&with_ott_monetization_types=&with_ott_providers=${platform}&with_release_type=&with_runtime.gte=0&with_runtime.lte=400&language=ko&page=`;
};

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
    const res = await fetch(`/tv/${email}/liked`);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log();
  }
};

const getUserRatedList = async () => {
  try {
    const res = await fetch(`/tv/${email}/rated`);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const getUserReviewdList = async () => {
  try {
    const res = await fetch(`/tv/${email}/reviewed`);
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

const addLike = async (tvid, title, poster, genres) => {
  try {
    const data = {
      tvvo: { tvid, title, poster, genres },
      lvo: { tvid, email },
    };
    const config = {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(data),
    };
    const res = await fetch(`/tv/like/${tvid}`, config);
    const result = await res.text();
    if (result == 1) {
      alert("즐겨찾기 추가 성공");
      likedList.push({ tvid, title, poster });
      // 즐겨찾기 추가 -> 삭제 구현
      let likeBtn = document.querySelector(`a[data-likeId="${tvid}"]`);
      likeBtn.outerHTML = `<a onclick="removeLike('${tvid}')" class="dropdown-item" href="#" data-likeId="${tvid}">즐겨찾기 해제</a>`;
    }
  } catch (e) {
    alert("즐겨찾기 추가 실패..");
    console.log(e);
  }
};

const removeLike = async (tvid, name, poster, genres) => {
  try {
    const data = {
      tvid,
      email,
    };
    const config = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/tv/like/${tvid}`, config);
    const result = await res.text();
    if (result) {
      alert("즐겨찾기 제거 성공");
      likedList = likedList.filter((each) => each.tvid == tvid);
      // 즐겨찾기 추가 -> 삭제 구현
      let likeBtn = document.querySelector(`a[data-likeId="${tvid}"]`);
      likeBtn.outerHTML = `<a onclick="addLike('${tvid}','${name}','${poster}', '${genres}')" data-likeId="${tvid}" class="dropdown-item" href="#">즐겨찾기 추가</a>`;
    } else {
      alert("즐겨찾기 제거 실패..");
    }
  } catch (e) {
    console.log(e);
  }
};

const setData = (rating, tvid, title, poster, genres) => {
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
  tvData = { tvid, title, poster, genres };
  ratingStar.dataset.tvid = tvid;
};

const addRating = async (tvid, email, rating, genres) => {
  try {
    const data = {
      tvvo: tvData,
      rtvo: { tvid, email, rating },
    };
    const config = {
      headers: { "Content-Type": "application/json" },
      method: "POST",
      body: JSON.stringify(data),
    };
    const res = await fetch(`/tv/rating/${tvid}`, config);
    const result = await res.text();
    let rateBtn = document.querySelector(`a[data-ratingId="${tvid}"]`);

    if (result > 0) {
      // 평점 남기기 -> 평점 수정 구현
      rateBtn.outerHTML = `<a class="dropdown-item" onclick="setData(${rating}, '${tvData.tvid}', '${tvData.title}', '${tvData.poster}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${tvData.id}" data-status="mod" href="#">평점 수정하기</a>`;
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

const modifyRating = async (tvid, email, rating) => {
  try {
    const data = {
      tvid,
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
    const res = await fetch(`/tv/rating/${tvid}`, config);
    const result = await res.text();
    if (result != null || result != "NoData") {
      let rateBtn = document.querySelector(`a[data-ratingId="${tvid}"]`);
      rateBtn.outerHTML = `<a class="dropdown-item" onclick="setData(${rating}, '${tvData.tvid}', '${
        tvData.title
      }', '${tvData.poster}', '${tvData.genres
        .map((each) => each.id)
        .join(",")}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${
        tvData.tvid
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

const removeRating = async (tvid, email) => {
  try {
    const data = {
      tvid,
      email,
    };
    const config = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/tv/rating/${tvid}`, config);
    const result = await res.text();
    if (result != null || result != "NoData") {
      let rateBtn = document.querySelector(`a[data-ratingId="${tvid}"]`);
      rateBtn.outerHTML = `<a class="dropdown-item" onclick="setData(${null}, '${tvData.id}', '${
        tvData.title
      }', '${tvData.poster}', '${tvData.genres
        .map((each) => each.id)
        .join(",")}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${
        tvData.id
      }" data-status="reg" href="#">평점 남기기</a>`;
      alert("평점 삭제 성공");
    } else {
      alert("평점 삭제 실패..");
    }
  } catch (e) {
    console.log(e);
  }
};

const renderTVs = async (json, page = 1) => {
  console.log(json);
  let cardContainer = document.getElementById("cardContainer");

  if (page == 1) {
    cardContainer.innerHTML = "";
  }

  json.forEach((tv) => {
    let isLiked = 0;
    let isRated = null;
    likedList?.forEach((liked) => {
      if (tv.id == liked.tvid) {
        isLiked = 1;
      }
    });
    ratedList?.forEach((rated) => {
      if (tv.id == rated.tvid) {
        isRated = rated.rating;
      }
    });
    if (json == null || json.length == 0) {
      cardContainer.innerHTML = `<div style="text-align: center; padding: 100px 0px;"><h5 class="noDataMsg">정보가 없습니다.</h5></div>`;
      console.log(document.getElementById("moreBtn"));
      document.getElementById("moreBtn").style.display = "none";
    } else if (json.length < 20) {
      document.getElementById("moreBtn").style.display = "none";
    }
    const card = `
    <div class="cards col-lg-3">
      <a href="/tv/detail/${tv.id}" >
        <img id="listImg"
        src="https://www.themoviedb.org/t/p/w440_and_h660_face${tv.poster_path}"
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
                ? `<a onclick="removeLike('${tv.id}','${tv.name}','${tv.poster_path}', '${tv.genre_ids.join(
                    ","
                  )}')" class="dropdown-item" href="#" data-likeId="${tv.id}">즐겨찾기 해제</a>`
                : `<a onclick="addLike('${tv.id}','${tv.name}','${tv.poster_path}', '${tv.genre_ids.join(
                    ","
                  )}')" data-likeId="${tv.id}" class="dropdown-item" href="#">즐겨찾기 추가</a>`
            }
            
            </li>
            <li>
            ${
              isRated != null
                ? `<a class="dropdown-item" onclick="setData(${isRated}, '${tv.id}', '${tv.name}', '${
                    tv.poster_path
                  }', '${tv.genre_ids.join(
                    ","
                  )}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${
                    tv.id
                  }" data-status="mod" href="#">평점 수정하기</a>`
                : `<a class="dropdown-item" onclick="setData(${isRated}, '${tv.id}', '${tv.name}', '${
                    tv.poster_path
                  }', '${tv.genre_ids.join(
                    ","
                  )}')" data-bs-toggle="modal" data-bs-target="#ratingModal" data-ratingId="${
                    tv.id
                  }" data-status="reg" href="#">평점 남기기</a>`
            }
            
            </li>
          </ul>
        </div>
        <div class="cardContent">
          <div class="cardTitle">${tv.name}</div>
          <div class="cardSub">${tv.first_air_date}</div>
        </div>
      </a>
    </div>`;
    cardContainer.innerHTML += card;
  });
};

document.addEventListener("DOMContentLoaded", () => {
  getUserData().then(() => {
    getJson().then((result) => renderTVs(result.results));
  });
});

document.addEventListener("click", (e) => {
  if (e.target.id == "moreBtn") {
    ++page;
    getJson(page).then((result) => renderTVs(result.results, page));
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
    getJson().then((result) => renderTVs(result.results));
    e.target.style.visibility = "hidden";
  } else if (e.target.id == "deleteRatingBtn") {
    const tvid = document.getElementById("ratingStar").dataset.tvid;
    removeRating(tvid, email);
  }
});

document.addEventListener("change", (e) => {
  if (e.target.id == "orderBy") {
    sortQuery = e.target.value;
    searchBtn.style.visibility = "";
  } else if (e.target.id == "ratingStar") {
    const status = e.target.dataset.status;
    const tvid = e.target.dataset.tvid;
    const rating = e.target.value;
    if (status == "reg") {
      addRating(tvid, email, rating);
    } else {
      console.log("변경");
      modifyRating(tvid, email, rating);
    }
  }
});
