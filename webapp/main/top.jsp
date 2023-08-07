<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<div class="backyard2">
<div class="intro" style="margin:20px auto; background: rgb(1, 39, 60);">
	<div id="title">
		<h2>&nbsp;&nbsp;&nbsp;It:da</h2>
	</div>

	<script>
		$(document).ready(function(){
			$("#join_seller").click(function(e){
				var id = `<%= (String) session.getAttribute("userId") %>`
				if (id == null) {
					e.preventDefault();
					alert("로그인이 필요합니다.");
					
				}else {
					location.href = "../member/Seller_Form.jsp";
				}
				
			});//click end
			
		});//ready end
	</script>

</div>
</div>
