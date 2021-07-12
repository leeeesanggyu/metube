<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>metube</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn-login").click(function(){
			var email=$("#email").val();
			var password=$("#password").val();
			
			if(email == ""){
				alert("email insert");
				$("#email").focus();
				return;
			}
			if(password == ""){
				alert("password insert");
				$("#password").focus();
				return;
			}
			document.login_form.action="/loginCheck.do"
			document.login_form.submit();
		}),
		$("#btn-signUp").click(function(){
			location.href="/signUpPage.do"
		})
	})
</script>
</head>
<body>
<center>
<br><br><h1>METUBE</h1>
<br><br><br>
	<h2>Login</h2>
	
	<form name="login_form" method="post">
		email   : <input name="email" id="email"><br><br>
		&nbsp;&nbsp;&nbsp;
		pw: <input type="password" name="password" id="password"><br><br>
		
		<button type="button" id="btn-login">login</button>
		<button type="button" id="btn-signUp">signUp</button>
	</form>
	<c:if test="${msg == 'fail'}">
		<div style="color: red">
			login fail
		</div>
	</c:if>
	<c:if test="${msg == 'logout'}">
		<div style="color: red">
			success logout
		</div>
	</c:if>
</center>
</body>
</html>