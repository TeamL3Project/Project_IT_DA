<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="../css/common.css" rel="stylesheet" type="text/css">
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
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
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
	font-weight: bold;
	border-radius: 12px;
	border: 1px solid #01273C;
	width: 7em;
	font-size: 15px;
	text-align: center;
	line-height: 20px;
}

.bt-hover:hover, .on, .bt-on {
	background: #FBD1A7;
	color: #01273C;
	opacity: 0.8;
	border: 1px solid #FBD1A7;
}

.bt-item:active {
	border: none;
}

.bt-normal {
	display: flex;
	align-items: center;
	justify-content: center;
	margin: 0 5px;
	font-weight: bold;
	height: 33px;
	border-radius: 12px;
	border: 1px solid #01273C;
	width: 7em;
	font-size: 15px;
	text-align: center;
	line-height: 20px;
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

.container {
	width: 100%;
	padding: 5;
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
	width: 100%;
}

.home_img {
	width: 220px;
	height: 240px;
	margin-right: 30px;
}

.content {
	margin-left: 35;
}
</style>
<script>
$(document).ready(function() {
    initializeDefaultContent();
    setButtonClickEvents();
    $("#subscribeBtn").on("click", function() {
        alert("구독되었습니다.");
    });
});

function setButtonClickEvents() {
    $(".bt-item").click(function() {
        $(".bt-item.on").removeClass('on');
        $(this).addClass('on').css("box-shadow", "none");
    });
}


function setInnerHTML1() {
    const element = document.getElementById('my_div');
    element.innerHTML = `
        <div class="background-wrap">
            <div class="content">
                  <c:forEach var="c" items="${channelhome}">
                       <img class="home_img" src="../image/content/${c.chNum}/${c.boardNum}/${c.thumbNail}">
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
		          <td><a> 전체 </a></td>
		        </tr>
			    <tr>
			          <td><a href="https://www.naver.com/" class='test'> 김시운 </a></td>
			    </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 김민섭 </a></td>
		        </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 책장위고양이 에세이 </a></td>
		        </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 고양이 성격 </a></td>
		        </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 고양이 간식 </a></td>
		        </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 고양이상품 신상소개 </a></td>
		        </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 간식만드는법 </a></td>
		        </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 고양이 장난감 언박싱 </a></td>
		        </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 츄르 소개 </a></td>
		        </tr>
		        <tr>
		          <td><a href="https://www.naver.com/" class='test'> 뚱냥이 관리법 </a></td>
		        </tr>
		        
	       </table>
	    </div>`;
}

// 초기 내용을 "홈" 섹션으로 설정하는 함수
function initializeDefaultContent() {
    const element = document.getElementById('my_div');
    element.innerHTML = `
        <div class="background-wrap">
            <div class="content">
                <c:forEach var="c" items="${channelhome}">
                    <img class="home_img" src="../image/content/${c.chNum}/${c.boardNum}/${c.thumbNail}"></a>
                </c:forEach>
            </div>
        </div>`;

    // "홈" 버튼을 활성화 상태로 설정
    const homeButton = document.querySelector('.bt-item[value="홈"]');
    homeButton.classList.add('on');
}


	// 페이지 로드될 때 함수 호출하여 초기 내용 및 버튼 설정
	window.onload = function() {
		initializeDefaultContent();
};
	
	
</script>
</head>
<body>
	<div class="wrapper">
		<br> <br>
		<div class="info">
			<h3 style="margin: 0 10; padding: 10;">[${channel.chname}]</h3>

			<div id="profile1" style="width: 735px;">
				<div id="profile"
					style="padding: 20; border-radius: 2px; border: 1.8px solid #01273c; float: left;">

					<div class="chinfo"
						style="padding: 35; padding: 35; height: 220px;">${channel.chinfo}</div>
					<div class="info_button">
						<button class="btn bt-item bt-hover" id="profiledetail"
							onClick="location.href='${pageContext.request.contextPath}/channel/${channel.chnum}/ChannelDetail.chl'">자세히보기</button>
					</div>

				</div>
				<img src="../image/channel/${channel.chnum}/${channel.chprofile}"
					style="width: 360px; height: 320px; border-bottom-left-radius: 50px; padding: 10px; margin-top: 10px;">
			</div>

			<br>
			<div class="sub_alram_btn"
				style="padding: 30px; margin-top: -38px; padding-left: 15px;">
				<button class="btn bt-item bt-hover" id="subscribeBtn">구독하기</button>
				<img src="../image/channel/alram_white.png"
					style="width: 38px; height: 38px; margin-left: 10px; display: inline-block;">
			</div>
			<br> <br>
		</div>
		<hr style="border: 1px bold silver;" width="100%">
		<br>

		<div class="category" style="padding: 0 235">
			<input class="btn bt-item bt-hover" type='button' value='홈'
				onclick='setInnerHTML1()' /> <input class="btn bt-item bt-hover"
				type='button' value='카테고리' onclick='setInnerHTML2()' />
		</div>

		<br> <br>
		<div id='my_div'></div>


		<div class="category2">
			<br> <br> <br>
			<div class="bt-normal">최신글</div>
			<br> <br>
		</div>
		<div class="container">
			<table class="table table-bordered"
				style="width: 65%; margin: 0 203;">
				<c:forEach var="c" items="${channeldetail}">
					<tr>
						<td><a href="https://www.naver.com/" class='test'>${c.boardTitle}</a></td>
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