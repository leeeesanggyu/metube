//페이지 권한 설정
if(role == "1"){
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
		title: ori_title,	
		kind: 3,
		description: ''
	},
	methods: {
		upload: function(e) {
			e.preventDefault();

			const editorData = editor.getData();
			this.description = editorData;

			if(this.title == ""){
				alert("title insert", this.desciption);
				$(this.title).focus();
				return;
			}
			if(this.description == ""){
				alert("description insert");
				$(this.description).focus();
				return;
			}
			if(this.kind == ""){
				alert("kind insert", this.kind);
				$(this.kind).focus();
				return;
			}

			if(ori_title == this.title && ori_kind == this.kind && ori_description == this.description){
				alert("변경 사항이 없습니다.");
				return;
			}

			const requestOptions = {
					method: "PUT",
					headers: {
						"Content-Type": "application/json" 
					},
					body: JSON.stringify({
						pk: post_pk,
						title: this.title, 
						description: this.description,
						kind: this.kind
					})
			};
			fetch("/post/modify", requestOptions)
			.then(res=>res.json())
			.then(json=>{ 
				if(json == true){
					console.log("fetch result: " + json);
					location.href="/post/list";
				}
			})
			.catch(err => console.log(err))
		}
	}
});