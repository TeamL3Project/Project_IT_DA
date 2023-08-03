<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<jsp:include page="header.jsp" />
<html lang="kor">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>channel Page</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<style>
.wrapper {
	display: grid;
	place-items: center;
	min-height: 100dvh;
}

body {
	background-color: #CCCCCC;
}

#profile {
	background-color: #D9D9D9;
	width: 240px;
	height: 240px;
	margin: 50px;
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
</style>
<script>
	$(function() {
		$(".bt-item").click(function() {
			$(".bt-item.on").removeClass('on');
			console.log('test');
			$(this).addClass('on').css("box-shadow", "none");
		});
	});
</script>
</head>
<body>
	<div class="wrapper" style="width: 1250px;" align="center;">
		<div class="info">
			<h3>[책장 위 고양이]</h3>
			<div id="profile">
				<p>
					안녕하세요:)<br> 세 마리 고양이를 키우는<br> 집사입니다.<br> 잘 부탁드립니다.
				</p>
			</div>
			<img src="../image/ccc.jpg" style="width: 330px; height: 300px; border-bottom-left-radius: 50px;"> <br>
			<button class="btn bt-item bt-hover">구독하기</button>

			<img src="../image/alram.png" style="width: 40px; height: 40px; margin-left: 10px;">
		</div>
		<hr style="border: 1px bold silver; width="100%">
		<div>
			<button class="btn bt-item bt-hover">홈</button>
			<button class="btn bt-item bt-hover">카테고리</button>
		</div>
	</div>
<jsp:include page="footer(css포함).jsp" />
</body>
</html>
