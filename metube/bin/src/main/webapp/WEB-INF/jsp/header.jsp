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
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script>
<% 
	String name = (String)session.getAttribute("name");
	int user_pk = (int)session.getAttribute("user_pk");
	String role = (String)session.getAttribute("role");
	int lock = (int)session.getAttribute("lock");

%>
<%	
	String role_n = "";
	if(role == "1"){
		role_n = "Guest";
	}
	else if(role == "2"){
		role_n = "User";
	}
	else if(role == "3"){
		role_n = "Admin";
	}
%>
</script>
</head>
<body>
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
		<a href="/user/detail/<%=user_pk %>" >내 채널</a>&nbsp;&nbsp;
		
		<c:if test="${role eq '3'}" >
			<a href="/user/search">회원 검색(Admin)</a>
		</c:if>
			<span style="float: right;">
				(<c:if test="${role eq '1'}" >
					Guest
				</c:if>
				<c:if test="${role eq '2'}" >
					User
				</c:if>
				<c:if test="${role eq '3'}" >
					Admin
				</c:if>
				) <%=name %> 님
				<span id="logout">
					<button class="button" @click="logout()">logout</button>
				</span>
			</span>
	</div>
	<br><hr>
</body>
<script src="/resources/js/logout.js"></script>
<script src="/resources/js/search.js"></script>
</html>