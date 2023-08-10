<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script src="http://code.jquery.com/jquery-latest.js"></script>
<link rel="stylesheet" type="text/css" href="../main/channelList.css">
<script>

//온이 되어 있는거에 아이디를 가져와서 조회, 벨류값이 0이면, category_id를 보냄 
//ajax 매개변수 값 있는 버전, 없는 버전
$(".cate-button .btn").click(function () {
	if ($(this).hasClass("on")) 
	var categoryId = parseInt($(this).index());
	loadChannelList(categoryId);
});


$(document).ready(function () {
	loadChannelList(); //화면 켰을땐 없음
});

function loadChannelList(category) {
	$.ajax({
		type: "GET",
		url: "ChannelListAction.chl", // 서버에서 채널 목록을 가져오는 URL
		data: { category_id: category }, // 선택한 카테고리 전달
		dataType: "json",
		success: function (data) {
			// 서버로부터 받아온 채널 목록 데이터를 화면에 표시합니다.
			var channelList = data;
			var channelTable = $("#channelTable");
			channelTable.empty(); // 테이블 초기화

			for (var i = 0; i < channelList.length; i++) {
				var channel = channelList[i];
				var row = "<tr><td>" + channel.chNum + "</td><td>" + channel.ownerid + "</td><td>" + channel.chName + "</td><td>" + channel.chprofile + "</td><td>" + channel.chinfo + "</td></tr>";
				channelTable.append(row);
			}
		},
		error: function () {
			alert("채널 목록을 불러오는 데 실패했습니다.");
		}
	});
}
</script>
	<div style="display: flex; justify-content: center;">
		<h1>잇다 추천 채널</h1>
	</div>
	<div class="buttons-container">
		<div class="cate-button">
			<button class="btn bt-item bt-hover bt-2 on" ><span>전체</span>
			</button>
			<button class="btn bt-item bt-hover bt-5" ><span>경제/시사</span>
			</button>
			<button class="btn bt-item bt-hover bt-5"><span>문화/예술</span>
			</button>
			<button class="btn bt-item bt-hover bt-5"><span>IT트렌트</span>
			</button>
			<button class="btn bt-item bt-hover bt-2" ><span>역사</span>
			</button>
			<button class="btn bt-item bt-hover bt-2"><span>과학</span>
			</button>
			<button class="btn bt-item bt-hover bt-2"><span>건강</span>
			</button>
			<button class="btn bt-item bt-hover bt-2"><span>요리</span>
			</button>
			<button class="btn bt-item bt-hover bt-3" ><span>스포츠</span>
			</button>
			<button class="btn bt-item bt-hover bt-3" ><span>재테크</span>
			</button>
			<button class="btn bt-item bt-hover bt-2" ><span>취미</span>
			</button>
		</div>
	</div>

	<div class="container">
		<div class="row area">
			<div class="col-md-4">
				<a href="chboard_list">
					<div class="card d-flex justify-content-center">
						<img src="../image/main/man.png" class="card-img-top rounded-circle mx-auto d-block"
							alt="...">
						<div class="card-body">
							<h5 class="card-title">Card 1</h5>
							<p class="card-text">채널명1</p>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4">
				<a href="#">
					<div class="card d-flex justify-content-center">
						<img src="../image/main/man.png" class="card-img-top rounded-circle mx-auto d-block"
							alt="...">
						<div class="card-body">
							<h5 class="card-title">Card 2</h5>
							<p class="card-text">채널명2</p>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4">
				<a href="#">
					<div class="card d-flex justify-content-center">
						<img src="../image/main/man.png" class="card-img-top rounded-circle mx-auto d-block"
							alt="...">
						<div class="card-body">
							<h5 class="card-title">Card 3</h5>
							<p class="card-text">채널명3</p>
						</div>
					</div>
				</a>
			</div>
		</div>
		<div class="row area">
			<div class="col-md-4">
				<a href="#">
					<div class="card d-flex justify-content-center">
						<img src="../image/main/man.png" class="card-img-top rounded-circle mx-auto d-block"
							alt="...">
						<div class="card-body">
							<h5 class="card-title">Card 4</h5>
							<p class="card-text">채널명4</p>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4">
				<a href="#">
					<div class="card d-flex justify-content-center">
						<img src="../image/main/man.png" class="card-img-top rounded-circle mx-auto d-block"
							alt="...">
						<div class="card-body">
							<h5 class="card-title">Card 5</h5>
							<p class="card-text">채널명5</p>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4">
				<a href="#">
					<div class="card d-flex justify-content-center">
						<img src="../image/main/man.png" class="card-img-top rounded-circle mx-auto d-block"
							alt="...">
						<div class="card-body">
							<h5 class="card-title">Card 6</h5>
							<p class="card-text">채널명6</p>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>
