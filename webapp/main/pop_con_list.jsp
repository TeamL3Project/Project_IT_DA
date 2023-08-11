<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="popular-list-view-wrap">
    <div class="popular-list-area">
        <div class="popular-list-top" style="padding-left: 20px">
            <span style="font-size: 22px; font-weight: bolder; color: #01273C; font-family: '맑은 고딕';">#신규 콘텐츠</span>
        </div>
        <div class="popular-list-bar">
            <button class="contents_category btn bt-item bt-hover bt-2  on" id="0"><span>전체</span>
            </button>
            <t:forEach var="c" items="${channelCategory}">
                <button class="contents_category btn bt-item bt-hover bt-5" id="1"><t:out value="${c.categoryName}"/>
                </button>
            </t:forEach>
            <button class="contents_category btn bt-item bt-hover bt-5" id="2"><span>문화/예술</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-5" id="3"><span>IT트렌트</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-2" id="4"><span>역사</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-2" id="5"><span>과학</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-2" id="6"><span>건강</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-2" id="7"><span>요리</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-3" id="8"><span>스포츠</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-3" id="9"><span>재테크</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-2" id="10"><span>취미</span>
            </button>
        </div>
        <div class="popular-list-cards">
            <ul class="popular-list-ul" style=" display: flex; width: 1080px; flex-wrap: wrap; padding: 0">
            </ul>
        </div>
    </div>
    <div class="loading">
        <div class="loader" style="display:flex; justify-content:center;">
            <div class="dot"></div>
        </div>
        <br>
    </div>
</div>
<style>
    :root {
        --dot1: #0359AE;
        --dot2: #14B09B;
        --dot3: #EBE5D9;
    }

    .dot:before {
        margin: 15px;
        content: "";
        width: 10px;
        height: 10px;
        display: block;
        border-radius: 100%;
        animation: palette 2s infinite cubic-bezier(0.25, 0.46, 0.09, 0.32);
    }

    @keyframes palette {
        0% {
            color: var(--dot1);
            box-shadow: 20px 0px 0 0 var(--dot2), 6.18034px 19.02113px 0 0 var(--dot3), -16.18034px 11.75571px 0 0 var(--dot1), -16.18034px -11.75571px 0 0 var(--dot2), 6.18034px -19.02113px 0 0 var(--dot3);
        }
        20% {
            color: var(--dot2);
            box-shadow: 20px 0px 0 0 var(--dot3), 6.18034px 19.02113px 0 0 var(--dot1), -16.18034px 11.75571px 0 0 var(--dot2), -16.18034px -11.75571px 0 0 var(--dot3), 6.18034px -19.02113px 0 0 var(--dot1);
        }
        40% {
            color: var(--dot3);
            box-shadow: 20px 0px 0 0 var(--dot1), 6.18034px 19.02113px 0 0 var(--dot2), -16.18034px 11.75571px 0 0 var(--dot3), -16.18034px -11.75571px 0 0 var(--dot1), 6.18034px -19.02113px 0 0 var(--dot2);
        }
        60% {
            color: var(--dot1);
            box-shadow: 20px 0px 0 0 var(--dot2), 6.18034px 19.02113px 0 0 var(--dot3), -16.18034px 11.75571px 0 0 var(--dot1), -16.18034px -11.75571px 0 0 var(--dot2), 6.18034px -19.02113px 0 0 var(--dot3);
        }
        80% {
            color: var(--dot2);
            box-shadow: 20px 0px 0 0 var(--dot3), 6.18034px 19.02113px 0 0 var(--dot1), -16.18034px 11.75571px 0 0 var(--dot2), -16.18034px -11.75571px 0 0 var(--dot3), 6.18034px -19.02113px 0 0 var(--dot1);
        }
        100% {
            color: var(--dot3);
            box-shadow: 20px 0px 0 0 var(--dot1), 6.18034px 19.02113px 0 0 var(--dot2), -16.18034px 11.75571px 0 0 var(--dot3), -16.18034px -11.75571px 0 0 var(--dot1), 6.18034px -19.02113px 0 0 var(--dot2);
            transform: rotate(360deg);
        }
    }
</style>
</body>
</html>
