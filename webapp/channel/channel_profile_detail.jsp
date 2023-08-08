<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="webapp/css/common.css" rel="stylesheet" type="text/css">
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
<html lang="kor">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>채널정보 자세히보기</title>

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

.channel_info {
	width: 1200;
	height: 200;
	display: inherit;
	margin-right: -20;
	display: inherit;
}

.channel_info1 {
	width: 900px;
	height: 200px;
	border: 1px solid black;
	border-radius: 15px;
	padding: 20;
	margin: 0 30;
}

.channel_info2 {
	width: 900px;
	height: 200px;
	border: 1px solid black;
	border-radius: 15px;
	padding: 20;
}

.channel_data {
	width: 1170px;
	height: 200px;
	border: 1px solid black;
	border-radius: 15px;
	padding: 20;
	margin-left: 50;
}
</style>
</head>
<body>
	<div class="wrapper">
		<br> <br>
		<div class="channel_name">
			<h1 style="margin-left: -560;">[${channeldata.chname}]</h1>
			<br>
			<div class="sub_alram_btn"
				style="padding: 30; margin-top: -38; padding-left: 15; margin-left: -580;">
				<button class="btn bt-item bt-hover" id="subscribeBtn">구독하기</button>
				<img src="../image/channel/alram_white.png"
					style="width: 38px; height: 38px; margin-left: 10px; display: inline-block;">
			</div>
		</div>
		<hr style="border: 1px bold silver;" width="100%">
		<br>
		<div class="channel_info">
			<div class="channel_info1">
				<h2>채널 소개</h2>
				<p>${channeldata.chinfo}</p>

			</div>
			<br>
			<div class="channel_info2">
				<h2>판매자 정보</h2>
			</div>
		</div>

		<br>

		<div class="channel_data">
			<h2>구독자 통계</h2>
			<p>고양이</p>
		</div>
		<br>

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
</body>
</html>

