<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script
            src="http://code.jquery.com/jquery-latest.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/content_write.css">
    <script src="${pageContext.request.contextPath}/js/content_write.js"></script>
    <script src="ckeditor5/build/ckeditor.js"></script>
    <script type="module" src="../js/Ckeditor.js"></script>
    <script !src="">
        const chnum = <c:out value="${chnum}"/>
    </script>
    <title>게시글 작성</title>
</head>
<body>
<jsp:include page="../main/header.jsp"/>
<div class="board_write_wrap">
    <div class="board_group">
        <form action="contentadd.co" method="post" enctype="multipart/form-data">
            <div class="text_form board_head_wrap">
                <select class="category_select_from">
                    <option disabled selected>카테고리를 선택하세요</option>
                    <c:forEach var="item" items="${cbctlist}">
                        <option value="${item.chcate_id}">${item.chcate_Name}</option>
                    </c:forEach>
                </select>
                <input type="hidden" name="categoryId" class="categoryId" value="">
            </div>
            <div class="board_subject_wrap">
                <input class="text_from board_subject_from" type=text name="boardTitle" placeholder="제목을 입력하세요">
            </div>
            <div class="board_content_wrap">
                <div class="editor_group">
                    <textarea name="content" id="editor"></textarea>
                </div>
            </div>
            <div class="board_tag_wrap">
                <input class="board_tag_text" type=text id="input_tag" placeholder="태그를 추가해 보세요(최대 5개)"><button class="tag_add" id="tag_add" type="button" onclick="cnt_tag();">추가</button>
                <div class="add_tag_group">
                    <div class="viewer_tag">
                        <ul class="viewer_tag_list" id="ul_tag_list">
                        </ul>
                    </div>
                </div>
            </div>
            <div class="board_file_wrap">
                <div class="thumbNailArea">
<%--                    <span class="thumbNailName">썸네일</span>--%>
                    <div style="height: 50px; margin: 15px 20px 0px 0px">
                        <label class="thumbNailUploadArea" for="file">
                        <img src="../image/content/thumbnailupload.png" style="width:100px; height: 100px; "><br>
                            <span style="color: #c9c9c9">썸네일 업로드</span>
                        <input class="thumbNailUpload" type="file" name="thumbNail" id="file" accept="image/*">
                        </label>
                    </div>
                    <img class="thumbNailImage" src="${src}" alt="profile">
                </div>
            </div>
            <div class="board_button_wrap">
                <button type="button" class="board_write_button write_cancel">작성취소</button>
                <button type="submit" class="board_write_button">작성완료</button>
            </div>
        </form>
    </div>
</div>
<jsp:include page="../main/footer.jsp"/>
</body>
</html>
