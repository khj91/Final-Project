<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="../common/header.jsp" />
<jsp:include page="../common/nav.jsp" />
<style>
 tr{
 vertical-align: middle;
 }
</style>
<%-- <ul>
	<c:forEach items="${list }" var="user">
		<li><span style="color: ${user.fontColor}">${user.email }</span> : ${user.grade }</li>
	</c:forEach>

</ul>

 --%>
<table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Email</th>
      <th scope="col">Name</th>
      <th scope="col">NickName</th>
      <th scope="col">LastLogin</th>
      <th scope="col">Grade</th>
      <th scope="col">Modify</th>
    </tr>
  </thead>
  <tbody>
  <c:forEach items="${list }" var="user" varStatus="n">
    <tr>
      <th scope="row">${n.count }</th>
      <td>${user.email }</td>
      <td>${user.name }</td>
      <td>${user.nickName }</td>
      <td>${user.lastLogin }</td>
      <td>
      	<input  type="number" name="grade" value="${user.grade }">
      </td>
      <td>
      	<button type="button" data-email="${user.email }" class="btn btn-primary">mod</button>
      </td>
    </tr>
   </c:forEach>
  </tbody>
</table>
<script>
const modifyGrade = async (email, grade) => {
	  try {
		  console.log(email, grade);
	    const config ={
	      headers:{ "Content-Type": "application/json"},
	      method: "POST",
	      body: JSON.stringify({email, grade})
	    }
	    const res = await fetch("/user/modGrade", config);
	    const result = res.text();
	    return result;
	  } catch (e) {
	    console.log(e);
	  }
	}

	document.addEventListener("click", (e) => {
	  if (e.target.classList.contains("btn")) {
	    modifyGrade(e.target.dataset.email ,e.target.closest("tr").querySelector("input").value).then(result => {
	      if(result > 0){
	        alert("정보 수정 완료");
	      }
	    })
	  }
	});

</script>