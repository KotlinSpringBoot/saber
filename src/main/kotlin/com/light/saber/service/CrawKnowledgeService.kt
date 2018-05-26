package com.light.saber.service

import com.light.saber.dao.CrawSourceDao
import com.light.saber.dao.KnowledgeDao
import com.light.saber.model.Knowledge
import com.light.saber.webclient.CrawlerWebClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class CrawKnowledgeService {
    @Autowired
    lateinit var KnowledgeDao: KnowledgeDao
    @Autowired
    lateinit var CrawSourceDao: CrawSourceDao

    fun doCrawJianShuKnowledge() {

        val 简书专题URLs = CrawSourceDao.findJianShu()

        简书专题URLs.forEach {
            launch(CommonPool) {
                for (page in 1..100) {
                    crawJianShuArticles(page, it.url)
                }
            }
        }
    }


    fun doCrawSegmentFaultKnowledge() {
        for (page in 1..803) {
            launch(CommonPool) {
                crawSegmentFault(page)
            }
        }
    }

    private fun crawSegmentFault(page: Int) {
        val SegmentFault文章列表的HTML = CrawlerWebClient.getPageHtmlText("https://segmentfault.com/blogs?page=$page")
        val document = Jsoup.parse(SegmentFault文章列表的HTML)
        //document.getElementsByClassName('blog-stream')[0].children.length
        document.getElementsByClass("blog-stream")[0].children().forEach {
            //            document.getElementsByClassName('blog-stream')[0].children[0].children[1].children[0].children[0]
//            <a href=​"/a/​1190000000270453">​开启 NFS 文件系统提升 Vagrant 共享目录的性能​</a>​
            val url = "https://segmentfault.com" + it.child(1).child(0).child(0).attr("href")
            if (KnowledgeDao.countByUrl(url) == 0) {
                val SegmentFault文章HTML = CrawlerWebClient.getPageHtmlText(url)
                val SegmentFault文章Document = Jsoup.parse(SegmentFault文章HTML)
                val title = 获取SegmentFault文章标题(SegmentFault文章Document)
                val content = 获取SegmentFault文章内容(SegmentFault文章Document)
                println(title)
                println(url)
                println(content)

                doSaveKnowledge(
                        url = url,
                        title = title,
                        content = content
                )
            }
        }

    }

    private fun 获取SegmentFault文章内容(segmentFault文章Document: Document?): String? {
//        document.getElementsByClassName('article__content')
        val e = segmentFault文章Document?.getElementsByClass("article__content")
        return e?.html()
    }

    private fun 获取SegmentFault文章标题(segmentFault文章Document: Document?): String? {
//        document.getElementById('articleTitle').children[0].innerHTML
//        " 开启 NFS 文件系统提升 Vagrant 共享目录的性能"
        val e = segmentFault文章Document?.getElementById("articleTitle")
        return e?.child(0)?.html()
    }


    private fun crawJianShuArticles(page: Int, 要遍历的简书专题URL: String) {
        val 简书专题分页URL = "${要遍历的简书专题URL}?order_by=added_at&page=${page}"
        val 简书专题HTML = CrawlerWebClient.getPageHtmlText(简书专题分页URL)
        val document = Jsoup.parse(简书专题HTML)
        document.getElementsByClass("content").forEach {
            val url = getKnowledgeUrl(it)
            if (KnowledgeDao.countByUrl(url) == 0) {
                val 简书文章HTML = CrawlerWebClient.getPageHtmlText(url)
                val 简书文章Document = Jsoup.parse(简书文章HTML)
                val title = 获取简书文章标题(简书文章Document)
                val content = 获取简书文章内容(简书文章Document)
                println(title)
                println(url)
                println(content)

                doSaveKnowledge(
                        url = url,
                        title = title,
                        content = content
                )
            }
        }


    }

    private fun 获取简书文章内容(jianShuArticleDocument: Document?): String? {
//        document.getElementsByClassName('article')[0].children
//        HTMLCollection(3) [h1.title, div.author, div.show-content]
        val e = jianShuArticleDocument?.getElementsByClass("article")
        return e?.get(0)?.child(2)?.html()
    }

    private fun 获取简书文章标题(jianShuArticleDocument: Document?): String? {
        val e = jianShuArticleDocument?.getElementsByClass("article")
        return e?.get(0)?.child(0)?.html()
    }

    private fun getKnowledgeUrl(it: Element): String {
        return "http://www.jianshu.com" + it.child(0).attr("href")
    }

    private fun doSaveKnowledge(url: String, title: String?, content: String?) {
        val Knowledge = Knowledge()
        Knowledge.url = url
        Knowledge.title = title ?: ""
        Knowledge.content = content ?: ""
        try {
            KnowledgeDao.save(Knowledge)
        } catch (e: Exception) {
            // ignore
        }
    }
}