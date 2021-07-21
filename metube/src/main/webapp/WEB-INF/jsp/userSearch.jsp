<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
</head>
<body>
<%@ include file="header.jsp"%>
	<h2>search-user</h2><hr>
	
	<form name="upload_form" id="search_form" v-on:submit="search">
		<div class="login_id">
			<h4>name</h4>
			<input v-model="name" placeholder="name">
		</div>
		
		<button class="submit" type="submit">search</button>
	</form>
</body>
<script>
	//페이지 권한 설정
	if(<%=role %> != "3"){
		alert("권한이 없습니다.");
		history.go(-1);
	}
	
	const search_form = new Vue({
	    el: '#search_form',
	    data: {
	    	name: ''
	    },
	    methods: {
	    	search: function(e) {
	        	e.preventDefault();
	        	
				if(this.name == ""){
					alert("name insert", this.desciption);
					$(this.name).focus();
					return;
				}
				location.href= this.name;
	        }
	    
	    }
	});
</script>
</html>