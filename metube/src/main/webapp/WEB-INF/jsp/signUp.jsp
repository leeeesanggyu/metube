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
		$("#btn-signUp").click(function(){
			var email=$("#email").val();
			var password=$("#password").val();
			var name=$("#name").val();
			var role=$("#role").val();
			
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
			if(name == ""){
				alert("name insert");
				$("#name").focus();
				return;
			}
			document.signUp_form.action="/signUp.do"
			document.signUp_form.submit();
		})
	})
</script>
</head>
<body>
<center>
<br><br><br><br><br><br><br>
	<h2>Sign Up</h2>
	
	<form name="signUp_form" method="post">
		email   : <input name="email" id="email"><br><br>
		&nbsp;&nbsp;&nbsp;
		pw: <input type="password" name="password" id="password"><br><br>
		&nbsp;&nbsp;&nbsp;
		name: <input name="name" id="name"><br><br>
		
		<button type="button" id="btn-signUp">Sign Up</button>
	</form>
	
</center>
</body>
</html>