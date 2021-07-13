
const uploadForm = new Vue({
	el: '#uploadForm',
	data: {
		title: '',
		description: '',
		url: '',
		cover_img: '',
		user_pk: '',
		comment_pk: '',
		like_count: '',
		view_count: '',
	},
	methods: {
		upload: function() {	
			if(this.title == ""){
				alert("title insert");
				$("#title").focus();
				return;
			}
			if(this.description == ""){
				alert("description insert");
				$("#description").focus();
				return;
			}
			document.uploadForm.action="/createPost.do"
	    }
	}
});











