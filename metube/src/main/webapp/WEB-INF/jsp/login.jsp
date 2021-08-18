<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<link rel="stylesheet" href="./resources/css/loginForm.css" />
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script>
	<%if (session.getAttribute("name") != null) {%>
		location.href = "/post/list";
	<%}%>
</script>

<style>
#popup01 {
	display: none;
}

#popup01 {
	width: 500px;
	height: 500px;
	position: absolute;
	top: 50%;
	left: 50%;
	margin: -250px 0 0 -250px;
	background-color: #fff;
	z-index: 2;
	border-radius: 15px;
}

.backon {
	content: "";
	width: 100%;
	height: 100%;
	background: #00000054;
	position: fixed;
	top: 0;
	left: 0;
	z-index: 1;
}

.close {
	position: absolute;
	top: -25px;
	right: 0;
	cursor: pointer;
}

.openPopup {
	cursor: pointer;
}
</style>

</head>
<body>
	<script src="/resources/js/accountValidation.js"></script>
	<div id="loginForm">
		<div class="wrap">
			<div class="login">
				<form name="loginForm" id="loginForm" v-on:submit="login">
					<a href="/"><img src="/resources/images/logo.png" width="280"></a>
					<div class="login_id">
						<h4>E-mail</h4>
						<input v-model="email" placeholder="Email@google.com">
					</div>

					<div class="login_pw">
						<h4>Password</h4>
						<input type="password" v-model="password" placeholder="Password">
					</div>
					<br>
					
					<!-- button -->
					<div class="submit">
						<div class="openPopup">
							<button type="button" id="btn-login">login</button>
						</div>
						<br> <br>
						<button type="button" id="btn-signUp" @click="onClickRedirect()">signUp</button>
					</div>
					
					<!-- popup -->
					<div id="popup01">
					    <div class="close">close</div>
					    <br><br><br><br><br><br><br>
					    <!-- simple captcha -->
						<label for="captcha" style="display: block">
							<strong>자동 로그인 방지</strong>
						</label>
						<div style="overflow: hidden">
							<div style="float: left">
								<img id="captcha" title="캡차이미지" src="/captchaImg" alt="캡차이미지" />
								<div id="ccaudio" style="display: none"></div>
							</div>
						</div>
						<div style="padding: 3px">
							<input id="reload" type="button" onclick="javaScript:getImage()"
								value="새로고침" /> <input id="soundOn" type="button"
								onclick="javaScript:audio()" value="음성듣기" />
						</div>
						<div style="padding: 3px">
							<input id="answer" type="text" v-model="captcha">
						</div>
						
						<div class="submit">
							<button type="submit" id="btn-login">login</button>
							<br> <br>
							<button type="button" id="btn-signUp" @click="onClickRedirect()">signUp</button>
						</div>
						
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
<script>

$(document).ready(function( $ ){     
    $(".openPopup").on("click", function(event) {  //팝업오픈 버튼 누르면
    $("#popup01").show();   //팝업 오픈
    $("body").append('<div class="backon"></div>'); //뒷배경 생성
    });
    
    $("body").on("click", function(event) { 
        if(event.target.className == 'close' || event.target.className == 'backon'){
            $("#popup01").hide(); //close버튼 이거나 뒷배경 클릭시 팝업 삭제
              $(".backon").hide();
        }
      });
 
  });



</script>
<script src="/resources/js/login.js"></script>
</html>