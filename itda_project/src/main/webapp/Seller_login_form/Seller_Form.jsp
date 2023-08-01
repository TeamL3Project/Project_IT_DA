<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<script src="./jquery-3.7.0.js"></script>
<style>
	#sellerback {
		width: 100%;
		display: flex;
    	justify-content: center;
	}
	#sellerform {
		width: 40%;
		height: 600px;
		border: 1px solid black;
		background: beige;
		margin: 150px auto 50px auto;
	}
	label {
	    display: inline-block;
	    max-width: 100%;
	    margin-bottom: 5px;
	    font-weight: 700;
	}
	.num1, .num2, .num3, .num4, .num5, .num6 {
		text-align: center;
		margin: 15px;
	}
	#channel {
		width: 72%;
		height: 40px;
		outline: none;
		border: none;
		border-bottom: 1px solid black;
		background: beige;
	}
	#channel_butt {
		width: 20%;
		height: 40px;
	}
	#profile {
		width: 100%;
		height: 40px;
	}
	#phone {
		width: 100%;
		height: 40px;
		outline: none;
		border: none;
		border-bottom: 1px solid black;
		background: beige;
	}
	#email {
		width: 100%;
		height: 40px;
		outline: none;
		border: none;
		border-bottom: 1px solid black;
		background: beige;
	}
	#categories {
		width: 100%;
		height: 40px;
		margin: 15px;
	}
	#intro {
		width: 100%;
		height: 40px;
		background: beige;
	}
	.signup_butt{
		width: 45%;
		height: 40px;
	}
	.cancel_butt{
		width: 45%;
		height: 40px;
	}
</style>
</head>
<body>
<script>
$(function() {
	$('#myform').submit(function() {
		var channel = $('#channel').val().trim();
		var number1 = $('#number1').val().trim();
		var email = $("#email").val().trim();
		var intro = $('#intro').val().trim();

		if (channel == '') {
			alert("채널명을 입력하세요");
			$('#channel').focus();
			return false;
		}else if (number1 == '') {
			alert("전화번호 앞자리를 입력하세요");
			$('#number1').focus();
			return false;
		}else if (number1.length != 2
			&& number1.length != 3
			&& number1.length != 4) {
			alert("전화번호 2~4자리를 입력하세요");
			$('#number1').focus();
			return false;
		}else if (number2 == '') {
			alert("전화번호 중간자리를 입력하세요");
			$('#number2').focus();
			return false;
		}else if (number2.length != 3
			&& number2.length != 4) {
			alert("전화번호 3~4자리를 입력하세요");
			$('#number2').focus();
			return false;
		}else if (number3 == '') {
			alert("전화번호 뒷자리를 입력하세요");
			$('#number3').focus();
			return false;
		}else if (number3.length != 4) {
			alert("전화번호 4자리를 입력하세요");
			$('#number3').focus();
			return false;
		}else if (email == '') {
			alert("이메일을 입력하세요");
			$('#email').focus();
			return false;
		}

		var categorys = $('input[name="category"]:checked').length;
		if (categorys < 1) {
			alert("카테고리를 1개 이상 선택하세요")
			return false;
		}else if (intro == '') {
			alert("채널소개를 입력하세요");
			$('#intro').focus();
		return false;
		}
		
		
	})//submit end

							
	let channelcheck = '';

	$('#channel-butt').click(function() { 								//채널명 유효성 검사
		var channel = $('#channel').val().trim();

		if (channel == '') {
			alert("채널명을 입력하세요");
			$('#channel').focus();
		}else {
			pattern = /^[a-z][A-Za-z0-9_]{10,}$/;
			if (pattern.test(channel)) {
				channelcheck = channel;
				var ref = `channelcheck.html?channel=${channel}`;
				alert('채널명을 사용할 수 있습니다')
				window.open(ref, 'channelcheck',
				'width = 300, height = 250');
			}else if (channel.length < 1) {
				alert("채널명은 최소 한글자 이상이어야 합니다.");
				$('#channel').val('');
				$('#channel').focus();
			}
			
		}

	})//click end
	
	
	

	$('form').submit(function() { 								//회원가입 버튼 클릭할때 이벤트
			const channel = $('#channel');

			if ($.trim(channel.val()) == '') {
				alert('채널명을 입력하세요');
				channel.focus();
				eturn false;
			}

			let submit_channel_val = $.trim(channel.val()); 	//submit channel값과 중복검사 channel값이 다른 경우
			
			if (submit_channel_val != channelcheck) {
				alert('채널명 중복검사를 하세요');
				eturn false;
			}

			$('#number1').keyup(function() {
				var number1 = $('#number1').val();
				if (number1.trim().length == 2 || number1.trim().length == 3
													|| number1.trim().length == 4) {
						
					pattern = /^[0-9]{3}$/;
							
					if (pattern.test($(this).val())) {
						$('#number2').focus();
					} else {
						alert("전화번호 앞자리를 형식에 맞게 입력하세요")
						$('#number1').val('').focus();
					}
				}
				
			})//keyup end

						
	})//submit end


})//ready end

	</script>
<div id="sellerback">
<form name='sellerform' id='sellerform' method='post' action='send'>
	<div class='num1'>
		<label for='channel' style="float: left;">채널명</label><br>
			<input type='text' placeholder='영문/숫자만 사용 가능, 100자 이하' name='channel' id='channel'>
			<input type='button' id="channel_butt" value='중복확인'>
	</div>
	<div class='num2'>
		<label for='profile' style="float: left;">채널 프로필</label>
			<input type='image' name='profile' id='profile'>
	</div>
	<div class='num3'>
		<label for='phone' style="float: left;">휴대폰번호</label>
			<input type='text' maxLength='12' name='phone' id='phone' placeholder='예:010-1234-5678'>
	</div>
	<div class='num4'>
		<label for='email' style="float: left;">이메일</label>
			<input type='text' name='email' id='email' placeholder='예:itda@itda.com'>
	</div>
	<div id="categories">
		<label for='category' style="float: left;">채널 카테고리</label>
			<input type='radio' name='category1' id='category1' value="공부"> 공부
			<input type='radio' name='category2' id='category2' value="게임"> 게임
			<input type='radio' name='category3' id='category3' value="운동"> 운동
			<input type='radio' name='category4' id='category4' value="등산"> 등산
			<input type='radio' name='category5' id='category5' value="낚시"> 낚시 <br>
			<input type='radio' name='category6' id='category6' value="경제"> 경제
			<input type='radio' name='category7' id='category7' value="문학"> 문학
			<input type='radio' name='category8' id='category8' value="정치"> 정치
			<input type='radio' name='category9' id='category9' value="환경"> 환경
			<input type='radio' name='category10' id='category10' value="역사"> 역사
	</div>
	<div class='num5'>	
		<label for='intro' style="float: left;">채널 소개글</label>
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
