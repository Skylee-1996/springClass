<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<jsp:include page="layout/header.jsp"></jsp:include>
<h1>
	Hello world!  
</h1>
<div>
<c:if test="${ses.id ne null }">
<p>	${ses.id }님 안녕하세요
  <span class="badge text-bg-primary">마지막로그아웃시간${ses.last_login }</span></p>
</c:if>
</div>
<jsp:include page="layout/footer.jsp"></jsp:include>
