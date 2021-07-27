<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
</head>
<body>
<%@ include file="header.jsp"%>
   	<h2>Modify Post</h2><hr>
	<form name="upload_form" id="upload_form" v-on:submit="upload">
		<div class="submit">
			<button type="submit" id="btn-upload">Upload</button>
		</div>
		<h4>게시판 선택</h4>
		<select name="kind" v-model="kind">
		    <option value="">== 게시판 선택 ==</option>
		    <option value="1" disabled>광고 게시판</option>
		    <option value="2">자유 게시판</option>
		    <option value="3">공지 사항</option>
	  	</select>
		<div class="login_id">
			<h4>title</h4>
			<input v-model="title">
		</div>
	</form>
	
	<!-- 2. TEXT 편집 툴을 사용할 textarea -->
	<h4>description</h4>
    <textarea name="content" id="editor">
    	${post.description}
    </textarea>
</body>
<script>
	//페이지 권한 설정
	if(<%=role %> == "1"){
		alert("Guest는 권한이 없습니다.");
		history.go(-1);
	}
	
	// 세션 받기
	var user_pk = <%=user_pk%>;
	var role = <%=role%>;
	 		
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
	    	title: "${post.title }",	
	    	kind: "${post.kind }",
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
	            
	            const requestOptions = {
	                   method: "PUT",
	                   headers: {
	                	   "Content-Type": "application/json" 
	                   },
	                   body: JSON.stringify({
	                	   pk: "${post.pk }",
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
</script>
</html>