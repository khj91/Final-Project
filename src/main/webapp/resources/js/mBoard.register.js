document.getElementById('likeBtn').addEventListener('click',()=>{
    document.getElementById('likeHate').value = 1;
    console.log(document.getElementById('likeHate').value);
    document.getElementById('likeBtn').classList.add("clickLHBtn");
    document.getElementById('hateBtn').classList.remove("clickLHBtn");
    document.getElementById('regBtn').disabled = false;
});
document.getElementById('hateBtn').addEventListener('click',()=>{
    document.getElementById('likeHate').value = 0;
    console.log(document.getElementById('likeHate').value);
    document.getElementById('hateBtn').classList.add("clickLHBtn");
    document.getElementById('likeBtn').classList.remove("clickLHBtn");
    document.getElementById('regBtn').disabled = false;
});