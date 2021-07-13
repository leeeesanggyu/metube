<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
%>
<title>getUserPage</title>
<style>
table.type09 {
	border-collapse: collapse;
	text-align: left;
	line-height: 1.5;

}
table.type09 thead th {
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 3px solid RED;
}
table.type09 tbody th {
	width: 150px;
	padding: 10px;
	font-weight: bold;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
	background: #f3f6f7;
}
table.type09 td {
	width: 350px;
	padding: 10px;
	vertical-align: top;
	border-bottom: 1px solid #ccc;
}
</style>
</head>
<body>
<%@ include file="header.jsp"%>
<center>
	<table class="type09">
		<thead>
			<tr>
				<th scope="cols">계정 정보</th>
				<th scope="cols">내용</th>
			</tr>
		</thead>
	<tbody>
		<tr>
			<th scope="row">email</th>
			<td>${userInfo.email}</td>
		</tr>
		<tr>
			<th scope="row">name</th>
			<td>${userInfo.name}</td>
		</tr>
		<tr>
			<th scope="row">role</th>
			<td>${userInfo.role}</td>
		</tr>
	</tbody>
</table>
</center>
</body>
</html>