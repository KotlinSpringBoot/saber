package com.light.saber.webclient


import com.gargoylesoftware.htmlunit.WebClient
import com.gargoylesoftware.htmlunit.WebRequest
import com.gargoylesoftware.htmlunit.WebResponse
import com.gargoylesoftware.htmlunit.html.HtmlPage
import com.gargoylesoftware.htmlunit.util.WebConnectionWrapper
import org.springframework.stereotype.Service
import java.io.IOException


@Service
class CrawlerWebClient {

    private fun instanceWebClient(javaScriptTimeout: Long): WebClient {
        val webClient = WebClient()
        webClient.javaScriptTimeout = javaScriptTimeout
        webClient.options.isJavaScriptEnabled = false //2-8 RULE,提高爬虫抓取性能，我们不启用JS解释器，默认为true
        webClient.options.isCssEnabled = false
        webClient.options.isThrowExceptionOnScriptError = false //js运行错误时，是否抛出异常
        webClient.options.isUseInsecureSSL = true
        webClient.options.isDoNotTrackEnabled = false
        webClient.webConnection = object : WebConnectionWrapper(webClient) {
            @Throws(IOException::class)
            override fun getResponse(request: WebRequest): WebResponse {
                var response = super.getResponse(request)
                val requestUrl = request.getUrl()
                println("requestUrl=$requestUrl")
                println("response=${response.contentAsString}")
                return response
            }
        }
        return webClient
    }

    fun getPageHtmlText(url: String): String? {
        var result: String? = ""
        val webClient = instanceWebClient(7000)
        try {
            result = webClient.getPage<HtmlPage>(url).asXml()
        } catch (e: Exception) {
            e.printStackTrace()
            result = null
        }
        webClient.close()

        return result
    }

}


/**
可以通过page.executeJavaScript来执行js

例如：

HtmlPage page = wc.getPage("http://xxx.com/");
wc.waitForBackgroundJavaScript(30 * 1000); /* will wait JavaScript to execute up to 30s */
ScriptResult result = page.executeJavaScript("document.getElementById('pk_1248827').onmouseover(window.event)");
HtmlPage jspage = (HtmlPage) result.getNewPage();

 */