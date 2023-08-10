<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<% response.setContentType("text/html; charset=UTF-8"); %>
<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="kor">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=no">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/common.css">
    <link rel="stylesheet" href="css/mainpage.css">
    <script src="js/mainpage.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<jsp:include page="top.jsp"/>

<div class="recommend_area">
    <div class="recommend_bar">
        <button type="button" class="left-button left-area"><span class="blind">이전</span></button>
        <ul class="recommend_cards">
            <div style="position: relative; z-index: 1000; width:100%; height: 100%; top: 0px; transform: translate(-200px, 0px); will-change: transform;">
                <t:forEach var="c" items="${popcontentList}">
                    <li class="reco_card" style="position: absolute; transform: translate(0% , 0px);">
                        <div class="card-view">
                            <a style="text-decoration: none; color: black" href="contents/${c.chNum}/${c.boardNum}">
                                <img class="card_img" src="image/content/${c.chNum}/${c.boardNum}/${c.thumbNail}">
                                <div class="card-body card-body-font">
                                    <h5 class="card-title"><t:out value="${c.boardTitle}"/></h5>
                                    <p class="card-text">
                                        <t:out value="${c.boardContent}"/>
                                        <t:if test="${c.boardContent.length() >= 20}">
                                            <t:out value="${c.boardContent.substring(0,20)}..."/>
                                        </t:if>
                                        <t:if test="${c.boardContent.length() < 20}">
                                            <t:out value="${c.boardContent}"/>
                                        </t:if>
                                    </p>
                                </div>
                            </a>
                        </div>
                    </li>
                </t:forEach>

                <p></p>
            </div>
        </ul>
        <button type="button" class="right-button right-area"><span class="blind">다음</span></button>
    </div>
</div>
<hr>
<jsp:include page="channelList.jsp"/>

<jsp:include page="pop_con_list.jsp"/>

<jsp:include page="footer.jsp"/>

</body>

</html>