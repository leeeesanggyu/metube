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
	<h4 class="layout">&nbsp;&nbsp;구독 동영상</h4>
	<div class="content">
		<c:forEach var="subPostList" items="${subPostList}">
			<div class="oneContent">
				<c:if test="${subPostList.is_delete eq '0'}">
					<a href="/post/${subPostList.pk}/normal/0">
						<c:if test="${subPostList.kind eq 3}">
							* Notice *
						</c:if> 
						<c:if test="${subPostList.kind ne 3}">
							<img src="/upload/image/${subPostList.img_name}/${subPostList.img_ext}" />
						</c:if>
						<p><c:out value="${subPostList.title }" /></p>
						<div class="small">
							<a href="/user/detail/${subPostList.user_pk }"><p>${subPostList.name }</p></a>
							<c:if test="${subPostList.update_at eq null}">
								<p>조회수 ${subPostList.view_count }회 • ${subPostList.create_at }</p>
							</c:if>
							<c:if test="${subPostList.update_at ne null}">
								<p>조회수 ${subPostList.view_count }회 • 수정 ${subPostList.update_at }</p>
							</c:if>
						</div>
					</a>
				</c:if>

				<c:if test="${subPostList.is_delete eq '1'}"><br><br><br><br><br><br>
					<p>======================</p>
					<p>&nbsp;[ 관리자에 의해 삭제된 게시글 ]</p>
					<p>======================</p><br><br><br><br><br><br>
				</c:if>
			</div>
		</c:forEach>
	</div>
</body>
</html>