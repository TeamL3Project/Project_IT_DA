$(function () {
    card = $('.reco_card')
    count = 1;


    function rotate() {
        for (let i = 0; i < card.length; i++) {
            rate = (i + count) % card.length;
            if (rate < 0)
                rate = card.length + rate;
            card[i].style.transform = 'translate(' + rate + '00%,0px)'
        }
        count++;
    }

    function rotateReverse() {
        for (let i = 0; i < card.length; i++) {
            rate = (i + count) % card.length;
            if (rate < 0)
                rate = card.length + rate;
            card[i].style.transform = 'translate(' + rate + '00%,0px)'
        }
        count--;
    }

    setInterval(rotate, 3000);
    $('.left-area').click(rotate);

    $('.right-area').click(rotateReverse);

    $(".bt-item").click(function () {
        $(".bt-item.on").removeClass('on');
        console.log('test');
        $(this).addClass('on').css("box-shadow", "none");
    });
});

$(document).ready(function() {
    // 카드를 클릭했을 때 이벤트 핸들러 등록
    $(".reco_card").on("click", function() {
        // 클릭한 카드 안의 링크 주소 가져오기
        const cardLink = $(this).find("a").attr("href");
        if (cardLink) {
            // 채널 페이지로 이동
            window.location.href = cardLink;
        }
    });
});