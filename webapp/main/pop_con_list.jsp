<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    $(function () {
        let isExecuted = false;
        $(window).scroll(function () {
            // 전체 문서 높이 계산
            const totalHeight = $(document).height();
            // 현재 스크롤 위치 계산
            const currentScrollPosition = $(window).scrollTop();
            // 스크롤 위치가 맨 바닥인지 확인
            if (!isExecuted && currentScrollPosition + 1188 >= totalHeight - 1) {
                isExecuted = true; // 조건이 한 번 실행되었음을 표시
                setTimeout(() => {
                    $(".popular-list-cards").append(currentScrollPosition + 1188 + " / " + totalHeight + " / test<br><br><br><br><br><br><br><br><br><br><br><br>test<br>");
                    // 여기에 원하는 동작을 추가하면 됩니다.
                    isExecuted = false;
                }, 3000);
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
            <button class="btn bt-item bt-hover bt-2 on" id="0"><span>전체</span>
            </button>
            <button class="btn bt-item bt-hover bt-5" id="1"><span>경제/시사</span>
            </button>
            <button class="btn bt-item bt-hover bt-5" id="2"><span>문화/예술</span>
            </button>
            <button class="btn bt-item bt-hover bt-5" id="3"><span>IT트렌트</span>
            </button>
            <button class="btn bt-item bt-hover bt-2" id="4"><span>역사</span>
            </button>
            <button class="btn bt-item bt-hover bt-2" id="5"><span>과학</span>
            </button>
            <button class="btn bt-item bt-hover bt-2" id="6"><span>건강</span>
            </button>
            <button class="btn bt-item bt-hover bt-2" id="7"><span>요리</span>
            </button>
            <button class="btn bt-item bt-hover bt-3" id="8"><span>스포츠</span>
            </button>
            <button class="btn bt-item bt-hover bt-3" id="9"><span>재테크</span>
            </button>
            <button class="btn bt-item bt-hover bt-2" id="10"><span>취미</span>
            </button>
        </div>
        <div class="popular-list-cards">
            <ul style=" display: flex; width: 1080px; flex-wrap: wrap; padding: 0">
                <t:forEach var="c" items="${contentSelect_per_cate}">
                    <a href="/contents/${c.chNum}/${c.boardNum}" class="popular-list-card">
                        <li class="popular-list-content"><span class="popular-list-title">${c.boardTitle}</span><br>
                            <span>${c.boardContent}</span>
                        </li>
                        <li class="popular-list-imgframe">
                            <img src="image/content/${c.chNum}/${c.boardNum}/${c.thumbNail}" class="popular-list-img">
                        </li>
                    </a>
                </t:forEach>
            </ul>
        </div>
    </div>
    <div class="loading">
        <div class="loader" style="display:flex; justify-content:center;">
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;"></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;"></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;"></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;"></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;"></div>
            <div class="dot" style="background-color: black; border-radius: 50%; width: 10px; height: 10px;"></div>
        </div>
    </div>
</div>
<style>
    @keyframes dot1 {
        0% {
        }
        25% {
        }
        50% {
        }
        75% {
        }
        100% {
        }
    }
</style>
</body>
</html>
