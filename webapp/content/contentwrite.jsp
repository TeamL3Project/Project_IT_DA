<%--
  Created by IntelliJ IDEA.
  User: badro
  Date: 2023-08-05
  Time: 오후 4:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="contentregit.co" method="POST">
    <input type="text" name="boardtitle" placeholder="제목을 입력하세요">
    <textarea name="boardcontent" id="editor" placeholder="내용을 입력하세요"></textarea>
    <input type="submit" value="전송">
    <input type="hidden" name="writer">
    <input type="hidden" name="chcate_id">
    <input type="hidden" name="chnum">

</form>
<jsp:include page="CKeditor.jsp"/>
</body>
</html>
