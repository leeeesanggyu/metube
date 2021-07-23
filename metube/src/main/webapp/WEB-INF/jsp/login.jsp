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
<%
	if(session.getAttribute("name") != null) {
%>
		location.href="/post/list";
<%
	}
%>
	/*매번 랜덤값을 파라미터로 전달하는 이유 : 
	IE의 경우 매번 다른 임의 값을 전달하지 않으면 '새로고침' 
	클릭해도 정상 호출되지 않아 이미지가 변경되지 않는 문제가 발생된다*/
	function audio(){ 
		var rand = Math.random(); 
		var uAgent = navigator.userAgent;
		var soundUrl = '${ctx}/captchaAudio.do?rand='+rand; 
		if(uAgent.indexOf('Trident')>-1 || uAgent.indexOf('MISE')>-1){ /*IE 경우 */ 
			audioPlayer(soundUrl); 
		} else if (!!document.createElement('audio').canPlayType){ /*Chrome 경우 */ 
				try { new Audio(soundUrl).play(); 
			} catch (e) { 
				audioPlayer(soundUrl); 
			} 
		} else { 
			window.open(soundUrl,'','width=1,height=1'); 
		} 
	} 
	
	function getImage() { 
			var rand = Math.random(); 
			var url = '${ctx}/captchaImg.do?rand='+rand; document.querySelector('img').setAttribute('src', url); 
	}
	
	function audioPlayer(objUrl){ 
		document.querySelector('#ccaudio').innerHTML = '<bgsoun src="' +objUrl +'">'; 
	}
</script>
</head>
<body>
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
					<!-- simple captcha -->
					<label for="captcha" style="display:block"><strong>자동 로그인 방지</strong></label> 
					<div style="overflow:hidden"> 
						<div style="float:left"> 
							<img title="캡차이미지" src="/captchaImg.do" alt="캡차이미지"/> 
							<div id="ccaudio" style="display:none"></div> 
						</div> 
					</div> 
					<div style="padding:3px"> 
						<input id="reload" type="button" onclick="javaScript:getImage()" value="새로고침"/> 
						<input id="soundOn" type="button" onclick="javaScript:audio()" value="음성듣기"/> 
					</div> 
					<div style="padding:3px">
						<input id="answer" type="text" v-model="captcha"> 
					</div>
					
					<div class="submit">
					    <button type="submit" id="btn-login">login</button><br><br>
					    <button type="button" id="btn-signUp" @click="onClickRedirect()">signUp</button>
					</div>
		        </form>
			</div>
		</div>
	</div>
</body>
<script>
	const loginForm = new Vue({
	    el: '#loginForm',
	    data: {
	        email: '',
	        password: '',
	        captcha : ''
	    },
	    methods: {  
	    	onClickRedirect: function() {
	    		location.href="user/goSignUp"
	    	},
	        login: function(e) {
	        	e.preventDefault();
	        	
	            if(this.email == ""){
	               alert("email insert");
	               $(this.email).focus();
	               return;
	            }
	            if(this.password == ""){
	               alert("password insert");
	               $(this.password).focus();
	               return;
	            }
	            if(this.captcha == ""){
	                alert("captcha insert");
	                $(this.captcha).focus();
	                return;
	             }
	         		
				const captchOptions = {
	                   method: "POST",
	                   headers: {
	                	   "Content-Type": "application/json" 
	                   },
	                   body: this.captcha
	            };
	            fetch("/chkAnswer.do", captchOptions)
	  				.then(res=>{
	  					if(res.status == 200) {
		  					const requestOptions = {
		  		                   method: "POST",
		  		                   headers: {
		  		                	   "Content-Type": "application/json" 
		  		                   },
		  		                   body: JSON.stringify({
		  		                      email: this.email, password: this.password 
		  		                   })
		  		            };
		  		            fetch("/user/check", requestOptions)
		  		  				.then(res=> res.json())
		  		  				.then(data=> location.reload())
		  		  				.catch(err => console.log(err))
	  					} else {
	  						alert("보안문자를 다시 입력해주세요 !");
	  						return;
	  					}
	  				})
					.catch(err => console.log(err))	
			}
	    }
	});
</script>
</html>