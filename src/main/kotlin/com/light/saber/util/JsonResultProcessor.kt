package com.light.saber.util

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.light.saber.dto.ImageCategoryAndUrl
import java.net.URL
import java.nio.charset.Charset

object JsonResultProcessor {

    fun getGankImageUrls(url: String): MutableList<String> {
        return parseGankImageUrls(jsonstr = getUrlContent(url))
    }


    fun getUrlContent(url: String): String {
        return URL(url).readText(Charset.defaultCharset())
    }

    fun parseGankImageUrls(jsonstr: String): MutableList<String> {
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


