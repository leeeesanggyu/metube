new Vue({
	el: '#app',
	data: {

	},
	methods: {
		withdrawal: function(pk) {
			if(pk != s_user_pk){
				alert("본인만 탈퇴할수 있습니다 !");
				return;
			}
			var URL = "/user/withdrawal/" + pk;

			answer = confirm("회원탈퇴 하시겠습니까 ?");
			if (answer){
				const requestOptions = {
						method: "DELETE",
						headers: {
							"Content-Type": "application/json" 
						},
						body: JSON.stringify({

						})
				};
				fetch(URL, requestOptions)
				.then(res=>res.json())
				.then(json=>{ 
					if(json == true){
						console.log("fetch result: " + json);
						location.href="/";
					}
				})
				.catch(err => console.log(err))
			}
			else{
				return;
			}

		}
	}
});