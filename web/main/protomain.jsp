<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="kor">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
<%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script>
        $(function () {
            card = $('.card')
            count = 1;

            function rotate() {
                for (let i = 0; i < card.length; i++) {
                    rate = (i + count) % card.length;
                    if (rate < 0)
                        rate = card.length + rate;
                    card[i].style.transform = 'translate(' + rate + '00%,0px)'
                }
                count++;
            };

            function rotateReverse() {
                for (let i = 0; i < card.length; i++) {
                    rate = (i + count) % card.length;
                    if (rate < 0)
                        rate = card.length + rate;
                    card[i].style.transform = 'translate(' + rate + '00%,0px)'
                }
                count--;
            };

            setInterval(rotate, 10000);
            $('.left-area').click(rotate);

            $('.right-area').click(rotateReverse);
        });
    </script>
    <link rel="stylesheet" href="mainpage.css">
</head>

<body>
<jsp:include page="header.jsp" />
<jsp:include page="top.jsp"/>

<div class="recommend_area">
    <div class="recommend_bar">
        <button type="button" class="left-button left-area"><span class="blind">이전</span></button>
        <ul class="recommend_cards">
            <div
                    style="position: relative; z-index: 2000; width:100%; height: 100%; top: 0px; transform: translate(-200px, 0px); will-change: transform;">
                <li class="card" style="position: absolute; transform: translate(0% , 0px);">
                    <div class="card-view">
                        <a style="text-decoration: none; color: black" href="https://www.naver.com">
                            <img class="card_img" src="../img_pro/1.jpg">
                            <div class="card-body">
                                <h5 class="card-title" style="color: #353C42">Card title</h5>
                                <p class="card-text" style="color: #FBD1A7">teetstest.</p>
                            </div>
                        </a>
                    </div>
                </li>
                <li class="card" style="position: absolute; transform: translate(100% , 0px);">
                    <div class="card-view">
                        <img class="card_img" src="../img_pro/2.jpg">
                    </div>
                </li>
                <li class="card" style="position: absolute; transform: translate(200% , 0px);">
                    <div class="card-view">
                        <img class="card_img" src="../img_pro/3.jpg">
                    </div>
                </li>
                <li class="card" style="position: absolute; transform: translate(300% , 0px);">
                    <div class="card-view">
                        <img class="card_img"  src="../img_pro/4.jpg" >
                    </div>
                </li>
                <li class="card" style="position: absolute; transform: translate(400% , 0px);">
                    <div class="card-view">
                        <img class="card_img" src="../img_pro/5.jpg">
                    </div>
                </li>
                <li class="card" style="position: absolute; transform: translate(500% , 0px);">
                    <div class="card-view">
                        <img class="card_img" src="../img_pro/6.jpg" >
                    </div>
                </li>
                <li class="card" style="position: absolute; transform: translate(600% , 0px);">
                    <div class="card-view">
                        <img class="card_img" src="../img_pro/7.jpg" >
                    </div>
                </li>
                <p></p>
            </div>
        </ul>
        <button type="button" class="right-button right-area"><span class="blind">다음</span></button>
    </div>
</div>
<hr>


<jsp:include page="footer.jsp"/>
</body>

</html>