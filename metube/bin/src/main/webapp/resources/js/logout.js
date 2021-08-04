const logout = new Vue({
	el: '#logout',
	methods: {
		logout: function() {    		 
			answer = confirm("정말 로그아웃 하시겠습니까 ?");
			if (answer){
				const requestOptions = {
						method: "GET",
						headers: {
							"Content-Type": "application/json" 
						}
				};
				fetch("/user/logout", requestOptions)
				.then(res=>{ location.href="/"; })
				.catch(err => console.log(err))
			}else{
				return;
			}
		}
	}
});