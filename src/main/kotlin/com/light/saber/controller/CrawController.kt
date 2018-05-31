package com.light.saber.controller

import com.light.saber.service.CrawImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by jack on 2017/7/22.
 */

@Controller
class CrawController {

    @Autowired lateinit var crawImageService: CrawImageService

    @RequestMapping(value = ["doGankImageCrawJob"], method = [(RequestMethod.GET)])
    @ResponseBody
    fun doGankImageCrawJob(): String {
        crawImageService.doGankImageCrawJob()
        return "doBaiduImageCrawJob JOB Started"
    }

}
