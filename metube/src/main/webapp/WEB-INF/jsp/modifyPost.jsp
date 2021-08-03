<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/body-line.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
<script
	src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<h4>Modify Post</h4>
	<hr>
	<form name="upload_form" id="upload_form" v-on:submit="upload">
		<div class="submit">
			<button type="submit" id="btn-upload">Upload</button>
		</div>
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
	var ori_title = "${post.title }"
	var ori_description = "${post.description }"
	var ori_kind = "${post.kind }"
	var post_pk = "${post.pk}"
	// 세션 받기
	var user_pk = <%=user_pk%>;
	var role = <%=role%>;
</script>
<script src="/resources/js/modifyPost.js"></script>
</html>