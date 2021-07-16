const upload_form = new Vue({
    el: '#upload_form',
    data: {
    	title: '',
    	description: '',
    	url: '',
    	cover_img: '',
    	user_pk: user_pk,
    	comment_pk: 0,
    	like_count: 0,
    	view_count: 0,
    },
    methods: {
    	upload: function(e) {
        	e.preventDefault();
        	
			if(this.title == ""){
				alert(this.user_pk);
				$(this.title).focus();
				return;
			}
			if(this.description == ""){
				alert("description insert");
				$(this.description).focus();
				return;
			}
			if(this.url == ""){
				alert("url insert");
				$(this.url).focus();
				return;
			}
			if(this.cover_img == ""){
				alert("cover_img insert");
				$(this.cover_img).focus();
				return;
			}
            
            const requestOptions = {
                   method: "POST",
                   headers: {
                	   "Content-Type": "application/json" 
                   },
                   body: JSON.stringify({
                	   title: this.title, 
                	   description: this.description,
                	   url: this.url,
                	   cover_img: this.cover_img,
                	   user_pk: this.user_pk,
                	   comment_pk: this.comment_pk,
                	   like_count: this.like_count,
                	   view_count: this.view_count 
                   })
               };
            fetch("/post/post.do", requestOptions)
  				.then(res=>res.json())
				.then(json=>{ 
					console.log("fetch result: " + json);
					location.href="/";
				})
			.catch(err => console.log(err))
        }
    }
});