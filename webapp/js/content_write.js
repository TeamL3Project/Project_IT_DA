/*$(document).ready(function(){
	
	
})*/
/*function cnt_tag(){ 
	if($('ul li').length > 6){
		console.log($('ul li').length);
		alert("태그는 5개까지 입력 가능합니다.")
		const target = $('#tag_add');
		target.disabled = true;
	}
};*/
/*
function cnt_tag() {
    if ($('ul li').length < 6) {
        console.log($('ul li').length);
        alert("태그는 5개까지 입력 가능합니다.");
        const target = $('#tag_add');
        target.prop('disabled', true); // 버튼 비활성화
    }
}
*/

function back(){
	window.history.back();
}


$(document).ready(function(){
	
	$("#file").change(function(){
		console.log($(this).val()) // c:\fakepath\upload.png
		const inputfile = $(this).val().split('\\');
	});
});

// 태그를 추가하는 기능
function action_add(){
		 var text_add = $("#input_tag").val();
		 text_add.value = null;
		 var ul_list = $("#ul_tag_list");
		 ul_list.append('<li class="viewer_tag_item" id="tag_item"><a href="#" class="viewer_tag_link">' + "#"+ text_add + "</a></li>")
	 };

// 태그 클릭시 태그 전체 삭제...
function action_remove(){
	 	$("#tag_item *").remove();
	 }; 
	 

	 
/*alert 창이 뜨면 버튼 비활성화 하고 싶은데 어떻게 해야해?*/