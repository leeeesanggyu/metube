<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<link rel="stylesheet" href="/resources/css/loginForm.css" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script src="/resources/js/signUp_available.js"></script>
</head>
<body>
	<div class="wrap">
		<div class="login">
			<form name="signUp_form" id="signUp_form" v-on:submit="signUp">
				<a href="login"><img src="/resources/images/logo.png"width="280"></a>
				<div class="sign_id">
					<h4>E-mail</h4>
					<input v-model="email" name="email" placeholder="example@naver.com"><br>
					<br>
				</div>
				<div class="sign_pw">
					<h4>Password</h4>
					<input type="password" v-model="password"
						placeholder="************"> <small>(4자리 ~ 12자리)</small><br>
					<br>
				</div>
				<div class="sign_id">
					<h4>Name</h4>
					<input v-model="name" placeholder="이상규"><br>
					<br>
				</div>
				<div class="submit">
					<button type="submit">Sign-Up</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script src="/resources/js/signUp.js"></script>
</html>