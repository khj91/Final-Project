let trendCardContainer = document.getElementById("trendCardContainer");

const getTrendData = async (key) => {
  try {
    const res = await fetch(
      `https://api.themoviedb.org/3/trending/movie/${key}?language=ko-KR&api_key=6e6b78d7518e1d61e33e6121c3d5e62d`
    );
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const renderCards = (data) => {
  console.log(data);
  trendCardContainer.innerHTML = "";
  data.results.forEach((movie) => {
    const card = `
          <div class="movieCard">
            <a href="/movie/detail/${movie.id}">
           <img
                src="https://themoviedb.org/t/p/w220_and_h330_face${movie.poster_path}"
                alt=""
              /> 
            </a>
            <div class="cardDesc">
              <div class="cardTitle"><a href="/movie/detail/${movie.id}">${movie.title}</a></div>

              <div class="cardSub">${movie.release_date}</div>
            </div>
          </div>
    `;
    trendCardContainer.innerHTML = trendCardContainer.innerHTML + card;
  });
};

document.addEventListener("click", (e) => {
  if (e.target.id == "todayTrend") {
    getTrendData("day").then((result) => {
      renderCards(result);
      e.target.style.backgroundColor = "#082546";
      e.target.style.color = "#FFF";
      document.getElementById("weekTrend").style.backgroundColor = "#FFF";
      document.getElementById("weekTrend").style.color = "black";
    });
  } else if (e.target.id == "weekTrend") {
    getTrendData("week").then((result) => {
      renderCards(result);
      e.target.style.backgroundColor = "#082546";
      e.target.style.color = "#fff";
      document.getElementById("todayTrend").style.backgroundColor = "#FFF";
      document.getElementById("todayTrend").style.color = "black";
    });
  }
});

document.addEventListener("DOMContentLoaded", (e) => {
  getTrendData("week").then((result) => {
    renderCards(result);
  });
});
