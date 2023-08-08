$(function () {
    card = $('.reco_card')
    count = 1;

    function card_translate(){
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

});
