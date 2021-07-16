<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="/resources/css/font.css" />
<% 
	String name = (String)session.getAttribute("name");
	int user_pk = (int)session.getAttribute("user_pk");
	String role = (String)session.getAttribute("role");
%>
</head>
<body>
<a href="goHome.do"><img src="/resources/images/logo.png" width="200"></a>

<hr>
<div class="menu">
	<a href="/post/goCreate.do">동영상 업로드</a>&nbsp;&nbsp;
	<a href="/user/detail.do">개인 정보 보기</a>
	<div style="float: right;">
		<%=name %> 님 안녕하세요.
		<a href="logout.do">Log-out</a>
	</div>
</div>
<hr>		
</body>
</html>