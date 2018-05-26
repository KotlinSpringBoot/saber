package com.light.saber.webclient


import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.html.HtmlPage


object CrawlerWebClient {
    private var webClient: WebClient? = null

    private fun instanceWebClient(javaScriptTimeout: Long): WebClient {
        if (webClient == null) {
            webClient = WebClient()
        }
        if (javaScriptTimeout > 0) {
            webClient?.javaScriptTimeout = javaScriptTimeout
        }
        webClient?.options?.isJavaScriptEnabled = true //启用JS解释器，默认为true
        webClient?.options?.isCssEnabled = false
        webClient?.options?.isThrowExceptionOnScriptError = false //js运行错误时，是否抛出异常
        webClient?.options?.isUseInsecureSSL = true
        return webClient as WebClient
    }

    fun getPageHtmlText(url: String): String? {
        webClient = instanceWebClient(3000)
        return webClient?.getPage<HtmlPage>(url)?.asXml()
    }

}
