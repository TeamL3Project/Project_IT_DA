<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<script> var contextPath = "<%=request.getContextPath()%>"; </script>
<script>
$(function(){
	$("#joinForm").click(function(){
		location.href = "/join.me";

	})

	$("#logoutLink").click(function(e) {
        e.preventDefault();
        $.ajax({
            type: "POST",
            url: "${pageContext.request.contextPath}/logout.me",
            success: function(rdata) {
            	if (rdata.success) {
            		window.location.reload();
            	}
            },
            error: function() {
                console.error("로그아웃 실패");
            }
        });
    });



	function getid() {
		var saveId = getCookie("saveid");
		if(saveId != "") {
			$("#userId").val(saveId);
			$("#remember").prop("checked",true);
		}
	} //getid()

	function saveid() {
		var expdate = new Date();
		// 기본적으로 3일동안 기억하게 함. 일수를 조절하려면 * 3에서 숫자를 조절하면 됨
		if($("#remember").prop("checked")){
		    expdate.setTime(expdate.getTime() + 1000 * 3600 * 24 * 3); // 3일
		} else {
			expdate.setTime(expdate.getTime() - 1); // 쿠키 삭제조건
		}

		setCookie("saveid", $("#userId").val(), expdate);
	} //saveid()

	function setCookie (name, value, expires) {
		document.cookie = name + "=" + escape (value) +"; path=/; expires=" + expires.toGMTString();
	} //setCookie(name,value,expires)

	function getCookie(Name) {
		var search = Name + "=";
		console.log(search);
		if (document.cookie.length > 0) { // 쿠키가 설정되어 있다면
			offset = document.cookie.indexOf(search);
			if (offset != -1) { // 쿠키가 존재하면
				offset += search.length;
		        // set index of beginning of value
		        end = document.cookie.indexOf(";", offset);
		        // 쿠키 값의 마지막 위치 인덱스 번호 설정
		        if (end == -1)
		        	end = document.cookie.length;
		        return unescape(document.cookie.substring(offset, end));
			}
		}
		return "";
	} //getCookie(Name)

	getid();

	$("#remember").click(function(){
		saveid();
	});//#chkuser_id.click


		const userId = '<%=session.getAttribute("userId")%>';
		const userProfile = '<%=session.getAttribute("userProfile")%>';
		const userFolder = '<%=util.dateService.toDay()%>';

		//id값이 있는 경우
		if (userId) {
			// 프로필 사진 파일 이름과 확장자 추가
			// 경로에 저장된 이미지를 찾고 출력하기 위함
			let profileImgPath = "";
			contextPath + "/image/Member/" + userId + "/"+ userFolder + "/"
			if (userProfile != "profile.png") {
				profileImgPath += contextPath + "/image/Member/" + userId + "/"+ userFolder + "/"+userProfile;
			} else {
				profileImgPath += contextPath + "/image/common/profile.png";
			}

			// 프로필 사진 출력
			if (profileImgPath) {
				$("#profile_img").attr("src", profileImgPath);
			}
		}

	});
</script>
<div class="backyard">
	<nav id="navbody">
		<!-- 왼쪽 상단 로고 링크 -->
		<a id="logo-brand" href="${pageContext.request.contextPath}/main">
			<img
			src="${pageContext.request.contextPath}/image/common/itda_logo.png"
			style="width: 100px; height: auto;">
		</a>
		<ul id="right_btns">
			<li id="search" style="margin-bottom: 24px;"><a id="search_btn"
				onclick="location.href='${pageContext.request.contextPath}/search.me'">
					<img
					src="${pageContext.request.contextPath}/image/common/search.png"
					style="width: 30px; height: auto;">
			</a></li>
			<%
			String userId = (String) session.getAttribute("userId");
			String userProfilePath = (String) session.getAttribute("userProfilePath"); // 프로필 사진 경로 가져오기

			if (userId != null && !userId.equals("")) {
			%>

			<!-- 로그인한 경우 프로필 사진을 표시합니다. -->
			<div class="dropdown">
				<button class="dropbtn">
					<img id="profile_img" src="${userProfilePath}"
						style="width: 30px; height: auto;" />
				</button>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/myPage.me">마이 페이지</a>
					<a href="#" id="logoutLink">로그아웃</a>
				</div>
			</div>

			<%
			} else {
			%>

			<li id="login_modal">
				<!-- 헤더의 로그인 버튼 -->
				<button type="button" class="login_btn" data-toggle="modal"
					data-target="#myModal"
					style="color: #FBD1A7; background: rgb(1, 39, 60); border: none;">로그인</button>

				<!-- Modal -->
				<form id="modalForm"
					action="${pageContext.request.contextPath}/loginProcess.me"
					method="post">
					<div class="modal fade" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header"
									style="text-align: center; border: none;">
									<h3 class="modal-title"
										style="border: none; text-align: center; margin: 20px auto auto auto;">SIGN
										IN</h3>
								</div>

								<div class="modal-body">
									<p>
										<br> <input type="text" name="userId" id="userId"
											style="width: 100%; height: 40px; border: none; background: white; border-bottom: 1px solid rgb(1, 39, 60) !important; outline: none;"
											placeholder="아이디"><br>
									</p>
									<p>
										<br> <input type="password" name="userPw" id="userPw"
											style="width: 100%; height: 40px; border: none; background: white; border-bottom: 1px solid rgb(1, 39, 60) !important; outline: none;"
											placeholder="비밀번호" autocomplete="on">
									</p>
									<div id="rememberbox">
										<label for="remember"> <input type="checkbox"
											name="remember" id="remember" value="store"><strong>아이디
												기억하기</strong>
										</label>
									</div>
									<div style="float: right">
										<a class="find_account" href="account.jsp"
											style="font-size: 8px; color: #1479a7 !important;">아이디·비밀번호
											찾기</a>
									</div>
									<br>
									<br>
									<div style="text-align: center; margin: auto;">
										<button type="submit" class="real_login_btn"
											style="color: white; background: rgb(1, 39, 60); text-align: center; width: 100%; height: auto; margin: auto;">로그인</button>
									</div>
								</div>

								<div class="modal-footer"
									style="text-align: center; border: none;">
									<a class="modal_join"
										style="text-align: center; margin: auto 0px auto auto; color: #1479a7 !important;"
										disabled>아이디가 없으신가요?</a>&nbsp; <a class="modal_join"
										href="${pageContext.request.contextPath}/join.me"
										id="joinForm"
										style="color: #1479a7 !important; text-decoration: underline; text-align: center; margin: auto auto auto 0px;">회원가입</a>
								</div>
							</div>
							<!-- modal-content end -->
						</div>
						<!-- modal-dialog end -->
					</div>
					<!-- Modal end -->
				</form>
			</li>
			<!-- login_modal end -->
			<%
			}
			%>
		</ul>
	</nav>
</div>