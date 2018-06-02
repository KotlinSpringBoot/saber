package com.light.saber.service

interface CrawImageService {
    fun doCrawJob()
    fun saveImage(page: Int)
}