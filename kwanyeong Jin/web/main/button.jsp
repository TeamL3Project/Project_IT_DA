<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <style>
        .bt-item{
            /*position: relative;*/
            margin: 0 5px;
            color: #01273C;
            font-weight: bold;
            border-radius: 12px;
            border: 1px solid #01273C;
            width: 5em;
            font-size: 15px;
            text-align: center;
            line-height: 18px;
        }

        .bt-hover:hover, .on , .bt-on {
            background: #FBD1A7;
            color: #01273C;
            opacity: 0.8;
            border: 1px solid #FBD1A7;
        }
        .bt-item:active{
            border: none;
        }
    </style>
    <script>
        $(function (){
            $(".bt-item").click(function (){
                $(".bt-item.on").removeClass('on');
                console.log('test');
                $(this).addClass('on').css("box-shadow", "none");
            });

        });
    </script>
</head>
<body>
  <div class="cate-button">
    <button class="btn bt-item">홈</button>
    <button class="btn bt-item bt-hover"><span>사회</span></button>
    <button class="btn bt-item"><span>경제</span></button>
    <button class="btn bt-item bt-on"><span>IT</span></button>
    <button class="btn bt-item bt-on" style="width: 3em; height: 20px; line-height: 2px"><span>it</span></button>
  </div>
</body>
</html>
