<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<link rel="stylesheet" href="./css/common.css" type="text/css">
<link rel="stylesheet" href="./css/myPage.css" type="text/css">
</head>
<body>
	<jsp:include page="../main/header.jsp" />
	<div class="myPage-container">
		<div class="myPage-area">
		<div class="forinline">
			<div class="myPage-profile">
				<img src="./image/common/profile.png" class="myPageprofile">
			</div>
			<a href="infoUpdate.me" class="infobutt"><img src="./image/common/option.png" class="infobuttimg"></a>
		</div>
		<hr style="border: 0.5px solid; width: 100%;">
			<div class="myPage-bar">
				<button class="btn myPage-item" id="selecbutt1">결제내역</button>
				<button class="btn myPage-item" id="selecbutt2">구독 컨텐츠</button>
				<button class="btn myPage-item" id="selecbutt3">관심 컨텐츠</button>
			</div>
			<div class="myPage-inner">
				<div class="myPage-frame">내용 출력</div>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
