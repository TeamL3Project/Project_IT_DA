<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
	.backyard2 {
		width: 100%;
		background: rgb(204, 204, 204);
		display: flex;
    	justify-content: center;
	}
	
	.intro {
		width: 40%;
		height: 150px;
	}

	#join_seller {
		float: right;
		border: none;
		background: rgb(1, 39, 60);
		color: rgb(251, 209, 167);
		position: relative;
		right: 20px;
   		top: 5px;
	}
	
	#join_seller:hover {
		background: #FBD1A7;
        color: #01273C;
        opacity: 0.8;
        border: 1px solid black;
		
	}
	
	#title {
		color: rgb(251, 209, 167);
		
	}
	
	#line {
		text-align: center;
		color: rgb(251, 209, 167);
	}
</style>
<div class="backyard2">
<div class="intro" style="margin:20px auto; background: rgb(1, 39, 60);">
	<div id="title">
		<h2>&nbsp;&nbsp;&nbsp;It:da</h2>
	</div>
	<div id="line">
		<h4>누구나 쉽게 콘텐츠를 생산할 수 있는 공간, 누구나 창작자가 될 수 있는 공간</h4>
	</div>
	<div>
		<button type="button" id="join_seller" onclick="location.href='Seller_Form.jsp'">판매회원 가입하기</button>
	</div>
</div>
</div>
