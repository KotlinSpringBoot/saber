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
        CrawKnowledgeService.doCrawJianShuKnowledge()
        return "DONE"
    }

    @GetMapping("/knowledge/doCrawSegmentFaultKnowledge")
    fun doCrawSegmentFaultKnowledge(): String {
        CrawKnowledgeService.doCrawSegmentFaultKnowledge()
        return "DONE"
    }

}