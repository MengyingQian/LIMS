(function() {

    $('.func-list li,.ui-arrowlink').on("click", function(e) {
        if ($(e.target).hasClass('label')) return;
        var item = $(this);
        item.addClass('active');
        setTimeout(function() {
            item.removeClass('active');
        }, 300);
    });

    $('.read_btn_more').on('click', function() {
        var cid = $(this).attr('data-cid');
        window.location.href = "http://qqread.qq.com/book/list/list_" + cid + '.html';
    })


    var ua = navigator.userAgent;
    var REGEXP_IOS_QQ = /(iPad|iPhone|iPod).*? (IPad)?QQ\/([\d\.]+)/; //
    var REGEXP_ANDROID_QQ = /V1_AND_SQI?_([\d\.]+)(.*? QQ\/([\d\.]+))?/; // 国际版的 QQ 的 ua 是 sqi，
    var REGEXP_IOS = /(iPad|iPhone|iPod).*?/; //
    var REGEXP_ANDROID = /android/; //
    var iOS = REGEXP_IOS.test(ua);
    var android = REGEXP_ANDROID.test(ua.toLowerCase());
    var iOS_QQ = REGEXP_IOS_QQ.test(ua);
    var AND_QQ = REGEXP_ANDROID_QQ.test(ua);
    if (iOS && android) iOS = false;

    //配置，如果DOM改变，相关信息可能取不到
    var book_cover = $(".ui-list-img img").attr("src");
    var book_id = book_cover.match(/b_(\d*)\.jpg/)[1]; //书籍ID是基于图片命名提取的，如果有变将失败
    var config = {
        bookid: book_id || '462589',
        title: '手Q阅读中心',
        qrcode_url: window.location.href.split('?')[0] + "?_wv=1",
        img_url: book_cover || ''
    };
    if (!iOS_QQ || !AND_QQ) {
        $(".read_btn_other").click(function() {
            directToRead()
        });
    }


    $(".read_btn_center").click(function() {
        if (iOS_QQ || AND_QQ || android || iOS) {
            var url = "mqqapi://readingcenter/open?src_type=internal&version=1&readtype=22&url=bookDetails.html?id=" + config.bookid + "&stay=0";
            window.location.href = url;
            return;
        } else {
            // var title = config.title ? config.title : '书籍详情页';
            // var qrcode_url = config.qrcode_url ? config.qrcode_url : 'http://m.vip.qq.com/';
            // var img_url = 'http://qqread.qq.com/book/public/img/theme.png';
            // window.location.href = "http://qqread.qq.com/qrcode.html?title=" + title + "&qrcode_url=" + encodeURIComponent(qrcode_url) + "&img_url=" + encodeURIComponent(img_url);
            // return;
        }
    });

    function directToRead() {
        if (iOS_QQ) {
            url = "mqqapi://readingcenter/open?src_type=internal&version=1&readtype=20&id=" + config.bookid + "&name=" + config.title + "&cid=1&tchap=100&stay=0";
            openURL(url);
        } else if (AND_QQ) {
            url = "mqqapi://qqreader/open?src_type=internal&version=1&readtype=25&bid=" + config.bookid + "&cid=1&flag=1&add=0&stay=0";
            openURL(url);
        } else if (android || iOS) {
            url = "mqqapi://readingcenter/open?src_type=internal&version=1&readtype=22&url=bookDetails.html?id=" + config.bookid + "&stay=0";
            window.location.href = url;
        } else {
            // var title = config.title ? config.title : '书籍详情页';
            // var qrcode_url = config.qrcode_url ? config.qrcode_url : 'http://m.vip.qq.com/';
            // var img_url = 'http://qqread.qq.com/book/public/img/theme.png';
            // window.location.href = "http://qqread.qq.com/qrcode.html?title=" + title + "&qrcode_url=" + encodeURIComponent(qrcode_url) + "&img_url=" + encodeURIComponent(img_url);
            // return;
        }
    }

    $(".read_btn").click(function() {
        directToRead();
    });

    $('.book-list-horizontal').delegate("li", "click", function(e) {
        if ($(e.target).hasClass('label')) return;
        var item = $(this);
        item.addClass('active');
        var bid = $(this).attr('data-bid');
        // setTimeout(function() {
        //     item.removeClass('active');
        //     window.location.href = "http://qqread.qq.com/book/detail/" + bid + '.html';
        // }, 300);
    });
    var openURL = function(url) {
        var iframe = document.createElement('iframe');
        iframe.style.cssText = 'display:none;width:0px;height:0px;';

        function failCallback() {}
        if (iOS) {
            iframe.onload = failCallback;
            iframe.src = url;
        }
        (document.body || document.documentElement).appendChild(iframe);
        if (android || !iOS) {
            iframe.onload = failCallback;
            iframe.src = url;
        }
        // android 捕获了iframe的url之后, 也是中断 js 进程的, 所以这里可以用个 setTimeout 0 来删除 iframe
        setTimeout(function() {
            iframe.parentNode.removeChild(iframe);
        }, 0);
        return;
    };

})()