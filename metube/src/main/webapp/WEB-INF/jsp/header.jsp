<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>header</title>
<link rel="stylesheet" href="/resources/css/font.css" />
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script>
<% 
	String name = (String)session.getAttribute("name");
	int user_pk = (int)session.getAttribute("user_pk");
	String role = (String)session.getAttribute("role");
	int lock = (int)session.getAttribute("lock");

	if(lock == 1){
%>
		alert("회원님은 lock 상태 입니다.(관리자에게 나문희 )");
		location.href="/user/lock/logout";
<%
	}
	
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
	<a href="/post/list"><img src="/resources/images/logo.png" width="200"></a>
	<hr>
	<div class="menu">
		<a href="/post/goCreate">동영상 업로드</a>&nbsp;&nbsp;
		<a href="/user/detail/">개인 정보 보기</a>&nbsp;&nbsp;
		<c:if test="${role eq '3'}" >
			<a href="/user/search">회원 검색(Admin)</a>
		</c:if>
		
		<div style="float: right;">
			(<c:if test="${role eq '1'}" >
				Guest
			</c:if>
			<c:if test="${role eq '2'}" >
				User
			</c:if>
			<c:if test="${role eq '3'}" >
				Admin
			</c:if>
			) <%=name %> 님 안녕하세요..
			<span id="logout">
				<button @click="logout()">logout</button>
			</span>
		</div>
	</div>
	<hr>
</body>
<script>
new Vue({
    el: '#logout',
    methods: {
    	logout: function() {    		 
    		answer = confirm("정말 로그아웃 하시겠습니까 ?");
    	    if (answer){
    	        const requestOptions = {
    	                method: "GET",
    	                headers: {
    	             	   "Content-Type": "application/json" 
    	                }
    	            };
    	         fetch("/user/logout", requestOptions)
    				.then(res=>{ location.href="/"; })
    			.catch(err => console.log(err))
   	    	}else{
   	    		return;
   	    	}
    	}
    }
});
</script>
</html>