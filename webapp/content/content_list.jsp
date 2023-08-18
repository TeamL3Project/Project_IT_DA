<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<link href="${pageContext.request.contextPath}/css/common.css"
	rel="stylesheet" type="text/css">
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
<title><c:out value="${param.chcate_name}" default='전체글' />:${param.chname}</title>
<link rel="stylesheet" href="../css/content_list.css">
<script src="${pageContext.request.contextPath}/js/content_list.js"></script>
<jsp:include page="../main/header.jsp" />
</head>
<body>
	<div class="header_wrap">
		<div class="header_inline"></div>
	</div>
	<c:if test="${listcount == 0 }">
		<div class="content_group_wrap" style="margin-bottom:70px;">
			<div class="content_group">
				<div class="head_content">
					<div class="head_service">
						<div class="head_back">
							<a class=back_content href="chnnals/${param.channelnum}">
							<img src="../image/content/errow_left.png" width="20"></a>
						</div>
					</div>
					<h2 class="head_title">${param.chname}</h2>
				</div>
				<hr>
				<div class="content_line">
					<div class="content_tap_wrap">
						<h3 class="content_head">
							<span class="content_cate_title"><c:out
									value="${param.chcate_name}" default='전체글' /></span><em>${listcount }</em>
						</h3>
					</div>
					<div class="category_sorting_wrap">
						<div class="sorting_wrap">

							<ul class="sorting_list">
								<li class="sorting_item" role="radio" aria-checked="true"><a
									href="contentlist.co?channelnum=${param.channelnum}&chcate_id=${param.chcate_id}&chcate_name=<c:out value="${param.chcate_name}"  default='전체글'/>&chname=${param.chname}&order=desc"
									class="sorting_link" id="sort_desc" onClick="sort_desc()"></a>
								</li>
								<li class="sorting_item" role="radio"><a
									href="contentlist.co?channelnum=${param.channelnum}&chcate_id=${param.chcate_id}&chcate_name=<c:out value="${param.chcate_name}"  default='전체글'/>&chname=${param.chname}&order=asc"
									class="sorting_link" id="sort_asc" onClick="sort_asc()"></a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="content_list_wrap">
					<h4 style="text-align: center; margin:100px 0px;" >등록된 글이 없습니다.</h4>
				</div>
			</div>
		</div>
	</c:if>
	<c:if test="${listcount > 0 }">
		<div class="content_group_wrap">
			<div class="content_group">
				<div class="head_content">
					<div class="head_service">
						<div class="head_back">
							<a class=back_content href="../channels/${param.channelnum}"><img
								src="../image/content/errow_left.png" width="20"></a>
						</div>
					</div>
					<h2 class="head_title">${param.chname}</h2>
				</div>
				<hr>
				<div class="content_line">
					<div class="content_tap_wrap">
						<h3 class="content_head">
							<span class="content_cate_title"><c:out
									value="${param.chcate_name}" default='전체글' /></span><em>${listcount }</em>
						</h3>
					</div>
					<div class="category_sorting_wrap">
						<div class="sorting_wrap">
                             <input type="hidden" name="order" value="${order }">
							<ul class="sorting_list">
								<li class="sorting_item" role="radio" aria-checked="true"><a
									href="contentlist.co?page=${page }&channelnum=${param.channelnum}&chcate_id=${param.chcate_id}&chcate_name=<c:out value="${param.chcate_name}"  default='전체글'/>&chname=${param.chname}&order=desc"
									class="sorting_link" id="sort_desc" >최신순</a>
								</li>
								<li class="sorting_item" role="radio"><a
									href="contentlist.co?page=${page }&channelnum=${param.channelnum}&chcate_id=${param.chcate_id}&chcate_name=<c:out value="${param.chcate_name}"  default='전체글'/>&chname=${param.chname}&order=asc"
									class="sorting_link" id="sort_asc" >과거순</a>
								</li>
							</ul>
						</div>
					</div>
				</div>


				<div class="content_list_wrap">
						<c:set var="num" value="${listcount-(page-1)*limit}" />
					<ul class="content_list">
						<c:forEach var="b" items="${contentlist}" >

							<li class="content_item">
								<div class="content_item_line">
									<a href="${pageContext.request.contextPath}/contents/${param.channelnum}/${b.boardNum}"><img
										src="${pageContext.request.contextPath}/image/content/${param.channelnum}/${b.boardNum }/${b.thumbNail}"
										class="content_thumb"> </a>
									<div class="content_text">
										<a class="content_text_link"
											href="${pageContext.request.contextPath}/contents/${param.channelnum}/${b.boardNum}">

											<strong class="content_title">${b.chcate_name }</strong> 
											<c:if
												test="${b.boardTitle.length()>=20}">
												<c:out value="${b.boardTitle.substring(0,20)}..." />
											</c:if> <c:if test="${b.boardTitle.length()<20}">
												<c:out value="${b.boardTitle}" />
											</c:if>
										</a>
										<div class="content_info">
											<span class="content_comment_wrap"> <span
												class="content_info_text"><fmt:formatDate
														pattern="yyyy.MM.dd" value="${b.boardDate}" /></span> <a><img
													class="content_comment_img"
													src="../image/content/heart.png"> <span
													class="content_comment_count">${b.boardHeart}</span> </a> <a><img
													class="content_comment_img"
													src="../image/content/comment.png"> <span
													class="content_comment_count">${b.cnt}</span> </a>
											</span>
										</div>
									</div>
									<br>
								</div>
							</li>
						</c:forEach>
					</ul>
				</div>
			</div>
		</div>
		<!-- content_group_wrap -->
		<div class="center-block">

			<ul class="pagination justify-content-center">
				<c:if test="${page <= 1}">
					<li class="page-item"><a class="page-link gray">이전&nbsp;</a></li>
				</c:if>
				<c:if test="${page > 1}">
					<li class="page-item"><a
						href="contentlist.co?order=${order }&channelnum=${param.channelnum}&chcate_name=<c:out value="${param.chcate_name}" default='전체글' />&chcate_id=${param.chcate_id}&chname=${param.chname}&page=${page-1}"
						class="page-link">이전&nbsp;</a></li>
				</c:if>

				<c:forEach var="a" begin="${startpage}" end="${endpage}">
					<c:if test="${a == page}">
						<li class="page-item active"><a class="page-link">${a}</a></li>
					</c:if>
					<c:if test="${a != page }">
						<li class="page-item"><a
							href="contentlist.co?order=${order }&channelnum=${param.channelnum}&chcate_name=<c:out value="${param.chcate_name}" default='전체글' />&chcate_id=${param.chcate_id}&chname=${param.chname}&page=${a}"
							class="page-link">${a}</a></li>
					</c:if>
				</c:forEach>

				<c:if test="${page >= maxpage }">
					<li class="page-item"><a class="page-link gray">&nbsp;다음</a></li>
				</c:if>
				<c:if test="${page < maxpage }">
					<li class="page-item"><a
						href="contentlist.co?order=${order }&channelnum=${param.channelnum}&chcate_name=<c:out value="${param.chcate_name}" default='전체글' />&chcate_id=${param.chcate_id}&chname=${param.chname}&page=${page+1}"
						class="page-link">&nbsp;다음</a></li>
				</c:if>
			</ul>
		</div>
	</c:if>

	<jsp:include page="../main/footer.jsp" />
</body>
</html>