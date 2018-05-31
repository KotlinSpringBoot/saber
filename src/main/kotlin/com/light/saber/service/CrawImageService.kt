package com.light.saber.service

import com.light.saber.api.GankApiBuilder
import com.light.saber.dao.ImageRepository
import com.light.saber.model.Image
import com.light.saber.util.JsonResultProcessor
import com.light.saber.webclient.CrawlerWebClient
import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.launch
import kotlinx.coroutines.experimental.runBlocking
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.net.URL
import java.text.SimpleDateFormat
import java.util.*

@Service
class CrawImageService {
    val logger = LoggerFactory.getLogger(CrawImageService::class.java)

    @Autowired
    lateinit var CrawlerWebClient: CrawlerWebClient

    @Autowired
    lateinit var imageRepository: ImageRepository

    fun doGankImageCrawJob() = runBlocking {
        for (page in 1..7) {
            launch(CommonPool) {
                saveGankImage(page)
            }
        }
    }

    private fun saveGankImage(page: Int) {
        val api = GankApiBuilder.build(page)
        JsonResultProcessor.getGankImageUrls(api).forEach {
            val url = it
            if (imageRepository.countByUrl(url) == 0) {
                val image = Image()
                image.category = "干货集中营福利 ${SimpleDateFormat("yyyy-MM-dd").format(Date())}"
                image.url = url
                image.sourceType = 1
                image.imageBlob = getByteArray(url)
                logger.info("image = ${image}")
                imageRepository.save(image)
            }
        }
    }

    private fun getByteArray(url: String): ByteArray {
        val urlObj = URL(url)
        return urlObj.readBytes()
    }
}
