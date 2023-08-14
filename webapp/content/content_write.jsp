<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1">
    <link href="${pageContext.request.contextPath}/css/common.css" rel="stylesheet" type="text/css">
    <jsp:include page="../main/header.jsp"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/content_write.css">
    <script
            src="http://code.jquery.com/jquery-latest.js"></script>
    <script src="../js/content_write.js"></script>
    <title>게시글 작성</title>
</head>
<body>
<div class="board_write_wrap">
	<div class="board_group">
	<form action="contentadd.co" method="post" enctype="multipart/form-data">
	<div class="text_form board_head_wrap">
	<select class="category_select_from" >
		<option disabled selected>카테고리를 선택하세요</option>
		<c:forEach var="item" items="${cbctlist}">
		<option value="category_title">${item.chcate_Name}</option>
		</c:forEach>
	</select>
	</div>
		<br>
		<div class="board_subject_wrap">
		<input class="text_from board_subject_from" type=text name="boardTitle" placeholder="제목을 입력하세요">
		</div>
		<br>
			<div class="board_file_wrap">
				<input class="file" type="file" name="thumbNail" id="file">
		</div>
		<br>
		<br>
	<div class="board_content_wrap">
	<div class="editor_group">
	<jsp:include page="CKeditor.jsp"/>
<!-- 		<textarea id="ckeditor" class="board_content_write" placeholder="내용을 입력하세요">
    </textarea> -->
	</div>

	</div>
	<br>
	<div class="board_tag_wrap">
		<input class="board_tag_text" type=text id="input_tag" name="tagname" placeholder="태그를 추가해 보세요(최대 5개)">
		<button class="tag_add" id="tag_add" type="button" onclick="action_add(); /*cnt_tag();*/  <!-- cnt_tag(); -->">추가</button>
		 <div class="add_tag_group">
			<div class="viewer_tag">
				<ul class="viewer_tag_list" id="ul_tag_list" >
				</ul>
			</div>
 		</div>
	</div>
	<br>
	<div class="board_button_wrap">
		<button class="board_write_button" onClick="back();">작성취소</button>
		<button type=submit class="board_write_button">작성완료</button>
	</div>	
	</form>
	</div>
</div>
<jsp:include page="../main/footer.jsp"/>

</body>
</html>