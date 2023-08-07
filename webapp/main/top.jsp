<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<body>
	<div class="backyard2">
		<div class="intro" style="margin:20px auto; background: rgb(1, 39, 60);">
			<div id="title">
				<h2>&nbsp;&nbsp;&nbsp;IT:DA</h2>
			</div>
			<div id="line">
				<h4>누구나 쉽게 콘텐츠를 생산할 수 있는 공간, 누구나 창작자가 될 수 있는 공간</h4>
			</div>
			<div>
				<button type="button" id="join_seller">판매회원 가입하기</button>
			</div>
		</div>
	</div>
	<script>
		$(document).ready(function(){
			$("#join_seller").click(function(e){
				if (!<%= (String) session.getAttribute("userId") %>) {
					e.preventDefault();
					alert("로그인이 필요합니다.");
					
				}else {
					location.href = "user_Check.me";
				}
				
			});//click end
			
		});//ready end
	</script>
</body>