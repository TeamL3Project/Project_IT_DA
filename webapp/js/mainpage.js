$(function () {
    card = $('.reco_card')
    count = 1;

    function card_translate() {
        for (let i = 0; i < card.length; i++) {
            rate = (i + count) % card.length;
            if (rate < 0) rate += card.length;
            card[i].style.transform = 'translate(' + rate + '00%,0px)'
        }
    }

    function rotate() {
        card_translate();
        count++;
    }

    function rotateReverse() {
        card_translate();
        count--;
    }

    rotate();
    setInterval(rotate, 3000);

    $('.left-area').click(rotate);
    $('.right-area').click(rotateReverse);

    $(".bt-item").click(function () {
        $(".bt-item.on").removeClass('on');
        $(this).addClass('on').css("box-shadow", "none");
    });

    function callContents_ajax(categoryNum) {
        if (isCategoryButtonOn != categoryNum) {
            pageCount = FIRST_PAGE;
            isCategoryButtonOn = categoryNum;
            $(".popular-list-ul").html("");
        }
        $.ajax({
            type: "GET",
            url: contextPath + "/contentByCategory.co",
            data: {
                categoryNum: categoryNum,
                pageCount: pageCount
            },
            dataType: "json",
            success: function (data) {

                $(data).each(function () {
                        if (data.length != 10) {
                            $(".loader").css('display', 'none');
                        } else {
                            $(".loader").css('display', 'flex');
                        }
                        if (this.intro.length > 80) {
                            this.intro = this.intro.substring(0, 80) + "...";
                        }
                        if (this.boardTitle.length > 17) {
                            this.boardTitle = this.boardTitle.substring(0, 17) + "...";
                        }
                        var appendData = '<a href="contents/' + this.chNum + '/' + this.boardNum + '" class="popular-list-card">'
                            + '<li class="popular-list-content"><span class="popular-list-title">' + this.boardTitle + '</span><br>'
                            + '<p>' + this.intro + '</p></li>'
                            + '<li class="popular-list-imgframe">';
                        if (this.thumbNail == null) {
                            appendData += '<img src="image/common/itda_logo3.png" class="popular-list-img"></li></a>'
                        } else {
                            appendData += '<img src="image/content/' + this.chNum + '/' + this.boardDate.substring(0, 10) + '/' + this.thumbNail + '" class="popular-list-img"></li></a>'
                        }
                        $(".popular-list-ul").append(appendData);
                    }
                )
            }
        })
    }

    let isCategoryButtonOn = $('.contents_category.on').prop('id')

    $('.contents_category').click(function () {
        if (isCategoryButtonOn == $(this).prop('id')) return;
        const categoryNum = $(this).prop('id');
        console.log(categoryNum);
        callContents_ajax(categoryNum);
    })

    const FIRST_PAGE = 1;
    let pageCount = FIRST_PAGE;
    let isExecuted = false;
    $(window).scroll(function () {
        const totalHeight = $(document).height();
        const currentScrollPosition = $(window).scrollTop();
        if (!isExecuted && currentScrollPosition + 1188 >= totalHeight - 1) {
            isExecuted = true;
            setTimeout(() => {
                pageCount++;
                callContents_ajax(isCategoryButtonOn);
                console.log(pageCount);
                isExecuted = false;
        }, 700);
        }
    });
    let firstEntrance = 0;
    callContents_ajax(firstEntrance);
});

