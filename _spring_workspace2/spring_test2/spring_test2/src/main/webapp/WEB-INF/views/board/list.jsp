<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
    
<jsp:include page="../layout/header.jsp"></jsp:include>

<h1>즐거운 게시판</h1>

<br>
    <table class="table">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">title</th>
      <th scope="col">writer</th>
      <th scope="col">reg_date</th>
      <th scope="col">readCount</th>
    </tr>
  </thead>
  <tbody>
  	<c:forEach items="${list }" var="bvo">
    <tr>
      <th scope="row">${bvo.bno }</th>
      <td><a href="/board/detail?bno=${bvo.bno }">${bvo.title }</a>
      <h6 class="badge bg-secondary">댓글: ${bvo.cmtQty }</h6> 
       <h6 class="badge bg-secondary">파일: ${bvo.hasFile }</h6> 
      </td>
      <td>${bvo.writer }</td>
      <td>${bvo.regAt }</td>
      <td>${bvo.readCount }</td>
    </tr>
    </c:forEach>
  </tbody>
</table>

<script>
	const isDel = `<c:out value="${isDel}" />`;
	if(isDel == 1){
		alert("게시글이 삭제 되었습니다.");
	}
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>
    