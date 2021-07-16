<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>metube</title>
<link rel="stylesheet" href="/resources/css/loginForm.css" />
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
			document.login_form.action="/user/check.do"
			document.login_form.submit();
		}),
		$("#btn-signUp").click(function(){
			location.href="/user/goSignUp.do"
		})
	})
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
		<form name="login_form" method="post" class="loginForm">
			<a href="login.do"><img src="/resources/images/logo.png" width="280"></a>
			<div class="login_id">
			
			
				<h4>E-mail</h4>
				<input name="email" id="email" placeholder="Email">
	        </div>
			<div class="login_pw">
	            <h4>Password</h4>
	            <input type="password" name="password" id="password" placeholder="Password">
	        </div>
			<div class="submit">
				<button type="button" id="btn-login">login</button><br><br>
				<button type="button" id="btn-signUp">signUp</button>
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


</body>
</html>