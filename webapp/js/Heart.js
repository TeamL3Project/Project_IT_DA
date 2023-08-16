$(function(){
	let heartstate = 0;
	let clickCK = 0;

	$(document).on('click',".heart_butt",function(e) {
		e.preventDefault();
		
		
        
        if (clickCK === 0) {
			$('.content_comment_img').attr('src', contextPath + '/image/content/heart2.png');
        	heartstate = 1;
        	clickCK = 1;
        }else if(clickCK === 1){
			$('.content_comment_img').attr('src', contextPath + '/image/content/heart.png');
			heartstate = 0;
			clickCK = 0;
		}
		
		var boardNum = $(this).data('id');
		console.log(boardNum);
		$.ajax({
			type : "post",
			url : contextPath + "/heartCount.co",
			data : {boardNum : boardNum,
					heartstate : heartstate},
			dataType : "json",
			success : function(rdata){
				if (rdata.success) {
					var updatedValue = rdata.updatedValue;
                    $(".u_heart_count").text(updatedValue).val(updatedValue);
					$(this).data('heartstate', heartstate === 0 ? 1 : 0);
				}
			},
			error : function() {
				console.error("콘솔 메세지 : heart 실패");
			}
		
		})//ajax end
	
	})//click end
})