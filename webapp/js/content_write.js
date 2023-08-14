// 태그를 추가하는 기능
function action_add(){
		 var text_add = $("#input_tag").val();
		 var ul_list = $("#ul_tag_list");
		 ul_list.append('<li class="viewer_tag_item"><button class="viewer_tag_link">' + "#"+ text_add + "</button></li>")
	 };



