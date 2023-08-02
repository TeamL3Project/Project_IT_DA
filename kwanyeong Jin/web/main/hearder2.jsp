<%@page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="kor">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Document</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
  <style>
    .wrapper {
      display: grid;
      place-items: center;
      min-height: 100dvh;
    }
    .header {
      background: #01273C;
      width: 100%;
    }

    .header-container {
      height: 90px;
      color: #c9c9c9;
      padding: 30px;
      padding-bottom: 10px;
    }

    .footer {
      position: absolute;
      top: 100%;
      background: #01273C;
      width: 100%;
    }

    .footer-container {
      height: 281px;
      color: #c9c9c9;
      padding: 30px;
      padding-bottom: 10px;
    }

    tr>td {
      width: 180px;
    }

    table, span {
      font-size: 8px;
    }

    body {
      background-color: #CCCCCC;
    }

    #profile {
      background-color: #D9D9D9;
      width: 240px;
      height: 240px;
      margin: 50px;
      display: inline-block;
    }

  </style>
</head>
<body>

<header class="header">
  <div class="header-container">
    <img src="img_pro/itda_ logo.png" style="width: 100px; height: auto; margin: -30px; margin-left: 30px;">
    <img src="img_pro/login.png" style="width: 40px; height: 40px; float: right; margin-right: 20px;">
    <img src="img_pro/search.png" style="width: 40px; height: 40px; float: right; margin-right: 20px;">
  </div>
</header>
<div class="wrapper" >
  <div class="info">
    <h3>[책장 위 고양이]</h3>
    <div id="profile">
      <p>안녕하세요:)<br>
        세 마리 고양이를 키우는<br>
        집사입니다.<br>
        잘 부탁드립니다.
      </p>
    </div>
    <img src="img_pro/ccc.jpg" style="width: 330px; height: 300px; border-bottom-left-radius: 50px;">
    <br>
    <button>
      구독하기
    </button>
    <img src="img_pro/alram.png" style="width: 40px; height: 40px;">
  </div>
  <footer class="footer">
    <div class="footer-container">
      <p style="font-weight: bold; font-size: 15px;">잇:다(주) 사업자 정보</p>
      <table>
        <tr>
          <td>사업자 등록번호</td>
          <td>220-81-62517</td>
        </tr>
        <tr>
          <td>전화 번호</td>
          <td>1588-1588</td>
        </tr>
        <tr>
          <td>이메일</td>
          <td>itda@itda.com</td>
        </tr>
        <tr>
          <td>주소</td>
          <td>서울 종로구 청와대로 1</td>
        </tr>
        <tr>
          <td>호스팅 서비스 제공</td>
          <td>NAVER Cloud</td>
        </tr>
      </table>
      <br> <span>네이버(주)는 통신판매중개시스템의 제공자로서 통신판매의 당사자가 아닙니다. 콘텐츠 판매, 환불 등과 관련한 의무와 책임은 판매자에게 있습니다.</span> <br> <img src="img_pro/itda_ logo2.png" style="width: 100px; height: auto; margin: 0px;">
    </div>
  </footer>
</div>
</body>
</html>