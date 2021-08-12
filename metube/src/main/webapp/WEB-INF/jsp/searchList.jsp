<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib  prefix="spring" uri="http://www.springframework.org/tags" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/content.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
</head>
<body>
<%@ include file="./header.jsp"%>
<h4 class="layout">검색된 채널</h4>
	<div class="content">
		<c:forEach var="userList" items="${data.userList}">
			<div class="oneContent">
				<a href="/user/detail/${userList.pk}">
					<p>${userList.name }</p>
				</a>
			</div>
		</c:forEach>
	</div>
	
	<hr align="center" style="border: outset 1px; width: 88%;">
	
	<h4 class="layout">검색된 동영상</h4>
	<%@ include file="./block/freeVideoList.jsp"%>

</body>
</html>