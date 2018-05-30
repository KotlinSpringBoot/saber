package com.light.saber;

import com.light.saber.textrank.HtmlUtil;
import com.light.saber.textrank.TextRankKeyword;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class HtmlUtilTest {
    @Test
    public void testGetText() throws Exception {
        String html = "<val data-name=\"blog_content_type\" data-value=\"richtext\">  </val>  <div class=\"BlogContent clearfix\">   <p> 今天讲点关于我们公司技术架构演化历程，本人在公司还没正式成立的时候就来了，在此期间有幸经历并参与了公司技术架构的多次升级的改造工作，也很高兴能见证一个公司的发展历程，从中也学到了很多东西。好了废话不说了，开喷： </p>   <p> <strong> 一、初始阶段 ：网站发展初期，我们的架构是什么样子的呢？下面用一张图还说明： </strong> </p>   <p> <img alt=\"\" height=\"671\" src=\"http://static.oschina.net/uploads/space/2016/1017/181307_mmWE_2830950.png\" width=\"1002\"> </p>   <p> 看着图来介绍下，我们初始阶段的网站技术架构，当然看着也不错了，开始阶段就用了这么多技术思想，感觉挺不错吧，用了反向代理、用了集群、用了分布式缓存、用了独立的文件服务器和独立的数据服务器，架构看起来貌似不错。但是随着用户访问量的增加，逐渐发现流量高峰期的时候我们的响应时间总是很慢，甚至有几次直接挂了，主要是访问数据库过于频繁，因为读写操作都是操作一个库，而我们只有一个库，之后呢，我们做了数据库读写分离，写请求走主库，读请求走读库，解决了数据库连接数不足的问题。 </p>   <p> <strong> 二、发展中期：网站发展中期，随着用户访问量的不断递增，我的技术架构发展成什么样子了呢？入下图所示： </strong> </p>   <p> <img alt=\"\" height=\"688\" src=\"http://static.oschina.net/uploads/space/2016/1017/181915_JTCs_2830950.png\" width=\"1133\"> </p>   <p> 随着流量的增大，发现读写分离已经不能满足我们的要求，写库，逐渐出现瓶颈，读库也因为越来越多的人操作，从而也开始变慢；然后我们开始升级我们的架构，先是按业务功能拆分代码，独立部署，然后抽出公共服务，做分布式部署，即所谓的soa服务化；因为服务化之后呢，不同业务使用不同的数据库，即所谓的垂直分库，后来呢 针对部分业务，我们又做了分库、分表技术，通过这些技术手段，我们解决数据库因流量大二导致请求响应慢的问题。 </p>   <p> <strong> 三、发展后期：网站发展后期，我们的技术架构各方面都逐步完善，那到后期我们的技术架构是什么样呢？入下图所示： </strong> </p>   <p> <img alt=\"\" height=\"747\" src=\"http://static.oschina.net/uploads/space/2016/1017/181805_tVtO_2830950.png\" width=\"1046\"> </p>   <p> 到后期，我们有做了很多的技术改造，先后用了搜索引擎elasticsearch，在写数据的时候同时把数据写到es中，供其它应用查询使用，这样我们的查询数据就不用操作数据库了！在后来，我们在程序中各种做缓存，把凡是可降级的接口，均走本地缓存或者远程缓存服务、把非关联的服务做异步并行化调用，从而达到加快服务请求的目的，又通过zookeeper的监视器watcher功能做了统一配置管理服务！！！！写到这里有点累了，用到的技术要点太多，只能简单说下，具体使用每个技术都是为了解决项目中遇到的一些实际问题，上边说大的技术就是我们目前的技术架构，当然还有一些像中间件的技术、排序算法、大数据分析、实时报表等技术，还有网关等等都给略过了，等有时间，我在详细说下每个细节，谢谢！！！！！！ </p>  </div>";
        String text = HtmlUtil.getText(html);
        System.out.println(text);
        System.out.println(new TextRankKeyword().getKeyword("", text));
    }
}
