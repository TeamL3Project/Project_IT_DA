<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<script>
    $(function () {
        $(window).scroll(function () {
            // 전체 문서 높이 계산
            const totalHeight = $(document).height();
            // 현재 스크롤 위치 계산
            const currentScrollPosition = $(window).scrollTop();
            // 스크롤 위치가 맨 바닥인지 확인
            $(".recommend_area").append($(window).height()+" / "+currentScrollPosition +" / "+ totalHeight + "<br>");

            if (currentScrollPosition + 1188>= totalHeight-1) {
                // 맨 바닥에 도달한 경우 수행할 동작
                $(".popular-list-cards").append(currentScrollPosition+$(window).height()+" / " + totalHeight+ " / test<br><br><br><br><br><br><br><br><br><br><br><br>test<br>")

                // 여기에 원하는 동작을 추가하면 됩니다.
            }
        });
    });
</script>
<div class="popular-list-view-wrap">
    <div class="popular-list-area">
        <div class="popular-list-top" style="padding-left: 20px">
            <span style="font-size: 22px; font-weight: bolder; color: #01273C; font-family: '맑은 고딕';">#한 주에 뜨는  컨텐츠</span>
        </div>
        <div class="popular-list-bar">
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
        <div class="popular-list-cards">
            <ul style="display: flex; width: 1080px; flex-wrap: wrap; padding: 0">
                <a href="" class="popular-list-card">
                    <li class="popular-list-content"><span class="popular-list-title">제목 카드 1</span><br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="image/main/1.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card" >
                    <li class="popular-list-content"><span class="popular-list-title">제목 카드 2</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="image/main/2.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card">
                    <li class="popular-list-content"><span class="popular-list-title">제목 카드 3</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/content/3.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card" >
                    <li class="popular-list-content"><span class="popular-list-title">제목 카드 4</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/content/4.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card" >
                    <li class="popular-list-content"><span class="popular-list-title">제목 카드 5</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/content/5.jpg" class="popular-list-img">
                    </li>
                </a>
                <a href="" class="popular-list-card" >
                    <li class="popular-list-content"><span class="popular-list-title">제목 카드 6</span> <br>
                        <span>내용ddddddddsasdfsadfasdfasdfasdfdd dddddddddddddddddddddddd</span>
                    </li>
                    <li class="popular-list-imgframe">
                        <img src="../image/content/6.jpg" class="popular-list-img">
                    </li>
                </a>
            </ul>
        </div>
    </div>
    <div class="loading">
        <div class="loader" style="display:flex; justify-content:center;">
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;" ></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;" ></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;" ></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;" ></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;" ></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;" ></div>
        </div>
    </div>
</div>
<style>
    @keyframes dot1 {
        0%{}
        25%{}
        50%{}
        75%{}
        100%{}
    }
</style>
</body>
</html>
