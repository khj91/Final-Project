const colors = document.querySelectorAll(".fontColor");

let fontColorVal = document.querySelector("input[name=color]");
document.addEventListener("click", (e) => {
  if (e.target.id == "profileImgSubmitBtn") {
    document.getElementById("profileImgForm").submit();
  } else if (e.target.id == "changeEmailSubmitBtn") {
    console.log("?");
    const nickName = document.getElementById("nickName").value;
    const newNickName = document.getElementById("nickNameMatch").value;
    if (nickName == newNickName) {
      document.getElementById("changeNickNameForm").submit();
    } else {
      alert("닉네임이 일치하지 않습니다.");
      nickName.focus();
    }
  } else if (e.target.id == "changePwdSubmitBtn") {
    const newPwd = document.getElementById("newPwd").value;
    const pwdChk = document.getElementById("pwdChk").value;
    console.log(newPwd, pwdChk);
    if (newPwd == pwdChk) {
      document.getElementById("changePwdForm").submit();
    }
  }
});
Array.from(colors).forEach((color) => {
  if (color.classList.contains("buyAble")) {
    color.addEventListener("click", (e) => {
      console.log(e.target);
      Array.from(colors).forEach((color) => {
        if (color.classList.contains("buyAble")) {
          color.innerText = "";
        }
      });
      if (e.target.classList.contains("buyAble")) {
        e.target.innerText = "selected";
        fontColorVal.value = e.target.style.backgroundColor;
      }
    });
  }
});
