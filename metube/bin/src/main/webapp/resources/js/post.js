const post = new Vue({
	el: '#post',
	data: {
		pk: post_pk,
		user_pk: p_user_pk,
		content: ''
	},
	methods: {
		goModifyPost: function() {
			if(this.user_pk != s_user_pk) {
				alert("자신이 작성한 글만 수정할 수 있습니다 !! ");
				return;
			}
			location.href="/post/goModify/" + post_pk;
		},
		deletePost: function() {
			if(user_role == 3){
				if(post.kind == 3){
					answer = confirm("정말 삭제하시겠습니까?");
					if(answer){
						const requestOptions = {
								method: "DELETE",
								headers: {
									"Content-Type": "application/json" 
								}
						};
						fetch(URL, requestOptions)
						.then(res=>res.json())
						.then(json=>{ 
							console.log("fetch result: " + json);
							location.href="/post/list";
						})
						.catch(err => console.log(err))
					}
					else{
						return;
					}

				}
				else{
					answer = confirm("(Admin)정말 삭제하시겠습니까?");
					if (answer){
						const requestOptions = {
								method: "DELETE",
								headers: {
									"Content-Type": "application/json" 
								}
						};
						fetch("/post/admin/" + this.pk, requestOptions)
						.then(res=>res.json())
						.then(json=>{ 
							console.log("fetch result: " + json);
							location.href="/post/list";
						})
						.catch(err => console.log(err))
					}
					else{
						return;
					}
				}

			}else{
				if(this.user_pk != s_user_pk) {
					alert("자신이 작성한 글만 삭제할 수 있습니다 !! ");
					return;
				}
				else{
					answer = confirm("정말 삭제하시겠습니까?");
					if (answer){
						const requestOptions = {
								method: "DELETE",
								headers: {
									"Content-Type": "application/json" 
								}
						};
						fetch(URL, requestOptions)
						.then(res=>res.json())
						.then(json=>{ 
							console.log("fetch result: " + json);
							location.href="/post/list";
						})
						.catch(err => console.log(err))
					}
					else{
						return;
					}
				}
			}
		}
	}
});