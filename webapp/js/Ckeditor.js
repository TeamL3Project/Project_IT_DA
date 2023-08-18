import UploadAdapter from "./ImageUploadAdapter.js";
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
        let editor = newEditor;
    })
    .catch(error => {
        console.error(error);
    });

function MyCustomUploadAdapterPlugin(editor) {
    editor.plugins.get('FileRepository').createUploadAdapter = (loader) => {
        return new UploadAdapter(loader)
    }
}
