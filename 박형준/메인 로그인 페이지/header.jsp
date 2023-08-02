<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<style>
	.backyard {
		width: 100%;
		background: rgb(1, 39, 60);
		display: flex;
    	justify-content: center;
	}
	
	#navbody {
		width: 70%;
		background: rgb(1, 39, 60);
		
	}

	#logo-brand {
    	position: relative;
    	top: 0;
    	left: 100px;
  	}
	
	
	#right_btns {
		float: right;
		display: flex;
		align-items: center;
		position: relative;
		right: 100px;
		top: 25px;
		list-style: none;
	}

	#right_btns li {
		margin-left: 15px;
	}
	
	#search_btn {
		width: 100px;
		height: 50px;
		cursor: pointer;
		
	}
	
	#login_btn {
		width: 100px;
		height: 50px;
	}
	
	#login_btn:hover {
		background: #FBD1A7;
        color: #01273C;
        opacity: 0.8;
        border: 1px solid #FBD1A7;
	}
</style>
<div class="backyard">
<nav id="navbody">
	<!-- 왼쪽 상단 로고 링크 -->
	<a id="logo-brand" href="main.jsp"><img src="../image/common/itda_logo.png" style="width: 100px; height: auto;"></a>	
	<ul id="right_btns">
    	<li id="search">
	      	<a id="search_btn" onclick="location.href='search.jsp'">
		  		<img src="../image/common/search.png" style="width: 30px; height: auto;">
		  	</a>
		</li>
		
	<%
		String id = (String) session.getAttribute("id");
		if (id != null && !id.equals("")) {
	%>
	
		<!-- 로그인이 되어 있는 경우 프로필사진 출력 -->
		<div id="login_img"><a class="nav-link" href="mypage.jsp"><img src="/../image/common/profile.png"></a></div>
		<div id="logout_tg"><a class="nav-link" href="logout.jsp">로그아웃</a></div>
		
	<%
		}else {
	%>
	  
		<li id="login_modal">
      	<!-- 헤더 로그인 버튼 -->
		<button type="button" class="login_btn" data-toggle="modal" data-target="#myModal"
		 		style="color:#FBD1A7; background: rgb(1, 39, 60); border: none;"
		 		onmouseover="this.style.background='#FBD1A7'; this.style.color='#01273C';
		 					 this.style.opacity='0.8';"
    			onmouseout="this.style.background='rgb(1, 39, 60)'; this.style.color='rgb(251, 209, 167)';
    						this.style.opacity='1';">로그인</button>
			<!-- Modal -->
			<div class="modal fade" id="myModal">
				<div class="modal-dialog">
				<div class="modal-content" style="background: rgb(204, 204, 204);">
					<div class="modal-header" style="text-align: center; border: none;">
						<h3 class="modal-title" style="
						 border: none; text-align: center; margin:20px auto auto auto;">SIGN IN</h3>
					</div>
					<div class="modal-body">
				    	<p><br>
				    		<input type="text" style="width:100%; height: 40px;
				    			border: none; background: rgb(204, 204, 204);
				    			border-bottom: 1px solid rgb(1, 39, 60) !important;
				    			outline: none;"
				    			placeholder="아이디"><br>
				    	</p>
						<p><br>
							<input type="text" style="width:100%; height: 40px;
				    			border: none; background: rgb(204, 204, 204);
				    			border-bottom: 1px solid rgb(1, 39, 60) !important;
				    			outline: none;"
				    			placeholder="비밀번호">
						</p>
						<div style="float: right">
							<a class="find_account" href="account.jsp"
								style="color: black; font-size: 8px;">아이디·비밀번호 찾기</a>
						</div><br><br>
						<div style="text-align:center; margin:auto;">
						<button type="submit" class="btn" style="color: white;
							background: rgb(1, 39, 60); text-align:center;
							width: 100%; height: auto; margin:auto;">로그인</button>
						</div>
					</div>
					<div class="modal-footer" style="text-align: center; border: none;">
						<a class="modal_join" style="color: black; disabled
								text-align: center; margin: auto 0px auto auto;">아이디가 없으신가요?</a>&nbsp;
						<a class="modal_join" href="join.jsp" style="color: black;
							text-decoration: underline; text-align: center; margin: auto auto auto 0px;;">회원가입</a>
					</div>
				        
				</div>	<!-- modal-content end -->
				</div>	<!-- modal-dialog end -->
			</div>	<!-- Modal end -->
		</li><!-- login_modal end -->
	<%
		}
	%>
	</ul>
</nav>
</div>