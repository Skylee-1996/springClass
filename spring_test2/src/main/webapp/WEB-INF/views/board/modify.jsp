<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<c:set value="${bdto.bvo }" var="bvo"></c:set>
    <div class="container-md">
     <h1>Board modify Page</h1>
     
  <form action="/board/modify" method="post" enctype="multipart/form-data">
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
  
  <!-- file line  -->
  <c:set value="${bdto.flist }" var="flist"></c:set>
 <div class="col-12">
  <label for="f" class="form-label"></label>
 	<ul class="list-group list-group-flush">
 		<c:forEach items="${flist }" var="fvo">
 		 <li class="list-group-item">
 		 	<c:choose>
 		 		<c:when test="${fvo.fileType == 1 }"> 
 		 			<div>
 		 				<img alt="" src="/upload/${fvo.saveDir }/${fvo.uuid }_th_${fvo.fileName}">
 		 			</div>
 		 		</c:when>
 		 		<c:otherwise>
 		 			<div>
 		 	<!--일반파일 표시할 아이콘-->
		 		 		<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-briefcase" viewBox="0 0 16 16">
						 <path d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"/>
						</svg>
						<span class="badge text-bg-success">${fvo.fileSize}Byte</span>
 		 			</div>
 		 		</c:otherwise>
 		 	</c:choose>
 		 	<div class="ms-2 me-auto" style="display:flex">
 		 		<div class="fw-bold">${fvo.fileName }</div> &nbsp
 		 		<button type="button" data-uuid="${fvo.uuid }" class="btn btn-outline-danger btn-sm file-x">x</button>
 		 	</div>
 		 </li>
 		
 		</c:forEach>
  </ul>
 <!-- 파일 등록 라인-->
 <!-- file 입력 라인 추가 -->
<div class="mb-3">
  <label for="file" class="form-label">fileUpload</label>
  <input type="file" class="form-control" name="files" id="files" multiple="multiple" style="display : none">
    <!-- 파일버튼 트리거 사용하기 위해서 주는 버튼  -->
    <button type="button" class="btn btn-primary" id="trigger">FileUpload</button>
</div>
<!-- 파일목록 표시라인  -->
<div class="mb-3" id="fileZone">
	
</div>
  
 <a><button type="button" class="btn btn-primary">List</button></a>
<button type="submit" class="btn btn-success" id="regBtn">Modify</button>
</div>
</form>
 </div>
 <script src="/resources/JS/boardRegister.js"></script>
 <script src="/resources/JS/boardModify.js"></script>
<jsp:include page="../layout/footer.jsp"></jsp:include>