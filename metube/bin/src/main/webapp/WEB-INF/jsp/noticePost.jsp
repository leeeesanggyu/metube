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
<%@ include file="header.jsp"%>
<h4 class="layout">공지사항&nbsp;&nbsp;
	<c:if test="${role eq '3'}" >
		<a href="/post/goNoticeCreate">업로드</a>
	</c:if>
</h4>
	<div class="content">
		<c:forEach var="noticeList" items="${noticeList}">
			<div class="oneContent">
				<a href="/post/notice/${noticeList.pk}">
					<p>* NOTICE *</p>
					<p>${noticeList.title}</p>
					<div class="small">
						<p>${noticeList.name }</p>
						<c:if test="${noticeList.update_at eq null}" >
							<p>조회수 ${noticeList.view_count }회 • ${noticeList.create_at }</p>
						</c:if>
						<c:if test="${noticeList.update_at ne null}" >
							<p>조회수 ${noticeList.view_count }회 • 수정 ${noticeList.update_at }</p>
						</c:if>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
</body>
</html>