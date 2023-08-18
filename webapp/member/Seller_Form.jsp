<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
<title>판매회원가입 페이지</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<script src="http://code.jquery.com/jquery-latest.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="./css/sellerform.css">
<script>
$(function() {
	$('#sellerform').submit(function() {
		var channel = $('#channel').val();
		var phone = $('#phone').val();
		var email = $("#email").val();
		var intro = $('#intro').val();
		
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

		
		let channelcheck = '';
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
			
		return true;
		
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

	
	
	

	$('input[type=file]').change(function(e){
		const inputfile = $(this).val().split('\\');
		const profileName = inputfile[inputfile.length - 1];		//inputfile.length - 1 = 2
		const pattern = /(gif|jpg|jpeg|png)$/i;						//i(ignore case) : 대소문자 무시를 의미한다
		
		if (pattern.test(profileName)) {
			$('#profileName').text(profileName);
			
			const reader = new FileReader();						//파일을 읽기 위해 객체 생성
			reader.readAsDataURL(event.target.files[0]);
			
			reader.onload = function(){								//읽기에 성공한 경우 실행되는 이벤트 핸들러
				$('#previewImage > img').attr('src', this.result);
			
			};
		}else {
			alert('이미지 파일(gif, jpg, jpeg, png)가 아닌 경우 업로드되지 않습니다.');
			$(this).val('');
		
		}
		
	});//change end
	
	
	$(".cancel_butt").click(function(){
		location.href="${pageContext.request.contextPath}/main";
	})
	
	document.querySelector('.btn-Upload').addEventListener('click', function() {
        document.getElementById('profile').click();
    });
	
});//ready end

</script>
</head>
<body style="margin: 0;">
<div id="sellerback">
<form name='sellerform' id='sellerform' enctype="multipart/form-data" method='post'
 action='${pageContext.request.contextPath}/sellerjoinprocess.me'>
	<h1 style="margin: 30px 50px;">Join Seller</h1>
	<div class='num0'>
		<label for='id' style="float: left;">&nbsp;아이디</label>
		<input type="text" name="userid" id="showid" value="${userId}" readOnly>
	</div>
	<div class='num1 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>채널명</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' placeholder='영문/숫자만 사용 가능, 100자 이하' name='channel' id='channel' style="width: 80%;" required>
	        <input type='button' id="channel_butt" value='중복확인' style="width: 20%;">
	    </div>
	</div>
	<div class='num2 clearfix'>
	<label for='profile' style="float: left;">채널 프로필<span style="font-size: 12px; display: felx;
	 padding-right: 130px;"> (설정하지 않으면 기본프로필로 나타납니다.)</span></label><br>
		<div id="profile_select">
			<label for="inputFile">
				<div class="btn-Upload">파일 선택</div>
			<input type='file' name='profile' id='profile' style="margin-top: 20px;" accept="image/*">
			</label>
		</div>
		<div id="profile_preview">
			<span id="profileName"></span>
			<span id="previewImage">
				<c:choose>
					<c:when test="${empty param.profile}">
						<c:set var="src" value="image/common/profile.png" />
					</c:when>
					<c:otherwise>
						<c:set var="src" value="${param.profile}" />
					</c:otherwise>
				</c:choose>
				<img src="${src}" width="100px" alt="image/*">
			</span>
		</div>
	<br>
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
			<input type='radio' name='category' id='category1' value="1"> 경제/시사
			<input type='radio' name='category' id='category2' value="2"> 문화/예술
			<input type='radio' name='category' id='category3' value="3"> IT트렌트
			<input type='radio' name='category' id='category4' value="4"> 역사
			<input type='radio' name='category' id='category5' value="5"> 과학</div>
			<div id="section2">
			<input type='radio' name='category' id='category6' value="6"> 건강
			<input type='radio' name='category' id='category7' value="7"> 요리
			<input type='radio' name='category' id='category8' value="8"> 스포츠
			<input type='radio' name='category' id='category9' value="9"> 재테크
			<input type='radio' name='category' id='category10' value="10"> 취미</div>
	</div>
	<div class='num5'>	
		<label for='info' style="float: left;"><span style="color: red">*</span>채널 소개글</label><br>
			<textarea rows='10' name='info' id='info' maxLength='300'
			 placeholder='채널 소개글을 작성해주세요. 최대 300자' required></textarea>
	</div>
	<div class='num6 clearfix'>
		<button type='submit' value="판매회원가입" class='signup_butt'>판매회원가입</button>
		<button type='button' class='cancel_butt'>취소</button>
	</div>
		
</form>
</div>
</body>
