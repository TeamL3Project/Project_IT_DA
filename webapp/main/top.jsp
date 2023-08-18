<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<head>
<script>
	$(document).ready(function(){
		$("#join_seller").click(function(e){
			var id = `<%= session.getAttribute("userId") %>`;
			var contextPath = '<%= request.getContextPath() %>';
			
			if (id == 'null') {
				e.preventDefault();
				alert("로그인이 필요합니다.");
				
			}else {
				$.ajax({
					type : 'post',
					url : contextPath + "/sellerCheck.me",
					data : {userId : id},
					success : function (rdata) {
						if (rdata === "true") {
							alert("이미 판매회원으로 등록된 아이디입니다.");
						}else {
							location.href = contextPath + "/sellerjoin.me";
						}
					}
					
				});//ajax end
			}
			
		});//click end
		
	});//ready end
</script>
</head>
<body>
	<div class="backyard2">
		<div class="intro" style="margin:20px auto 60px auto;">
			<div id="title">
				<img
				src="${pageContext.request.contextPath}/image/common/topimg.png"
				style="width: 825px; height: 200; border: 1px solid black; border-radius: 15px;">
				<div>
					<button type="button" id="join_seller">판매회원 가입하기</button>
				</div>
			</div>
		</div>
	</div>
</body>