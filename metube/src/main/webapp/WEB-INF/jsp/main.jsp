<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getPostList</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>

</head>
<body>
<%@ include file="header.jsp"%>
<c:forEach var="postList" items="${postList}">
		<div>
			<p>===========================================</p>
			<p>pk: ${postList.pk }</p>
			<p>user_pk: ${postList.user_pk }</p>
			<p>comment_pk: ${postList.comment_pk}</p>
			<p>title: ${postList.title}</p>
			<p>description: ${postList.description }</p>
			<p>url: ${postList.url }</p>
			<p>cover_img: ${postList.cover_img }</p>
			<p>like_count: ${postList.like_count }</p>
			<p>view_count: ${postList.view_count }</p>
			<p>===========================================</p>
			<a href="deletePost_confirm.do?user_pk=${postList.user_pk}&post_pk=${postList.pk}&role=<%=role %>">삭제</a>
			<br><br>
		</div>
</c:forEach>

</body>
</html>