new Vue({
	el: '#app',
	data: {

	},
	methods: {
		lock: function(user_pk, lock) {
			var URL = "/user/lock";

			answer = confirm("해당 계정을 잠그시겠습니까? (잠금상태일경우 해제)");
			if (answer){
				const requestOptions = {
						method: "POST",
						headers: {
							"Content-Type": "application/json" 
						},
						body: JSON.stringify({
							pk: user_pk,
							lock: lock
						})
				};
				fetch(URL, requestOptions)
				.then(res=>res.json())
				.then(json=>{ 
					if(json == true){
						console.log("fetch result: " + json);
						location.reload();
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