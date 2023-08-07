<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<style>
* {
	box-sizing: border-box;
}

#searchyard {
	width: 100%;
	text-align: center;
}

#searchInput {
	width: 70%;
	font-size: 16px;
	padding: 12px 20px 12px 40px;
	margin-bottom: 12px;
}

#searchUL {
	list-style-type: none;
	padding: 0;
	margin: auto;
	width: 70%;
}

#searchUL li a {
	border: 1px solid #ddd;
	margin-top: -1px; 					/* 테두리 중복 방지 */
	background-color: #f6f6f6;
	padding: 12px;
	text-decoration: none;
	font-size: 18px;
	color: black;
	display: block;
	text-align: left !important;
}

#searchUL li a:hover:not(.header) {
	background-color: #eee;
}
</style>
</head>
<body>
	<div id="searchyard">
		<input type="text" id="searchInput" onkeyup="myFunction()"
			placeholder="검색어를 입력하세요" title="Type in category">

		<ul id="searchUL">
			<li><a href="#">스포츠/건강</a></li>
			<li><a href="#">요리</a></li>
			<li><a href="#">책/작가</a></li>
			<li><a href="#">취미/학습</a></li>
			<li><a href="#">부동산</a></li>
			<li><a href="#">시사/이슈</a></li>
			<li><a href="#">트렌드</a></li>
		</ul>

		<script>
			function myFunction() {
				var input, filter, ul, li, a, i, txtValue;
				input = document.getElementById("searchInput");
				filter = input.value.toUpperCase();
				ul = document.getElementById("searchUL");
				li = ul.getElementsByTagName("li");
				for (i = 0; i < li.length; i++) {
					a = li[i].getElementsByTagName("a")[0];
					txtValue = a.textContent || a.innerText;
					if (txtValue.toUpperCase().indexOf(filter) > -1) {
						li[i].style.display = "";
					} else {
						li[i].style.display = "none";
					}
				}
			}
		</script>
	</div>
</body>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <title>검색 페이지</title>
    <!-- 검색 페이지에 필요한 특정 CSS 또는 JS 파일을 추가하세요. -->
</head>
<style>
body {
	display: flex;
	align-items: center;
	min-height: 100vh;
	margin: 0;
	flex-direction: column;
}

.search-container {
	text-align: center;
}

#num0 {
	text-align: center;
	margin: 15px;
}

#searchBox {
	width: 680px;
	height: 63px;
	outline: none;
	border: none;
	display: flex;
	-webkit-box-flex: 1;
	flex-direction: row; /* 요소들을 가로로 나란히 배치합니다. */
        justify-content: space-between;
}

.head_content {
	display: flex;
	align-items: center;
}

.search-form {
	width: 1920px;
	height: 63px;
	display: flex;
	align-items: center;
}

  button {
        margin-left: auto;
    }
</style>
<body>
    <jsp:include page="" />
    <jsp:include page=""/>

    <!-- 여기에 검색 페이지 내용을 추가하세요. -->
    <div class="search_page">
    <div class='head_inner clearfix'>
    	<div class="head_content">
    		<a href="/previous-page" class="back_button" date-clk="pch_search.back">
    			<span class="blind">뒤로가기</span>
			</a>
        <form action="searchResult.jsp" method="GET">
             <input type='text' name='searchBox' id='searchBox' placeholder='검색어를 입력하세요' required>
        </form>
    </div>
</div>
</div>
    <jsp:include page=""/>
</body>
</html>

