package com.light.saber.util

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.light.saber.dto.ImageCategoryAndUrl
import java.net.URL
import java.nio.charset.Charset

object JsonResultProcessor {

    fun getBaiduImageCategoryAndUrlList(url: String): MutableList<ImageCategoryAndUrl> {
        return parseBaiduImageCategoryAndUrlList(jsonstr = getUrlContent(url))
    }

    fun getGankImageUrls(url: String): MutableList<String> {
        return parseGankImageUrls(jsonstr = getUrlContent(url))
    }

    fun parseBaiduImageCategoryAndUrlList(jsonstr: String): MutableList<ImageCategoryAndUrl> {
        val imageResultList = mutableListOf<ImageCategoryAndUrl>()
        try {
            val obj = JSON.parse(jsonstr) as Map<*, *>
            val dataArray = obj.get("data") as JSONArray
            dataArray.forEach {
                val category = (it as Map<*, *>).get("fromPageTitleEnc") as String
                val url = it.get("thumbURL") as String
                if (passFilter(url)) {
                    val imageResult = ImageCategoryAndUrl(category = category, url = url)
                    imageResultList.add(imageResult)
                }
            }

        } catch (ex: Exception) {

        }
        return imageResultList
    }

    fun getUrlContent(url: String): String {
        return URL(url).readText(Charset.defaultCharset())
    }

    fun passFilter(imgUrl: String): Boolean {
        return imgUrl.endsWith(".jpg")
                && !imgUrl.contains("baidu.com/")
                && !imgUrl.contains("126.net")
                && !imgUrl.contains("pconline.com")
                && !imgUrl.contains("nipic.com")
                && !imgUrl.contains("zol.com")
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


