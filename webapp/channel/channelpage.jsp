<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<html lang="ko">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<title>channel Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<jsp:include page="../main/header.jsp" />
<style>
.wrapper {
	display: flex;
	flex-direction: column;
	align-items: center;
}

body {
	background-color: #fffff;
}

#profile {
	background-color: #fffff;
	width: 330px;
	height: 300px;
	margin: 20 20;
	display: inline-block;
}

.bt-item {
	/*position: relative;*/
	margin: 0 5px;
	color: #01273C;
	border-radius: 12px;
	border: 1px solid #01273C;
	width: 7em;
	font-size: 15px;
	text-align: center;
	line-height: 20px;
	font-weight: bold;
}

.bt-hover:hover {
	background: #FBD1A7;
	color: #01273C;
	opacity: 0.8;
	border: 1px solid #FBD1A7;
}

.on, .bt-on {
	background: #FBD1A7;
	color: #01273C;
	opacity: 0.8;
	border: 1px solid #FBD1A7;
}

.bt-item:active {
	border: none;
}

.info_button {
	text-align: right;
}

tr>td:nth-child(odd) {
	font-weight: normal;
	width: 720px;
}

td {
	text-align: left;
}

a.test:hover {
	text-decoration: underline;
	color: inherit;
}

td>a {
	display: block;
	color: inherit;
}

.category-content {
	display: flex;
	max-width: 756px;
	padding-left: 29px
}

.recent-write {
	font-size: 18px;
	line-height: 20px;
	font-weight: 500;
	margin-left: -330;
}

.home_img {
	height: 50%;
	width: 100%;
	border-radius: 20px;
	padding: 15;
}

.colmd4 {
	border: 1px solid #01273C;
	width: 210px;
	display: inline-block;
	height: 290px;
	margin-bottom: 25px;
	margin-right: 25px;
	border-radius: 10px;
	overflow: hidden;
}

.background-wrap {
	display: flex;
	flex-direction: column;
}

.row {
	display: flex;
	flex-wrap: nowrap;
	margin-right: -50;
}

.card-body {
	text-align: center;
}

.container {
	display: flex;
	max-width: 770px;
	padding-left: 43px;
}

.bt-hover2 {
	background: #ccccc;
	color: #01273C;
	opacity: 0.8;
	border: 1px solid #ccccc;
}

</style>
<script>
$(document).ready(function() {
    setButtonClickEvents();
    initializeDefaultContent();
    

       id = '${userId}'
    	
    	if(id){ //로그인 한 경우
    		//구독 유무 확인 select하기
    		//select * from sub where userid =? and chnum=?
    
	    $.ajax({
			url : "../Channelissub.chl",
			type: "POST",
			data: { userId : id, chnum :  '${channel.chnum}' },
			success: function(data) {
				if(data==1) {				
					$("#subscribeBtn").removeClass('bt-hover').addClass('bt-on');
					$("#subscribeBtn").text("구독완료");
				}
			}
		});	
   	}
    
    
    $(document).on('click', '#subscribeBtn', function() {
    	//세션 아이디값이 있는지 확인 
    	//yes인 경우
    	//ajax로 
    	//insert into sub (userid, subchnum, subdate) 
    	//values(${userId},${channel.chnum},timestamp);
    	// 1 ? : 세션 아이디
    	// 2 ? : 파라미터 
    			//channelsub.ch?userId=admin
    	id = '${userId}'
    	
    		if (id) {
                if ($("#subscribeBtn").hasClass('bt-on')) { // 이미 구독한 경우 (unsubscribe)
                    // 구독 삭제
                    $.ajax({
                        url: "../Canclechsub.chl",
                        type: "POST",
                        data: { userId: id, chnum: '${channel.chnum}' },
                        success: function(data) {
                        	
                        	if (data == 1) {
                                alert("[${channel.chname}] 구독이 취소되었습니다.");
                                $("#subscribeBtn").removeClass('bt-on').addClass('bt-item');
                                $("#subscribeBtn").text("구독하기");
                            } else {
                                alert("구독 취소 오류입니다.");
                            }
                        }
                    });
                } else { // 구독하지 않은 경우 (subscribe)
                    // 구독 추가
	    		//구독 안한 사람 insert하기
	    		$.ajax({
	    			url : "../Channelsub.chl",
	    			type: "POST",
	    			data: { userId : id, chnum :  '${channel.chnum}' },
	    			success: function(data) {
		    				if(data==1) {
		    					alert("[${channel.chname}] 구독되었습니다.");
		        				$("#subscribeBtn").removeClass('bt-hover').addClass('bt-on');
		        				$("#subscribeBtn").text("구독완료");
		    				} else {
		                        alert("구독 오류입니다.");
		                    }
		                }
		            });
		        }
		    } else {
		        alert("로그인 후 이용하세요.");
		    }
		});
	});

function setButtonClickEvents() {
    $(".category .bt-item").click(function() {
        $(".bt-item.on").removeClass('on');
        $(this).addClass('on').css("box-shadow", "none");
        if ($(this).text() === "인기글") {
            setInnerHTML1();
        } else if ($(this).text() === "카테고리") {
            setInnerHTML2();
        }
    });
}


function setInnerHTML1() {
    const element = document.getElementById('my_div');
    element.innerHTML = `
        <div class="background-wrap">
            <div class="row">
                <c:forEach var="c" items="${channelhome}" varStatus="loop">
                    <div class="colmd4">
                    <a href="${pageContext.request.contextPath}/contents/${channel.chnum}/${c.boardNum}">
                        <img class="home_img" src="../image/content/${c.chNum}/${c.boardNum}/${c.thumbNail}">
                        <div class="card-body card-body-font">
                            <h5 class="card-title">
                                <c:out value="${c.boardTitle}" />
                            </h5>
                            <p class="card-text">
                                <c:out value="${c.boardContent}" />
                                <c:if test="${c.boardContent.length() >= 20}">
                                    <c:out value="${c.boardContent.substring(0,20)}..." />
                                </c:if>
                                <c:if test="${c.boardContent.length() < 20}">
                                    <c:out value="${c.boardContent}" />
                                </c:if>
                            </p>
                        </div>
                    </div>
                    <c:if test="${(loop.index + 1) % 3 == 0 || loop.last}">
                        </div>
                        <div class="row">
                    </c:if> 
                </a>
                </c:forEach>
            </div>
        </div>`;
}



function setInnerHTML2() {
	const element = document.getElementById('my_div');
	element.innerHTML = `
    <div class="category-content">
       <table class="table table-bordered" style="margin: 0 8;">
	        <tr>
	          <td>
	          <a href="${pageContext.request.contextPath}/channel/contentlist.co?channelnum=${channel.chnum}&chcate_id=0">전체 </a>
	          </td>
	        </tr>
			<c:forEach var="c" items="${chcategory}">
			  <tr>
	          <td>
	          <a href="${pageContext.request.contextPath}/channel/contentlist.co?channelnum=${channel.chnum}&chcate_name=${c.chcate_Name}&chcate_id=${c.chcate_id}">
	          ${c.chcate_Name}
	      	  </a>
	          </td>
	        </tr>
			</c:forEach>
       </table>
    </div>`;
}

function initializeDefaultContent() {
    setInnerHTML1();
    // "인기글" 버튼을 활성화 상태로 설정
    const homeButton = document.querySelector('.bt-item[value="인기글"]');
    homeButton.classList.add('on');
}
</script>
</head>
<body>
	<div class="wrapper">
		<br> <br>
		<div class="info">
			<div class="head"
				style="display: flex; justify-content: space-between;">
				<h3 style="margin-left: 25;">[${channel.chname}]</h3>
				<a
					href="${pageContext.request.contextPath}/content/contentwrite.co?chnum=${channel.chnum}">
					<button class="btn bt-item bt-hover" style="margin: 9;">글쓰기</button>
				</a>
			</div>
			<div id="profile1" style="width: 735px;">
				<div id="profile"
					style="padding: 20; border-radius: 2px; border: 1.8px solid #01273c; float: left;">

					<div class="chinfo"
						style="padding: 35; padding: 35; height: 220px;">${channel.chinfo}</div>
					<div class="info_button">
						<button class="btn bt-item bt-hover" id="profiledetail"
							name="${channel.chnum}"
							onClick="location.href='${pageContext.request.contextPath}/ChannelDetail.chl?channelnum=${channel.chnum}'">자세히보기</button>
					</div>

				</div>
				<img src="../image/channel/${channel.chnum}/${channel.chprofile}"
					style="width: 360px; height: 320px; border-bottom-left-radius: 50px; padding: 10px; margin-top: 10px;">
			</div>

			<br>
			<div class="sub_alram_btn"
				style="padding: 30px; margin-top: -38px; padding-left: 15px;">
				<button class="btn bt-item" id="subscribeBtn">구독하기</button>
				<img src="../image/channel/alram_white.png"
					style="width: 38px; height: 38px; margin-left: 10px; display: inline-block;">
			</div>


			<br> <br>
		</div>
		<hr style="border: 1px bold silver;" width="100%">
		<br>

		<div class="category" style="padding: 0 235 display: flex;">

			<input class="btn bt-item bt-hover" type='button' value='인기글'
				onclick='setInnerHTML1()' /> <input class="btn bt-item bt-hover"
				type='button' value='카테고리' onclick='setInnerHTML2()' />
		</div>

		<br> <br>
		<div id='my_div'></div>


		<div class="category2">
			<br> <br> <br>
			<div class="recent-write">최신글</div>
			<br>
		</div>
		<div class="container">
			<table class="table table-bordered">
				<c:forEach var="c" items="${channeldetail}">
					<tr>
						<td><a
							href="${pageContext.request.contextPath}/contents/${channel.chnum}/${c.boardNum}">${c.boardTitle}</a></td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>

	<br>
	<br>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>