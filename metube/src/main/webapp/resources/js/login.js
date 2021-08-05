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
					.then(res => res.json())
					.then(result =>{
						if(result == true){
							location.reload();
						}else {
							alert("아이디와 비밀번호를 확인해주세요");
							return;
						}
					})
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