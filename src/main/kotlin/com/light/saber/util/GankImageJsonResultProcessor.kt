package com.light.saber.util

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.light.saber.api.GankImageApiBuilder
import java.net.URL
import java.nio.charset.Charset

object GankImageJsonResultProcessor {

    fun getGankImageUrls(page: Int): MutableList<String> {
        val api = GankImageApiBuilder.build(page)
        return parseGankImageUrls(jsonstr = getUrlContent(api))
    }

    private fun getUrlContent(url: String): String {
        return URL(url).readText(Charset.defaultCharset())
    }

    private fun parseGankImageUrls(jsonstr: String): MutableList<String> {
        val urls = mutableListOf<String>()
        try {
            val obj = JSON.parse(jsonstr) as Map<*, *>
            val dataArray = obj.get("results") as JSONArray
            dataArray.forEach {
                val url = (it as Map<*, *>).get("url") as String
                urls.add(url)
            }
        } catch (ex: Exception) {
        }
        return urls
    }


}


