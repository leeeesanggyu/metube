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
			fetch("/user/", requestOptions)
			.then(res => {
				location.href="/";
			})
			.catch(err => console.log(err))

		}
	}
});