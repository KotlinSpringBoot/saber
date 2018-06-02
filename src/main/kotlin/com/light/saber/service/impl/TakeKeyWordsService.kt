package com.light.saber.service.impl

import com.light.saber.dao.KnowledgeMapper
import com.light.saber.textrank.HtmlUtil
import com.light.saber.textrank.TextRankKeyword
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * 批量提取知识库中文章的关键字（当为 keyWords 空时，执行提取设置操作）
 */
@Service
class TakeKeyWordsService {
    @Autowired lateinit var knowledgeMapper: KnowledgeMapper

    fun take() {
        val ids = knowledgeMapper.listAllId()
        ids.forEach {
            try {
                val k = knowledgeMapper.getOne(it)
                val keyWords = getKeyWords(k.content)
                knowledgeMapper.updateKeyWords(it, keyWords)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getKeyWords(html: String): String {
        try {
            val text = HtmlUtil.getText(html)
            return TextRankKeyword().getKeyword("", text)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return ""
    }

}
