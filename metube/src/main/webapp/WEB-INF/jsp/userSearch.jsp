<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/content.css" />
<link rel="stylesheet" href="/resources/css/body-line.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="create_form">
		<div class="layout">
			<form name="upload_form" id="user_search_form" v-on:submit="search">
				<div class="submit">
					<button class="submit" type="submit">search</button>
				</div>
				<div class="login_id">
					<h4>name-search</h4>
					<input v-model="name" placeholder="name">
				</div>
				
			</form>
		</div>
	</div>
</body>
<script>
	var s_user_pk = <%=user_pk%> //세션
	var s_user_role = <%=role%> //세션
</script>
<script src="/resources/js/userSearch.js"></script>
</html>