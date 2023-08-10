<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<head>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
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
				<a href="infoUpdate.me" class="infobutt"><img
					src="./image/common/option.png" class="infobuttimg"></a>
			</div>
			<hr style="border: 0.5px solid; width: 100%;">
			<div class="update-form-container">
				<div class="update-form">
					<form class="infoupdateform" method="post" action="infoUpdateProcess.me">
						<div class='num1 clearfix'>
							<label for='infouserId' style="float: left;"><span
								style="color: red">*</span>채널명</label><br>
							<div style="display: flex; justify-content: space-between;
								 align-items: center; width: 100%;">
								<input type='text' placeholder='영문/숫자만 사용 가능, 100자 이하'
									name='infouserId' id='infouserId' style="width: 80%;">
								<input type='button' id="infouserId_butt" value='중복확인'
									style="width: 20%;">
							</div>
						</div>
						<div class='num2'>
							<label for='infouserPw' style="float: left;">비밀번호</label>
							<input type='text' maxLength='14' name='infouserPw' id='infouserPw'
							 placeholder='예:010-1234-5678'>
						</div>
						<div class='num3'>
							<label for='infouserPhone' style="float: left;">휴대폰번호</label>
							<input type='text' maxLength='14' name='infouserPhone' id='infouserPhone'
							 placeholder='예:010-1234-5678'>
						</div>
						<div class='num4'>
							<label for='infouserEmail' style="float: left;">이메일</label>
							<input type='text' name='infouserEmail' id='infouserEmail'
							 placeholder='예:itda@itda.com'>
						</div>
						<div id="infocategories clearfix">
							<label for='infousercategory' style="float: left; margin-left: 15px;">
								채널 카테고리</label><br>
							<div id="section1">
								<input type='radio' name='category' id='category1' value="1">
								공부 <input type='radio' name='category' id='category2' value="2">
								게임 <input type='radio' name='category' id='category3' value="3">
								운동 <input type='radio' name='category' id='category4' value="4">
								등산 <input type='radio' name='category' id='category5' value="5">
								낚시
							</div>
							<div id="section2">
								<input type='radio' name='category' id='category6' value="6">
								경제 <input type='radio' name='category' id='category7' value="7">
								문학 <input type='radio' name='category' id='category8' value="8">
								정치 <input type='radio' name='category' id='category9' value="9">
								환경 <input type='radio' name='category' id='category10'
									value="10"> 역사
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<jsp:include page="../main/footer.jsp" />
</body>
