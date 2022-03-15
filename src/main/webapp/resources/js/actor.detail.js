console.log("vscode", id);
const detailUrl = `https://api.themoviedb.org/3/person/${id}?api_key=6e6b78d7518e1d61e33e6121c3d5e62d&language=ko-KR`;
const creditUrl = `https://api.themoviedb.org/3/person/${id}/combined_credits?api_key=6e6b78d7518e1d61e33e6121c3d5e62d&language=ko-KR`;

const getDetail = async () => {
  try {
    const res = await fetch(detailUrl);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const getCredits = async () => {
  try {
    const res = await fetch(creditUrl);
    const result = await res.json();
    return await result;
  } catch (e) {
    console.log(e);
  }
};

const renderActor = (actor) => {
  document.getElementById("name").innerText = actor.name;
  document.getElementById("bDay").innerText = actor.birthday;
  document.getElementById("age").innerText =
    new Date().getFullYear() - new Date(actor.birthday).getFullYear() - 1;
  document.getElementById("bPlace").innerText = actor.place_of_birth;
  document.getElementById("popularity").innerText = actor.popularity;
  document
    .getElementById("profileImg")
    .setAttribute("src", `https://www.themoviedb.org/t/p/w300_and_h450_bestv2${actor.profile_path}`);
};

const renderCard = (data) => {
  const cardContainer = document.getElementById("cardContainer");
  data.forEach((movie, i) => {
    if (i <= 10) {
      const card = `
      <div class="cards">
      <a href="/movie/detail/${movie.id}">
        <img
        src="https://www.themoviedb.org/t/p/w300_and_h450_bestv2${movie.poster_path}"
        alt=""
        />
      </a>
      <div class="cardContent">
      <div class="cardTitle">${movie.title}</div>
      </div>
      </div>
      `;
      cardContainer.innerHTML = cardContainer.innerHTML + card;
    }
  });
};

document.addEventListener("DOMContentLoaded", () => {
  getDetail().then((result) => renderActor(result));
  getCredits().then((result) => {
    result.cast.sort((a, b) => b.popularity - a.popularity);
    document.getElementById("castCnt").innerText = result.cast.length;
    console.log(result.cast);
    renderCard(
      result.cast.sort((a, b) => b.popularity - a.popularity).filter((each) => each.media_type != "tv")
    );
  });
});
