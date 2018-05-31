package com.light.saber.api

object ImageSearchApiBuilder {
    fun build(word: String, page: Int): String {
        return "http://image.baidu.com/search/acjson?tn=resultjson_com&ipn=rj&fp=result&word=${word}&pn=${page}"
    }
}
