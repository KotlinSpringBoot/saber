package com.light.saber.api

object SinaImageApiBuilder {
    fun build(page: Int): String {
        return "http://platform.sina.com.cn/slide/album_photo_col?app_key=1985696825&photo_col_id=13&tags=cat&tagmode=any&format=json&page=${page}"
    }
}