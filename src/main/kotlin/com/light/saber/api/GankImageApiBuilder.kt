package com.light.saber.api

object GankImageApiBuilder {
    fun build(page: Int): String {
        // 此处 api 中的中文需要转义
        return "http://gank.io/api/data/%E7%A6%8F%E5%88%A9/100/${page}"
    }
}

/**
 * http://gank.io/api/data/福利/100/6
 *
 * 分类数据: http://gank.io/api/data/数据类型/请求个数/第几页

数据类型： 福利 | Android | iOS | 休息视频 | 拓展资源 | 前端 | all
请求个数： 数字，大于0
第几页：数字，大于0
例：
http://gank.io/api/data/Android/10/1
http://gank.io/api/data/福利/10/1
http://gank.io/api/data/iOS/20/2
http://gank.io/api/data/all/20/2

{
error: false,
results: [
{
_id: "56cc6d23421aa95caa707c6f",
createdAt: "2015-08-06T01:33:55.463Z",
desc: "8.6",
publishedAt: "2015-08-06T04:16:55.601Z",
type: "福利",
url: "http://ww4.sinaimg.cn/large/7a8aed7bgw1eusn3niy2bj20hs0qo0wb.jpg",
used: true,
who: "张涵宇"
},
 ...
]
}



 */
