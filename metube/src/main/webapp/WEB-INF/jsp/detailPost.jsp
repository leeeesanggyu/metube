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
			
			document.comment_form.action="/createComment.do"
			document.comment_form.submit();
		})
	})
</script>
</head>
<body>
<%@ include file="header.jsp"%>
	<div>
		<p>===========================================</p>
		<p>pk: ${post.pk }</p>
		<p>user_pk: ${post.user_pk }</p>
		<p>comment_pk: ${post.comment_pk}</p>
		<p>title: ${post.title}</p>
		<p>description: ${post.description }</p>
		<p>url: ${post.url }</p>
		<p>cover_img: ${post.cover_img }</p>
		<p>like_count: ${post.like_count }</p>
		<p>view_count: ${post.view_count }</p>
		<p>===========================================</p>
		
		<c:forEach var="comment" items="${comment}">
			<div>
				<p>========================</p>
				<p>pk: ${comment.pk}</p>
				<p>post_pk: ${comment.post_pk }</p>
				<p>user_pk: ${comment.user_pk }</p>
				<p>content: ${comment.content }</p>
				<p>========================</p>
				
			</div>
		</c:forEach>
		
		<form name="comment_form" method="post">
			댓글 <input name="content" id="content">
			<input type="hidden" name="user_pk" value=<%=user_pk%>>
			<input type="hidden" name="post_pk" value=${post.pk }>
			
			<button type="button" id="btn-comment">댓글달기</button>
		</form>
		
		<a href="deletePost_confirm.do?user_pk=${post.user_pk}&post_pk=${post.pk}&role=<%=role %>">게시물 삭제</a>
		
		<br><br>
	</div>

</body>
</html>