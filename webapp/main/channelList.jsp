<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

$(document).ready(function () {
	
	 loadChannelList(0);

	    $(".channel_category").click(function () {
	        if ($(this).hasClass("on")) {
	            var categoryId = $(this).attr("id");
	            loadChannelList(categoryId);
	        }
	    });
});

function loadChannelList(categoryNum) {
   


    // AJAX 요청을 시작합니다.
    $.ajax({
        type: "GET", 
        url: "ChannelListAction.chl",  
        data: {categoryNum, categoryNum},  
        dataType: "json",               
        success: function (data) {     
           
            var channelTable = $(".row.area");
            channelTable.empty();
            

             
            $(data).each(function () {
            	 var appendData = '<div class="col-md-4">'
                     + '<a href="/channels/' + this.chNum + '">'
                     + '<div class="card d-flex justify-content-center">'
                     + '<img src="' + this.chProfile + '" class="card-img-top rounded-circle mx-auto d-block" alt="...">'
                     + '<div class="card-body">'
                     + '<h5 class="card-title">' + this.chName + '</h5>'
                     + '<p class="card-text">' + this.chinfo + '</p>'
                     + '</div>'
                     + '</div>'
                     + '</a>'
                     + '</div>';

      channelTable.append(appendData);
   
                    })
        },         
        error: function () {   
            
            alert("채널 목록을 불러오는 데 실패했습니다.");
        }
    });
}



</script>
<div style="display: flex; justify-content: center;">
		<h1>잇다 추천 채널</h1>
	</div>
	<div class="buttons-container">
		  <div class="cate-button">
            <button class="channel_category btn bt-item bt-hover bt-2  on" id="0"><span>전체</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-5" id="1"><span>경제/시사</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-5" id="2"><span>문화/예술</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-5" id="3"><span>IT트렌트</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-2" id="4"><span>역사</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-2" id="5"><span>과학</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-2" id="6"><span>건강</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-2" id="7"><span>요리</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-3" id="8"><span>스포츠</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-3" id="9"><span>재테크</span>
            </button>
            <button class="channel_category btn bt-item bt-hover bt-2" id="10"><span>취미</span>
            </button>
		</div>
	</div>

	<div class="container">
		<div class="row area">
			
		</div>
	</div>
