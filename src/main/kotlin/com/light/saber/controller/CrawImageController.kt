package com.light.saber.controller

import com.light.saber.service.impl.CrawGankImageService
import com.light.saber.service.impl.CrawSinaImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

/**
 * Created by jack on 2017/7/22.
 */

@Controller
class CrawImageController {

    @Autowired
    lateinit var crawGankImageService: CrawGankImageService
    @Autowired
    lateinit var crawSinaImageService: CrawSinaImageService

    @RequestMapping(value = ["crawGankImageService"], method = [(RequestMethod.GET)])
    @ResponseBody
    fun crawGankImageService(): String {
        crawGankImageService.doCrawJob()
        return "crawGankImageService JOB Started"
    }

    @RequestMapping(value = ["crawSinaImageService"], method = [(RequestMethod.GET)])
    @ResponseBody
    fun crawSinaImageService(): String {
        crawSinaImageService.doCrawJob()
        return "crawSinaImageService JOB Started"
    }

}
