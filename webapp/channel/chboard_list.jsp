<!--<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  -->
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
    <!DOCTYPE html>
    <html>
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link rel="stylesheet"
   href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
   src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script
   src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
   src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link rel="stylesheet"
   href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <jsp:include page="../main/header.jsp"/>
    <head>
    <meta charset="utf-8">
    <title>카테고리명{$cate_name}:채널명${chname}</title>
    <link rel="stylesheet" href="../css/chboard_list.css">
    <script src = "../js/jquery-3.7.0.js"></script>
    </head>
    <body>
    <body>
    <div class = "content_group_wrap">
        <div class="content_group">
            <div class="head_content">
                <div class="head_service">
                    <div class="head_back">
                        <a class=back_content href><img src="../image/errow_left.png" width="20"></a>
                    </div>
                </div>
                    <h2 class="head_title">채널명 입니다 ${chName}</h2>
            </div>
            <hr>
            <div class="content_line">
                <div class="content_tap_wrap">
                    <h3 class="content_head"><a href><sapn>전체 콘텐츠<em>${listcount }</em><img src="../image/errow_down.png" width="15"></sapn></a></h3>
                </div>
                <div class="category_sorting_wrap">
                    <div class="sorting_wrap">
                        <ul class="sorting_list">
                            <li class="sorting_item" role="radio" aria-checked="true">
                                <a href class="sorting_link" data-clk="chlh_clist.ftlatest">최신순</a>
                            </li>
                            <li class="sorting_item" role="radio">
                                <a href class="sorting_link" data-clk="chlh_clist.ftoldest">과거순</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="content_list_wrap">
                <ul class="content_list">
           		 <c:if test = "${listcount > 0 }">
                    <li class="content_item">
                        <div class="content_item_line">
                      		<c:set var="num" value="${listcount-(page-1)*limit}"/>
							<c:forEach var="b" items="${chboardlist}">

                            <a href><img src class="content_thumb">
                            </a>
                            <div class="content_text">
                                <a class="content_text_link" href="ChBoardDetailAction.bo?num=${b.boardNum }">
                                
                                    <strong class="content_title">${boardTitle }콘텐츠 타이틀 입니다</strong>
                                    	<c:if test="${b.boardTitle.length()>=20}">
										<c:out value="${b.boardTitle.substring(0,20)}..."/>
									</c:if>
									<c:if test="${b.boardTitle.length()<20}">
										<c:out value="${b.board_Title}" />
									</c:if>
								</a>[${b.cnt}]
                                <div class="content_info">
                                    <span class="content_comment_wrap">
                                        <span class="content_info_text">${boardDate}2023.08.02</span>
                                        <a><img class="content_comment_img" src=../image/heart.png>
                                            <span class="content_comment_count">${boardHeart}</span>
                                        </a>
                                        <a><img class="content_comment_img" src=../image/comment.png>
                                                <span class="content_comment_count">${boardComent}</span>
                                        </a>
                                    </span>
                                </div>
                            </div>
                            </c:forEach>
                        </div>
                    </li>
<!--                     <li class="content_item">
                        <div class="content_item_line">
                            <a href><img src="../image/고양이.jpg" class="content_thumb">
                            </a>
                            <div class="content_text">
                                <a class="content_text_link" href>
                                    <strong class="content_title">고양이를 키우는 집사의 일상</strong>
                                </a>
                                    <div class="content_info">
                                        <span class="content_comment_wrap">
                                            <span class="content_info_text">2023.08.02</span>
                                            <a><img class="content_comment_img" src=../image/heart.png>
                                                <span class="content_comment_count">00</span>
                                            </a>
                                            <a><img class="content_comment_img" src=../image/comment.png>
                                                <span class="content_comment_count">00</span>
                                            </a>
                                        </span>
                                    </div>
                            </div>
                        </div>
                    </li>
                    <li class="content_item">
                        <div class="content_item_line">
                            <a href><img src="../image/짜장면.jpg" class="content_thumb">
                            </a>
                            <div class="content_text">
                                <a class="content_text_link" href>
                                    <strong class="content_title">중식의 맛있는 맛을 찾아서~~</strong>
                                </a>
                                <div class="content_info">
                                    <span class="content_comment_wrap">
                                        <span class="content_info_text">2023.08.02</span>
                                            <a><img class="content_comment_img" src=../image/heart.png>
                                                <span class="content_comment_count">00</span>
                                            </a>
                                            <a><img class="content_comment_img" src=../image/comment.png>
                                                <span class="content_comment_count">00</span>
                                            </a>
                                    </span>
                                </div>
                            </div>
                        </div>
                    </li> -->
                		<%-- 아직 페이지가 0 --%>
		<div class="center-block">
			<ul class="pagination justify-content-center">
				<c:if test="${page <= 1}">
					<li class="page-item">
						<a class="page-link gray">이전&nbsp;</a>
					</li>
				</c:if>
				<c:if test="${page > 1}">
					<li class="page-item">
						<a href="ChBoardList.bo?page=${page-1}"
							class="page-link">이전&nbsp;</a>
					</li>
				</c:if>
				
				<c:forEach var ="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == page}">
						<li class="page-item active">
							<a class="page-link">${a}</a>
						</li>
					</c:if>
					<c:if test="${a != page }">
						<li class="page-item">
							<a href="ChBoardList.bo?page=${a}"
								class="page-link">${a}</a>
						</li>
					</c:if>
				</c:forEach>
				
				<c:if test="${page >= maxpage }">
					<li class="page-item">
						<a class="page-link gray">&nbsp;다음</a>
					</li>
				</c:if>
				<c:if test="${page < maxpage }">
					<li class="page-item">
						<a href="ChBoardList.bo?page=${page+1}"
							class="page-link">&nbsp;다음</a>
					</li>
				</c:if>
				</ul>
			</div>
	</c:if>		
                </ul>
            </div>
	<%-- <c:if test = "${listcount > 0 }"> end --%>
	
	<%-- 게시글이 없는 경우 --%>
	<c:if test="${listcount == 0 }">
		<h3 style="text-align:center">등록된 글이 없습니다.</h3>
	</c:if>
	
	<button type="button" class="btn btn-info float-right">글 쓰 기</button>
	</div> <%-- <div class="container"> end --%>
        </div>
    </div>
        <!-- 
                <div class="content_list_wrap ">
            <ul id="likeItCountViewDiv" class="content_list _CONTENT_LIST" data-cp-name="bookcrew" data-sub-id="essaycat" data-category-id="" data-tag="" data-author-id="" data-alliance-id="" data-template="SCS_PREMIUM_CONTENT_LIST" data-cursor-name="lastContentId" data-cursor="221017175705313le" data-has-next="true">
                <li class="content_item as_thumb">
                    <div class="content_item_inner">
                        <a href="/bookcrew/essaycat/contents/221114153251181cc" class="content_thumb" data-clk="chlh_clist.ctgrlist">
                            <img src="https://scs-phinf.pstatic.net/MjAyMjExMTRfNDEg/MDAxNjY4NDA2NDc2OTAz.oDB-NfCAF9KlutkpGqeXbRb1NErju0_8qQSiGR7Pissg.HHahFfVuaSdX9I4mNSW0biTrzyJLELuXxYjpI3JHbaAg.PNG/image.png?type&#x3D;nfs220_220" width="94" height="94" alt="" onerror="this.outerHTML='<span class=&quot;no_image&quot;></span>'">
                        </a>
                        <div class="content_text">
                            <a href="javascript:;" class="content_category"></a>
                            <a href="/bookcrew/essaycat/contents/221114153251181cc" class="content_text_link" data-clk="chlh_clist.ctgrlist">
                            <strong class="content_title">
                                [신규 콘텐츠 발행 중단 공지] 다시 만날 때까지 안녕
                            </strong>
                            </a>
                            <div class="content_author">
                                <em class="content_author_by">by</em>
                                <div class="content_author_text">채널담당자</div>
                            </div>
                            <div class="content_info">
                            <div class="content_info_inner">
                                <em class="content_lock _CONTENT_AUTHORITY_LIST" data-cp-name="bookcrew" data-sub-id="essaycat" data-content-id="221114153251181cc">
                                    <span class="blind">잠김</span>
                                </em>
                                                            <span class="content_info_text">완독 1분 소요</span>
                                                            <span class="content_info_text">2022.11.14.</span>
                            </div>
                            <div class="content_text_count">
                                <div class="u_likeit_list_module _reactionModule" data-sid="SCS" data-cid="p_bookcrew_essaycat_221114153251181cc">
                                    <a href="#" class="u_likeit_list_btn _button" data-type="like" aria-pressed="false" data-ishiddenlabel="false">
                                        <span class="u_ico _icon"></span>
                                        <em class="u_txt _label">좋아요</em>
                                        <em class="u_cnt _count"></em>
                                    </a>
                                </div>
                                <span class="comment_count_wrap">
                                    <span class="blind">댓글</span>
                                    <a href="/bookcrew/essaycat/comment/221114153251181cc" class="comment_count _COMMENT_COUNT_LIST" data-ticket="scs" data-object-id="p_bookcrew_essaycat_221114153251181cc" data-zero-allow="false"></a>
                                </span>
                            </div>
                            </div>
                        </div>
                    </div>
                </li>
            <div class="box">
            </div>
        </div>
        </div>
        <div class="content_group">
        <div class="content_group_head_wrap">
        <div class="content_group_head_inside">
        <div class="content_group_head">
                    <div class="content_tab_wrap">
                <h3 class="content_tab">
                    <a href="/bookcrew/essaycat/category" class="content_tab_link" data-clk="chlh_clist.">
                        <span class="content_tab_text">
                            전체 콘텐츠
                            <em>3</em>
                        </span>
                    </a>
                </h3>
            </div>
        </div>
    </div>
    </div>
    </div>
    </div>
    </div>
     -->
    </body>
    </html>