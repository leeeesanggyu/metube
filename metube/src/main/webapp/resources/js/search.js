const search_form = new Vue({
	el: '#search_form',
	data: {
		search_data: ''
	},
	methods: {
		search: function(e) {   
			e.preventDefault();

			if(this.search_data.replaceAll(" ", "") == ""){
				alert("검색어를 입력하세여");
				$(this.search_data).focus();
				return;
			}
			location.href= "/post/search/" + this.search_data;
		}
	}
});