<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<head>
<title>일반회원가입 페이지</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" type="text/css" href="css/joinForm.css">
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
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

     // 중복확인 버튼 클릭 핸들러를 추가합니다.
     $("#id_butt").click(function() {
         const pattern = /^\w{5,12}$/;
         const input_id = $("#showid").val();

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
		
        // 서버에 아이디 중복 확인 요청을 보내는 ajax 코드
        $.ajax({
            url: "idcheck.chl", // 실제 아이디 확인 서버 엔드포인트로 대체해야 함
            data: { id: input_id },
            success: function (resp) {
                if (resp == -1) {
                    $("#message").css('color', 'green').html("사용 가능한 아이디 입니다.");
                    checkid = true;
                    idcheck = input_id;
                } else {
                    $("#message").css('color', 'red').html("사용중인 아이디 입니다.");
                    checkid = false;
                    idcheck = '';
                }
            }
        });
    });


		// 회원가입 폼 제출 이벤트 리스너 등록
		   $("#sellerform").submit(function(e) {
							// 필요한 변수들을 가져옴
							var id = $("#showid").val().trim();
							var password = $('#password').val().trim();
							var date_birth = $('#date_birth').val().trim();
							var phone = $('#phone').val().trim();
							var email = $("#email").val().trim();

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
						    $("input[name=password]").on('keyup', function() {
						        const password = $(this).val();
						        const pattern = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*]).{6,12}$/;

						        if (!pattern.test(password)) {
						            $("#password_message").css('color', 'red').html("비밀번호는 영문/숫자/특수문자를 모두 포함한 6 ~ 12자리로 작성해주세요.");
						            checkpassword = false;
						        } else {
						            $("#password_message").css('color', 'green').html("비밀번호 형식에 맞습니다.");
						            checkpassword = true;
						        }
						    });


							// 이메일 입력 확인 및 유효성 검사
							if (email == '') {
								alert("이메일을 입력하세요");
								$('#email').focus();
								return false;
							}
							var pattern = /^[A-Za-z0-9_]{1,100}@[A-Za-z0-9_]{1,100}\.[A-Za-z0-9]{2,3}$/;
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

							// 전화번호 유효성 검사
							var phone = $('#phone').val();
							if (phone.trim().length == 13 || phone.trim().length == 14) {
							const pattern = /^[0-9]{3,4}-[0-9]{4}-[0-9]{4}$/;
								if (!pattern.test(phone)) {
									alert("전화번호를 형식에 맞게 입력하세요")
								    $('#phone').val('').focus();
							        return false;
								}
							}
							
						});//submit end

		let channelcheck = '';

	});//ready end
</script>
<div id="sellerback">
    <form name="sellerform" id="sellerform" method="post" action="joinProcess.me">
        <h1 style="margin: 30px 50px;">Sign in it-da</h1>
        <div class='num0 clearfix'>
                <label for='id' style="float: left;"><span style="color: red">*</span>아이디</label>
                <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
                    <input type="text" id="showid" name="id" placeholder="아이디를 입력하세요" maxLength="10" required> 
                    
                    <div id="message" style="color: red;"></div>
                    <input type='button' id="id_butt" value='중복확인' style="width: 20%;">
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
	       <input type='password' name='password_confirm' id='password_confirm' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num3 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>이름</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name='name' id='name' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num4 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>생년월일</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name='date_birth' id='date_birth' placeholder='YYYY-MM-DD 형식' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num5 clearfix'>
	<label for='channel' style="float: left;"><span style="color: red">*</span>성별</label>
	</div>
		<div>
			<label><input type="radio" name="gender" value="남" checked>남자</label>
			<label><input type="radio" name="gender" value="여">여자</label>
		</div>
	<div class='num6'>
		<label for='phone' style="float: left;"><span style="color: red">*</span>휴대폰번호</label>
			<input type='text' maxLength='14' name='phone' id='phone' placeholder='예:010-1234-5678' required>
	</div>
	<div class='num7 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>주소</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name=address1 id='address1' style="width: 100%;" required>
	    </div>    
	</div>
	<div class='num8 clearfix'>
		<label for='channel' style="float: left;"><span style="color: red">*</span>상세주소</label><br>
	    <div style="display: flex; justify-content: space-between; align-items: center; width: 100%;">
	        <input type='text' name='address2' id='address2' style="width: 100%;" required>
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
			<label><input type='radio' name='category' id='category1' value="경제/시사"> 경제/시사</label>
			<label><input type='radio' name='category' id='category2' value="문화예술"> 문화예술</label>
			<label><input type='radio' name='category' id='category3' value="IT트렌드"> IT트렌드</label>
			<label><input type='radio' name='category' id='category4' value="역사"> 역사</label>
			<label><input type='radio' name='category' id='category5' value="과학"> 과학</label>
			</div>
			<div id="section2">
			<label><input type='radio' name='category' id='category6' value="건강"> 건강</label>
			<label><input type='radio' name='category' id='category7' value="요리"> 요리</label>
			<label><input type='radio' name='category' id='category8' value="스포츠"> 스포츠</label>
			<label><input type='radio' name='category' id='category9' value="재태크"> 재태크</label>
			<label><input type='radio' name='category' id='category10' value="취미"> 취미</label>
			</div>
	</div>
	
	 <div class='num11 clearfix'>
                <button type='submit' value="일반회원가입" class='signup_butt'>일반회원가입</button>
                <button type='reset' value="취소" class='cancel_butt'>취소</button>
            </div>
        </form>
    </div>
</body>


