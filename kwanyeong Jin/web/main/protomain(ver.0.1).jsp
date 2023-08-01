<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<!DOCTYPE html>
<html lang="kor">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">

    <script>
        // window.onload = function () {
        //     var card = document.getElementsByClassName('card');
        //     var count = 1;
        //     setInterval(function () {
        //         for (var i = 0; i < card.length; i++) {
        //             card[i].style.transform = 'translate(' + ((i + count) % card.length) + '00%,0px)'
        //         }
        //         count++;
        //     }, 3000)
        //     // 무한 자동반복이동 로직
        //     //             i      = 0    1   2   3| 0    1   2   3|0    1   2   3|0  1   2   3  |0   1   2   3  |0   1   2   3
        //     //             count  =-2   -2  -2  -2|-1   -1  -1  -1|0    0   0   0|1  1   1   1  |2   2   2   2  |3   3   3   3
        //     //             i+count=-2   -1   0   1|-1    0   1   2|0    1   2   3|1  2   3   4  |2   3   4   5  |3   4   5   6
        //     // i+count%card.length=-2   -1   0   1|-1    0   1   2|0    1   2   3|1  2   3   0  |2   3   0   1  |3   0   1   2
        //     //                     200  300  0  100 300  0  100 200       

        // };                      -1
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
            // var left_button = document.getElementsByClassName('left-button')[0];
            // left_button.addEventListener('click', prevCard());
            $('.left-area').click(rotate);

            $('.right-area').click(rotateReverse);
        });

        // function prevCard(){
        //     for (var i = 0; i < card.length; i++) {
        //         if ((((i + count) % card.length)-1) == -1)
        //             card[i].style.transform = 'translate(' + 10 + '00%,0px)';
        //         else
        //             card[i].style.transform = 'translate(' + (((i + count) % card.length)-1) + '00%,0px)';
        //         }
        // }

        // 마우스 드래크 금지
        document.addEventListener('mousedown', function (event) {
            event.preventDefault();
        });
        document.addEventListener('dragstart', function (event) {
            event.preventDefault();
        });

    </script>
    <link rel="stylesheet" href="../css/mainpage.css">
</head>

<body>
<%--<jsp:include page="header.jsp" />--%>
<jsp:include page="top.jsp"/>

<div class="recommend_area">
    <div class="recommend_bar">
        <button type="button" class="left-button left-area"><span class="blind">이전</span></button>
        <ul class="recommend_cards">
            <div
                    style="position: relative; z-index: 2000; width:100%; height: 100%; top: 0px; transform: translate(-200px, 0px); will-change: transform;">
                <li class="card" style="position: absolute; transform: translate(0% , 0px);">
                    <div class="card-view">
                        <a href="https://www.naver.com">
                            <img class="card_img" src="../img_pro/1.jpg">
                            <div class="card-body">
                                <h5 class="card-title">Card title</h5>
                                <p class="card-text">teetstest.</p>
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
    <div class="card_nav_bar">
        <p class="card_nav_icon" style="opacity: 0.2;">1</p>
        <p class="card_nav_icon" style="opacity: 0.4;">2</p>
        <p class="card_nav_icon" style="opacity: 0.6">3</p>
        <p class="card_nav_icon on" style="opacity: 0.8;">4</p>
        <p class="card_nav_icon" style="opacity: 0.6;">5</p>
        <p class="card_nav_icon" style="opacity: 0.4;">6</p>
        <p class="card_nav_icon" style="opacity: 0.2;">7</p>
    </div>
</div>
<hr>


<jsp:include page="footer.jsp"/>
</body>

</html>