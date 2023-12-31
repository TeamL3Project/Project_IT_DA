<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>  
<html>
<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
	<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width,initial-scale=1">
    <script src="http://code.jquery.com/jquery-latest.js"></script>
    <script> var contextPath = "<%= request.getContextPath() %>"; </script>
    <script src="${pageContext.request.contextPath}/js/Reply.js"></script>
    <script src="${pageContext.request.contextPath}/js/Heart.js"></script>
	<link href='${pageContext.request.contextPath}/css/Reply.css' type='text/css' rel='stylesheet'>
	<link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
	<link href="${pageContext.request.contextPath}/css/content_detail.css" type="text/css" rel="stylesheet">
	<link rel="stylesheet" href="${pageContext.request.contextPath}/css/content-styles.css" type="text/css">
    <jsp:include page="../main/header.jsp"/>

<title>${co.boardTitle }</title>
</head>
<body>
<input type="hidden" id="LoginId" value="${userId}" name="LoginId">
<div class="board_detail_wrap">
	<div class="board_detail_all_group">
	<div class="board_detail_title_group">
	<div class="board_detail_category">
		<a href="${pageContext.request.contextPath}/channels/${co.chNum}" class="viewer_category_link">${co.chcate_name}</a>
	</div>
	<div class="board_detail_title_inline">
		<span class="board_detail_title">${co.boardTitle }</span>
	</div>
	<div class="viewer_title_content">
		<div class="viewer_date"><fmt:formatDate pattern="yyyy.MM.dd a HH:mm" value="${co.boardDate}"/>
		</div>
		<div class="viewer_count">
			<div class="viewer_count_func_wrap">
			     <div class="content_info">
                      <span class="content_comment_wrap">
                            <a href="#" class="heart_butt" data-id="${co.boardNum}">
                            	<img class="content_comment_img"
                             	src= '${pageContext.request.contextPath}/image/content/heart.png'>
                            	<span class="content_comment_count"></span>
                            	<input type="hidden" name="boardHeart" value="${co.boardHeart}" id="boardHeart">
                           		<em class="u_heart_count">${co.boardHeart}</em>
                            </a>
                            <a href="#reply_area"><img class="content_comment_img2" src= '${pageContext.request.contextPath}/image/content/comment.png'>
                                <span class="content_comment_count"></span>
                                <em class="u_cnt_count">${rcnt}</em>
                            </a>
                      </span>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="viewer_main_text_group">
		<div class="ck-content content_main_text">${co.boardContent }
			<input type="hidden" name="num" value="${co.boardNum}" id="Reply_board_num">
		</div>
	</div>
	<div class="viewer_bottom_warp">
		<div class="viewer_paywall_none">
			<p class="viewer_paywall_none_text">
			해당 콘텐츠는 프리미엄 구독자 공개(유료) 콘텐츠로 무단 캡쳐 및 불법 공유시 법적 제재를 받을 수 있습니다.</p>
		</div>
		<div class="viewer_paywall">
			<div class="viewer_paywall_text">
				<strong class="viewer_paywall_title">본 콘텐츠는 무료로 제공중입니다.<br>콘텐츠가 마음에 드셨나요?</strong>
				<p class="viewer_paywall_desc">올바른 구독으로 더 많은 콘텐츠를 만나보세요!</p>
			</div>
			<div class="viewer_paywall_subscribe _PAYWALL_BUTTON" data-is-ticket="true" data-is-product="" data-price="19,900" data-url="/allbareun/allbareunkr/subscriptions?rContentId=230726221406327lp">
				<div class="viewer_paywall_subscribe_inner">
				  <a href  data-clk="chlh_cont.subspay" data-cp-name="allbareun" data-sub-id="allbareunkr">
					<span class="viewer_paywall_subscribe_title">프리미엄 구독으로 다양한 콘텐츠를 만나보세요!</span>
				</a>
				</div>
			</div>
		</div>
		<div class="viewer_bottom_info">
			<div class="viewer_tag">
				<ul class="viewer_tag_list">
	 			<c:forEach var="t" items="${tname}" varStatus="status">
					<li class="viewer_tag_item">
						<a href class="viewer_tag_link" data-clk="chlh_cont.tag">${t[status.index]}</a>
						<a href class="viewer_tag_link" data-clk="chlh_cont.tag">${t}</a>
					</li>
				</c:forEach>
				</ul>
			</div>
			<div class="viewer_bottom_count_wrap">
				<div class="viewer_bottom_count">
                      <span class="content_comment_wrap">
                            <a href><img class="content_comment_img" src= '${pageContext.request.contextPath}/image/content/heart.png'>
                               <span class="content_comment_count"></span>
                           		<em class="u_heart_count">${co.boardHeart}</em>
                            </a>
                            <a href><img class="content_comment_img2" src= '${pageContext.request.contextPath}/image/content/comment.png'>
                                <span class="content_comment_count"></span>
                                <em class="u_cnt_count">${rcnt}</em>
                            </a>
                      </span>
				</div>
			</div>
		</div>
	</div>
</div>
			<div class="reply_area" id="reply_area">
				<div class="reply_head">
					<h5 class="reply_title">
						댓글 <sup class="reply_count"></sup>
					</h5>
					<div class="reply_order">			
						<ul class="reply_order_list">	<%-- 등록순 최신순 목록 --%>	
						</ul>
					</div>
				</div>	<%-- comment_head end --%>
				<ul class="reply_list" style="margin-left: 200px; margin-right: 200px;">	<%-- 댓글 목록 --%>
				</ul>
				<div class="reply_write">
					<div class="reply_write_area">
						<b class="reply_write_area_name">${userId}</b> 
						<span class="reply_write_area_count">0/200</span>
						<textarea placeholder="댓글을 남겨보세요" rows="1"
						class="reply_write_area_text" maxLength="200"></textarea>
					</div>
					<div class="register_box" >
						<div class="button btn-cancel" >취소</div><%-- 댓글의 취소는 display:none, 등록만 보이도록 합니다.--%>
						<div class="button btn-register" >등록</div>
					</div>
				</div>	<%-- reply_write end --%>
			</div>
</div>
<jsp:include page="../main/footer.jsp" />
</body>
</html>