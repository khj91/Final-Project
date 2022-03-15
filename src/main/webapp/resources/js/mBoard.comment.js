document.addEventListener("DOMContentLoaded", () => {
  getCmtList(mbIdVal);
});

async function postCmtToServ(cmtData) {
  try {
    const url = "/mComment/post";
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
      mbId: mbIdVal,
      writer: document.getElementById("cmtWriter").value,
      content: cmtText.value,
    };
    postCmtToServ(cmtData).then((result) => {
      if (parseInt(result)) {
        alert("댓글 등록 성공");
      }
      getCmtList(cmtData.mbId);
    });
  }
});

async function printCmtFromServ(mbId, page) {
  try {
    const resp = await fetch("/mComment/" + mbId + "/" + page);
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
    const url = "/user/profileImg/" + email;
    const config = {
      method: "post",
      headers: {
        "Content-Type": "application/json; charset=utf-8",
      },
      body: JSON.stringify(email),
    };
    const resp = await fetch(url, config);
    const result = await resp.json();
    return await result;
  } catch (error) {
    console.log(error);
  }
}

function getCmtList(mbId, page = 1) {
  printCmtFromServ(mbId, page).then((result) => {
    const div = document.getElementById("cmtListArea");
    if (result.cmtListM.length) {
      div.innerHTML = "";

      for (let mcvo of result.cmtListM) {
        console.log(mcvo);
        printProfileImg(mcvo.writer).then((result) => {
          console.log(result);
          let li = `<div data-mcid="${mcvo.mcId}" class="cmtBox">`;
          li += `<div class="d-flex p-2">
                    <div class="flex-shrink-0 bg-light"><img class="rounded-circle imgSize" src="/fileUpload/${result.profileImg}"></div>`;
          li += `<div class="ms-3 cmtContBox">
                    <div class="fw-bold cmtWir">${result.nickName}</div>
                    <div class="cmtDate">${mcvo.modAt}</div>
                    <div class="cmtCont">${mcvo.content}</div></div>`;
          if (mcvo.writer == authEmail) {
            li += `<div style="width: 70px; float: right"><button type="button" class="mod bg-light" data-bs-toggle="modal" data-bs-target="#myModal">
                        <i class="fas mod fa-pencil-alt"></i></button>
                        <button type="button" class="del bg-light"><i class="far del fa-trash-alt"></i></button></div>`;
          }
          li += `</div></div>`;
          div.innerHTML += li;
        });
      }
      printPage(result.prev, result.startPage, result.pgvo, result.endPage, result.next);
    } else {
      let li = `<div>댓글 없음</div>`;
      div.innerHTML += li;
    }
  });
}

async function eraseCmtAtServ(mcId) {
  try {
    const url = "/mComment/" + mcId;
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
    const url = "/mComment/" + cmtDataMod.mcId;
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
    let mcIdVal = cmtBox.dataset.mcid;
    eraseCmtAtServ(mcIdVal).then((result) => {
      alert("댓글 삭제 성공");
      getCmtList(mcIdVal);
      cmtBox.remove();
    });
  } else if (e.target.classList.contains("mod")) {
    let cmtBox = e.target.closest(".cmtBox");
    let cmtCont = cmtBox.querySelector(".cmtCont").innerText;
    console.log(cmtCont);
    document.getElementById("cmtTextMod").value = cmtCont;
    document.getElementById("cmtModBtn").setAttribute("data-mcid", cmtBox.dataset.mcid);
  } else if (e.target.id == "cmtModBtn") {
    let cmtDataMod = {
      mcId: e.target.dataset.mcid,
      content: document.getElementById("cmtTextMod").value,
    };
    console.log(cmtDataMod);
    editCmtToServ(cmtDataMod).then((result) => {
      if (parseInt(result)) {
        document.querySelector(".btn-close").click();
      }
      getCmtList(mbIdVal);
    });
  } else if (e.target.classList.contains("page-link")) {
    e.preventDefault();
    getCmtList(mbIdVal, e.target.getAttribute("href"));
  }
});
