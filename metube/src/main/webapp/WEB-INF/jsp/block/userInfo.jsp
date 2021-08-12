<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<center>
		<table class="type09">
			<thead>
				<tr>
					<th scope="cols">계정 정보</th>
					<th scope="cols">내용</th>
					<th scope="cols">
						<div id="app">
							<button class="button" @click="withdrawal(${data.userInfo.pk})">회원탈퇴</button>
						</div>
					</th>
				</tr>
			</thead>
			<tbody>
				<tr>
					<th scope="row">이메일</th>
					<td>${data.userInfo.email}</td>
				</tr>
				<tr>
					<th scope="row">이름</th>
					<td>${data.userInfo.name}</td>
				</tr>
				<tr>
					<th scope="row">권한</th>
					<td><c:if test="${data.userInfo.role eq '1'}">
						Guest
					</c:if> <c:if test="${data.userInfo.role eq '2'}">
						User
					</c:if> <c:if test="${data.userInfo.role eq '3'}">
						Admin
					</c:if></td>
				</tr>
				<tr>
					<th scope="row">구독자 수</th>
					<td>${data.sub_count}명</td>
				</tr>
			</tbody>
		</table>
	</center>
</body>
</html>