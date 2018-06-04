package com.light.saber.crawler

interface CrawImageService {
    fun doCrawJob()
    fun saveImage(page: Int)
}