<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>일반회원가입 페이지</title>
    <script src="../js/jquery-3.7.0.js"></script>
<style>
	#sellerback {
		width: 100%;
		display: flex;
    	justify-content: center;
    	text-align: center;
	}
	#sellerform {
		width: 40%;
		border: 1px solid black;
		margin: 150px auto 50px auto;
		background: white;
	}
	label {
	    display: inline-block;
	    max-width: 100%;
	    margin-bottom: 5px;
	    font-weight: 700;
	}
	#section1, #section2 {
		margin: 5px 0px;
	}
	.num0, .num1, .num2, .num3, .num4, .num5, 
	.num6, .num7, .num8, .num9, .num10, .num11 {
		text-align: center;
		margin: 15px;
	}
	
	#name, #date_birth, #address, #detail_address, 
	#zip_code, #password, #password_confirm {
		width: 100%;
		height: 40px;
		outline: none;
		border: none;
		border-bottom: 1px solid black;
	}
	#password_butt {
		width: 20%;
		height: 40px;
		background: rgb(1, 39, 60);
		color: white;
		border-radius: 3%;
		cursor: pointer;
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
	}
	#email {
		width: 100%;
		height: 40px;
		outline: none;
		border: none;
		border-bottom: 1px solid black;
	}
	#categories {
		width: 100%;
		height: 40px;
		margin: 15px;
	}
	input[type="radio"] {
  		margin-left: 30px;
	}
	#intro {
		width: 100%;
		height: 150px;
		resize: none;
	}
	.signup_butt{
		width: 45%;
		height: 40px;
		background: rgb(1, 39, 60);
		color: white;
		border-radius: 3%;
		cursor: pointer;
	}
	.cancel_butt{
		width: 45%;
		height: 40px;
		background: rgb(1, 39, 60);
		color: white;
		border-radius: 3%;
		cursor: pointer;
	}
	#showid {
		width: 100%;
		height: 40px;
		outline: none;
		border: none;
		border-bottom: 1px solid black;
	}
</style>
</head>
<body>
<script>

$(function() {
	 let checkid = false;
     let checkemail = false;
     let idcheck = ''; // 중복된 아이디 검사 결과를 저장하기 위한 변수 추가
     
     function checkId(id) {
         return id === idcheck;
     }

     $("input[name=id]").on('input', function () {
         const pattern = /^\w{5,12}$/;
         const input_id = $("input:eq(0)").val();

         if (!pattern.test(input_id)) {
             $("#message").css('color', 'red').html("영문자 숫자 _로 5 ~ 12자 가능합니다.");
             checkid = false;
             return;
         }

        
        // Enter 키를 누를 때 폼 제출 방지
        $("#sellerform").on("keydown", function (e) {
            if (e.key === "Enter") {
                e.preventDefault();
                return false;
            }
        });
        
        // 버튼 클릭 시 폼 제출
        $("#submit_btn").on("click", function () {
            $("#sellerform").submit();
        });
		
        $.ajax({
            url: "idcheck.net", // 실제 아이디 확인 서버 엔드포인트로 대체해야 함
            data: { id: input_id },
            success: function (resp) {
                if (resp == -1) {
                    $("#message").css('color', 'green').html("사용 가능한 아이디 입니다.");
                    checkid = true;
                    idcheck = input_id;
                } else {
                    $("#message").css('color', 'blue').html("사용중인 아이디 입니다.");
                    checkid = false;
                    idcheck = '';
                }
            }
        });
    });
     
     // 중복 확인 버튼 클릭 핸들러를 추가합니다.
     $("#password_butt").on('click', function () {
            performIdCheck();
        });

     function performIdCheck() {
         // 키를 누르는 것 외에도 다른 입력 방법 (예: 붙여넣기, 드래그 등)에도 이벤트가 트리거되도록 input으로 처리
    
            const pattern = /^\w{5,12}$/;
            const input_id = $("input:eq(0)").val();

            if (!pattern.test(input_id)) {
                $("#message").css('color', 'red').html("영문자 숫자 _로 5 ~ 12자 가능합니다.");
                checkid = false;
                return;
            }
         
         
// 회원가입 폼 제출 이벤트 리스너 등록
    $("#sellerform").submit(function(e) {
        e.preventDefault(); // 폼 기본 동작을 중단함으로써 무결성 검사 전에 폼 전송을 중단
    
    // 필요한 변수들을 가져옴
     var id = $("#showid").val().trim();
     var password = $('#password').val().trim();
     var date_birth = $('#date_birth').val().trim();
     var phone = $('#phone').val().trim();
     var email = $("#email").val().trim();
     var intro = $('#intro').val().trim();
    
    
    //생년월일 중복 검사
     if (!date_birth) {
        alert("생년월일을 입력하세요");
        $("input[name='date_birth']").val('').focus();
        return false;
    }
    
  // 생년월일 유효성 검사
     const dateOfBirthPattern = /^(19|20)\d\d-(0\d|1[0-2])-(0\d|1\d|2\d|3[01])$/; // YYYY-MM-DD 형식
     if (!dateOfBirthPattern.test(date_birth)) {
         alert("올바른 생년월일 형식인 YYYY-MM-DD로 입력하세요");
         $('#date_birth').val('').focus();
         return false;
     }
    
    // 아이디 입력 확인
    if (id == '') {
        alert('아이디를 입력하세요.');
        $("#showid").focus();
        return false;
    }

    // 아이디 중복 검사
    if (!checkid) {
        alert("사용 가능한 아이디인지 확인하세요.");
        $("#showid").focus();
        return false;
    }

    // 비밀번호 입력 확인
    if (password == '') {
        alert("비밀번호를 입력하세요");
        $('#password').focus();
        return false;
    } else if ($("#password_confirm").val().trim() != password) {
        alert("비밀번호가 일치하지 않습니다");
        $("#password_confirm").focus();
        return false;
    }
    
    //비밀번호 유효성 검사
     const password = $('#password').val();
        const pattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{6,12}$/;
        if (!pattern.test(password)) {
            alert("비밀번호는 영문/숫자/특수문자를 모두 포함한 6 ~ 12자리로 작성해주세요.");
            $('#password').focus();
            return;
        }

    // 휴대폰번호 유효성 검사
    if (phone.length != 13 && phone.length != 14) {
        alert("전화번호를 예시에 맞게 13~14자리를 입력하세요");
        $('#phone').focus();
        return false;
    }

    // 이메일 입력 확인 및 유효성 검사
    if (email == '') {
        alert("이메일을 입력하세요");
        $('#email').focus();
        return false;
    }
    const pattern = /^[A-Za-z0-9_]{1,100}@[A-Za-z0-9_]{1,100}\.[A-Za-z0-9]{2,3}$/;
    if (!pattern.test(email)) {
        alert("이메일의 형식을 확인해주세요");
        $('#email').val('').focus();
        return false;
    }

    // 카테고리 선택 확인
    var categorys = $('input[name="category"]:checked').length;
    if (categorys === 0) {
        alert("채널의 주제로 삼을 카테고리를 선택하세요");
        return false;
    }
		
    // ID 중복 검사 재확인
    const idInput = $('#showid');
    if ($.trim(idInput.val()) == '') {
        alert('아이디를 입력하세요');
        idInput.focus();
        return;
    }
    let submit_id_val = $.trim(idInput.val());
    if (submit_id_val != idcheck) {
        alert('아이디 중복검사를 하세요');
        return;
    }
    if (checkId(submit_id_val)) {
        alert('이미 사용중인 아이디입니다.');
        return;
    }
			
    // 전화번호 유효성 검사
    var phone1 = $('#phone').val();
    if (phone1.trim().length == 13 || phone1.trim().length == 14) {
        var pattern = /^[0-9]{3,4}-[0-9]{4}-[0-9]{4}$/;
        if (!pattern.test(phone1)) {
            alert("전화번호를 형식에 맞게 입력하세요")
            $('#phone').val('').focus();
            return false;
        }
    }

		var email1 = $('#email').val();
		var pattern = /^[A-Za-z0-9_]{1,100}@[A-Za-z0-9_]{1,100}\.[A-Za-z0-9]{1,10}$/;
							
		if (!pattern.test(email_value) && email_value !== '') {
			$("#email_message").css('color', 'red').html("이메일 형식이 맞지 않습니다.");
			checkemail = false;
		}else if (email_value === ''){
		    $("#email_message").html("");
		    checkemail = false;
		} else {
			$("#email_message").css('color', 'green').html("이메일 형식에 맞습니다.");
			checkemail = true;
		}

	});//submit end

		let channelcheck = '';

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

			} else {
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
    <form name="sellerform" id="sellerform" method="post" action="send">
        <h1 style="margin: 30px 50px;">SIGN it-da</h1>
        <div class='num0 clearfix'>
                <label for='id' style="float: left;"><span style="color: red">*</span>아이디</label>
                <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
                    <input type="text" id="showid" name="id" placeholder="아이디를 입력하세요" maxLength="10"> 
                    <div id="message" style="color: red;"></div>
                    <input type='button' id="password_butt" value='중복확인' style="width: 20%;">
                </div>
            </div>
	 <div class='num1 clearfix'>
                <label for='password' style="float: left;"><span style="color: red">*</span>비밀번호</label><br>
                <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
                    <input type='password' placeholder='영문/숫자만 사용 가능, 10자 이하' name='password' id='password' style="width: 100%;" required>
                </div>
            </div>
	<div class='num2 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>비밀번호 확인</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	       <input type='text' name='password_confirm' id='password_confirm' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num3 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>이름</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name='name' id='name' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num4 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>생년월일 (수정)</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name='date_birth' id='date_birth' placeholder='YYYY-MM-DD 형식' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num5 clearfix'>
	<label for='channel' style="float: left;"><span style="color: red">*</span>성별</label>
	</div>
		<div>
			<input type="radio" name="gender" value="남" checked><span>남자</span>
			<input type="radio" name="gender" value="여"><span>여자</span>
		</div>
	<div class='num6'>
		<label for='phone' style="float: left;"><span style="color: red">*</span>휴대폰번호</label>
			<input type='text' maxLength='14' name='phone' id='phone' placeholder='예:010-1234-5678' required>
	</div>
	<div class='num7 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>주소</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name=address id='address' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num8 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>상세주소</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name='detail_address' id='detail_address' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num9 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>우편번호</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name='zip_code' id='zip_code' style="width: 100%;" required>
	    </div>    
	</div>
	
	
	<div class='num10'>
		<label for='email' style="float: left;"><span style="color: red">*</span>이메일</label>
			<input type='text' name='email' id='email' placeholder='예:itda@itda.com' required>
	</div>
	<div id="categories clearfix">
		<label for='category' style="float: left; margin-left: 15px;"><span style="color: red">*</span>관심 카테고리</label><br>
			<div id="section1">
			<input type='radio' name='category' id='category1' value="경제/시사"> 경제/시사
			<input type='radio' name='category' id='category2' value="문화예술"> 문화예술
			<input type='radio' name='category' id='category3' value="IT트렌드"> IT트렌드
			<input type='radio' name='category' id='category4' value="역사"> 역사
			<input type='radio' name='category' id='category5' value="과학"> 과학</div>
			<div id="section2">
			<input type='radio' name='category' id='category6' value="건강"> 건강
			<input type='radio' name='category' id='category7' value="요리"> 요리
			<input type='radio' name='category' id='category8' value="스포츠"> 스포츠
			<input type='radio' name='category' id='category9' value="재태크"> 재태크
			<input type='radio' name='category' id='category10' value="취미"> 취미</div>
	</div>
	
	 <div class='num11 clearfix'>
                <button type='submit' value="일반회원가입" class='signup_butt'>일반회원가입</button>
                <button type='reset' value="취소" class='cancel_butt'>취소</button>
            </div>
        </form>
    </div>
</body>
