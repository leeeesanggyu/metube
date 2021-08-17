const like = new Vue({
	el: '#like',
	methods: {
		add_like: function() {
			const requestOptions = {
					method: "POST",
					headers: {
						"Content-Type": "application/json" 
					},
					body: JSON.stringify({
						post_pk: post_pk,
						user_pk: s_user_pk
					})
			};
			fetch("/like/add", requestOptions)
			.then(res=>res.json())
			.then(json=>{ 
				if(json == true){
					socket.send(
						"like," 
						+ s_user_pk + "," +user_name + ","
						+ p_user_pk + "," + user_email + "," 
						+ post_title + "," + post_pk
 					);
					location.reload();
				}
			})
			.catch(err => console.log(err))
		},
		delete_like: function() {
			const requestOptions = {
					method: "DELETE",
					headers: {
						"Content-Type": "application/json" 
					},
					body: JSON.stringify({
						post_pk: post_pk,
						user_pk: s_user_pk
					})
			};
			fetch("/like/del", requestOptions)
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