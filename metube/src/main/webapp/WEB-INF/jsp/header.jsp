<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<link rel="stylesheet" href="/resources/css/font.css" />
<link rel="stylesheet" href="/resources/css/button.css" />
<link rel="stylesheet" href="/resources/css/body.css" />
<link rel="stylesheet" href="/resources/css/sideAlarm.css" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
</head>
<body>
	<%@ include file="./block/socket.jsp"%>

	<a href="/post/list"><img src="/resources/images/logo.png" width="150"></a>
	<span class="text_area" style="float: right;">
		<form id="search_form" v-on:submit="search" >
				<input type="text" v-model="search_data" placeholder="검색어 입력"><button class="button" type="submit">검색</button>
		</form>
	</span>
	<hr><br>
	<div class="menu">
		<a href="/post/goCreate">동영상 업로드</a>&nbsp;&nbsp;
		<a href="/post/notice/list">공지사항</a>&nbsp;&nbsp;
		<a href="/sub/goSubPost">구독</a>&nbsp;&nbsp;
		<a href="/user/detail/<%=(int)session.getAttribute("user_pk") %>" >내 채널</a>&nbsp;&nbsp;
		<c:if test="${role eq '3'}" >
			<a href="/user/search">회원 검색(Admin)</a>
		</c:if>
			<span style="float: right;">
					<span class="btn" onclick="getList()">
						🔔
					</span>
					<span onclick="history.back();" class="page_cover"></span>
					<span id="menu">
						<br><span class="btn" onclick="allDelete()">
							전체삭제⛔
						</span>
						<span onclick="history.back();" class="close"></span>
						<br><br><br>
						<div id="data">
						</div>
					</span>
					
				(<c:if test="${role eq '1'}" >
					Guest
				</c:if>
				<c:if test="${role eq '2'}" >
					User
				</c:if>
				<c:if test="${role eq '3'}" >
					Admin
				</c:if>
				) <%=(String)session.getAttribute("name") %> 님
				<span id="logout">
					<button class="button" @click="logout()">logout</button>
				</span>
			</span>
	</div>
	<br><hr>
</body>
<script>
	var p_user_pk = <%=(int)session.getAttribute("user_pk") %>;
</script>
<script src="/resources/js/alarm.js"></script>
<script src="/resources/js/sideAlarm.js"></script>
<script src="/resources/js/logout.js"></script>
<script src="/resources/js/search.js"></script>
</html>