package com.light.saber.controller

import com.light.saber.service.CrawKnowledgeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


@RestController
class KnowledgeCrawController {
    @Autowired
    lateinit var CrawKnowledgeService: CrawKnowledgeService


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

}