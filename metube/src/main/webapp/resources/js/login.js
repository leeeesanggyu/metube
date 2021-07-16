const loginForm = new Vue({
    el: '#loginForm',
    data: {
        email: '',
        password: ''
    },
    methods: {
        login: function(e) {
        	e.preventDefault();
        	
            if(this.email == ""){
               alert("email insert");
               $(this.email).focus();
               return;
            }
            if(this.password == ""){
               alert("password insert");
               $(this.password).focus();
               return;
            }
            
            const requestOptions = {
                   method: "POST",
                   headers: {
                	   "Content-Type": "application/json" 
                   },
                   body: JSON.stringify({
                      email: this.email, password: this.password 
                   })
               };
            fetch("/user/check.do", requestOptions)
  				.then(res=>res.json())
				.then(json=>{ 
					console.log("fetch result: " + json);
					location.reload();
				})
			.catch(err => console.log(err))
        },
        
        onClickRedirect: function() {
           location.href="/user/goSignUp.do"
        }
    }
});