<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
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
		
			<c:if test="${postList.is_delete eq '0'}" >
				<a href="/post/detail/${postList.pk}">
					<p>=========================</p>
					<p>kind: ${postList.kind}</p>
					<p>title: ${postList.title}</p>
					<p>cover_img: ${postList.cover_img }</p>
					<p>user_pk: ${postList.user_pk }</p>
					<p>view_count: ${postList.view_count }</p>
					<p>create_at: ${postList.create_at }</p>
					<p>is_delete: ${postList.is_delete }</p>
					<p>=========================</p>
				</a>
			</c:if>
			<c:if test="${postList.is_delete eq '1'}" >
				<p>=========================</p>
				<p>[ 관리자에 의해 삭제된 게시글 입니다 ]</p>
				<p>=========================</p>
			</c:if>
		</span>
	</c:forEach>
</body>
</html>