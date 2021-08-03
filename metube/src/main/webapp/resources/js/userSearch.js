//페이지 권한 설정
if(s_user_role != "3"){
	alert("권한이 없습니다.");
	history.go(-1);
}

const user_search_form = new Vue({
	el: '#user_search_form',
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
			location.href= "/user/" + this.name;
		}

	}
});