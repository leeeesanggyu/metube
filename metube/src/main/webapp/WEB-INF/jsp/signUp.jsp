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
<script>
	//정규표현식을 정의합니다. 
	function email_check( email ) { 
		var regex=/([\w-\.]+)@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.)|(([\w-]+\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\]?)$/; 
		return (email != '' && email != 'undefined' && regex.test(email)); 
	}

	$(document).ready(function(){
		// name 속성이 'email'인 input 이 focus를 잃었을때 처리한다. 
		$("input[name='email']").blur(function(){ var email = $(this).val(); 
		// 값을 입력안한경우는 아예 체크를 하지 않는다. 
			if( email == '' || email == 'undefined') return; 
			// 이메일 유효성 검사 
			if(! email_check(email) ) { 
				alert('잘못된 형식의 이메일 주소입니다.'); 
				return false; 
			} 
		});
	})
</script>
</head>
<body>
	<div class="wrap">
	    <div class="login">
			<form name="signUp_form" id="signUp_form" v-on:submit="signUp">
				<a href="login.do"><img src="/resources/images/logo.png" width="280"></a>
				
				<div class="sign_id">
					<h4>E-mail</h4> 
					<input v-model="email" name="email" placeholder="example@naver.com" ><br><br>
				</div>
				<div class="sign_pw">
					<h4>Password</h4>
					 <input type="password" v-model="password" placeholder="************">
					<small>(4자리 ~ 12자리)</small><br><br>
				</div>
				<div class="sign_id">
					<h4>Name</h4>
					<input v-model="name" placeholder="이상규"><br><br>
				</div>
				<div class="submit">
					<button type="submit">Sign-Up</button>
				</div>
			</form>
		</div>
	</div>
</body>
<script>
	new Vue({
	    el: '#signUp_form',
	    data: {
	    	email: '',
	    	password: '',
	    	name: ''
	    },
	    methods: {
	    	signUp: function(e) {
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
	   			if(this.name == ""){
	   				alert("name insert");
	   				$(this.name).focus();
	   				return;
	   			}
	   			if(this.name.length > 6 || this.name.length < 2){
	                   alert("이름의 글자 수를 확인해주세요");
	                   $(this.name).focus();
	                   return;
	            }
	   			if(this.password.length > 12 || this.password.length < 4){
	                   alert("비밀번호 글자 수를 확인해주세요");
	                   $(this.password).focus();
	                   return;
	            }
	    		
	            const requestOptions = {
	                    method: "POST",
	                    headers: {
	                 	   "Content-Type": "application/json" 
	                    },
	                    body: JSON.stringify({
	                    	email: this.email, 
	                    	password: this.password,
	                    	name: this.name,
	                    	role: this.role
	                    })
	            };
	            fetch("/user/sign", requestOptions)
	   				.then(res => {
	   					location.href="/";
	   				})
	 				.catch(err => console.log(err))
	            
	        }
	    }
	});
</script>
</html>