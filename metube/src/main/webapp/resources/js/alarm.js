/**
 * 알림 목록을 가져온다.
 */

getList = function() {    		 
	$.ajax({
		type : "GET",
		url : "/alarm/list",
		success : function(res){
			console.log(res);
		
			var htmlCode = '';
            htmlCode += '<ul>'
            	
			for(var i=0; i<res.length; i++) {
                htmlCode += '<li><a href="/post/' + res[i]['post_pk'] + '/normal/' + res[i]['pk'] + '">' + res[i]['name'] + ' 님이 ' 
                				+ res[i]['title'] + '에'
                
                if(res[i]['cmd'] == "comment"){
                    htmlCode += ' 댓글을 달았습니다. '
                }
                else if(res[i]['cmd'] == "like"){
                    htmlCode += ' 좋아요를 남겼습니다. '
                }
                htmlCode += '<br>'
                htmlCode += res[i]['create_at']; +'</a></li>'
                htmlCode += '<br><br>'
			}
            
            htmlCode += '</ul>'
            
            console.log(htmlCode);
			$('#data').html(htmlCode);
		},
		error : function(XMLHttpRequest, textStatus, errorThrown){
			console.log("통신 실패.")
		}
	});
}
