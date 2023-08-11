 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
   src="https://cdn.jsdelivr.net/npm/jquery@3.6.1/dist/jquery.min.js"></script>
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.0/classic/ckeditor.js"></script>
<script src = "../js/content_write.js"></script>
<title>게시글 작성</title>
</head>
<body>
<div class="board_write_wrap">
	<form action="contentwritePreocess.co" method="post" enctype="multipart/form-data">
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
		<input class="text_from board_subject_from" type=text id="" placeholder="제목을 입력하세요">
		</div>
		<br>
		<div class="board_file_wrap">
				<input type="file" name="file" id="file">
		</div>
		<br>
		<br>
	<div class="board_content_wrap">
	<div class="editor_group">
		<textarea id="ckeditor" class="board_content_write" placeholder="내용을 입력하세요">
    </textarea>
	</div>

    <script>
        ClassicEditor
            .create( document.querySelector( '#ckeditor' ))
            .catch( error => {
                console.error( error );
            } );
    </script>
		<!-- 
		 <p>제목</p>
		 -->
	</div>
	<br>
	<div class="board_tag_wrap">
		<input class="board_tag_text" type=text id="input_tag" name="input_tag" placeholder="태그를 추가해 보세요(최대 5개)">
		<button class="tag_add" type="button" onclick="action_add()">추가</button>
		 <div class="add_tag_group">
			<div class="viewer_tag">
				<ul class="viewer_tag_list" id="ul_tag_list">
				</ul>
			</div>
 		</div>
	</div>
	<br>
	<div class="board_button_wrap">
		<button class="board_write_button">작성취소</button>
		<button type=submit class="board_write_button">작성완료</button>
	</div>	
	</form>
</div>
<jsp:include page="../main/footer.jsp" />

</body>
</html>