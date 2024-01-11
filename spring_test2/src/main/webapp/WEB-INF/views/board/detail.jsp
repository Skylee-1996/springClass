<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<jsp:include page="../layout/header.jsp"></jsp:include>
<c:set value="${bdto.bvo }" var="bvo"></c:set>
    <div class="container-md">
     <h1>Board Detail Page</h1>
   <div class="mb-3">
  <label for="bno" class="form-label">Bno</label>
  <input type="text" class="form-control" name="bno" id="bno" value="${bvo.bno }" readonly="readonly">
	</div>
   <div class="mb-3">
  <label for="title" class="form-label">Title</label>
  <input type="text" class="form-control" name="title" id="title" value="${bvo.title }" readonly="readonly">
	</div>
<div class="mb-3">
  <label for="writer" class="form-label">Writer</label>
  <input type="text" class="form-control" name="writer" id="writer" value="${bvo.writer }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="reg_date" class="form-label">reg_date</label>
  <span class="badge text-bg-primary">조회수${bvo.readCount }</span>
  <input type="text" class="form-control" name="reg_date" id="reg_date" value="${bvo.regAt }" readonly="readonly">
</div>
<div class="mb-3">
  <label for="content" class="form-label">Content</label>
  <textarea class="form-control" id="content" name="content" rows="3" readonly="readonly">${bvo.content }
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
 		 				<a href="/upload/${fvo.saveDir }/${fvo.uuid }_${fvo.fileName}" download="${fvo.fileName }"><img alt="" src="/upload/${fvo.saveDir }/${fvo.uuid }_th_${fvo.fileName}"></a>
 		 			</div>
 		 		</c:when>
 		 		<c:otherwise>
 		 			<div>
 		 				<!--일반파일 표시할 아이콘-->
 		 				<a href="/upload/${fvo.saveDir }/${fvo.uuid }_${fvo.fileName}" download="${fvo.fileName }">
	 		 				<svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-briefcase" viewBox="0 0 16 16">
							 <path d="M6.5 1A1.5 1.5 0 0 0 5 2.5V3H1.5A1.5 1.5 0 0 0 0 4.5v8A1.5 1.5 0 0 0 1.5 14h13a1.5 1.5 0 0 0 1.5-1.5v-8A1.5 1.5 0 0 0 14.5 3H11v-.5A1.5 1.5 0 0 0 9.5 1h-3zm0 1h3a.5.5 0 0 1 .5.5V3H6v-.5a.5.5 0 0 1 .5-.5zm1.886 6.914L15 7.151V12.5a.5.5 0 0 1-.5.5h-13a.5.5 0 0 1-.5-.5V7.15l6.614 1.764a1.5 1.5 0 0 0 .772 0zM1.5 4h13a.5.5 0 0 1 .5.5v1.616L8.129 7.948a.5.5 0 0 1-.258 0L1 6.116V4.5a.5.5 0 0 1 .5-.5z"/>
							</svg>
						</a>
<span class="badge text-bg-success">${fvo.fileSize}Byte</span>
 		 			</div>
 		 		</c:otherwise>
 		 	</c:choose>
 		 	<div class="ms-2 me-auto">
 		 		<div class="fw-bold">${fvo.fileName }</div>
 		 		
 		 	</div>
 		 </li>
 		
 		</c:forEach>
  </ul>
</div>
 <a href="/board/list"><button type="button" class="btn btn-primary">List</button></a>
 <a href="/board/modify?bno=${bvo.bno }"><button type="button" class="btn btn-success">Modify</button></a>
 <a href="/board/remove?bno=${bvo.bno }"><button type="button" class="btn btn-danger">delete</button></a>


    
   </div>
   
   
  
 <br>
 <hr>
 <!--댓글등록라인-->
 <div class="input-group mb-3">
 	<span class="input-group-text" id="cmtWriter">${bvo.writer }</span>
 	<input type="text"  class="form-control" id="cmtText" placeholder="Add Comment...">
 	<button class="btn btn-outline-secondary" type="button" id="cmtPostBtn">댓글 등록</button>
 </div>
 
 <br>
 
</div>
 <!--댓글표시라인-->
 <div class="accordion" id="accordionExample">
  <div class="accordion-item">
    <h2 class="accordion-header">
      <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
       no. cno, writer, reg_date
      </button>
    </h2>
    <div id="collapseOne" class="accordion-collapse collapse show" data-bs-parent="#accordionExample">
      <div class="accordion-body">
        <strong>Add Comment..</strong>
      </div>
    </div>
  </div>
</div>
<br>

<!-- 댓글 더보기 버튼  -->
<div>
	<button type="button" id="more" data-page="1" class="btn btn-dark" style="visibility:hidden">More+</button>
</div> 
   
   
   <!-- 모달창  -->
 <div class="modal" id="myModal" tabindex="-1">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title">Writer</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        	<div class="input-group mb-3">
        		<input type="text"  class="form-control" id="cmtTextMod">
        		<button type="button" class="btn btn-primary" id="cmtModBtn">Post</button>
        	</div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>

<script type="text/javascript">
	let bnoVal = `<c:out value="${bdto.bvo.bno}" />`;
	console.log(bnoVal);
</script>
   
<script src="/resources/JS/boardComment.js"></script>
	<script type="text/javascript">
	spreadCommentList(bnoVal);
	</script>
<jsp:include page="../layout/footer.jsp"></jsp:include>