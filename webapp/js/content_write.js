function cnt_tag() {
    if ($(".board_tag_text").val() == "") {
        return;
    }
    if ($('li.viewer_tag_item').length < 5) {
        var text_add = $("#input_tag").val();
        var ul_list = $("#ul_tag_list");
        ul_list.append('<li class="viewer_tag_item" id="tag_item">' +
            '<input name="tagname" type="text" class="viewer_tag_link" value="#' + text_add + '" style="width:'+ ((text_add.length+2))+'em" readonly></li>');
        $("#input_tag").val("");
    } else {
        alert("태그는 5개까지 입력 가능합니다.");
        const target = $('#tag_add');
        target.prop('disabled', true); // 버튼 비활성화
    }
}

$(function () {
    $(document).on('click', '.viewer_tag_link', function () {
        $(this).parent().remove();
        if ($('li.viewer_tag_item').length < 5) {
            const target = $('#tag_add');
            target.prop('disabled', false);
        }
    })
    $('.category_select_from').change(function () {
        $('.categoryId').val($(this).val())
    })


    $('.write_cancel').click(function () {
        history.back();
    })

    function adjustWidth(inputElement) {
        inputElement.style.width = inputElement.value.length + 'ch';
    }


    $('.thumbNailUpload').change(function (e) {
        const inputfile = $(this).val().split('\\');
        const filename = inputfile[inputfile.length - 1];		//inputfile.length - 1 = 2
        const pattern = /(gif|jpg|jpeg|png)$/i;						//i(ignore case) : 대소문자 무시를 의미한다

        if (pattern.test(filename)) {
            $('#filename').text(filename);

            const reader = new FileReader();						//파일을 읽기 위해 객체 생성
            reader.readAsDataURL(event.target.files[0]);
            console.log(reader);
            reader.onload = function () {								//읽기에 성공한 경우 실행되는 이벤트 핸들러
                $('.thumbNailImage').attr('src', this.result).css('display', 'inline-block');

            };
        } else {
            alert('이미지 파일(gif, jpg, jpeg, png)가 아닌 경우 업로드되지 않습니다.');
            $(this).val('');
        }
    });
});
