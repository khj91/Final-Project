const API_KEY = "6e6b78d7518e1d61e33e6121c3d5e62d";
let cardContainer = document.getElementById("cardContainer");
// let data = [];

let currentRating = null;
const getDetail = async (id, platform) => {
  try {
    const url = `https://api.themoviedb.org/3/${platform}/${id}?api_key=${API_KEY}&language=ko-KR`;
    const res = await fetch(url);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const renderCard = async () => {
  cardContainer.innerHTML = "";
  if (platform == "tv") {
    console.log(tvsData);

    tvsData.forEach((each) => {
      console.log(each.title);
      getDetail(each.tvid, platform).then((result) => {
        let tv = `
      <div class="movie" data-id="${result.id}">
        <div class="imageDiv">
        ${
          result.poster_path
            ? `<img src="https://www.themoviedb.org/t/p/w150_and_h225_bestv2${result.poster_path}" />`
            : `<img src="https://www.themoviedb.org/t/p/w150_and_h225_bestv2" style="width:150;height:225"/>`
        }
        </div>
        <div class="contentDiv">
          <div class="titleDiv">
            <div class="rating">${result.vote_average * 10}</div>
            <div class="titleText">
              <a href="/tv/detail/${result.id}">${result.name}</a>
              <span>${result.first_air_date}</span>
            </div>
          </div>
          <div class="descDiv">
            ${result.overview}
          </div>
          <div class="btnDiv">
            <ul style="list-style-type: none; padding: 0">
              <li>
                ${
                  each.rating
                    ? `<a class="btn ratingBtn headerRateBtn btn-sm headerBtn" style="background-color: #25e525; color: white;font-weight:bold;border:none;"  data-bs-toggle="modal" data-bs-target="#ratingModal" data-id="${each.tvid}" data-email="${email}">${each.rating}</a>`
                    : `<a class="btn ratingBtn headerRateBtn btn-sm headerBtn"  data-bs-toggle="modal" data-bs-target="#ratingModal" data-id="${each.tvid}" data-email="${email}"><i class="bi bi-star-fill"></i></a>`
                }
                평점
              </li>
              <li>
                <a style="${
                  each.isLiked
                    ? "color: white; border: 1px solid #dd54be; background-color:#dd54be;"
                    : "color: #9c9a9a;border: 1px solid #9c9a9a;"
                }" class="btn likeBtn btn-outline-secondary btn-sm ${
          each.isLiked ? "clicked" : ""
        }"><i class="bi bi-heart-fill"></i></a>즐겨찾기
              </li>
              <li>
                <a class="btn delBtn btn-outline-secondary btn-sm"><i class="bi bi-x"></i></a>제거
              </li>
            </ul>
          </div>
        </div>
      </div>`;
        cardContainer.innerHTML = cardContainer.innerHTML + tv;
      });
    });
  } else {
    console.log(moviesData);

    moviesData.forEach((each) => {
      console.log(each.title);
      getDetail(each.mid, platform).then((result) => {
        let movie = `
      <div class="movie" data-id="${result.id}">
        <div class="imageDiv">
        ${
          result.poster_path
            ? `<img src="https://www.themoviedb.org/t/p/w150_and_h225_bestv2${result.poster_path}" />`
            : `<img src="https://www.themoviedb.org/t/p/w150_and_h225_bestv2" style="width:150;height:225"/>`
        }
        </div>
        <div class="contentDiv">
          <div class="titleDiv">
            <div class="rating">${result.vote_average * 10}</div>
            <div class="titleText">
              <a href="/movie/detail/${result.id}">${result.title}</a>
              <span>${result.release_date}</span>
            </div>
          </div>
          <div class="descDiv">
            ${result.overview}
          </div>
          <div class="btnDiv">
            <ul style="list-style-type: none; padding: 0">
              <li>
              ${
                each.rating
                  ? `<a class="btn ratingBtn headerRateBtn btn-sm headerBtn" style="background-color: #25e525; color: white;font-weight:bold;border:none;"  data-bs-toggle="modal" data-bs-target="#ratingModal" data-id="${each.mid}" data-email="${email}">${each.rating}</a>`
                  : `<a class="btn ratingBtn headerRateBtn btn-sm headerBtn"  data-bs-toggle="modal" data-bs-target="#ratingModal" data-id="${each.mid}" data-email="${email}"><i class="bi bi-star-fill"></i></a>`
              }
                평점
              </li>
              <li>
                <a style="${
                  each.isLiked
                    ? "color: white; border: 1px solid #dd54be; background-color:#dd54be;"
                    : "color: #9c9a9a;border: 1px solid #9c9a9a;"
                }" class="btn likeBtn btn-outline-secondary btn-sm ${
          each.isLiked ? "clicked" : ""
        }"><i class="bi bi-heart-fill"></i></a>즐겨찾기
              </li>
              <li>
                <a class="btn delBtn btn-outline-secondary btn-sm"><i class="bi bi-x"></i></a>제거
              </li>
            </ul>
          </div>
        </div>
      </div>`;

        cardContainer.innerHTML = cardContainer.innerHTML + movie;
      });
    });
  }
};

const postRating = async (email, rating, id) => {
  try {
    const data = {
      mvvo: null,
      tvvo: null,
      rtvo: { mid: id, tvid: id, email, rating },
    };
    const config = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const url = `/${platform}/rating/${id}`;
    const res = await fetch(url, config);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const modifyRating = async (email, rating, id) => {
  try {
    const data = {
      mid: id,
      tvid: id,
      email,
      rating,
    };
    const config = {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/${platform}/rating/${id}`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const removeRating = async (email, id) => {
  try {
    const data = {
      mid: id,
      tvid: id,
      email,
    };
    const config = {
      headers: { "Content-Type": "application/json" },
      method: "DELETE",
      body: JSON.stringify(data),
    };
    const res = await fetch(`/${platform}/rating/${id}`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const drawStar = (target) => {
  document.querySelector(`.star span`).style.width = `${target.value * 10}%`;
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

document.getElementById("ratingStar").addEventListener("change", (e) => {
  let id = e.target.dataset.id;
  let email = e.target.dataset.email;
  console.log("changed", e.target.value);
  // 평점 등록
  if (currentRating == null) {
    postRating(email, e.target.value, id).then((result) => {
      if (parseFloat(result) > 0) {
        alert("평점 등록 성공, 1포인트 획득!");
        gainPoints(email, 1);
        moviesData.map((movie) => {
          if (movie.mid == id) {
            movie.rating = e.target.value;
          }
        });
        tvsData.map((tv) => {
          if (tv.tvid == id) {
            tv.rating = e.target.value;
          }
        });

        document.querySelector(
          `a[data-id="${id}"]`
        ).outerHTML = `<a class="btn ratingBtn headerRateBtn btn-sm headerBtn" style="background-color: #25e525; color: white;font-weight:bold;border:none;"  data-bs-toggle="modal" data-bs-target="#ratingModal" data-id="${id}" data-email="${email}">${e.target.value}</a>`;
        // 평균 평점 result로 변하게.
      } else {
        alert("평점 등록 실패..");
        currentRating = null;
      }
    });
  } else {
    // 평점 수정
    modifyRating(email, e.target.value, id).then((result) => {
      if (parseFloat(result) > 0) {
        alert("평점 수정 성공");
        // 평균 평점 result로 변하게

        moviesData.map((movie) => {
          if (movie.mid == id) {
            movie.rating = e.target.value;
          }
        });
        tvsData.map((tv) => {
          if (tv.tvid == id) {
            tv.rating = e.target.value;
          }
        });

        document.querySelector(`a[data-id="${id}"]`).innerText = e.target.value;
      } else {
        alert("평점 수정 실패..");
        currentRating = null;
      }
    });
  }
  document.getElementById("modalCloseBtn").click();
  console.log(e.target.value);
});

const addLike = async (platform, id) => {
  try {
    const data = {
      mvvo: null,
      lvo: {
        mid: id,
        tvid: id,
        email,
      },
    };
    const config = {
      headers: {
        "Content-Type": "application/json",
      },
      method: "POST",
      body: JSON.stringify(data),
    };
    const url = `/${platform}/like/${id}`;
    const res = await fetch(url, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const removeLike = async (platform, id) => {
  try {
    const data = {
      mid: id,
      tvid: id,
      email,
    };
    const config = {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    };
    const url = `/${platform}/like/${id}`;
    const res = await fetch(url, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const remove = async (data, url) => {
  try {
    const config = {
      headers: {
        "Content-Type": "application/json",
      },
      method: "DELETE",
      body: JSON.stringify(data),
    };
    const res = await fetch(url, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

document.addEventListener("click", (e) => {
  let movie = e.target.closest(".movie");

  if (e.target.classList.contains("likeBtn") && !e.target.classList.contains("clicked")) {
    const id = movie.dataset.id;

    // 즐겨찾기 추가
    addLike(platform, id).then((result) => {
      if (parseInt(result) > 0) {
        console.log("즐겨찾기 추가 성공");
        e.target.classList.remove("btn-outline-secondary");
        e.target.classList.add("clicked");
        e.target.setAttribute("style", "color: white; border: 1px solid #dd54be; background-color:#dd54be;");
        alert("즐겨찾기 추가 성공");
      } else {
        console.log("즐겨찾기 추가 실패");
      }
    });
  } else if (e.target.classList.contains("likeBtn") && e.target.classList.contains("clicked")) {
    const id = movie.dataset.id;

    console.log(id);
    //즐겨찾기 삭제
    removeLike(platform, id).then((result) => {
      if (parseInt(result) > 0) {
        console.log("즐겨찾기 취소 성공");
        e.target.classList.add("btn-outline-secondary");
        e.target.classList.remove("clicked");
        e.target.setAttribute("style", "color: #9c9a9a;border: 1px solid #9c9a9a;");
        alert("즐겨찾기 취소 성공");
        if (list == "liked") {
          movie.remove();
        }
      } else {
        console.log("즐겨찾기 취소 실패");
      }
    });
  } else if (e.target.classList.contains("delBtn")) {
    const id = movie.dataset.id;

    console.log(id);
    console.log(list);
    console.log(platform);
    let url = "";
    let data = { mid: id, tvid: id, email };

    switch (list) {
      case "liked":
        url = `/${platform}/like/${id}`;
        break;
      case "rated":
        url = `/${platform}/rating/${id}`;
        break;
      case "reviewed":
        url = `/${platform}/review/${id}`;
        data = { mid: id, tvid: id, writer: email };
        break;
      default:
        break;
    }
    remove(data, url).then((result) => {
      // 두번째는 삭제후 평균 레이팅 값이 올때, 세번째는 삭제후 레이팅 값이 없을때 NoData가 옴.
      if (parseInt(result) > 0 || parseFloat(result) > 0 || result == "NoData") {
        console.log("제거 성공");
        alert("제거 성공");
        movie.remove();
      } else {
        console.log("제거 실패");
      }
    });
  } else if (e.target.classList.contains("ratingBtn")) {
    const id = movie.dataset.id;
    let ratingStar = document.getElementById("ratingStar");
    ratingStar.dataset.id = id;
    ratingStar.dataset.email = email;
    const currentRating_ = e.target.innerText;
    if (currentRating_ != null && currentRating_ != "") {
      currentRating = parseInt(currentRating_);
      document.getElementById("ratingStar").value = currentRating;
      document.querySelector(`.star span`).style.width = `${currentRating * 10}%`;
    } else {
      currentRating = null;
      document.getElementById("ratingStar").value = 0;
      document.querySelector(`.star span`).style.width = `${0}%`;
    }
  } else if (e.target.id == "deleteRatingBtn") {
    const id = document.getElementById("ratingStar").dataset.id;
    const email = document.getElementById("ratingStar").dataset.email;
    removeRating(email, id).then((result) => {
      if (result != null || result != "NoData") {
        alert("평점 삭제 성공");

        moviesData.map((movie) => {
          if (movie.mid == id) {
            movie.rating = null;
          }
        });
        tvsData.map((tv) => {
          if (tv.tvid == id) {
            tv.rating = null;
          }
        });

        // 평점 항이면 삭제
        if (list != null && list == "rated") {
          document.querySelector(`div[data-id="${id}"]`).remove();
        }
      }
    });
    document.getElementById("modalCloseBtn").click();
  }
});

document.addEventListener("DOMContentLoaded", () => {
  if (list == "rated") {
    renderCard();
  } else {
    renderCard();
  }
});
