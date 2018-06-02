package com.light.saber.service.impl

import com.light.saber.dao.CrawSourceMapper
import com.light.saber.dao.KnowledgeMapper
import com.light.saber.model.Knowledge
import com.light.saber.webclient.CrawlerWebClient
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.util.StringUtils

@Service
class CrawKnowledgeService {
    @Autowired
    lateinit var KnowledgeMapper: KnowledgeMapper
    @Autowired
    lateinit var CrawSourceMapper: CrawSourceMapper
    @Autowired
    lateinit var CrawlerWebClient: CrawlerWebClient

    fun doCrawBlockChainKnowledge() {
        for (page in 0..40) {
            try {
                crawBlockChain(page)
            } catch (e: Exception) {

            }
        }
    }

    fun doCrawJianShuKnowledge() {
        val 简书专题URLs = CrawSourceMapper.findJianShu()
        简书专题URLs.forEach {
            for (page in 1..100) {
                try {
                    crawJianShuArticles(page, it.url)
                } catch (e: Exception) {

                }

            }
        }
    }


    fun doCrawSegmentFaultKnowledge() {
        for (page in 1..803) {
            try {
                crawSegmentFault(page)
            } catch (e: Exception) {

            }

        }
    }

    fun doCrawOSChinaKnowledge() {
        for (page in 1..560) {
            try {
                crawOSChina(page)
            } catch (e: Exception) {

            }
        }
    }

    fun doCrawImportNewKnowledge() {
        for (page in 1..135) {
            try {
                crawImportNew(page)
            } catch (e: Exception) {

            }
        }
    }

    fun doCrawITEyeKnowledge() {
        for (page in 1..10000) {
            try {
                crawITEye(page)
            } catch (e: Exception) {

            }
        }
    }

    fun doCrawCNBlogKnowledge() {
        for (page in 1..200) {
            try {
                crawCNBlog(page)
            } catch (e: Exception) {

            }
        }
    }

    fun doCrawInfoQKnowledge() {
        for (page in 0..40) {
            try {
                crawInfoQ(page)
            } catch (e: Exception) {

            }
        }
    }

    private fun crawBlockChain(page: Int) {
        val pageUrl = "http://www.blockchainbrother.com/articles?page=${page}"
        val 文章列表HTML = CrawlerWebClient.getPageHtmlText(pageUrl)
        val document = Jsoup.parse(文章列表HTML)
        // document.getElementsByClassName("news_type2 full_screen")[0].children[1].children[0]
        //<a href=​"/​cn/​articles/​Reactive-Systems-Akka-Actors-DomainDrivenDesign" title=​"使用Akka的Actor模型和领域驱动设计构建反应式系统">​…​</a>​
        document.getElementsByClass("title").forEach {
            val url = it.child(0).attr("href")
            val title = it.child(0).html()
            if (KnowledgeMapper.countByUrl(url) == 0) {
                try {
                    val BlockChain文章HTML = CrawlerWebClient.getPageHtmlText(url)
                    val BlockChain文章Document = Jsoup.parse(BlockChain文章HTML)
                    val content = 获取BlockChain文章内容(BlockChain文章Document)

                    doSaveKnowledge(
                            url = url,
                            title = title,
                            content = content
                    )
                } catch (e: Exception) {

                }
            }
        }

    }


    private fun crawInfoQ(page: Int) {
        val pageUrl = "http://www.infoq.com/cn/java/articles/${page * 12}"
        val 文章列表HTML = CrawlerWebClient.getPageHtmlText(pageUrl)
        val document = Jsoup.parse(文章列表HTML)
        // document.getElementsByClassName("news_type2 full_screen")[0].children[1].children[0]
        //<a href=​"/​cn/​articles/​Reactive-Systems-Akka-Actors-DomainDrivenDesign" title=​"使用Akka的Actor模型和领域驱动设计构建反应式系统">​…​</a>​
        document.getElementsByClass("news_type2 full_screen").forEach {
            val url = it.child(1).child(0).attr("href")
            val title = it.child(1).child(0).html()
            if (KnowledgeMapper.countByUrl(url) == 0) {
                try {
                    val InfoQ文章HTML = CrawlerWebClient.getPageHtmlText(url)
                    val InfoQ文章Document = Jsoup.parse(InfoQ文章HTML)
                    val content = 获取InfoQ文章内容(InfoQ文章Document)
                    doSaveKnowledge(
                            url = url,
                            title = title,
                            content = content
                    )
                } catch (e: Exception) {

                }
            }
        }

    }

    private fun 获取InfoQ文章内容(infoQ文章Document: Document?): String? {
        return infoQ文章Document?.getElementsByClass("text_info text_info_article")?.get(0)?.html()
    }

    private fun crawCNBlog(page: Int) {
        val pageUrl = "https://www.cnblogs.com/#p$page"
        val 文章列表HTML = CrawlerWebClient.getPageHtmlText(pageUrl)
        val document = Jsoup.parse(文章列表HTML)
        // document.getElementsByClassName("titlelnk")[0]
        //<a class=​"titlelnk" href=​"https:​/​/​www.cnblogs.com/​qzrzq1/​p/​9069509.html" target=​"_blank">​基于Orangpi Zero和Linux ALSA实现WIFI无线音箱（一）​</a>​
        document.getElementsByClass("titlelnk").forEach {
            val url = it.attr("href")
            val title = it.html()
            if (KnowledgeMapper.countByUrl(url) == 0) {
                try {
                    val CNBlog文章HTML = CrawlerWebClient.getPageHtmlText(url)
                    val CNBlog文章Document = Jsoup.parse(CNBlog文章HTML)
                    val content = 获取CNBlog文章内容(CNBlog文章Document)
                    doSaveKnowledge(
                            url = url,
                            title = title,
                            content = content
                    )
                } catch (e: Exception) {

                }
            }
        }

    }

    private fun 获取CNBlog文章内容(cnBlog文章Document: Document?): String? {
        return cnBlog文章Document?.getElementById("cnblogs_post_body")?.html()
    }

    private fun crawITEye(page: Int) {
        val pageUrl = "http://www.iteye.com/blogs/category/language?page=$page"
        val 文章列表HTML = CrawlerWebClient.getPageHtmlText(pageUrl)
        val document = Jsoup.parse(文章列表HTML)

        // document.getElementsByClassName("content")[0].children[0].children[0]
        //<a href=​"http:​/​/​fhuan123.iteye.com/​blog/​2423594" title=​"C#Make自动化构建-简介" target=​"_blank">​C#Make自动化构建-简介​</a>​
        document.getElementsByClass("content").forEach {
            val url = it.child(0).child(0).attr("href")
            val title = it.child(0).child(0).html()
            if (KnowledgeMapper.countByUrl(url) == 0) {
                try {
                    val ITEye文章HTML = CrawlerWebClient.getPageHtmlText(url)
                    val ITEye文章Document = Jsoup.parse(ITEye文章HTML)
                    val content = 获取ITEye文章内容(ITEye文章Document)
                    doSaveKnowledge(
                            url = url,
                            title = title,
                            content = content
                    )
                } catch (e: Exception) {

                }
            }
        }

    }

    private fun 获取ITEye文章内容(itEye文章Document: Document?): String? {
        return itEye文章Document?.getElementById("blog_content")?.html()
    }

    private fun crawImportNew(page: Int) {
        val pageUrl = "http://www.importnew.com/all-posts/page/$page"
        val 文章列表HTML = CrawlerWebClient.getPageHtmlText(pageUrl)
        val document = Jsoup.parse(文章列表HTML)
        // document.getElementsByClassName("meta-title")[0]
        //<a class=​"meta-title" target=​"_blank" href=​"http:​/​/​www.importnew.com/​28577.html" title=​"使用 Java 注解自动化处理对应关系实现注释代码化 ">​使用 Java 注解自动化处理对应关系实现注释代码化​</a>​
        document.getElementsByClass("meta-title").forEach {
            val url = it.attr("href")
            if (KnowledgeMapper.countByUrl(url) == 0) {
                try {
                    val ImportNew文章HTML = CrawlerWebClient.getPageHtmlText(url)
                    val ImportNew文章Document = Jsoup.parse(ImportNew文章HTML)
                    val title = 获取ImportNew文章标题(ImportNew文章Document)
                    val content = 获取ImportNew文章内容(ImportNew文章Document)
                    doSaveKnowledge(
                            url = url,
                            title = title,
                            content = content
                    )
                } catch (e: Exception) {

                }
            }
        }

    }

    private fun 获取ImportNew文章内容(importNew文章Document: Document?): String? {
        // document.getElementsByClassName("entry")
        return importNew文章Document?.getElementsByClass("entry")?.get(0)?.html()
    }

    private fun 获取ImportNew文章标题(importNew文章Document: Document?): String? {
//        document.getElementsByClassName("entry-header")[0]
//        <div class=​"entry-header">​<h1>​使用 Java 注解自动化处理对应关系实现注释代码化​</h1>​</div>​
//        document.getElementsByClassName("entry-header")[0].children[0].innerHTML
//        "使用 Java 注解自动化处理对应关系实现注释代码化"
        return importNew文章Document?.getElementsByClass("entry-header")?.get(0)?.child(0)?.html()

    }

    private fun crawOSChina(page: Int) {
        val pageUrl = "https://www.oschina.net/action/ajax/get_more_recommend_blog?classification=0&p=$page"
        val 文章列表HTML = CrawlerWebClient.getPageHtmlText(pageUrl)
        val document = Jsoup.parse(文章列表HTML)

//        document.getElementsByClassName("blog-name")[0]

        val titles = arrayListOf<String>()

        document.getElementsByClass("blog-name").forEach {
            titles.add(it.html())
        }

//        document.getElementsByClassName("blog-title-link")[0]
//<a href=​"https:​/​/​my.oschina.net/​u/​3115385/​blog/​1819321" class=​"sc overh blog-title-link" target=​"_blank" title=​"JVM调优-堆大小设置、回收器选择">​…​</a>​

        val links = document.getElementsByClass("blog-title-link")

        if (titles.size != links.size) {
            return
        }

        links.forEachIndexed { index, it ->
            val url = it.attr("href")
            if (KnowledgeMapper.countByUrl(url) == 0) {
                try {
                    val OSChina文章HTML = CrawlerWebClient.getPageHtmlText(url)
                    val OSChina文章Document = Jsoup.parse(OSChina文章HTML)
                    val content = 获取OSChina文章内容(OSChina文章Document)
                    doSaveKnowledge(
                            url = url,
                            title = titles[index],
                            content = content
                    )
                } catch (e: Exception) {

                }
            }
        }
    }

    private fun 获取OSChina文章内容(osChina文章Document: Document?): String? {
//        document.getElementById("blogBody")
        return osChina文章Document?.getElementById("blogBody")?.html()
    }


    private fun crawSegmentFault(page: Int) {
        val SegmentFault文章列表的HTML = CrawlerWebClient.getPageHtmlText("https://segmentfault.com/blogs?page=$page")
        val document = Jsoup.parse(SegmentFault文章列表的HTML)
        //document.getElementsByClassName('blog-stream')[0].children.length
        document.getElementsByClass("blog-stream")[0].children().forEach {
            //            document.getElementsByClassName('blog-stream')[0].children[0].children[1].children[0].children[0]
//            <a href=​"/a/​1190000000270453">​开启 NFS 文件系统提升 Vagrant 共享目录的性能​</a>​
            try {
                val url = "https://segmentfault.com" + it.child(1).child(0).child(0).attr("href")
                if (KnowledgeMapper.countByUrl(url) == 0) {
                    val SegmentFault文章HTML = CrawlerWebClient.getPageHtmlText(url)
                    val SegmentFault文章Document = Jsoup.parse(SegmentFault文章HTML)
                    val title = 获取SegmentFault文章标题(SegmentFault文章Document)
                    val content = 获取SegmentFault文章内容(SegmentFault文章Document)
                    doSaveKnowledge(
                            url = url,
                            title = title,
                            content = content
                    )
                }
            } catch (e: Exception) {

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
            try {
                val url = getKnowledgeUrl(it)
                if (KnowledgeMapper.countByUrl(url) == 0) {
                    val 简书文章HTML = CrawlerWebClient.getPageHtmlText(url)
                    val 简书文章Document = Jsoup.parse(简书文章HTML)
                    val title = 获取简书文章标题(简书文章Document)
                    val content = 获取简书文章内容(简书文章Document)
                    doSaveKnowledge(
                            url = url,
                            title = title,
                            content = content
                    )
                }
            } catch (e: Exception) {

            }


        }


    }

    private fun 获取BlockChain文章内容(infoQ文章Document: Document?): String? {
        return infoQ文章Document?.getElementsByClass("widget-article")?.get(0)?.html()
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

    @Autowired lateinit var TakeKeyWordsService: TakeKeyWordsService
    private fun doSaveKnowledge(url: String, title: String?, content: String?) {
        if (StringUtils.isEmpty(url) || StringUtils.isEmpty(title) || StringUtils.isEmpty(content)) {
            return
        }

        val Knowledge = Knowledge()
        Knowledge.url = url
        Knowledge.title = title ?: ""
        Knowledge.content = content ?: ""

        val html = Knowledge.content
        if (!StringUtils.isEmpty(html)) {
            val keyWords = TakeKeyWordsService.getKeyWords(html)
            Knowledge.keyWords = keyWords
        }

        println(Knowledge.url)
        println(Knowledge.title)
        println(Knowledge.keyWords)

        try {
            KnowledgeMapper.save(Knowledge)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}