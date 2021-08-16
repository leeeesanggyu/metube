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
<script src="https://cdn.ckeditor.com/ckeditor5/29.0.0/classic/ckeditor.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
</head>
<body>
<%@ include file="./header.jsp"%>
	<div class="create_form">
		<div class="layout">
			<div id="upload_form">
				<div class="submit">
					<button @click="upload" id="btn-upload">Upload</button>
				</div>
				<div class="login_id">
					<h4>title</h4>
					<input v-model="title">
				</div>
			</div>
			<!-- 2. TEXT 편집 툴을 사용할 textarea -->
			<h4>description</h4>
		    <input name="content" id="editor">
		</div>
	</div>
</body>
<script>
	var s_user_pk = <%=(int)session.getAttribute("user_pk")%> //세션
	var user_role = <%=(String)session.getAttribute("role")%> //세션
</script>
<script src="/resources/js/noticeCreate.js"></script>
</html>