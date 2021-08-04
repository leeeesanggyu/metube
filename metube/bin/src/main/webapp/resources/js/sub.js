const sub = new Vue({
	el: '#sub',
	data: {

	},
	methods: {
		sub_add: function(p_user_pk) {
			const requestOptions = {
					method: "POST",
					headers: {
						"Content-Type": "application/json" 
					},
					body: JSON.stringify({
						p_user_pk: p_user_pk,
						c_user_pk: s_user_pk
					})
			};
			fetch("/sub/add", requestOptions)
			.then(res=>res.json())
			.then(json=>{ 
				if(json == true){
					location.reload();
				}
			})
			.catch(err => console.log(err))
		},
		sub_del: function(p_user_pk) {
			const requestOptions = {
					method: "DELETE",
					headers: {
						"Content-Type": "application/json" 
					},
					body: JSON.stringify({
						p_user_pk: p_user_pk,
						c_user_pk: s_user_pk
					})
			};
			fetch("/sub/del", requestOptions)
			.then(res=>res.json())
			.then(json=>{ 
				if(json == true){
					location.reload();
				}
			})
			.catch(err => console.log(err))
		}
	}
});