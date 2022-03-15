let fontColorName = document.querySelector("input[name=sname]");
let fontColorVal = document.querySelector("input[name=value]");
let category = document.querySelector("input[name=category]");
let email = document.querySelector("input[name=email]");
let sname = document.querySelector("input[name=sname]");
let price = document.querySelector("input[name=price]");
let value = document.querySelector("input[name=value]");

const colors = document.querySelectorAll(".fontColor");

Array.from(colors).forEach((color) => {
  if (color.classList.contains("buyAble")) {
    color.addEventListener("click", (e) => {
      Array.from(colors).forEach((color) => {
        if (color.classList.contains("buyAble")) {
          color.innerText = "";
        }
      });
      if (e.target.classList.contains("buyAble")) {
        fontColorName.value = e.target.id;
        e.target.innerText = "selected";
        fontColorVal.value = e.target.style.backgroundColor;
      }
    });
  }
});

const buyColor = async () => {
  try {
    const data = {
      category: category.value,
      email: email.value,
      sname: sname.value,
      price: price.value,
      value: value.value,
    };
    const config = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(data),
    };
    const res = await fetch(`/purchase/register`, config);
    const result = await res.text();
    return result;
  } catch (e) {
    console.log(e);
  }
};

document.getElementById("buyBtn").addEventListener("click", (e) => {
  if (fontColorVal.value.length > 0) {
    buyColor().then((result) => {
      if (result > 0) {
        alert(`구매 완료!`);
        location.reload();
      } else if (result == 0) {
        alert(`구매 실패. 다시 시도해주세요.`);
      } else {
        alert(`포인트가 부족합니다!`);
      }
    });
  } else {
    alert("폰트 컬러를 골라주세요.");
  }
});
