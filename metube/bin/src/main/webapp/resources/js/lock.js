if (s_lock == 1) {
		alert("잠금상태입니다.");
		
		const requestOptions = {
                method: "GET",
                headers: {
             	   "Content-Type": "application/json" 
                }
            };
         fetch("/user/logout", requestOptions)
			.then(res=>{ location.href="/"; })
			.catch(err => console.log(err))
}