package com.light.saber.util

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.light.saber.api.SinaImageApiBuilder
import java.net.URL
import java.nio.charset.Charset

object SinaImageJsonResultProcessor {

    fun getSinaImageUrls(page: Int): MutableList<String> {
        val api = SinaImageApiBuilder.build(page)
        return parseSinaImageUrls(jsonstr = getUrlContent(api))
    }

    private fun getUrlContent(url: String): String {
        return URL(url).readText(Charset.defaultCharset())
    }

    private fun parseSinaImageUrls(jsonstr: String): MutableList<String> {
        val urls = mutableListOf<String>()
        try {
            val obj = JSON.parse(jsonstr) as Map<*, *>
            val dataArray = obj.get("data") as JSONArray
            dataArray.forEach {
                val url = (it as Map<*, *>).get("img_url") as String
                urls.add(url)
            }
        } catch (ex: Exception) {
        }
        return urls
    }


}


