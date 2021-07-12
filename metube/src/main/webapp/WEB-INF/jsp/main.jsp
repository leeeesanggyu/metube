<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>main</title>
<% 
	String email = (String)session.getAttribute("email");
	String name = (String)session.getAttribute("name");
%>
</head>
<body>
<center>
	<c:if test="${msg == 'success'}">
		<h2>session email: <%=email %></h2>
		<h2>session name: <%=name %></h2>
	</c:if>
</center>
</body>
</html>