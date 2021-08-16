const comment = new Vue({
    el: '#comment',
    data: {
    	pk: post.post_pk,
    	content: ''
    },
    methods: {
        comment_upload: function(e) {
        	e.preventDefault();

        	if(this.content == ""){
				alert("댓글 insert");
				$(this.content).focus();
				return;
			}
			
			if(doubleSubmitCheck()) return;
			
        	const requestOptions = {
                    method: "POST",
                    headers: {
                 	   "Content-Type": "application/json" 
                    },
                  	body: JSON.stringify({
                	   post_pk: post_pk,
                	   content: this.content
	                })
                };
             fetch("/comment/", requestOptions)
   				.then(res=>res.json())
 				.then(json=>{ 
 					console.log("fetch result: " + json);
					location.reload();
 				})
 			.catch(err => console.log(err))
        }
   }
});

const comment_delete = new Vue({
    el: '#comment_delete',
    data: {
    },
    methods: {
    	deleteComment: function(pk, c_pk) {
			answer = confirm("댓글을 삭제하시겠습니까?");
			if (answer){
				if(s_user_pk == c_pk) {
            		const requestOptions = {
	                        method: "DELETE",
	                        headers: {
	                     	   "Content-Type": "application/json" 
	                        }
	                    };
	                 fetch("/comment/" + pk, requestOptions)
	       				.then(res=>res.json())
	     				.then(json=>{ 
	     					console.log("fetch result: " + json);
	 						location.reload();
	     				})
	     			.catch(err => console.log(err))
				}
				else if(s_user_pk == 3){
					const requestOptions = {
	                        method: "DELETE",
	                        headers: {
	                     	   "Content-Type": "application/json" 
	                        }
	                    };
	                 fetch("/comment/" + pk, requestOptions)
	       				.then(res=>res.json())
	     				.then(json=>{ 
	     					console.log("fetch result: " + json);
	 						location.reload();
	     				})
	     			.catch(err => console.log(err))
				}
				else{
	    			alert("자신이 작성한 댓글만 삭제할 수 있습니다 !! ");
						location.reload();
					return;
				}
    		}else{
    			return;
    		}
		}
    }
});