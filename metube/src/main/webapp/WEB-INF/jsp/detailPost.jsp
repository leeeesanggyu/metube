<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>getPostList</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<script>
	$(document).ready(function(){
		$("#btn-comment").click(function(){
			var content=$("#content").val();

			if(content == ""){
				alert("content insert");
				$("#content").focus();
				return;
			}
			
			document.comment_form.action="/comment"
			document.comment_form.submit();
		})
	})
</script>
</head>
<body>
<%@ include file="header.jsp"%>
	<div>
		<p>===========================================</p>
		<p>pk: ${post.pk }</p>
		<p>user_pk: ${post.user_pk }</p>
		<p>comment_pk: ${post.comment_pk}</p>
		<p>title: ${post.title}</p>
		<p>description: ${post.description }</p>
		<p>url: ${post.url }</p>
		<p>cover_img: ${post.cover_img }</p>
		<p>like_count: ${post.like_count }</p>
		<p>view_count: ${post.view_count }</p>
		<p>create_at: ${post.create_at }</p>
		<p>===========================================</p>
		
		<c:forEach var="comment" items="${comment}">
			<div>
				<p>========================</p>
				<p>pk: ${comment.pk}</p>
				<p>post_pk: ${comment.post_pk }</p>
				<p>user_pk: ${comment.user_pk }</p>
				<p>content: ${comment.content }</p>
				<p>========================</p>
				
			</div>
		</c:forEach>
		
		<form name="comment_form" method="post">
			댓글 <input name="content" id="content">
			<input type="hidden" name="user_pk" value=<%=user_pk%>>
			<input type="hidden" name="post_pk" value=${post.pk }>
			
			<button type="submit" id="btn-comment">댓글달기</button>
		</form>
		
		<div id="app">
			<button @click="deletePost">
				게시물 삭제 
			</button>
		</div>
		
		<br><br>
	</div>
</body>

<script>
	var s_user_pk = <%=user_pk%> //세션
	var p_user_pk = ${post.user_pk }
	var post_pk = ${post.pk}
	var user_role = <%=role%>
	
	var URL = "/post/" + post_pk;
	var admin_URL = "/post/admin/" + post_pk;

	new Vue({
	    el: '#app',
	    data: {
	    	pk: post_pk,
	    	user_pk: p_user_pk
	    },
	    methods: {
	    	deletePost: function() {
	    		if(user_role == 3){
	    			answer = confirm("(Admin)정말 삭제하시겠습니까?"+ URL);
		            if (answer){
		                const requestOptions = {
		                        method: "DELETE",
		                        headers: {
		                     	   "Content-Type": "application/json" 
		                        }
		                    };
		                 fetch(admin_URL, requestOptions)
		       				.then(res=>res.json())
		     				.then(json=>{ 
		     					console.log("fetch result: " + json);
	     						location.href="/post/list";
		     				})
		     			.catch(err => console.log(err))
		            }
		            else{
		          	  	return;
		            }
	    		}else{
	    			if(this.user_pk != s_user_pk) {
		    			alert("자신이 작성한 글만 삭제할 수 있습니다 !! ");
		    			return;
		    		}
	    			else{
	    				answer = confirm("정말 삭제하시겠습니까?");
			            if (answer){
			                const requestOptions = {
			                        method: "DELETE",
			                        headers: {
			                     	   "Content-Type": "application/json" 
			                        }
			                    };
			                 fetch(URL, requestOptions)
			       				.then(res=>res.json())
			     				.then(json=>{ 
			     					console.log("fetch result: " + json);
		     						location.href="/post/list";
			     				})
			     			.catch(err => console.log(err))
			            }
			            else{
			          	  	return;
			            }
	    			}
	    		}
    			
	    		
	        }
	   }
	});
</script>
</html>