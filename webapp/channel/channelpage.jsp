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
<jsp:include page="../main/header.jsp" />
<script src="../js/category.js"></script>
<html lang="ko">
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>channel Page</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
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
	overflow-x: auto;
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

#profiledetail {
	
}
</style>
</head>
<body>
	<div class="wrapper">
		<br> <br>
		<div class="info">
			<h3 style="margin: 0 10; padding: 10;">[${channel.chname}]</h3>

			<div id="profile1">
				<div id="profile"
					style="padding: 20; border-radius: 2px; border: 1.8px solid #01273c;">
					<p>
					<div class="chinfo" style="padding: 35;">${channel.chinfo}</div>
					<div class="info_button">
						<button class="btn bt-item bt-hover" id="profiledetail"
							onClick="location.href='channel_profile_detail.jsp'">자세히보기</button>

					</div>
					</p>
				</div>

				<img src="../image/channel/ccc.jpg"
					style="width: 360px; height: 320px; border-bottom-left-radius: 50px; padding: 10px;">
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
			<table class="table table-bordered">
				<tr>
					<td><a href="https://www.naver.com/" class='test'>
							[책장위고양이] 시인들의 에세이 </a></td>
					<%-- 공지사항 td에 불러오기 https://stackoverflow.com/questions/10245279/wrapping-html-table-rows-in-a-tags 사이트 참고--%>
				</tr>
				<tr>
					<td><a href="https://www.naver.com/" class='test'> 책장위고양이
							기프티콘 구글 설문지 링크를 다시 보내드립니다. </a></td>
				</tr>
				<tr>
					<td><a href="https://www.naver.com/" class='test'> 책장위고양이
							[기프티콘] 당첨을 축하하오! </a></td>
				</tr>
				<tr>
					<td><a href="https://www.naver.com/" class='test'> 공지사항
							있습니다! </a></td>
				</tr>
				<tr>
					<td><a href="https://www.naver.com/" class='test'>
							[책장위고양이] 같이사는 고양이를 소개합니다. </a></td>
				</tr>
				<tr>
					<td><a href="https://www.naver.com/" class='test'> 반갑습니다!
							앞으로 잘 부탁드립니다. </a></td>
				</tr>
			</table>
		</div>
	</div>

	<br>
	<br>
	<script>
	document.addEventListener("DOMContentLoaded", function() {
	  $(".bt-item").click(function() {
	    $(".bt-item.on").removeClass('on');
	    console.log('test');
	    $(this).addClass('on').css("box-shadow", "none");
	  });
	
	  const subscribeBtn = document.getElementById("subscribeBtn");
	
	  subscribeBtn.addEventListener("click", () => {
	    alert("구독되었습니다");
	  });
	});
</script>
</body>
<jsp:include page="../main/footer.jsp" />
</html>