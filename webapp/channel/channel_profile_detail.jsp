<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link href="css/common.css" rel="stylesheet" type="text/css">
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
	width: 450px;
	height: 200px;
	border: 1px solid black;
	border-radius: 15px;
	padding: 25;
	margin: 0 30;
}

.channel_info2 {
	width: 450px;
	height: 200px;
	border: 1px solid black;
	border-radius: 15px;
	padding: 25;
}

.channel_data {
	width: 934px;
    height: 220px;
    border: 1px solid black;
    border-radius: 15px;
    padding: 25;
    margin-left: -188;
    margin-bottom: 18;
}

tr>td:nth-child(odd) {
	font-weight: normal;
	width: 88px;
}

td {
	text-align: left;
}

.table-borderless {
	border: none;
}

.infotitle {
	font-weight: bolder;
	font-size: 17px;
}

.sellerinfo {
	margin-left: -10;
}

.channel_name {
	height: 130;
}
</style>
</head>
<body>
	<div class="total-content">
		<div class="wrapper">

			<div class="channel_name">
				<div class="chname">
					<h1 style="margin-left: -560; margin-top: 40; margin-bottom: 30;">[${channeldata.chname}]</h1>
				</div>
				<div class="sub_alram_btn"
					style="padding: 30; margin-top: -38; padding-left: 15; margin-left: -580;">
					<button class="btn bt-item bt-hover" id="subscribeBtn">구독하기</button>
					<img src="image/channel/alram_white.png"
						style="width: 38px; height: 38px; margin-left: 10px; display: inline-block;">
				</div>
			</div>
			<hr style="border: 1px bold silver; margin: 40px;" width="100%">
			<div class="channel_data">
				<p class="infotitle">채널 소개</p>
				<p>${channeldata.chinfo}</p>

			</div>

			<div class="channel_info">
				<div class="channel_info1">
					<p class="infotitle">구독자 통계</p>
					<div class="sellerinfo">
						<table class="table table-bordered table-borderless">
							<tr>
								<td>구독자수</td>
								<td>${sellergraph.chfollow}명</td>
							</tr>
							<tr>
								<td>방문자</td>
								<td>${sellergraph.chvisit}만명</td>
							</tr>
						</table>
					</div>
				</div>
				
				<div class="channel_info2">
					<p class="infotitle" style="margin-bottom: 0;">판매자 정보</p>
					<div class="sellerinfo">
						<table class="table table-bordered table-borderless">
							<tr>
								<td>대표자</td>
								<td>${sellerdata.userId}</td>
							</tr>
							<tr>
								<td>전화번호</td>
								<td>${sellerdata.sellerPhone}</td>
							</tr>
							<tr>
								<td>이메일</td>
								<td>${sellerdata.sellerEmail}</td>
							</tr>
						</table>
					</div>
				</div>
			</div>

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
		    alert("[${channel.chname}] 구독되었습니다.");
		  });
	});
</script>
	<jsp:include page="../main/footer.jsp" />
</body>
</html>
