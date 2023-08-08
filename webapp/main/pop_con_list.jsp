<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>

<script>
    var contextPath = '<%= request.getContextPath() %>';
    $(function () {
        function callContents_ajax(categoryNum) {
            $.ajax({
                type: "GET",
                url: contextPath + "/contentByCategory.co",
                data: {categoryNum: categoryNum ,
                        pageCount : pageCount },
                dataType: "json",
                success: function (data) {
                    if (isCategoryButtonOn != categoryNum) {
                        pageCount = FIRST_PAGE;
                        isCategoryButtonOn = categoryNum;
                        $(".popular-list-ul").html("");
                    }
                    $(data).each(function () {
                        const appendData = '<a href="/contents/' + this.chNum + '/' + this.boardNum + '" class="popular-list-card">'
                            + '<li class="popular-list-content"><span class="popular-list-title">' + this.boardTitle + '</span><br>'
                            + '<span>' + this.boardContent + '</span></li>'
                            + '<li class="popular-list-imgframe">'
                            + '<img src="image/content/' + this.chNum + '/' + this.boardNum + '/' + this.thumbNail + '" class="popular-list-img"></li>'
                            + '</a>'
                        $(".popular-list-ul").append(appendData);
                    })
                }
            })
        }

        let isCategoryButtonOn = $('.contents_category.on').prop('id')

        $('.contents_category').click(function () {
            if (isCategoryButtonOn == $(this).prop('id')) return;
            const categoryNum = $(this).prop('id');
            callContents_ajax(categoryNum);

        })
        const FIRST_PAGE = 1;
        let pageCount = FIRST_PAGE;
        let isExecuted = false;
        $(window).scroll(function () {
            const totalHeight = $(document).height();
            const currentScrollPosition = $(window).scrollTop();
            if (!isExecuted && currentScrollPosition + 1188 >= totalHeight - 1) {
                isExecuted = true;
                setTimeout(() => {
                    callContents_ajax(isCategoryButtonOn);
                    pageCount++;
                    console.log(pageCount);
                    isExecuted = false;
                }, 3000);
            }
        });
    });
</script>
<div class="popular-list-view-wrap">
    <div class="popular-list-area">
        <div class="popular-list-top" style="padding-left: 20px">
            <span style="font-size: 22px; font-weight: bolder; color: #01273C; font-family: '맑은 고딕';">#신규 콘텐츠</span>
        </div>
        <div class="popular-list-bar">
            <button class="contents_category btn bt-item bt-hover bt-2  on" id="0"><span>전체</span>
            </button>
            <button class="contents_category btn bt-item bt-hover bt-5" id="1"><span>경제/시사</span>
            </button>
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
