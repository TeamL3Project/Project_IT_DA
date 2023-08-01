<%--
  Created by IntelliJ IDEA.
  User: badro
  Date: 2023-07-30
  Time: 오후 5:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style>
        .ck-editor__editable { height: 400px;  background-color: #b1b1b1!important;}
        
    </style>
</head>
<script src="ckeditor5/build/ckeditor.js"></script>
<%--<script src="https://cdn.ckeditor.com/ckeditor5/34.0.0/classic/translations/ko.js"></script>--%>
<body>
<h1>CKEditor</h1>
<form action="" method="POST">
    <textarea name="text" id="editor"></textarea>
    <p><input type="submit" value="전송"></p>
</form>

<script>
    ClassicEditor.create( document.querySelector( '#editor' ), {
          });
</script>
</body>
</html>
