<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MeTube</title>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.6.0"></script>
<link rel="stylesheet" href="/resources/css/createPostForm.css" />
<link rel="stylesheet" href="/resources/css/content.css" />
<link rel="stylesheet" href="/resources/css/layout.css" />
<link rel="stylesheet" href="/resources/css/font-.css" />
<link rel="stylesheet" href="/resources/css/body-line.css" />
</head>
<body>
<%@ include file="header.jsp"%>
	<div class="layout">
		<p>${post.url }</p>
	
		<h4>${post.title}</h4>
		<p>${post.description }</p>
		<div class="small">
			<p>조회수 ${post.view_count }회 • ${post.create_at } • 👍 ${post.like_count }</p>
			<c:if test="${post.update_at ne null}" >
				<p>수정:
					${post.update_at }
				</p>
			</c:if>
		</div>
		<div id="post">
			<button @click="deletePost">
				게시물 삭제 
			</button>
			<button @click="goModifyPost">
				게시물 수정
			</button>
		</div>
		<hr>
		<div id="sub">
			<a href="/user/detail/${post.user_pk}">${post.name } </a>
			<span class="small">구독자 ${sub_count }명</span>
				<c:if test="${sub eq null}" >
					<button @click="sub_add(${post.user_pk})">
						구독
					</button>
				</c:if>
				<c:if test="${sub ne null}" >
					<button @click="sub_del(${post.user_pk})">
						구독중✔
					</button>
				</c:if>
		</div>
		<hr>
		<form id="comment" v-on:submit="comment_upload">
			댓글 <input v-model="content">
			<button type="submit" id="btn-comment">댓글달기</button>
		</form><br>
		
		<div class="comment">
			<c:forEach var="comment" items="${comment}">
				<div>
					<p><strong>${comment.name }</strong> • ${comment.create_at }</p>
					<p>${comment.content }</p>
					<div id="comment_delete">
						<button @click="deleteComment(${comment.pk }, ${comment.user_pk })">삭제</button>
					</div>
					<hr>
				</div>
			</c:forEach>
		</div>
		
	</div>
</body>

<script>
	var s_user_pk = <%=user_pk%> //세션
	var user_role = <%=role%> //세션
	var p_user_pk = ${post.user_pk }
	var post_pk = ${post.pk}
	var post_kind = ${post.kind}
	
	var URL = "/post/" + post_pk;
	var admin_URL = "/post/admin/" + post_pk;

	const sub = new Vue({
	    el: '#sub',
	    data: {
	    	 
	    },
	    methods: {
	    	sub_add: function(p_user_pk) {
	    		const requestOptions = {
                        method: "POST",
                        headers: {
                     	   "Content-Type": "application/json" 
                        },
                        body: JSON.stringify({
                        	p_user_pk: p_user_pk,
                        	c_user_pk: s_user_pk
  		                })
                    };
                 fetch("/sub/add", requestOptions)
       				.then(res=>res.json())
     				.then(json=>{ 
 						if(json == true){
 							location.reload();
 						}
     				})
     			.catch(err => console.log(err))
	        },
	        sub_del: function(p_user_pk) {
	    		const requestOptions = {
                        method: "DELETE",
                        headers: {
                     	   "Content-Type": "application/json" 
                        },
                        body: JSON.stringify({
                        	p_user_pk: p_user_pk,
                        	c_user_pk: s_user_pk
  		                })
                    };
                 fetch("/sub/del", requestOptions)
       				.then(res=>res.json())
     				.then(json=>{ 
 						if(json == true){
 							location.reload();
 						}
     				})
     			.catch(err => console.log(err))
	        }
	   }
	});
	const post = new Vue({
	    el: '#post',
	    data: {
	    	pk: post_pk,
	    	user_pk: p_user_pk,
	    	content: ''
	    },
	    methods: {
	    	deletePost: function() {
	    		if(user_role == 3){
	    			if(${post.kind } == 3){
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
	    			else{
	    				answer = confirm("(Admin)정말 삭제하시겠습니까?");
			            if (answer){
			                const requestOptions = {
			                        method: "DELETE",
			                        headers: {
			                     	   "Content-Type": "application/json" 
			                        }
			                    };
			                 fetch("/post/admin/" + this.pk, requestOptions)
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
	        },
	        goModifyPost: function() {
	        	if(this.user_pk != s_user_pk) {
	    			alert("자신이 작성한 글만 수정할 수 있습니다 !! ");
	    			return;
	    		}
    			location.href="/post/goModify/" + post_pk;
	        }
	   }
	});
	
	const comment = new Vue({
	    el: '#comment',
	    data: {
	    	pk: post.post_pk,
	    	content: ''
	    },
	    methods: {
	        comment_upload: function(e) {
	        	e.preventDefault();

	        	if(this.content == ""){
					alert("댓글 insert");
					$(this.content).focus();
					return;
				}
	        	const requestOptions = {
                        method: "POST",
                        headers: {
                     	   "Content-Type": "application/json" 
                        },
	                  	body: JSON.stringify({
	                	   post_pk: post_pk,
	                	   content: this.content
 		                })
                    };
                 fetch("/comment/", requestOptions)
       				.then(res=>res.json())
     				.then(json=>{ 
     					console.log("fetch result: " + json);
 						location.reload();
     				})
     			.catch(err => console.log(err))
	        }
	   }
	});
	const comment_delete = new Vue({
	    el: '#comment_delete',
	    data: {
	    },
	    methods: {
	    	deleteComment: function(pk, c_pk) {
    			answer = confirm("댓글을 삭제하시겠습니까?");
    			if (answer){
    				if(s_user_pk == c_pk) {
	            		const requestOptions = {
		                        method: "DELETE",
		                        headers: {
		                     	   "Content-Type": "application/json" 
		                        }
		                    };
		                 fetch("/comment/" + pk, requestOptions)
		       				.then(res=>res.json())
		     				.then(json=>{ 
		     					console.log("fetch result: " + json);
		 						location.reload();
		     				})
		     			.catch(err => console.log(err))
    				}
    				else if(s_user_pk == 3){
    					const requestOptions = {
		                        method: "DELETE",
		                        headers: {
		                     	   "Content-Type": "application/json" 
		                        }
		                    };
		                 fetch("/comment/" + pk, requestOptions)
		       				.then(res=>res.json())
		     				.then(json=>{ 
		     					console.log("fetch result: " + json);
		 						location.reload();
		     				})
		     			.catch(err => console.log(err))
    				}
    				else{
    	    			alert("자신이 작성한 댓글만 삭제할 수 있습니다 !! ");
 						location.reload();
    					return;
    				}
	    		}else{
	    			return;
	    		}
    		}
        }
	});
	
</script>
</html>