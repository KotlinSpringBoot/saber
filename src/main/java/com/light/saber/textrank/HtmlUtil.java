package com.light.saber.textrank;

import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.beans.StringBean;
import org.htmlparser.filters.CssSelectorNodeFilter;
import org.htmlparser.util.NodeList;
import org.springframework.util.StringUtils;

public class HtmlUtil {

    public static String getText(String html, String id) {
        try {
            Parser parser = new Parser(html);
            NodeFilter filter = new CssSelectorNodeFilter("#" + id);
            NodeList nList = parser.extractAllNodesThatMatch(filter);
            return nList == null || nList.size() == 0 ? null : nList.elementAt(
                    0).toPlainTextString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getTextByClass(String html, String css_class) {
        try {
            Parser parser = new Parser(html);
            NodeFilter filter = new CssSelectorNodeFilter("." + css_class);
            NodeList nList = parser.extractAllNodesThatMatch(filter);
            return nList == null || nList.size() == 0 ? null : nList.elementAt(
                    0).toPlainTextString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 获取网页中纯文本信息
     *
     * @param html
     * @return
     * @throws Exception
     * @throws Exception
     */
    public static String getText(String html) throws Exception {
        StringBean bean = new StringBean();
        bean.setLinks(false);
        bean.setReplaceNonBreakingSpaces(true);
        bean.setCollapse(true);

        // 返回解析后的网页纯文本信息
        Parser parser = Parser.createParser(html, "utf-8");
        parser.visitAllNodesWith(bean);
        parser.reset();
        String text = bean.getStrings();
        if(StringUtils.isEmpty(text)) return "";

        String reg = "[^\u4e00-\u9fa5]";
        text = text.replaceAll(reg, " ");
        return text;
    }
}
