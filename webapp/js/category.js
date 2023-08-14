let isSubscribed = false;

$("#subscribeBtn").on("click", () => {
    if (!isSubscribed) {
        subscribeChannel();
    } else {
        unsubscribeChannel();
    }
});

function subscribeChannel() {
    $.ajax({
        type: "POST",
        url: "/subscribeChannel",
        data: {
            userId: "${sessionScope.userId}",
            channelId: "${channel.chnum}"
        },
        dataType: "json",
        success: function(data) {
            if (data.result === "success") {
                isSubscribed = true;
                $("#subscribeBtn").text("구독 취소");
                alert("[${channel.chname}] 구독되었습니다.");
            } else {
                // 오류 처리
            }
        }
    });
}

function unsubscribeChannel() {
    $.ajax({
        type: "POST",
        url: "/unsubscribeChannel",
        data: {
            userId: "${sessionScope.userId}",
            channelId: "${channel.chnum}"
        },
        dataType: "json",
        success: function(data) {
            if (data.result === "success") {
                isSubscribed = false;
                $("#subscribeBtn").text("구독하기");
                alert("[${channel.chname}] 구독이 취소되었습니다.");
            } else {
                // 오류 처리
            }
        }
    });
}
