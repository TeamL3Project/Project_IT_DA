<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="../css/mainpage.css" type="text/css">
    <script src="../js/mainpage.js"></script>
    <style>
        .mem-info-container {
            height: 80%;
            width: 100%;
            display: flex;
            justify-content: center;
            align-content: center;
        }

        .mem-info-area {
            margin: 0 auto;
            display: flex;
            flex-direction: column;
            align-items: center;
            width: 500px;
        }

        .mem-info-profile {
            margin-top: 50px;
            width: 100%;
            height: 150px;
            display: flex;
            justify-content: center;
        }

        .mem-info-img {
            width: 150px;
            height: 150px;
            border: 1px solid black;
        }

        .mem-info-bar {
            margin: 20px 0;
        }

        .mem-info-inner {
            width: 600px;
        }

        .mem-info-frame {
            border: 1px solid black;
            height: 400px;
        }


    </style>
</head>
<body>
<jsp:include page="../main/header.jsp"/>

<div class="mem-info-container">
    <div class="mem-info-area">
        <div class="mem-info-profile">
            <img class="mem-info-img">
            <div style="border: 1px solid black; border-radius: 50%; width: 10px; height: 10px;">수정
                <button class="mem-info-modify" style="border: none"></button>
            </div>
        </div>
        <div class="mem-info-bar">
            <button class="btn bt-item">예시 1</button>
            <button class="btn bt-item">예시 2</button>
            <button class="btn bt-item">예시 3</button>
        </div>
        <div class="mem-info-inner">
            <div class="mem-info-frame">네모칸</div>
        </div>
    </div>
</div>


<jsp:include page="../main/footer.jsp"/>
</body>
</html>
