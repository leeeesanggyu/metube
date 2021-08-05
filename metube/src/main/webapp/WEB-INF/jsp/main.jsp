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
<link rel="stylesheet" href="/resources/css/body.css" />
<link rel="stylesheet" href="/resources/css/header.css" />
</head>
<body>
	<div class="header">
		<%@ include file="header.jsp"%>
	</div>
	<h4 class="layout">공지사항</h4>
	<div class="content">
		<c:forEach var="noticeList" items="${noticeList}">
			<div class="oneContent">
				<a href="/post/notice/detail/${noticeList.pk}">
					<p>* NOTICE *</p>
					<p>${noticeList.title}</p>
					<div class="small">
						<p>${noticeList.name }</p>
						<c:if test="${noticeList.update_at eq null}">
							<p>조회수 ${noticeList.view_count }회 • ${noticeList.create_at }</p>
						</c:if>
						<c:if test="${noticeList.update_at ne null}">
							<p>조회수 ${noticeList.view_count }회 • 수정 ${noticeList.update_at }</p>
						</c:if>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
	<hr align="center" style="border: outset 1px; width: 88%;">
	<h4 class="layout">동영상</h4>
	<div class="content">
		<c:forEach var="postList" items="${postList}">
			<div class="oneContent">
				<c:if test="${postList.is_delete eq '0'}">
					<a href="/post/detail/${postList.pk}">
						<p>
							<img src="/upload/image/${postList.img_name}/${postList.img_ext}" />
						</p>
						<p>${postList.title}</p>
						<div class="small">
							<p>${postList.name }</p>
							<c:if test="${postList.update_at eq null}">
								<p>조회수 ${postList.view_count }회 • ${postList.create_at }</p>
							</c:if>
							<c:if test="${postList.update_at ne null}">
								<p>조회수 ${postList.view_count }회 • 수정 ${postList.update_at }</p>
							</c:if>
						</div>
					</a>
				</c:if>

				<c:if test="${postList.is_delete eq '1'}">
					<p>======================</p>
					<p>[ 관리자에 의해 삭제된 게시글 ]</p>
					<p>======================</p>
				</c:if>
			</div>
		</c:forEach>
	</div>
</body>
<script>
	var s_lock = <%=lock%> //세션
</script>
<script src="/resources/js/lock.js"></script>
</html>