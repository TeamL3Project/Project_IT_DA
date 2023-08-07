<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>판매회원가입 페이지</title>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" href="../css/sellerform.css">
</head>
<body>
<script>
$(function() {
	$('#sellerform').submit(function() {
		var channel = $('#channel').val().trim();
		var phone = $('#phone').val().trim();
		var email = $("#email").val().trim();
		var intro = $('#intro').val().trim();
		
		if (channel == '') {
			alert("채널명을 입력하세요");
			$('#channel').focus();
			return false;
		
		}else if (phone.length != 13 && phone.length != 14) {
			alert("전화번호를 예시에 맞게 13~14자리를 입력하세요");
			$('#phone').focus();
			return false;
			
		}else if (email == '') {
			alert("이메일을 입력하세요");
			$('#email').focus();
			return false;
		
		}

		var categorys = $('input[name="category"]:checked').length;
		if (categorys === 0) {
			alert("채널의 주제로 삼을 카테고리를 선택하세요")
			return false;
			
		}else if (intro == '') {
			alert("채널 소개글을 입력하세요");
			$('#intro').focus();
			return false;
		
		}
		
		const channel1 = $('#channel');

		if ($.trim(channel1.val()) == '') {
			alert('채널명을 입력하세요');
			channel.focus();
			return false;
		}

		let submit_channel_val = $.trim(channel1.val()); 	//submit channel값과 중복검사 channel값이 다른 경우
			
		if (submit_channel_val != channelcheck) {
			alert('채널명 중복검사를 하세요');
			return false;
		}

			
		var phone1 = $('#phone').val();
				
		if (phone1.trim().length == 13 || phone1.trim().length == 14) {
						
			var pattern = /^[0-9]{3,4}-[0-9]{4}-[0-9]{4}$/;
							
			if (pattern.test(phone1)) {
				$('#intro').focus();
						
			}else {
				alert("전화번호를 형식에 맞게 입력하세요")
				$('#phone').val('').focus();
				return false;
			}
		}
				
		

		var email1 = $('#email').val();
		var pattern = /^[A-Za-z0-9_]{1,100}@[A-Za-z0-9_]{1,100}\.[A-Za-z0-9]{1,10}$/;
							
		if (pattern.test(email1)) {
			$('#intro').focus();
					
		}else {
			alert("이메일의 형식을 확인해주세요");
			$('#email').val('').focus();
			return false;
		}
			
		
		
	});//submit end

	
	
	
	
	
	
	
	let channelcheck = '';
	
	
	$('#channel_butt').click(function() { 								//채널명 유효성 검사
		var channel = $('#channel').val().trim();

		if (channel == '') {
			alert("채널명을 입력하세요");
			$('#channel').focus();
			return false;
			
		}else {
			var pattern = /^[A-Za-z0-9]{1,100}$/;
			
			if (pattern.test(channel)) {
				channelcheck = channel;
				var ref = `channelcheck.html?channel=${channel}`;
				alert('채널명을 사용할 수 있습니다');
				
			}else if (channel.length < 1) {
				alert("채널명은 최소 한글자 이상이어야 합니다.");
				$('#channel').val('');
				$('#channel').focus();
				return false;
			
			}else if (!pattern.test(channel)) {
				alert("채널명은 영문 대소문자와 숫자만 사용 가능하며, 1자 이상 100자 이하로 입력해야 합니다.");
                $('#channel').val('');
                $('#channel').focus();
                return false;
			}
			
		}

	});//click end
	
	
	
	//미리보기 기능
	const profileInput = document.getElementById('profile');
	const previewImage = document.getElementById('previewImage');

	profileInput.addEventListener('change', function(event) {
    const file = event.target.files[0];
	    
	    if (file) {
			const reader = new FileReader();
		        
			reader.onload = function() {
				previewImage.src = reader.result;
				previewImage.style.display = 'inline';
				$('#profile').css("display", "none");
			}
		        
			reader.readAsDataURL(file);
		        
		}else {
			previewImage.src = '#';
			previewImage.style.display = 'none';
		        
		}
	    
	    
	});//change end
	
	
	
	//미리보기 사진을 클릭시 다시 파일선택 가능
	previewImage.addEventListener('click', function() {
		profileInput.click();
	
	});


	
});//ready end

	</script>
<div id="sellerback">
<form name='sellerform' id='sellerform' method='post' action='send'>
	<h1 style="margin: 30px 50px;">Join it-da</h1>
	<div class='num0'>
		<label for='id' style="float: left;">&nbsp;아이디</label>
		<input type="text" id="showid" value="테스트 아이디" readOnly><!-- ${itda_user.id} 유저의 아이디를 보여줄 예정 -->
	</div>
	<div class='num1 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>채널명</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' placeholder='영문/숫자만 사용 가능, 100자 이하' name='channel' id='channel' style="width: 80%;" required>
	        <input type='button' id="channel_butt" value='중복확인' style="width: 20%;">
	    </div>
	</div>
	<div class='num2 clearfix'>
		<label for='profile' style="float: left;">채널 프로필<span style="font-size: 12px;"> (설정하지 않으면 기본프로필로 나타납니다.)</span></label><br>
			<input type='file' name='profile' id='profile' 
			 style=" margin-top: 20px;" accept="image/*">
			<br>
			<img id="previewImage" src="#" alt="프로필 미리보기" style="max-width: 300px;
			 max-height: 70px; display: none; margin-right: 550px;
			 border: 2px solid black; border-radius: 3%;">
	</div>
	<div class='num3'>
		<label for='phone' style="float: left;"><span style="color: red">*</span>휴대폰번호</label>
			<input type='text' maxLength='14' name='phone' id='phone' placeholder='예:010-1234-5678' required>
	</div>
	<div class='num4'>
		<label for='email' style="float: left;"><span style="color: red">*</span>이메일</label>
			<input type='text' name='email' id='email' placeholder='예:itda@itda.com' required>
	</div>
	<div id="categories clearfix">
		<label for='category' style="float: left; margin-left: 15px;"><span style="color: red">*</span>채널 카테고리</label><br>
			<div id="section1">
			<input type='radio' name='category' id='category1' value="공부"> 공부
			<input type='radio' name='category' id='category2' value="게임"> 게임
			<input type='radio' name='category' id='category3' value="운동"> 운동
			<input type='radio' name='category' id='category4' value="등산"> 등산
			<input type='radio' name='category' id='category5' value="낚시"> 낚시</div>
			<div id="section2">
			<input type='radio' name='category' id='category6' value="경제"> 경제
			<input type='radio' name='category' id='category7' value="문학"> 문학
			<input type='radio' name='category' id='category8' value="정치"> 정치
			<input type='radio' name='category' id='category9' value="환경"> 환경
			<input type='radio' name='category' id='category10' value="역사"> 역사</div>
	</div>
	<div class='num5'>	
		<label for='intro' style="float: left;"><span style="color: red">*</span>채널 소개글</label><br>
			<textarea rows='10' name='intro' id='intro' maxLength='300'
			 placeholder='채널 소개글을 작성해주세요. 최대 300자'></textarea>
	</div>
	<div class='num6 clearfix'>
		<button type='submit' value="판매회원가입" class='signup_butt'>판매회원가입</button>
		<button type='reset' value="취소" class='cancel_butt'>취소</button>
	</div>
		
</form>
</div>
</body>
