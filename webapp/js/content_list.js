/*$(document).ready(function(){
	
$("#sort_desc").on("click", function(){
	$(this).css('font-weight', 'bold');
	$('#sort_asc').css('font-weight', 'normal');

});


$("#sort_asc").on("click", function(){
	$(this).css('font-weight', 'bold');
	$('#sort_desc').css('font-weight', 'normal');
});
});*/

function sort_desc(){
	$('#sort_desc').css('font-weight', 'bold');
	$('#sort_asc').css('font-weight', 'normal');
}

function sort_asc(){
	$('#sort_asc').css('font-weight', 'bold');
	$('#sort_desc').css('font-weight', 'normal');
}


function go(page){
	const limit = $('#viewcount').val();
	/*const data = `limit=${limit}&state=ajax&page=${page}`; */
	const data = {limit:limit, state:"ajax", page: page}
	ajax(data);
	
	// ajax로 보낼 데이터를 오브젝트 형식으로 만들고 함수를 호출
}

function setPaging(href, digit){
	let active = "";
	let gray = "";
	if(href==""){ // href가 반문자열인 경우
		if(isNaN(digit)){ // 이전&nbsp; 또는 다음&nbsp;
			gray="gray"; // 11, 20번 라인처럼 href 속성이 없고 <a>요소의 textnode가 숫자가 아닌 경우
		} else {
			active = "active"; // 12, 19번 라인처럼 href 속성이 없고 <a>요소의 textnode가 숫자인 경우
		}
	}
	let output =`<li class = "page-item ${active}">`;
	//let anchor = "<a class='page-link + gray + "'" + href + ">" + digit + "</a></li>";
	let anchor = `<a class="page-link ${gray}" ${href}>${digit}</a></li>`;
	output += anchor;
	return output;
}

function ajax(sdata){
	console.log(sdata)
	
	var Parms = '?channelnum=+'+channelnum;
	Parms += '&chcate_id='+chcate_id;
	Parms += '&chcate_name='+chcate_name;
	if(chcate_name==null){
		chcate_name="전체글"
	}
	Parms += '&chname='+chname;
	
	$.ajax({
		type : "POST",
		data : sdata,
		url : "contentlist.co"+Parms,
		dataType : "json",
		cache : false,
		success : function(data){
			$("#viewcount").val(data.limit);
			$("thead").find("span").text("글 개수 : " + data.listcount);
			
			if(data.listcount > 0){ // 총 갯수가 0보다 큰 경우
				$(".category_sorting_wrap").remove();
				let num = data.listcount - (data.page -1) * data.limit;
				console.log(num)
				let output = '<div class="category_sorting_wrap">';
				$(data.contentlist).each(
					function(index, item){
						
						let title = item.boardTitle;
						if(title.length>=20){
							title=title.substr(0,20) + "..."; // 0부터 20개 부분 문자열 추출
						}
						
						output += '<div class="sorting_wrap">'
						output += '<ul class="sorting_list">'
						output += '<li class="sorting_item" aria-checked="true">'
						output += '<a href="contentlist.co'+Parms +'&order=desc" class="sorting_link" id="sort_desc" onClick="sort_desc()">최신순</a>'
						output += '<li class="sorting_item" role="radio">'
						output += '<a href="contentlist.co'+Parms +'&order=asc" class="sorting_link" id="sort_asc" onClick="sort_asc()">과거순</a>'
						output += '</li></ul></div></div></div>'
						output += '<div class="content_list_wrap">'
						output += '<ul class="content_list">'
						output += '<li class="content_item">'
						output += '<div class="content_item_line">'
						output += '<a href="'+contextPath +'/contents/'+channelnum+'/'+ item.boardNum+'>'
						output += '<img src="'+contextPath +'/image/content/'+ channelnum +'/'+ item.boardNum+'/'+ item.thnumbNail+'" class="content_thumb"> </a>'
						output += '<a href = "'+contextPath +'/contents/'+ channelnum +'/'+ item.boardNum+'><img src="../../image/content/'+channelnum+'/'+item.boardNum+'/'+item.thnumbNail+'" class="content_thumb"></a>'
						output += '<div class="content_text">'
						output += '<a class="content_text_link" href="'+contextPath +'/contents/'+channelnum+'/'+item.boardNum+'">'
						output += '<strong class="content_title">'+item.chcate_name+'</strong>'
						output += '<a>'+title.replace(/</g, '&lt;').replace(/>/g,'&gt;')+'</a>'
						output += '<div class="content_info">'
						output += '<span class="content_comment_wrap">'
						output += '<span class="content_info_text">'
						output += '<fmt:formatDate pattern="yyyy.MM.dd" value='+ item.boardDate +'/></span>'
						output += '<a><img class="content_comment_img" src= "../image/content/heart.png">'
						output += '<span class="content_comment_count">'+ item.boardHeart +'</span></a>'
						output += '<a><img class="content_comment_img" src= "../image/content/comment.png">'
						output += '<span class="content_comment_count"></span></a>'
						output += '</span></div></div><br></div>'
						
					})
				output += "</li>"
				$('.category_sorting_wrap').append(output) // table 완성
				
				$(".pagination").empty(); // 페이징 처리 영역 내용 제거
				output ="";
				
				let digit = '이전&nbsp;'
				let href="";
				if(data.page > 1){
					href = 'href=javascript:go(' + (data.page - 1) + ')';
				}
				output += setPaging(href, digit);
				
				for(let i = data.startpage; i<= data.endpage; i++){
					digit = i;
					href = "";
					if(i != data.page){
						href = 'href=javascript:go(' + i + ')';
					}
					output += setPaging(href, digit);
				}
				         
				digit = '&nbsp;다음&nbsp;';
				href="";
				if(data.page < data.maxpage){
					href = 'href=javascript:go(' + (data.page + 1) + ')';
				}
				output += setPaging(href, digit);
				
				$('.pagination').append(output)
			} //if(data.listcount)>0 end
			
		}, //success end
		error : function(){
			console.log('에러')
		}
	}) // ajax end
} // function ajax end

// 페이지 보여주는 function
 $(function(){
	 
	 $("#viewcount").change(function(){
		 go(1); // 보여줄 페이지를 1페이지로 설정합니다.
	 }); // change end
	 
	 
	 order=$("input[name=order]").val();
	 id = "#sort_" + order;
	 $(".sorting_list li a").css('font-weight', 'normal');
	 $(id).css('font-weight', 'bold');
 })