<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Title</title>
    <style>
        .ck-editor__editable {
            height: 400px;
        }
        img {
            width: 300px;
        }
    </style>
</head>
<script src="ckeditor5/build/ckeditor.js"></script>
<body>
<h1>CKEditor</h1>
<form action="contentregit.co" method="POST">
    <textarea name="text" id="editor"></textarea>
    <input type="hidden" name="channelNum" value="40">
    <input type="submit" value="전송">
</form>
<hr>
<script src="ImageUploadAdapter.js"></script>
<script>
    ClassicEditor.create(document.querySelector('#editor'), {
        language: 'ko',
        extraPlugins: [MyCustomUploadAdapterPlugin],
        removePlugins: ['Base64UploadAdapter', 'heading'],
        fontSize: {
            options: [
                14,
                15,
                16,
                17,
                18,
                19,
                20,
                21,
                22,
                23,
                24,
                25,
                26,
                27,
                28,
                29,
                30,
            ],}
    })
        .then(newEditor => {
            editor = newEditor;
        })
        .catch(error => {
            console.error(error);
        });
    function MyCustomUploadAdapterPlugin(editor) {
        editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
            return new UploadAdapter(loader)
        }
    }
</script>
</body>
</html>
