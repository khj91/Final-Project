const m1gen = Object.entries(movieGenres[0])[0][0];
const m1cnt = Object.entries(movieGenres[0])[0][1];
const m2gen = Object.entries(movieGenres[1])[0][0];
const m2cnt = Object.entries(movieGenres[1])[0][1];
const m3gen = Object.entries(movieGenres[2])[0][0];
const m3cnt = Object.entries(movieGenres[2])[0][1];
const t1gen = Object.entries(tvGenres[0])[0][0];
const t1cnt = Object.entries(tvGenres[0])[0][1];
const t2gen = Object.entries(tvGenres[1])[0][0];
const t2cnt = Object.entries(tvGenres[1])[0][1];
const t3gen = Object.entries(tvGenres[2])[0][0];
const t3cnt = Object.entries(tvGenres[2])[0][1];

const mp = `<p><span class="genreName">${m1gen}</span>: <span class="genreCnt"><i>${m1cnt}편</i></span></p><p><span class="genreName">${m2gen}</span>: <span class="genreCnt"><i>${m2cnt}편</i></span></p><p><span class="genreName">${m3gen}</span>: <span class="genreCnt"><i>${m3cnt}편</i></span></p>`;
const tp = `<p><span class="genreName">${t1gen}</span>: <span class="genreCnt"><i>${t1cnt}편</i></span></p><p><span class="genreName">${t2gen}</span>: <span class="genreCnt"><i>${t2cnt}편</i></span></p><p><span class="genreName">${t3gen}</span>: <span class="genreCnt"><i>${t3cnt}편</i></span></p>`;

document.querySelector(".favoriteMGenre").innerHTML += mp;
document.querySelector(".favoriteTGenre").innerHTML += tp;
