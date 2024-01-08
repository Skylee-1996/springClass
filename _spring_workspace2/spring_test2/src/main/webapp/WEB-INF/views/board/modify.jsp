<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
    <div class="container-md">
     <h1>Board modify Page</h1>
     
  <form action="/board/modify" method="post">
   <div class="mb-3">
  <label for="bno" class="form-label">Bno</label>
  <input type="text" class="form-control" name="bno" id="bno" value="${bvo.bno }" readonly="readonly">
	</div>
   <div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" class="form-control" name="title" id="title" value="${bvo.title }" >
	</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" class="form-control" name="writer" id="writer" value="${bvo.writer }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="reg_date" class="form-label">regAt</label>
  <input type="text" class="form-control" name="reg_date" id="reg_date" value="${bvo.regAt }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" id="content" name="content" rows="3" >${bvo.content }
  </textarea>
 
  
 <a><button type="button" class="btn btn-primary">List</button></a>
<button type="submit" class="btn btn-success" id="regBtn">Modify</button>
</div>
</form>
 </div>
 
<jsp:include page="../layout/footer.jsp"></jsp:include>