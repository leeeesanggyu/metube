<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/content.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
<link rel="stylesheet" href="/resources/css/body-line.css" />
<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
</head>
<body>
<%@ include file="header.jsp"%>
	<div class="layout">
	
		<div id="upload_form">
			<div class="submit">
				<button @click="upload" id="btn-upload">Upload</button>
			</div>
			
			<h4>image <small>(250 x 150)</small></h4>
		    <input type="file" id="image_input"/><br><br>
			<h4>video</h4>
		    <input type="file" id="video_input"/><br><br>
			
			<h4>게시판 선택</h4>
			<select name="kind" v-model="kind">
			    <option value="">== 게시판 선택 ==</option>
			    <option value="2">동영상 게시판</option>
			    <option value="1">본인 채널 커뮤니티</option>
		  	</select>
			<div class="login_id">
				<h4>title</h4>
				<input v-model="title">
			</div>
		</div>
		
		<!-- 2. TEXT 편집 툴을 사용할 textarea -->
		<h4>description</h4>
	    <input name="content" id="editor">
	    
	</div>
</body>

<script>
	//페이지 권한 설정
	if(<%=role %> == "1"){
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
	    	user_pk: <%=user_pk%>,
	    	comment_pk: 0,
	    	like_count: 0,
	    	view_count: 0,
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
				if(this.kind == 3 && <%=role %> != 3){
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
</script>
</html>