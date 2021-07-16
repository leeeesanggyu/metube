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
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>

</head>
<body>
<%@ include file="header.jsp"%>
<div class="wrap">
    <div class="login">
    	<h2>Create Post</h2>
		<form name="upload_form" id="upload_form" v-on:submit="upload">
			<div class="login_id">
				<h4>title</h4>
				<input v-model="title">
				<h4>description</h4>
				<input v-model="description">
				<h4>url</h4>
				<input v-model="url">
				<h4>cover_img</h4>
				<input v-model="cover_img">
			</div>

			<div class="submit">
				<button type="submit" id="btn-upload">Upload</button>
			</div>

		</form>
	</div>
</div>
<script>
	var user_pk = <%=user_pk%>
</script>
<script src="/resources/js/createPost.js"></script>
</body>
</html>