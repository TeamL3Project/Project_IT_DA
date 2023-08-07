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

