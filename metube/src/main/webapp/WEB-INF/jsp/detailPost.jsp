<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/content.css" />
<link rel="stylesheet" href="/resources/css/layout.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
<link rel="stylesheet" href="/resources/css/button.css" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<div class="layout">
	
	<c:if test="${detailPost.post.video_name ne null}">			
		<video width="640" height="480" controls>		
			<source src="/upload/videos/${detailPost.post.video_name }/${detailPost.post.video_ext }" type="video/mp4">
		</video>
	</c:if>
		
		<h4>${detailPost.post.title}</h4>
		<p>${detailPost.post.description }</p>
		<div class="small">
			<p>
				조회수 ${detailPost.post.view_count }회 • ${detailPost.post.create_at } •
				<c:if test="${detailPost.is_like eq null}">
					<span id="like">
						<button class="button" @click="add_like()">👍 ${detailPost.post_like}</button>
					</span>
				</c:if>

				<c:if test="${detailPost.is_like ne null}">
					<span id="like">
						<button class="button" @click="delete_like()">👍 ${detailPost.post_like}✔</button>
					</span>
				</c:if>
			</p>
			<c:if test="${detailPost.post.update_at ne null}">
				<p>수정: ${detailPost.post.update_at }</p>
			</c:if>
		</div>
		<div id="post">
			<button class="button" @click="deletePost">삭제</button>
			<button class="button" @click="goModifyPost">수정</button>
		</div>
		<hr>
		<div id="sub">
			<a href="/user/detail/${detailPost.post.user_pk}">${detailPost.post.name } </a> <span
				class="small">구독자 ${detailPost.sub_count }명</span>
			<c:if test="${detailPost.sub eq null}">
				<button class="button" @click="sub_add(${detailPost.post.user_pk})">구독</button>
			</c:if>
			<c:if test="${detailPost.sub ne null}">
				<button class="button" @click="sub_del(${detailPost.post.user_pk})">구독중✔</button>
			</c:if>
		</div>
		<hr>
		<form id="comment" v-on:submit="comment_upload">
			댓글 <input v-model="content">
			<button class="button" type="submit" id="btn-comment">댓글달기</button>
		</form>
		<br>
		<div class="comment">
			<c:forEach var="comment" items="${detailPost.comment}">
				<div>
					<p>
						<strong>${comment.name }</strong> • ${comment.create_at }
					</p>
					<p>${comment.content }</p>
					<div id="comment_delete">
						<button class="button"
							@click="deleteComment(${comment.pk }, ${comment.user_pk })">삭제</button>
					</div>
					<hr>
				</div>
			</c:forEach>
		</div>
	</div>
</body>

<script>
	var s_user_pk = <%=user_pk%> //세션
	var user_role = <%=role%> //세션
	var p_user_pk = ${detailPost.post.user_pk }
	var post_pk = ${detailPost.post.pk}
	var post_kind = ${detailPost.post.kind}
	
	var URL = "/post/" + post_pk;
	var admin_URL = "/post/admin/" + post_pk;
</script>
<script src="/resources/js/like.js"></script>
<script src="/resources/js/sub.js"></script>
<script src="/resources/js/post.js"></script>
<script src="/resources/js/comment.js"></script>
</html>