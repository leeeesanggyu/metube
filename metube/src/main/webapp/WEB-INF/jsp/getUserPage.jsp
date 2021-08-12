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
	
	<%@ include file="./block/userInfo.jsp"%>
	
	<h4 class="layout">Community</h4>
	<%@ include file="./block/communityList.jsp"%>

	<hr align="center" style="border: outset 1px; width: 88%;">
	
	<h4 class="layout">내 채널 동영상</h4>
	<%@ include file="./block/freeVideoList.jsp"%>

</body>
<script>
	var s_user_pk = <%=user_pk%> //세션
</script>
<script src="/resources/js/withdrawal.js"></script>
</html>