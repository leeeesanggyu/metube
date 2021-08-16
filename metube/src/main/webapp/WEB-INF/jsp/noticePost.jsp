<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/content.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
<link rel="stylesheet" href="/resources/css/header.css" />
</head>
<body>
	<div class="header">
		<%@ include file="./header.jsp"%>
	</div>
	<h4 class="layout">
		공지사항&nbsp;&nbsp;
		<c:if test="${role eq '3'}">
			<a href="/post/goNoticeCreate">업로드</a>
		</c:if>
	</h4>
	<%@ include file="./block/noticeList.jsp"%>

</body>
</html>