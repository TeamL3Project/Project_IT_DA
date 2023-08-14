<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<style>
    .ck-editor__editable {
        height: 400px;
    }

    .ck-dropdown__panel {
        overflow-y: scroll;
        max-height: 200px;
    }
</style>
<script src="ckeditor5/build/ckeditor.js"></script>
<textarea name="content" id="editor"></textarea>
<script src="ImageUploadAdapter.js"></script>
<script>
    ClassicEditor.create(document.querySelector('#editor'), {
        language: 'ko',
        extraPlugins: [MyCustomUploadAdapterPlugin],
        removePlugins: ['Base64UploadAdapter', 'heading'],
        fontSize: {
            options: [
                7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30,
            ],
        }
    })
        .then(newEditor => {
            editor = newEditor;
        })
        .catch(error => {
            console.error(error);
        });

    function MyCustomUploadAdapterPlugin(editor) {
        editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
            $('html').append(${uploadToday});
            return new UploadAdapter(loader)
        }
    }
</script>
