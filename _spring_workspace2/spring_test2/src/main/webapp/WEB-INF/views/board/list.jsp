<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    
    
<jsp:include page="../layout/header.jsp"></jsp:include>
<div class="container-md">
<h1>즐거운 게시판</h1>
<!-- 검색라인  -->
<div>
<form action="/board/list" method="get">
<div class="input-group">
	<select name="type"  class="form-select"  aria-label="Example select with button addon" id="inputGroupSelect04">
		<option ${ph.pgvo.type==null ? 'selected' : '' }>Choose...</option>
		<option value="t" ${ph.pgvo.type eq 't' ? 'selected' : '' }>Title</option>
		<option value="w" ${ph.pgvo.type eq 'w' ? 'selected' : '' }>Writer</option>
		<option value="c" ${ph.pgvo.type eq 'c' ? 'selected' : '' }>Content</option>
		<option value="tc" ${ph.pgvo.type eq 'tc' ? 'selected' : '' }>Title&Content</option>
		<option value="tw" ${ph.pgvo.type eq 'tw' ? 'selected' : '' }>Title&Writer</option>
		<option value="wc" ${ph.pgvo.type eq 'wc' ? 'selected' : '' }>Writer&Content</option>
		<option value="twc" ${ph.pgvo.type eq 'twc' ? 'selected' : '' }>All</option>
	</select>
	<input type="text" style="border: 1px solid black; margin-left:30px;" name="keyword" value="${ph.pgvo.keyword }" placeholder="Search...">
	<input type="hidden" name="pageNo" value="1">
	<input type="hidden" name="qty" value="10">
	<button type="submit" id="search" class="btn btn-outline-secondary">Search 
	<span class="position-absolute top-0 start-100 translate-middle badge rounded-pill bg-danger">
    ${ph.totalCount}
    <span class="visually-hidden">unread messages</span>
  </span></button>
 

</div>
</form>
</div>


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

<!--paging line만들기  -->
<nav aria-label="Page navigation example">
  <ul class="pagination">
  <!--이전  -->
   <c:if test="${ph.prev }">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.startPage-1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
    </li>
    </c:if>
    <!--페이지번호  -->
   <c:forEach begin="${ph.startPage }" end="${ph.endPage }" var="i">
    <li class="page-item"><a class="page-link" href="/board/list?pageNo=${i }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}">${i }</a></li>
    </c:forEach>
    <!-- 다음 -->
    <c:if test="${ph.next }">
    <li class="page-item">
      <a class="page-link" href="/board/list?pageNo=${ph.endPage+1 }&qty=${ph.pgvo.qty}&type=${ph.pgvo.type}&keyword=${ph.pgvo.keyword}" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    </c:if>
    <li class="page-item">
      <a class="page-link" href="/board/list">
        <span aria-hidden="true">전체보기</span>
      </a>
    </li>
  </ul>
</nav>
</div>
<script>
	const isDel = `<c:out value="${isDel}" />`;
	if(isDel == 1){
		alert("게시글이 삭제 되었습니다.");
	}
</script>

<jsp:include page="../layout/footer.jsp"></jsp:include>
    