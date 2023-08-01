<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .blur {
            position: relative;
            line-height: 1.5;
            /* 줄 간격 설정 */
            /* 글자 그라데이션 */
            /* 가상 요소의 높이 설정 */
            background: linear-gradient(to bottom, rgb(0, 0, 0), rgba(255, 255, 255, 1));
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            /* 배경 그라데이션 설정 */
            pointer-events: none;
        }
    </style>
    <script>
        // 마우스 드래그 금지
        document.addEventListener('mousedown', function (event) {
            event.preventDefault();
        });
        document.addEventListener('dragstart', function (event) {
            event.preventDefault();
        });
    </script>
</head>
<body>
<div class="blur">
        <span class="blur-last-line">
            This is some text. This is the last line that will appear blurred. <br>
            그라데이션 + 마우스 우클릭 금지 + 드레그 금지
        </span>
</div>
</body>
</html>
