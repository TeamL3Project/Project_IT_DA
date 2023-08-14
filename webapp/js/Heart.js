$(function(){
	$(document).on('click',".heart_butt",function(e) {
        e.preventDefault();
		var boardNum = $(this).data('id');
		
		$.ajax({
			type : "post",
			url : contextPath + "/heartCount.co",
			data : {boardNum : boardNum},
			dataType : "json",
			success : function(rdata){
				if (rdata.success) {
					var updatedValue = response.updatedValue;
                    $(".u_heart_count").text(updatedValue).val(updatedValue);
				}
			},
			error : function() {
				console.error("콘솔 메세지 : heart 실패");
			}
		
		})//ajax end
	
	})//click end
})