<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>metube</title>
<link rel="stylesheet" href="/resources/css/loginForm.css" />
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script>
<%
if(session.getAttribute("name") != null) {
%>
	location.href="/post/list.do";
<%
}
%>
</script>
</head>
<body>
<div class="wrap">
    <div class="login">
    
		<form name="loginForm" id="loginForm" v-on:submit="login">
			<a href="login.do"><img src="/resources/images/logo.png" width="280"></a>
			
			<div class="login_id">
				<h4>E-mail</h4>
				<input v-model="email" placeholder="Email@google.com">
			 </div>
			  
			<div class="login_pw">
			    <h4>Password</h4>
			    <input type="password" v-model="password" placeholder="Password">
			</div>
			  
			<div class="submit">
			    <button type="submit" id="btn-login">login</button><br><br>
			    <button type="button" id="btn-signUp" @click="onClickRedirect()">signUp</button>
			</div>
        </form>

		<c:if test="${msg == 'fail'}">
			<div style="color: red">
				login fail
			</div>
		</c:if>
		<c:if test="${msg == 'logout'}">
			<div style="color: red">
				logout success 
			</div>
		</c:if>
	</div>
</div>
<script src="/resources/js/login.js"></script>
</body>
</html>