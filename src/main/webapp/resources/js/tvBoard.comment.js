document.addEventListener("DOMContentLoaded", () => {
  getCmtList(tvbIdVal);
});

async function postCmtToServ(cmtData) {
  try {
    const url = "/tvComment/post";
    const config = {
      method: "post",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(cmtData),
    };
    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}

document.getElementById("cmtPostBtn").addEventListener("click", () => {
  const cmtText = document.getElementById("cmtText");
  if (cmtText.value == null || cmtText.value == "") {
    alert("댓글 내용을 입력해주세요");
    cmtText.focus();
    return false;
  } else {
    let cmtData = {
      tvbId: tvbIdVal,
      writer: document.getElementById("cmtWriter").value,
      content: cmtText.value,
    };
    postCmtToServ(cmtData).then((result) => {
      if (parseInt(result)) {
        alert("댓글 등록 성공");
      }
      getCmtList(cmtData.tvbId);
    });
  }
});

async function printCmtFromServ(tvbId, page) {
  try {
    const resp = await fetch("/tvComment/" + tvbId + "/" + page);
    const pageHandler = await resp.json();
    return await pageHandler;
  } catch (error) {
    console.log(error);
  }
}

function printPage(prev, startPage, pgvo, endPage, next) {
  let pgn = document.getElementById("cmtPaging");
  pgn.innerHTML = "";
  let ul = `<ul class="pagination justify-content-center">`;
  if (prev) {
    ul += `<li class="page-item"><a class="page-link" href="${startPage - 1}">이전</a></li>`;
  }
  for (let i = startPage; i <= endPage; i++) {
    ul += `<li class="page-item ${pgvo.pageNo == i ? "active" : ""}" aria-current="page">`;
    ul += `<a class="page-link" href="${i}>${i}</a></li>`;
  }
  if (next) {
    ul += `<li class="page-item"><a class="page-link" href="${endPage + 1}">다음</a></li>`;
  }
  ul += `</ul>`;
  pgn.innerHTML = ul;
}

async function printProfileImg(email) {
  try {
    const config = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(email),
    };
    const resp = await fetch("/user/profileImg/" + email, config);
    const result = await resp.json();
    return await result;
  } catch (error) {
    console.log(error);
  }
}

function getCmtList(tvbId, page = 1) {
  printCmtFromServ(tvbId, page).then((result) => {
    const div = document.getElementById("cmtListArea");
    if (result.cmtListTv.length) {
      div.innerHTML = "";
      for (let tvcvo of result.cmtListTv) {
        printProfileImg(tvcvo.writer).then((result) => {
          console.log(result);
          let li = `<div data-tvcid="${tvcvo.tvcId}" class="cmtBox">`;
          li += `<div class="d-flex p-2">
                                <div class="flex-shrink-0 bg-light"><img class="rounded-circle imgSize" src="/fileUpload/${result.profileImg}"></div>`;
          li += `<div class="ms-3 cmtContBox">
                                <div class="fw-bold cmtWir">${tvcvo.writer}</div>
                                <div class="cmtDate">${tvcvo.modAt}</div>
                                <div class="cmtCont">${tvcvo.content}</div></div>`;
          if (tvcvo.writer == authEmail) {
            li += `<div style="width: 70px; float: right"><button type="button" class="mod bg-light" data-bs-toggle="modal" data-bs-target="#myModal">
                                <i class="fas fa-pencil-alt mod"></i></button>
                                <button type="button" class="del bg-light"><i class="far fa-trash-alt del"></i></button></div>`;
          }
          li += `</div></div>`;
          div.innerHTML += li;
        });
      }
      printPage(result.prev, result.startPage, result.pgvo, result.endPage, result.next);
    } else if (result.cmtListTv.length) {
      let li = `<div>댓글 없음</div>`;
      div.innerHTML += li;
    }
  });
}

async function eraseCmtAtServ(tvcId) {
  try {
    const url = "/tvComment/" + tvcId;
    const config = {
      method: "delete",
    };
    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}

async function editCmtToServ(cmtDataMod) {
  try {
    const url = "/tvComment/" + cmtDataMod.tvcId;
    const config = {
      method: "put",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(cmtDataMod),
    };
    const resp = await fetch(url, config);
    const result = await resp.text();
    return result;
  } catch (error) {
    console.log(error);
  }
}

document.addEventListener("click", (e) => {
  if (e.target.classList.contains("del")) {
    let cmtBox = e.target.closest(".cmtBox");
    let tvcIdVal = cmtBox.dataset.tvcid;
    eraseCmtAtServ(tvcIdVal).then((result) => {
      alert("댓글 삭제 성공");
      getCmtList(tvcIdVal);
      cmtBox.remove();
    });
  } else if (e.target.classList.contains("mod")) {
    let cmtBox = e.target.closest(".cmtBox");
    let cmtCont = cmtBox.querySelector(".cmtCont").innerText;
    console.log(cmtCont);
    document.getElementById("cmtTextMod").value = cmtCont;
    document.getElementById("cmtModBtn").setAttribute("data-tvcid", cmtBox.dataset.tvcid);
  } else if (e.target.id == "cmtModBtn") {
    let cmtDataMod = {
      tvcId: e.target.dataset.tvcid,
      content: document.getElementById("cmtTextMod").value,
    };
    console.log(cmtDataMod);
    editCmtToServ(cmtDataMod).then((result) => {
      if (parseInt(result)) {
        document.querySelector(".btn-close").click();
      }
      getCmtList(tvbIdVal);
    });
  } else if (e.target.classList.contains("page-link")) {
    e.preventDefault();
    getCmtList(tvbIdVal, e.target.getAttribute("href"));
  }
});
