<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script>
        $(function () {
            $(".bt-item").click(function () {
                $(".bt-item.on").removeClass('on');
                console.log('test');
                $(this).addClass('on').css("box-shadow", "none");
            });
        });
    </script>
    <style>
        .popular-list-area {
            width: 1080px;
            margin: 0 auto;
        }

        .popular-list-cards {
            width: 1080px;
            display: flex;
            flex-wrap: wrap;
        }

        .popular-list-card {
            width: 500px;
            height: 150px;
            display: flex;
            margin:30px 10px 0 10px;
            border: 1px solid #01273C;
            border-radius: 10px;
            padding : 15px;
            box-shadow: 5px 5px 5px #888888;
        }
        .popular-list-content{
            display: inline-block;
            width: 500px;
        }
        .popular-list-imgframe{
            list-style: none;
        }
        .popular-list-img{
            width: 100px;
            height: auto;
        }
    </style>
    <link rel="stylesheet" href="../css/mainpage.css">
</head>
<body>
<div class="popular-list-view-wrap">

    <div class="popular-list-area">
        <div class="popular-list-top">
            <span>한 주에 뜨는  컨텐츠</span>
        </div>
        <div class="popular-list-bar">
            <button class="btn bt-item bt-hover on" style="width: 4em; height: 20px; line-height: 2px"><span>전체</span>
            </button>
            <button class="btn bt-item bt-hover" style="width: 6.5em; height: 20px; line-height: 2px"><span>시사/이슈</span>
            </button>
            <button class="btn bt-item bt-hover" style="width: 6.5em; height: 20px; line-height: 2px"><span>IT/테크</span>
            </button>
            <button class="btn bt-item bt-hover" style="width: 4em; height: 20px; line-height: 2px"><span>요리</span>
            </button>
            <button class="btn bt-item bt-hover" style="width: 4em; height: 20px; line-height: 2px"><span>건강</span>
            </button>
            <button class="btn bt-item bt-hover" style="width: 4em; height: 20px; line-height: 2px"><span>경제</span>
            </button>
            <button class="btn bt-item bt-hover" style="width: 4em; height: 20px; line-height: 2px"><span>과학</span>
            </button>
            <button class="btn bt-item bt-hover" style="width: 6.5em; height: 20px; line-height: 2px"><span>문화/예술</span>
            </button>
            <button class="btn bt-item bt-hover" style="width: 5em; height: 20px; line-height: 2px"><span>트렌드</span>
            </button>
        </div>
        <div class="popular-list-cards">
            <ul style="display: flex; width: 1080px; flex-wrap: wrap">
                <a href="" class="popular-list-card">
                    <li class="popular-list-content"><span>제목 카드 1</span>
                        <hr>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/main/1.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card" >
                    <li class="popular-list-content"><span>제목 카드 2</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/main/2.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card">
                    <li class="popular-list-content"><span>제목 카드 3</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/main/3.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card" >
                    <li class="popular-list-content"><span>제목 카드 4</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/main/4.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card" >
                    <li class="popular-list-content"><span>제목 카드 5</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/main/5.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card" >
                    <li class="popular-list-content"><span>제목 카드 5</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/main/6.jpg" class="popular-list-img">
                    </li>
                </a>
            </ul>
        </div>
    </div>
</div>
</body>
</html>
