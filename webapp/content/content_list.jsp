<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>  
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
    <!DOCTYPE html>
    <html>
    <link href="../css/common.css" rel="stylesheet" type="text/css">
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
    <head>
    <meta charset="utf-8">
    <title>카테고리명{chcate_name}:채널명${chname}</title>
    <link rel="stylesheet" href="../css/content_list.css">
    <script src = "../js/contetn_list.js"></script>
    <jsp:include page="../main/header.jsp"/>
    </head>
    <body>
    <div class="header_wrap">
    	<div class="header_inline">
    </div>
    	</div>
    <div class = "content_group_wrap">
        <div class="content_group">
            <div class="head_content">
                <div class="head_service">
                    <div class="head_back">
                        <a class=back_content href="javascript:history.back();"><img src="../image/content/errow_left.png" width="20"></a>
                    </div>
                </div>
                    <h2 class="head_title">${chName}</h2>
            </div>
            <hr>
            <div class="content_line">
                <div class="content_tap_wrap">
                    <h3 class="content_head"><span>${chcate_name }<em>${listcount }</em></span></h3>
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
                      		<c:set var="num" value="${listcount-(page-1)*limit}"/>
                      		
								<c:forEach var="b" items="${contentlist}">
                    <li class="content_item">
                           <div class="content_item_line">

                            <a href><img src="${pageContext.request.contextPath}/image/content/${chnum }/${b.boardNum }/${b.thumbNail}" class="content_thumb">
                            </a>
                            <div class="content_text">
                                <a class="content_text_link" href="content_detail.co?num=${b.boardNum }">
                                
                                    <strong class="content_title">${b.boardTitle }</strong>
                                    	<c:if test="${b.boardTitle.length()>=20}">
										<c:out value="${b.boardTitle.substring(0,20)}..."/>
									</c:if>
									<c:if test="${b.boardTitle.length()<20}">
										<c:out value="${b.boardTitle}" />
									</c:if>
								</a>
                                <div class="content_info">
                                    <span class="content_comment_wrap">
                                        <span class="content_info_text"><fmt:formatDate pattern="yyyy.MM.dd" value="${b.boardDate}"/></span>
                                        <a><img class="content_comment_img" src= "../image/content/heart.png">
                                            <span class="content_comment_count">${b.boardHeart}</span>
                                        </a>
                                        <a><img class="content_comment_img" src= "../image/content/comment.png">
                                                <span class="content_comment_count">${boardComent}</span>
                                        </a>
                                    </span>
                                </div>
                            </div>
                            <br>
                        </div>
                    </li>
                            </c:forEach>
                            

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
						<a href="contentlist.bo?page=${page-1}"
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
							<a href="contentlist.bo?page=${a}"
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
						<a href="contentlist.bo?page=${page+1}"
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
	
	</div> <%-- <div class="container"> end --%>
        </div>
 <jsp:include page="../main/footer.jsp" />
    </body>
    </html>