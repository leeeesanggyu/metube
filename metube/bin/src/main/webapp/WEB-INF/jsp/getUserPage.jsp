<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/resources/css/getUserPage.css" />
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
<link rel="stylesheet" href="/resources/css/content.css" />
<link rel="stylesheet" href="/resources/css/button.css" />
<title>MeTube</title>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<center>
		<table class="type09">
			<thead>
				<tr>
					<th scope="cols">계정 정보</th>
					<th scope="cols">내용</th>
					<th scope="cols">
						<div id="app">
							<button class="button" @click="withdrawal(${userInfo.pk})">회원탈퇴</button>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">이메일</th>
					<td>${userInfo.email}</td>
				</tr>
				<tr>
					<th scope="row">이름</th>
					<td>${userInfo.name}</td>
				</tr>
				<tr>
					<th scope="row">권한</th>
					<td><c:if test="${userInfo.role eq '1'}">
						Guest
					</c:if> <c:if test="${userInfo.role eq '2'}">
						User
					</c:if> <c:if test="${userInfo.role eq '3'}">
						Admin
					</c:if></td>
				</tr>
				<tr>
					<th scope="row">구독자 수</th>
					<td>${sub_count}명</td>
				</tr>
			</tbody>
		</table>
	</center>
	<h4 class="layout">Community</h4>
	<div class="content">
		<c:forEach var="communityList" items="${communityList}">
			<div class="oneContent">
				<a href="/post/detail/${communityList.pk}">
					<p>
						<img
							src="/upload/image/${communityList.img_name}/${communityList.img_ext}" />
					</p>
					<p>${communityList.title}</p>
					<div class="small">
						<p>${communityList.name }</p>
						<c:if test="${communityList.update_at eq null}">
							<p>조회수 ${communityList.view_count }회 •
								${communityList.create_at }</p>
						</c:if>
						<c:if test="${communityList.update_at ne null}">
							<p>조회수 ${communityList.view_count }회 • 수정
								${communityList.update_at }</p>
						</c:if>
					</div>
				</a>
			</div>
		</c:forEach>
	</div>
	<hr align="center" style="border: outset 1px; width: 88%;">
	<h4 class="layout">내 채널 동영상</h4>
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
	var s_user_pk = <%=user_pk%> //세션
</script>
<script src="/resources/js/withdrawal.js"></script>
</html>