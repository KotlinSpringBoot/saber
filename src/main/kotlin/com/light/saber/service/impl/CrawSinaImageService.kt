package com.light.saber.service.impl

import com.light.saber.dao.ImageRepository
import com.light.saber.model.Image
import com.light.saber.service.CrawImageService
import com.light.saber.util.SinaImageJsonResultProcessor
import com.light.saber.util.URLUtil
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.text.SimpleDateFormat
import java.util.*

@Service
class CrawSinaImageService : CrawImageService {
    val logger = LoggerFactory.getLogger(CrawSinaImageService::class.java)

    @Autowired
    lateinit var imageRepository: ImageRepository

    override fun doCrawJob() = runBlocking {
        for (page in 1..12) {
            launch(CommonPool) {
                saveImage(page)
            }
        }
    }

    override fun saveImage(page: Int) {
        SinaImageJsonResultProcessor.getSinaImageUrls(page).forEach {
            val url = it
            if (imageRepository.countByUrl(url) == 0) {
                val image = Image()
                image.category = "新浪图片 ${SimpleDateFormat("yyyy-MM-dd").format(Date())}"
                image.url = url
                image.sourceType = 1
                image.imageBlob = URLUtil.getByteArray(url)
                logger.info("image = ${image}")
                imageRepository.save(image)
            }
        }
    }

}
