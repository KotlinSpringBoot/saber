package com.light.saber.controller

import com.light.saber.crawler.*
import com.light.saber.crawler.CrawKnowledgeServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class KnowledgeCrawController {
    @Autowired
    lateinit var CrawKnowledgeService: CrawKnowledgeServiceImpl


    @Autowired
    lateinit var KnowledgeCrawlerOfSpring4All: KnowledgeCrawlerOfSpring4All


    @Autowired
    lateinit var KnowledgeCrawlerOfConcurrentProgramming: KnowledgeCrawlerOfConcurrentProgramming


    @GetMapping("/knowledge/doCrawJianShu")
    fun doCrawJianShu(): String {
        Thread {
            CrawKnowledgeService.doCrawJianShuKnowledge()
        }.start()


        return "DONE"
    }

    @GetMapping("/knowledge/doCrawSegmentFaultKnowledge")
    fun doCrawSegmentFaultKnowledge(): String {
        Thread {
            CrawKnowledgeService.doCrawSegmentFaultKnowledge()
        }.start()

        return "DONE"
    }

    @GetMapping("/knowledge/doCrawOSChinaKnowledge")
    fun doCrawOSChinaKnowledge(): String {
        Thread {
            CrawKnowledgeService.doCrawOSChinaKnowledge()
        }.start()

        return "DONE"
    }

    @GetMapping("/knowledge/doCrawImportNewKnowledge")
    fun doCrawImportNewKnowledge(): String {
        Thread {
            CrawKnowledgeService.doCrawImportNewKnowledge()
        }.start()

        return "DONE"
    }
    @GetMapping("/knowledge/doCrawITEyeKnowledge")
    fun doCrawITEyeKnowledge(): String {
        Thread {
            CrawKnowledgeService.doCrawITEyeKnowledge()
        }.start()

        return "DONE"
    }

    @GetMapping("/knowledge/doCrawCNBlogKnowledge")
    fun doCrawCNBlogKnowledge(): String {
        Thread {
            CrawKnowledgeService.doCrawCNBlogKnowledge()
        }.start()

        return "DONE"
    }

    @GetMapping("/knowledge/doCrawInfoQKnowledge")
    fun doCrawInfoQKnowledge(): String {
        Thread {
            CrawKnowledgeService.doCrawInfoQKnowledge()
        }.start()

        return "DONE"
    }


    @GetMapping("/knowledge/doCrawBlockChainKnowledge")
    fun doCrawBlockChainKnowledge(): String {
        Thread {
            CrawKnowledgeService.doCrawBlockChainKnowledge()
        }.start()

        return "DONE"
    }


    @GetMapping("/knowledge/KnowledgeCrawlerOfSpring4All")
    fun KnowledgeCrawlerOfSpring4All(): String {
        Thread {
            KnowledgeCrawlerOfSpring4All.doCraw()
        }.start()

        return "DONE"
    }


    @GetMapping("/knowledge/KnowledgeCrawlerOfConcurrentProgramming")
    fun KnowledgeCrawlerOfConcurrentProgramming(): String {
        Thread {
            KnowledgeCrawlerOfConcurrentProgramming.doCraw()
        }.start()

        return "DONE"
    }

}