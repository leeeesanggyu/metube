//페이지 권한 설정
if(user_role == "1"){
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
		user_pk: s_user_pk,
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
			if(this.kind == 3 && user_role != 3){
				alert("공지사항은 admin만 쓸수 있습니다 !");
				$(this.kind).focus();
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
						user_pk: this.user_pk,
						kind: 3
					})
			};
			fetch("/post/notice", requestOptions)
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