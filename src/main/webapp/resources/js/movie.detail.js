const API_KEY = `6e6b78d7518e1d61e33e6121c3d5e62d`;
const poster = document.getElementById("poster");
let movieTitle = document.getElementById("movieTitle");
let tagline = document.querySelector(".tLine");
let rating = document.getElementById("rt");
let overview = document.querySelector(".overviewSub");
let header = document.querySelector("header");
let currentRating = null; // 좋아요 기록 유무 임시 저장
let movieData = {};
let reviewcnt = 0;
let userData = {
  userReview: null,
  isLiked: false,
  rating: 0,
  reviewList: null,
  likeCount: 0,
  avgRating: 0,
};
const getMovieDetail = async () => {
  try {
    const url = `https://api.themoviedb.org/3/movie/${detailId}?api_key=${API_KEY}&language=ko-KR`;
    const res = await fetch(url);
    const result = res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const getCredits = async () => {
  try {
    const url = `https://api.themoviedb.org/3/movie/${detailId}/credits?api_key=${API_KEY}&language=ko-KR`;
    const res = await fetch(url);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const getWatchProviders = async () => {
  try {
    const url = `https://api.themoviedb.org/3/movie/${detailId}/watch/providers?api_key=${API_KEY}`;
    const res = await fetch(url);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const getMovieData = async () => {
  try {
    const url = `/movie/${detailId}`;
    const res = await fetch(url);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};
const renderDetail = (json) => {
  console.log("movie detail: ", json);
  poster.setAttribute("src", `https://www.themoviedb.org/t/p/w300_and_h450_face${json.poster_path}`);
  movieTitle.innerText = json.title + `(${json.release_date.slice(0, 4)})`;
  document.getElementById("dbRating").innerText = json.vote_average;
  tagline.innerText = json.tagline;
  overview.innerText = json.overview;

  header.style.backgroundImage = `url('https://www.themoviedb.org/t/p/w1920_and_h800_multi_faces${json.backdrop_path}')`;
};

const renderCredits = (json) => {
  console.log("credits: ", json);
  let castDiv = document.getElementById("castDiv");

  json.cast.forEach((each) => {
    const card = `
    <div class="castCard">
				<div class="castImg">
				<a href="/actor/${each.id}">
				${
          each.profile_path != null
            ? `<img  src="https://themoviedb.org/t/p/w138_and_h175_face${each.profile_path}">`
            : `<div style="height: 175px;width: 138px;display:flex;justify-content:center;align-items:center;color:black;background-colol:#e6e6e6;" >no image</>`
        }
				</a>
				</div>
				<div class="castDesc">
					<div class="realNameText"><a href="">${each.name}</a></div>
					<div class="castNameText">${each.character}</div>
				</div>
			</div>
    `;
    castDiv.innerHTML = castDiv.innerHTML + card;
  });
};

const renderReview = (list, writtenReview) => {
  let reviewWrapper = document.getElementById("reviewWrapper");
  if (list.length > 0) {
    reviewWrapper.innerHTML = "";
  } else {
    return;
  }

  list
    .sort((a, b) => new Date(a.regAt) - new Date(b.regAt))
    .forEach((review) => {
      const date = new Date(review.regAt);
      const li = `
    <li class="list-group-item review"  >
      <div class="reviewLeft">
        <div class="reviewImgDiv">
          <a href="/user/${review.writer}" ><img src="/fileUpload/${
        review.profileImg
      }" alt="" class="reviewProfileImg" /></a>
        </div>
        <span class="reviewContent" style="color: ${review.fontColor}">
          ${review.content}
        </span>
      </div>
      <div class="reviewRight">
        <div class="reviewRegAt">
          ${date.getMonth()}월 ${date.getDate()}, ${date.getFullYear()}
        
        </div>
        <div class="reviewWriterDiv">작성자: <a href="/user/${review.writer}" class="reviewWriter">${
        review.writer
      }</a></div>
      </div>
    </li>
    `;
      if (loggedInEmail != review.writer) {
        reviewWrapper.innerHTML = reviewWrapper.innerHTML + li;
      }
    });
  if (writtenReview != null) {
    addToList(writtenReview);
  }
};

const addToList = (data) => {
  let reviewWrapper = document.getElementById("reviewWrapper");
  if (userData.rvList.length == 0) {
    reviewWrapper.innerHTML = "";
  }

  const li = `
    <li class="list-group-item review userReview" style="background-color:#e6e6e6;" >
      <div class="reviewLeft">
        <div class="reviewImgDiv">
          <a href="/user/${data.writer}" ><img src="/fileUpload/${
    data.profileImg
  }" alt="" class="reviewProfileImg" /></a>
        </div>
        <span class="reviewContent" style="color: ${data.fontColor}">
          ${data.content}
        </span>
      </div>
      <div class="reviewRight">
        <div class="reviewRegAt">
          ${new Date(data.regAt).getMonth()}월 ${new Date(data.regAt).getDate()}, ${new Date(
    data.regAt
  ).getFullYear()}
        <div class="revBtnDiv"> <button class="btn btn-sm revModBtn" >mod</button><button class="btn btn-sm revDelBtn">del</button></div>
        </div>
        <div class="reviewWriterDiv">작성자: <a href="/user/${data.writer}" class="reviewWriter">${
    data.writer
  }</a></div>
      </div>
    </li>`;
  reviewWrapper.innerHTML = li + reviewWrapper.innerHTML;
};

const getList = async (query) => {
  try {
    const url = `https://api.themoviedb.org/3/search/movie?api_key=${API_KEY}&language=ko-KR&query=${query}&page=1&include_adult=false&region=KR`;
    const res = await fetch(url);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const postReview = async (content, writer) => {
  try {
    const data = {
      mvvo: movieData,
      rvvo: { mid: detailId, content, writer },
    };

    const config = {
      method: "post",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(data),
    };
    const url = `/movie/review/${detailId}`;
    const res = await fetch(url, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const modifyReview = async (content, writer) => {
  try {
    const data = {
      content,
      writer,
      mid: detailId,
    };
    const config = {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const url = `/movie/review/${detailId}`;
    const res = await fetch(url, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const removeReview = async (writer) => {
  try {
    const data = {
      mid: detailId,
      writer,
    };
    const config = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/movie/review/${detailId}`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log();
  }
};

const addLike = async (email) => {
  try {
    const data = {
      mvvo: movieData,
      lvo: { mid: detailId, email },
    };
    const config = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const url = `/movie/like/${detailId}`;
    const res = await fetch(url, config);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const removeLike = async (email) => {
  try {
    const data = {
      mid: detailId,
      email,
    };
    const config = {
      method: "DELETE",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const url = `/movie/like/${detailId}`;
    const res = await fetch(url, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const postRating = async (email, rating) => {
  try {
    const data = {
      mvvo: movieData,
      rtvo: { mid: detailId, email, rating },
    };
    const config = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const url = `/movie/rating/${detailId}`;
    const res = await fetch(url, config);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const modifyRating = async (email, rating) => {
  try {
    const data = {
      mid: detailId,
      email,
      rating,
    };
    const config = {
      method: "PATCH",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/movie/rating/${detailId}`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

const removeRating = async (email) => {
  try {
    const data = {
      mid: detailId,
      email,
    };
    const config = {
      headers: { "Content-Type": "application/json" },
      method: "DELETE",
      body: JSON.stringify(data),
    };
    const res = await fetch(`/movie/rating/${detailId}`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
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

document.addEventListener("click", (e) => {
  if (e.target.classList.contains("reviewRegBtn")) {
    const content = document.querySelector("[name=content]").value;
    const writer = document.querySelector("[name=writer]").value;
    if (content.trim().length == 0) {
      alert("내용을 입력하세요.");
      return;
    }
    console.log(writer, content, profileImg, Date.now());
    postReview(content, writer).then((result) => {
      console.log(result);
      if (parseInt(result) > 0) {
        alert("리뷰 등록 성공, 2포인트 획득!");
        // console.log(userData);
        gainPoints(writer, 2);
        reviewCnt++;
        addToList({ writer, content, profileImg, regAt: Date.now() });
        document.querySelector(".reviewRegBtn").disabled = true;
        document.querySelector("[name=content]").value = "이미 리뷰를 작성하셨습니다.";
        document.querySelector("[name=content]").readOnly = true;
      }
    });
  } else if (e.target.classList.contains("headerLikeBtn") && !e.target.classList.contains("clicked")) {
    const email = e.target.dataset.email;
    addLike(email).then((result) => {
      console.log(result);
      if (parseInt(result) > 0) {
        alert("즐겨찾기 추가 성공");
        e.target.classList.add("clicked");
        e.target.setAttribute("style", "color: red;");
      }
    });
  } else if (e.target.classList.contains("headerLikeBtn") && e.target.classList.contains("clicked")) {
    const email = e.target.dataset.email;
    removeLike(email).then((result) => {
      console.log(result);
      if (parseInt(result) > 0) {
        alert("즐겨찾기 삭제 성공");
        e.target.classList.remove("clicked");
        e.target.setAttribute("style", "color: #fff;");
      }
    });
  } else if (e.target.id == "deleteRatingBtn") {
    const email = document.querySelector(".headerLikeBtn").dataset.email;
    removeRating(email).then((result) => {
      console.log(result);
      if (parseFloat(result) > 0 || result == "NoData") {
        alert("평점 삭제 성공");
        rating.innerText = result;
        currentRating = null;
        document.querySelector(`.star span`).style.width = 0;
        document.querySelector(".headerRateBtn").style.color = "#fff";
        // 삭제후 별점 초기화 안됨;;
      } else {
        alert("평점 삭제 실패..");
      }
    });
  } else if (e.target.classList.contains("revModBtn")) {
    console.log(e.target);
    const review = e.target.closest("li");
    // const btn = document.querySelector(".reviewRegBtn");
    const btn = document.querySelector(".input-group").querySelector("button");
    document.getElementById("revInput").value = review.querySelector(".reviewContent").innerText;
    btn.disabled = false;
    btn.classList.add("reviewModBtn");
    btn.classList.remove("reviewRegBtn");
    btn.innerText = "mod";
    document.getElementById("revInput").readOnly = false;
  } else if (e.target.classList.contains("revDelBtn")) {
    removeReview(e.target.closest("li").querySelector(".reviewWriter").innerText).then((result) => {
      if (result > 0) {
        document.querySelector(".userReview").remove();
        const btn = document.querySelector(".input-group").querySelector("button");
        // btn.disabled = false;
        btn.innerText = "add";
        btn.classList.add("reviewRegBtn");
        btn.classList.remove("reviewModBtn");
        document.getElementById("revInput").value = "";
        document.getElementById("revInput").placeholder = "리뷰를 남겨주세요!";
        btn.disabled = false;
        if (--reviewCnt == 0) {
          document.getElementById("reviewWrapper").innerHTML =
            "<h4>등록된 리뷰가 없습니다. 리뷰를 등록해보세요!</h4>";
        }
        document.getElementById("revInput").readOnly = false;
        alert("리뷰를 삭제했습니다.");
      }
    });
  } else if (e.target.classList.contains("reviewModBtn")) {
    console.log("mod");
    const inputDiv = e.target.closest("div");
    console.log(inputDiv.querySelector("[name=content]").value);
    console.log(inputDiv.querySelector("[name=writer]").value);
    modifyReview(
      inputDiv.querySelector("[name=content]").value,
      inputDiv.querySelector("[name=writer]").value
    ).then((result) => {
      if (result.length > 0) {
        document.querySelector(".userReview").remove();
        addToList({
          writer: inputDiv.querySelector("[name=writer]").value,
          content: inputDiv.querySelector("[name=content]").value,
          profileImg,
          regAt: Date.now(),
        });
        inputDiv.querySelector("[name=content]").value = "이미 리뷰를 작성하셨습니다.";
        e.target.disabled = true;
        inputDiv.querySelector("[name=content]").readOnly = true;
        alert("리뷰가 수정되었습니다.");
      }
    });
  }
});

const drawStar = (target) => {
  document.querySelector(`.star span`).style.width = `${target.value * 10}%`;
};

document.getElementById("ratingStar").addEventListener("change", (e) => {
  const email = document.querySelector(".headerLikeBtn").dataset.email;
  // 평점 등록
  if (currentRating == null) {
    postRating(email, e.target.value).then((result) => {
      if (parseFloat(result) > 0) {
        alert("평점 등록 성공, 1포인트 획득!");
        // 평균 평점 result로 변하게.
        document.querySelector(".headerRateBtn").style.color = "#ffc107";
        gainPoints(email, 1);
        rating.innerText = result;
      } else {
        alert("평점 등록 실패..");
      }
    });
  } else {
    // 평점 수정
    modifyRating(email, e.target.value).then((result) => {
      if (parseFloat(result) > 0) {
        alert("평점 수정 성공");
        // 평균 평점 result로 변하게
        rating.innerText = result;
      } else {
        alert("평점 수정 실패..");
      }
    });
  }
  document.getElementById("modalCloseBtn").click();
  console.log(e.target.value);
});

document.addEventListener("DOMContentLoaded", () => {
  getMovieDetail().then((result) => {
    tvData = { tvid: result.id, title: result.name, poster: result.poster_path };
    movieData = {
      mid: result.id,
      title: result.title,
      poster: result.poster_path,
      genres: result.genres.map((each) => each.id).join(","),
    }; // 평점, 즐겨찾기, 리뷰 남길때 같이 보내줄 데이터
    renderDetail(result);
  });
  getMovieData().then((result) => {
    userData = result;
    console.log(userData);
    reviewCnt = userData.rvList.length;
    renderReview(userData.rvList, userData.rvdto);
    if (userData.avgRating != null) {
      rating.innerText = parseFloat(userData.avgRating).toFixed(1);
    }
    if (userData?.isLiked) {
      //좋아요 있을시 색 변하게 하기
      let headerLikeBtn = document.querySelector(".headerLikeBtn");
      headerLikeBtn.setAttribute("style", "color: red;");
      headerLikeBtn.classList.add("clicked");
    }
    if (userData?.rating != null) {
      // 좋아요 기록 있을시 해당 별점 임시저장
      document.querySelector(".headerRateBtn").setAttribute("style", "color:#ffc107;");
      document.getElementById("ratingStar").value = userData.rating;
      document.querySelector(`.star span`).style.width = `${userData.rating * 10}%`;
      currentRating = userData.rating;
    }
    if (userData?.rvdto != null) {
      document.querySelector(".reviewRegBtn").disabled = true;
      document.getElementById("revInput").placeholder = "이미 리뷰를 작성하셨습니다.";
    }
  });
  getCredits().then((result) => renderCredits(result));
  getWatchProviders().then((result) => {
    console.log("watch providers: ", result.results.KR);
    // movieTitle.innerHtml =
    //   movieTitle.innerHTML +
    //   `<img id="watchProvider" src="https://www.themoviedb.org/t/p/original/${result.results.KR.flatrate[0].logo_path}" />`;
    //	https://www.themoviedb.org/t/p/original/dgPueyEdOwpQ10fjuhL2WYFQwQs.jpg
  });
});
