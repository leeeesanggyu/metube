<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script
	src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
<script src="//code.jquery.com/jquery-3.2.1.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.css" integrity="sha512-3pIirOrwegjM6erE5gPSwkUzO+3cTjpnV9lexlNZqvupR64iZBnOOTiiLPb9M36zpMScbmUNIcHUqKD47M719g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js" integrity="sha512-VEd+nq25CkR676O+pLBnDW09R7VQX9Mdiij052gVCp5yVH3jGtH70Ho/UUv4mJDsEdTvqRCFZg0NKGiojGnUCw==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
<script>
	//전역변수 선언-모든 홈페이지에서 사용 할 수 있게 socket.js에 저장
	var socket = null;

	$(document).ready(function() {
		connectWs();
	});

	function connectWs() {
		sock = new SockJS("<c:url value="/echo"/>")
		socket = sock;

		sock.onopen = function() {
			console.log('info: connection opened.');
		};

		sock.onmessage = function(evt) {
			var data = evt.data;
			
			toastr.options = {
			  "closeButton": false,
			  "debug": false,
			  "newestOnTop": false,
			  "progressBar": true,
			  "positionClass": "toast-top-right",
			  "preventDuplicates": false,
			  "onclick": null,
			  "showDuration": "300",
			  "hideDuration": "1000",
			  "timeOut": "5000",
			  "extendedTimeOut": "1000",
			  "showEasing": "swing",
			  "hideEasing": "linear",
			  "showMethod": "fadeIn",
			  "hideMethod": "fadeOut"
			}
			
			toastr.options.onclick = function() { 
				const result = data.substring(data.lastIndexOf("pk") + 2);
				location.href="/post/" + result + "/normal"
			}
			
			const idx = data.indexOf("pk");
			const result = data.substring(0, idx)
			toastr.success(result, "알림 ! ");
		};

		sock.onclose = function() {
			console.log('connect close');
		};

		sock.onerror = function(err) {
			console.log('Errors : ', err);
		};

	}
</script>
</body>
</html>