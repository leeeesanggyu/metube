<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>metube</title>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn-upload").click(function(){
			var title=$("#title").val();
			var description=$("#description").val();
			var url=$("#url").val();
			var cover_img=$("#cover_img").val();

			if(title == ""){
				alert("title insert");
				$("#title").focus();
				return;
			}
			if(description == ""){
				alert("description insert");
				$("#description").focus();
				return;
			}
			
			document.upload_form.action="/createPost.do"
			document.upload_form.submit();
		})
	})
</script>
</head>
<body>
<%@ include file="header.jsp"%>
<div class="wrap">
    <div class="login">
    	<h2>Create Post</h2>
		<form name="upload_form" method="post" class="loginForm">
		
			<div class="login_id">
				<h4>title</h4>
				<input name="title" id="title">
				<h4>description</h4>
				<input name="description" id="description">
				<h4>url</h4>
				<input name="url" id="url">
				<h4>cover_img</h4>
				<input name="cover_img" id="cover_img">
			</div>
			
			<input type="hidden" name="user_pk" id="user_pk" value=<%=user_pk %>>
			<input type="hidden" name="comment_pk" id="comment_pk" value=0>
			<input type="hidden" name="like_count" id="like_count" value=0>
			<input type="hidden" name="view_count" id="view_count" value=0>
			
			<div class="submit">
				<button type="button" id="btn-upload">Upload</button>
			</div>
			
		</form>
	</div>
</div>


</body>
</html>