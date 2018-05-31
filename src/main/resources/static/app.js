// 点击按钮，返回顶部
function topFunction() {
    document.getElementsByClassName('layui-body')[0].scrollTop = 0;
}

function scrollFun() {
    var layuiBodyScrollTop = document.getElementsByClassName('layui-body')[0].scrollTop
    console.log(layuiBodyScrollTop)
    if (layuiBodyScrollTop > 1000) {
        document.getElementById("back-to-top").style.display = "block";
    } else {
        document.getElementById("back-to-top").style.display = "none";
    }
}

/*注册事件*/
if (document.addEventListener) {
    document.addEventListener('DOMMouseScroll', scrollFun, false);
}//W3C
window.onmousewheel = document.onmousewheel = scrollFun;//IE/Opera/Chrome


function addFavorite(id) {
    $.ajax({
        url: 'addFavorite',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (resp) {
            // alert(JSON.stringify(resp))
            new PNotify({
                title: '收藏操作',
                styling: 'bootstrap3',
                text: JSON.stringify(resp),
                type: 'success',
                delay: 500,
            });
        },
        error: function (msg) {
            // alert(JSON.stringify(msg))
            new PNotify({
                title: '收藏操作',
                styling: 'bootstrap3',
                text: JSON.stringify(msg),
                type: 'error',
                delay: 500,
            });
        }
    })
}

function deleteById(id) {
    $.ajax({
        url: 'delete',
        data: {id: id},
        dataType: 'json',
        type: 'post',
        success: function (resp) {
            // alert(JSON.stringify(resp))
            $('#sotu_favorite_table').bootstrapTable('refresh')
            $('#sotu_table').bootstrapTable('refresh')
            new PNotify({
                title: '删除操作',
                styling: 'bootstrap3',
                text: JSON.stringify(resp),
                type: 'info',
                delay: 500,
            });

        },
        error: function (msg) {
            // alert(JSON.stringify(msg))
            new PNotify({
                title: '删除操作',
                styling: 'bootstrap3',
                text: JSON.stringify(msg),
                type: 'error',
                delay: 500,
            });
        }
    })
}


function downloadImage(src) {
    var $a = $("<a></a>").attr("href", src).attr("download", "sotu.png");
    $a[0].click();
}


function downBase64Image(url) {
    var blob = base64Img2Blob(url);
    url = window.URL.createObjectURL(blob);
    var timestamp = (new Date()).getTime();
    var $a = $("<a></a>").attr("href", url).attr("download", `sotu${timestamp}.png`);
    $a[0].click();
}


function base64Img2Blob(code) {
    var parts = code.split(';base64,');
    var contentType = parts[0].split(':')[1];
    var raw = window.atob(parts[1]);
    var rawLength = raw.length;
    var uInt8Array = new Uint8Array(rawLength);
    for (var i = 0; i < rawLength; ++i) {
        uInt8Array[i] = raw.charCodeAt(i);
    }
    return new Blob([uInt8Array], {type: contentType});
}

function downloadFile(fileName, content) {
    var aLink = document.createElement('a');
    var blob = base64Img2Blob(content); //new Blob([content]);
    var evt = document.createEvent("HTMLEvents");
    evt.initEvent("click", false, false);//initEvent 不加后两个参数在FF下会报错
    aLink.download = fileName;
    aLink.href = URL.createObjectURL(blob);
    aLink.dispatchEvent(evt);
}

function slideShow(index) {
    var urlArray = []
    var size = $('img').length
    for (var i = 0; i < size; i++) {
        urlArray.push($($('img')[i]).attr('data-original'))
    }

    var nextLoop = setInterval(function () {
        $('#picNext').click()
    }, 5000)

    fnSlideShow(urlArray, index, nextLoop);
}

