<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <!-- 强制让文档与设备的宽度保持1：1 -->
    <meta name="viewport"
          content="width=device-width, initial-scale=1, minimum-scale=1, maximum-scale=1, user-scalable=no">
    <link href="/assets/logo.jpg" rel="shortcut icon" type="image/x-icon"/>
    <title>MicroTek</title>
    <link rel="stylesheet" href="/assets/layui/css/layui.css">
    <style>
        .layui-body {
            left: 40px;
        }

        .layui-layout-admin .layui-footer {
            left: 0;
        }

        .layui-layout-admin .layui-body {
            bottom: 0;
        }

        .layui-card-header {
            border-bottom: 0;
        }

        .layui-card {
            box-shadow: 0 0 0 0 rgba(0, 0, 0, .05);
        }
    </style>

    <link href="/bower_components/bootstrap/dist/css/bootstrap-theme.css" rel="stylesheet">
    <link href="/bower_components/bootstrap-table/src/bootstrap-table.css" rel="stylesheet">
    <link href="/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet">
    <link href="/bower_components/pnotify/src/pnotify.css" rel="stylesheet">
    <link href="/app.css" rel="stylesheet">

</head>
<body class="layui-layout-body">
<button onclick="topFunction()" id="back-to-top" title="回顶部">返回顶部</button>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header">
        <div class="layui-logo">
            <img src="/assets/logo.jpg" style="height: 2.5em ;width: 2.5em">
            <a href="/" style="color: #009688;font-size: 1.2em;">微技 GD</a>
        </div>
        <!-- 头部区域（可配合layui已有的水平导航） -->
        <ul class="layui-nav layui-layout-left">
            <li class="layui-nav-item"><a href="/">GD 技术流</a></li>

            <li class="layui-nav-item">
                <a href="javascript:;">微图</a>
                <dl class="layui-nav-child">
                    <dd><a href="/sotu_gank_view" target="_blank">干货福利</a></dd>
                    <dd><a href="/sotu_favorite_view" target="_blank">精选收藏</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a href="javascript:;">爬虫任务</a>
                <dl class="layui-nav-child">
                    <dd><a href="/knowledge/doCrawJianShu" target="_blank">抓取简书</a></dd>
                    <dd><a href="/knowledge/doCrawSegmentFaultKnowledge" target="_blank">抓取SegmentFault</a></dd>
                    <dd><a href="/knowledge/doCrawOSChinaKnowledge" target="_blank">抓取OSChina</a></dd>
                    <dd><a href="/knowledge/doCrawITEyeKnowledge" target="_blank">抓取ITEye</a></dd>
                    <dd><a href="/knowledge/doCrawImportNewKnowledge" target="_blank">抓取ImportNew</a></dd>
                    <dd><a href="/knowledge/doCrawCNBlogKnowledge" target="_blank">抓取CNBlog</a></dd>
                    <dd><a href="/knowledge/doCrawInfoQKnowledge" target="_blank">抓取InfoQ</a></dd>
                    <dd><a href="/knowledge/doCrawBlockChainKnowledge" target="_blank">BlockChain</a></dd>
                    <dd><a href="/knowledge/KnowledgeCrawlerOfSpring4All" target="_blank">Spring4All</a></dd>
                    <dd><a href="/knowledge/KnowledgeCrawlerOfConcurrentProgramming" target="_blank">并发编程网</a></dd>
                    <dd></dd>
                    <dd><a href="/doGankImageCrawJob">干货福利</a></dd>
                </dl>
            </li>

            <li class="layui-nav-item">
                <a href="javascript:;">解决方案</a>
                <dl class="layui-nav-child">
                    <dd><a href="javascript:;">列表一</a></dd>
                    <dd><a href="javascript:;">列表二</a></dd>
                    <dd><a href="">超链接</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">机器人管理</a></li>
            <li class="layui-nav-item"><a href="">发布服务</a></li>
            <li class="layui-nav-item"><a href="">用户管理</a></li>
            <li class="layui-nav-item">
                <a href="javascript:;">其它系统</a>
                <dl class="layui-nav-child">
                    <dd><a href="">邮件管理</a></dd>
                    <dd><a href="">消息管理</a></dd>
                    <dd><a href="">授权管理</a></dd>
                </dl>
            </li>
        </ul>

        <ul class="layui-nav layui-layout-right">
            <li class="layui-nav-item">
                <a href="javascript:;">微技</a>
                <dl class="layui-nav-child">
                    <dd><a href="">基本资料</a></dd>
                    <dd><a href="">安全设置</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item"><a href="">退了</a></li>
        </ul>
    </div>
    <div class="layui-body">