package com.light.saber.crawler

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONArray
import com.light.saber.service.KnowledgeService
import com.light.saber.webclient.CrawlerWebClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils
import java.net.URL

@Service
class KnowledgeCrawlerOfSpring4All : KnowledgeJsonCrawler {
    @Autowired
    lateinit var KnowledgeService: KnowledgeService
    @Autowired
    lateinit var CrawlerWebClient: CrawlerWebClient

    override fun getArticleBody(e: Element) = e.getElementsByClass("fmt").html()

    override fun pageUrls(page: Int): String = "http://www.spring4all.com/common/articles/${page}"

    override fun getPageJson(url: String) = URL(url).readText(Charsets.UTF_8)

    @Scheduled(cron = "0 30 0 1/1 * ?")
    override fun doCraw() {
        for (p in 1..62) {
            val pageUrl = pageUrls(p)
            val pageJson = getPageJson(pageUrl)
            val map = JSON.parse(pageJson) as Map<*, *>
            val data = map["data"] as Map<*, *>
            val list = data["list"] as JSONArray
            list.forEach {
                try {
                    val articleUrl = "http://www.spring4all.com/article/${(it as Map<*, *>)["id"]}"
                    val articleTitle = it["title"] as String
                    println(articleUrl)
                    val articleHTML = CrawlerWebClient.getPageHtmlText(articleUrl)
                    if (!StringUtils.isEmpty(articleHTML)) {
                        val articleDocument = Jsoup.parse(articleHTML)
                        val articleBody = getArticleBody(articleDocument)
                        println(articleTitle)
                        println(articleUrl)
                        KnowledgeService.doSaveKnowledge(articleUrl, articleTitle, articleBody)
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}