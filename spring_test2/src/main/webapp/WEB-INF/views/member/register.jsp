<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<jsp:include page="../layout/header.jsp"></jsp:include>
	
	<!-- email, pwd, nick_name -->
<div class="container-md">
<h1>Board Register Page</h1>
<br>
<form action="/member/register" method="post" > 
<div class="mb-3">
  <label for="e" class="form-label">Email</label>
  <input type="email" class="form-control" name="email" id="e" placeholder="email..">
</div>
<div class="mb-3">
  <label for="p" class="form-label">PassWord</label>
  <input type="password" class="form-control" name="pwd" id="p" placeholder="password">
</div>

<div class="mb-3">
  <label for="n" class="form-label">Nick Name</label>
   <input type="text" class="form-control" name="nickName" id="n">
</div>

<button type="submit" class="btn btn-primary" id="regBtn">Register</button>
</form>
</div>
<jsp:include page="../layout/footer.jsp"></jsp:include>

