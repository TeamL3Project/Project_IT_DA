let option=1;

function getList(state){
	console.log(state)
	option = state;
	$.ajax({
		type : "post",
		url : contextPath + "/ReplyList.co",
		data : {"boardNum" : $("#Reply_board_num").val(), state:state},
		datetype : "json",
		success : function(rdata){		//ReplyList에서 가져옴
			$(".reply_count").text(rdata.listcount).css('font-family', 'Lucida Console')
			let red1 = 'red';
			let red2 = 'red';
			
			if (state == 1) {
				red2 = 'gray';
			}else if (state == 2) {
				red1 = 'gray';
			}
			
			let output = "";
					//ReplyList에서 가져옴
			if (rdata.replylist.length > 0) {
				output += '<li class="reply_order_item" >'
				   		+ '		<a href="javascript:getList(1)" class="reply_order_button ' + red1 + '">등록순 </a>'
				   		+ '</li>'
				   		+ '<li class="reply_order_item" >'
				   		+ '		<a href="javascript:getList(2)" class="reply_order_button ' + red2 + '">최신순 </a>'
				   		+ '</li>';
				$('.reply_order_list').html(output);		//등록순, 최신순 버튼을 붙여준다
				
				output = '';
				$(rdata.replylist).each(function(){
					const lev = this.replylev;
					let reply_reply = '';
					
					if (lev >= 1 && lev <= 10) {
				        reply_reply = ' reply_list_item__reply lev' + lev;
				    }
					
					output += '<li id="' + this.replyNum + '" class="reply_list_item' + reply_reply + '">'
							+ '	<div class="reply_nick_area">'
							+ '  <div class="reply_box">'
							+ '		<div class="reply_nick_box">'
							+ '			<div class="reply_nick_info">'
							+ '				<div class="reply_nickname">' + this.replywriter  + '</div>'
							+ '			</div>'
							+ '		</div>'
							+ '  </div>'
							+ '	 <div class="reply_text_box">'
							+ '		<p class="reply_text_view">'
							+ '			<span class="text_reply">' + this.replycontent + '</span>'
							+ '		</p>'
							+ '	 </div>'
							+ '  <div class="reply_info_box">'
							+ '		<span class="reply_info_date">' + this.replydate + '</span>';
					
					if (lev < 10) {
						output += ' <a href="javascript:replyform(' + this.replyNum + ','
						  	   + lev + ',' + this.replyseq + ','
						  	   + this.replyref + ')" class="reply_info_button">답글쓰기</a>'
					}
														
					output += '  </div>'
					
					if ($("#LoginId").val() == this.replywriter){		//글 작성자와 로그인한 사람이 일치하는지
						output += '<div class="reply_tool">'
							    + '		<div title="더보기" class="reply_tool_button">'
							    + '     	<div>&#46;&#46;&#46;</div>' 
							    + ' 	</div>'
							    + ' 	<div id="reply_list_item_layer' +  this.replyNum + '"  class="LayerMore">' //스타일에서 display:none; 설정함
							    + '     	<ul class="layer_list">'							   
							    + '     	<li class="layer_item">'
							    + '      		<a href="javascript:updateForm(' + this.replyNum + ')"'
							    + '         	class="layer_button">수정</a>&nbsp;&nbsp;'
							    + '      		<a href="javascript:del(' + this.replyNum + ')"'
							    + '         	class="layer_button">삭제</a></li></ul>'
							    + '   	</div>'//LayerMore
							    + '</div>'//reply_tool
					}
					
					output += '</div>'
							+ '</li>'
						
				})//each end
				
				$('.reply_list').html(output);
				
			}//if end
			else {
				$('.reply_list').empty();
				$('.reply_order_list').empty();
			
			}
		
		}//success end
	
	})//ajax end
	
}//function(getList) end


function updateForm(num){
	$('.reply_tool').hide();
	
	$('.LayerMore').hide();
	
	let $num = $('#'+num);
	const replycontent = $num.find('.text_reply').text();
	const selector = '#'+num + '> .reply_nick_area'
	$(selector).hide();
	
	$num.append($('.reply_list+.reply_write').clone());
	$num.find('textarea').val(replycontent);
	$num.find('.btn-register').attr('data-id', num).addClass('update').text('수정완료');
	$num.find('.btn-cancel').css('display', 'block');
	const count = replycontent.length;
	$num.find('.reply_write_area_count').text(count+"/200");
	
}//fucntion(updateForm) end



function del(num){
	if (!confirm('정말 삭제하시겠습니까?')){
		$('#reply_list_item_layer' + num).hide();
		return;
	}

	$.ajax({
		url : contextPath + '/ReplyDelete.co',
		data : {num : num},
		dataType : 'json',
		success : function(rdata) {
			if (rdata[0] === true) {
				getList(option);
			}else {
				alert("댓글 삭제중 오류");
			}
		}
	})

}//function (del) end




function replyform(replyNum, lev, seq, ref) {
	$(".LayerMore").hide();
	
	let output = '<li class="reply_list_item reply_list_item__reply lev'  +  lev + '"></li>'
    const $replyNum = $('#'+replyNum);

	$replyNum.after(output);
	output=$('.reply_list+.reply_write').clone();
	
	const $replyNum_next = $replyNum.next();

	$replyNum_next.html(output);
	$replyNum_next.find('textarea').attr('placeholder', '답글을 남겨보세요');
	$replyNum_next.find('.btn-cancel').css('display','block').addClass('reply-cancel');
	$replyNum_next.find('.btn-register').addClass('reply')
			 .attr('data-ref', ref).attr('data-lev', lev).attr('data-seq', seq).text('답글완료');
	
}//function(replyform) end




$(function() {getList(option);
	$('.reply_area').on('keyup','.reply_write_area_text', function(){
		const length = $(this).val().length;
		$(this).prev().text(length + '/200');
		
	})
	
	
	$('ul+.reply_write .btn-register').click(function() {
		const replycontent=$('.reply_write_area_text').val();
		if(!replycontent){				//내용없이 등록 클릭한 경우
			alert("댓글을 입력하세요");
			return;
		}
		
		$.ajax({
			url : contextPath + '/ReplyAdd.co',  //댓글 등록
			data : {replywriter : $('#LoginId').val(),
					replycontent : replycontent,
					Reply_board_num : $('#Reply_board_num').val()	
			},
			type : 'post',
			success : function(rdata) {
				if (rdata == 1) {
					getList(option);
				}
			}
			
		})//ajax end
		
		$('.reply_write_area_text').val('');			//textarea 초기화
		
		$('.reply_write_area_count').text('0/200');		//입력한 글 카운트 초기화
		
	})//$('.btn-register').click end
	
	
	$(".reply_list").on('click', '.reply_tool_button', function() {       //댓글의 ...영역
		$(this).next().toggle();
		
		$(".reply_tool_button").not(this).next().hide(); 
		
	})//$(".reply_list").on end
	
	
	$('.reply_area').on('click','.update',function(){
		const replycontent = $(this).parent().parent().find('textarea').val();
		if(!replycontent){						//내용없이 등록 클릭한 경우
			alert("수정할 글을 입력하세요");
			return;
		}
		
		const replyNum = $(this).attr('data-id');
		console.log(replyNum);
		$.ajax({
			url : contextPath + '/ReplyUpdate.co',
			data : {replyNum : replyNum,
					replycontent : replycontent},
			success : function(rdata){
				if(rdata == 1){
				   getList(option);
				}//if
				
			}//success end
			
		});//ajax end
		
	})//$('.reply_area').on update end
	
	
	$('.reply_area').on('click','.btn-cancel',function(){
		const num = $(this).next().attr('data-id');	//취소버튼의 다음 형제요소인 수정완료버튼의 'data-id'속성 값을 num변수에 저장
		const selector='#' +num;
		
		$(selector + ' .reply_write').remove();
		
		//숨겨두었던 .reply_nick_area 영역을 보여줌
		$(selector + '>.reply_nick_area').css('display','block');	
				
		//수정 폼이 나타난 상태에는 더보기 영역을 숨겨놨기 때문에 취소를 선택하면 다시 보여줌
	    $(".reply_tool").show();
	    
	})//$('.reply_area').on btn-cancel end
	
	
	$('.reply_area').on('click','.reply',function(){
		const replycontent =$(this).parent().parent().find('.reply_write_area_text').val();
		if(!replycontent){			//내용없이 답글완료 클릭한 경우
			alert("답글을 입력하세요");
			return;
		}	
			
		const replyref = $(this).attr('data-ref');
		const replylev = $(this).attr('data-lev');
		const replyseq = $(this).attr('data-seq');
		
		$.ajax({
			url : contextPath + '/ReplyReply.co',  
			data : {replywriter : $("#LoginId").val(),
					replycontent : replycontent,
					Reply_board_num : $('#Reply_board_num').val(),
					replylev : replylev,
					replyref : replyref,
					replyseq : replyseq
			},
			type : 'post',
			success : function(rdata) {
				if (rdata == 1) {
					getList(option);
				}
			}//success end
			
		})//ajax end
		
	})////$('.reply_area').on reply end
	
	
	$('.reply_area').on('click','.reply-cancel',function(){
		$(this).parent().parent().parent().remove();
		$(".reply_tool").show(); 		//...영역 보이게
	
	})//$('.reply_area').on reply-cancel end
	
	
	$('.reply_area').on('click','.reply_info_button',function(event){
		$(".reply_tool").hide();		//답변쓰기 폼이 있는 상태에서 더 보기 영역을 숨김
		
		const length=$('.reply_area .btn-register.reply').length;		//답글쓰기 폼의 갯수
		
		if(length==1){  //답글쓰기 폼이 한 개가 존재하면 anchor 태그(<a>)의 기본 이벤트를 막아 
		                //또 다른 답글쓰기 폼이 나타나지 않도록 합니다.
			event.preventDefault();			
		}
	
	})//$('.reply_area').on reply_info_button end
	
	
	
})//ready reeeeeeeeeeeal end



