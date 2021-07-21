<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getPostList</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script>
	$(document).ready(function(){
		$("#btn-comment").click(function(){
			var content=$("#content").val();

			if(content == ""){
				alert("content insert");
				$("#content").focus();
				return;
			}
			
			document.comment_form.action="/create.do"
			document.comment_form.submit();
		})
	})
</script>
</head>
<body>
<%@ include file="header.jsp"%>
	<c:forEach var="postList" items="${postList}">
		<span>
			<a href="/post/detail/${postList.pk}">
				<p>========================</p>
				<p>title: ${postList.title}</p>
				<p>cover_img: ${postList.cover_img }</p>
				<p>user_pk: ${postList.user_pk }</p>
				<p>view_count: ${postList.view_count }</p>
				<p>create_at: ${postList.create_at }
				<p>========================</p>
			</a>
		</span>
	</c:forEach>
</body>
</html>