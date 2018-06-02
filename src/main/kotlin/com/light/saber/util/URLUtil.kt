package com.light.saber.util

import java.net.URL

object URLUtil {
    fun getByteArray(url: String): ByteArray {
        val urlObj = URL(url)
        return urlObj.readBytes()
    }
}