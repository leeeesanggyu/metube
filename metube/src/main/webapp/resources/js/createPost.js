//페이지 권한 설정
if(s_user_role == "1"){
	alert("Guest는 권한이 없습니다.");
	history.go(-1);
}

//CKEditor5
let editor;

ClassicEditor
.create( document.querySelector( '#editor' ), {

} )
.then( newEditer => {
	editor = newEditer;
})
.catch( error => {
	console.error( error );
} );

// vue
const upload_form = new Vue({
	el: '#upload_form',
	data: {
		title: '',
		description: '',
		kind: '',
		user_pk: s_user_pk
	},
	methods: {
		upload: function(e) {
			e.preventDefault();

			const editorData = editor.getData();
			this.description = editorData;

			if(this.title == ""){
				alert("title insert");
				$(this.title).focus();
				return;
			}
			if(this.description == null){
				alert("description insert");
				$(this.description).focus();
				return;
			}
			if(this.kind == null){
				alert("kind insert");
				$(this.kind).focus();
				return;
			}
			if(this.kind == 3 && s_user_role != 3){
				alert("공지사항은 admin만 쓸수 있습니다 !");
				$(this.kind).focus();
				return;
			}

			const image_data = document.getElementById("image_input");
			const video_data = document.getElementById("video_input");

			if(image_data.files[0] == null){
				alert("사진을 등록하세요.");
				return;
			}
			if(video_data.files[0] == null){
				alert("동영상을 등록하세요.");
				return;
			}
			const formData = new FormData();
			formData.append("image", image_data.files[0]);
			formData.append("video", video_data.files[0]);
			formData.append("title", this.title);
			formData.append("description", this.description);
			formData.append("kind", this.kind);
			formData.append("user_pk", this.user_pk);


			const requestOptions = {
					method: "POST",
					body: formData
			};
			fetch("/post/create", requestOptions)
			.then(res=>res.json())
			.then(json=>{ 
				if(json == true){
					location.href="/post/list";
				}
			})
			.catch(err => console.log(err))
		}
	}
});