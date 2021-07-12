<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>metube</title>

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
<br><br><br><br><br><br>
<center>
	<form name="upload_form" method="post">
		title  : <input name="title" id="title"><br><br>
		description: <input name="description" id="description"><br><br>
		url: <input name="url" id="url"><br><br>
		cover_img: <input name="cover_img" id="cover_img"><br><br>
		pk:<%=user_pk %><input type="hidden" name="user_pk" id="user_pk" value=<%=user_pk %>><br><br>
		<input type="hidden" name="comment_pk" id="comment_pk" value=0><br><br>
		<input type="hidden" name="like_count" id="like_count" value=0><br><br>
		<input type="hidden" name="view_count" id="view_count" value=0><br><br>
		
		<button type="button" id="btn-upload">Upload</button>
	</form>
</center>
</body>
</html>