
 <%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width,initial-scale=1">
<link rel="stylesheet" href="../css/board_write.css">
<script src="https://cdn.ckeditor.com/ckeditor5/39.0.0/classic/ckeditor.js"></script>
<script src = "../js/jquery-3.7.0.js"></script>
<title>게시글 작성</title>
</head>
<body>
<div class="board_write_wrap">
	<form>
	<div class="text_form board_head_wrap">
	<select class="category_select_from" value="">
		<option disabled selected>카테고리를 선택하세요</option>
		<option value="category_title">김겨울</option>
		<option value="category_title">이가을</option>
		<option value="category_title">박여름</option>
		<option value="category_title">최겨울</option>
	</select>
	</div>
		<br>
		<div class="board_subject_wrap">
		<input class="text_from board_subject_from" type=text id="" placeholder="제목을 입력하세요">
		</div>
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
		<input class="board_tag_text" type=text id="" placeholder="태그를 추가해 보세요(최대 5개)">
		<button class="tag_add">추가</button>
	</div>
	<br>
	<div class="board_button_wrap">
		<button class="board_write_button">작성취소</button>
		<button class="board_write_button">작성완료</button>
	</div>	
	</form>
</div>

</body>
</html>