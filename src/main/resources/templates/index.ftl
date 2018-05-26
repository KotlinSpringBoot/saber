<#include 'common/head.ftl'>
<div class="layui-fluid" id="LAY-component-timeline">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
                    <div class="layui-col-md12" style="text-align: right;margin-top: 10px">
                        <a class="layui-btn" href="/toAddKnowledgePage" target="_blank">写文章</a>
                        <div class="layui-inline">
                            <input id="searchText"
                       <#if title?exists>
                           value="${title}"
                       </#if>
                                   class="layui-input"
                                   style="border-radius: 10px"
                                   autocomplete="on"
                                   type='search'
                            >
                        </div>
                        <button id="search-btn" class="layui-btn layui-btn-radius">搜索</button>
                    </div>
                </div>
                <div class="layui-card-body" id="layui-card-body">
                    <ul class="layui-timeline">

                    <#if feeds?exists>
                        <#list feeds as e>
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis"></i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">
                                    <a href="/knowledge/${e.id}" target="_blank" style="color: #469689">${e.title}</a>
                                </h3>
                                <h5 class="layui-timeline-title">
                                    ${e.gmtCreate?string("yyyy-MM-dd HH:mm:ss")}
                                </h5>
                                <textarea class="content" id="content-${e.id}">
                                    ${e.content}
                                </textarea>
                            </div>
                        </li>
                        </#list>
                    </#if>

                    </ul>
                </div>

                <div class="layui-card-footer" style="text-align: center">
                    <div id="pagination"></div>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/assets/layui/layui.all.js"></script>
<script>
    $(function () {
        $('#search-btn').on('click', () => {
            var title = $('#searchText').val()
            location.href = '/?title=' + title;
        });

        layui.use('layedit', function () {
            var layedit = layui.layedit;
            <#if feeds?exists>
                <#list feeds as e>
                layedit.build('content-${e.id}',
                        {
                            height: 300,
                            tool: []
                        }
                );
                </#list>
            </#if>
        });

        var laypage = layui.laypage
        //完整功能
        laypage.render({
            elem: 'pagination'
            , count: <#if total?exists>${total}<#else>0</#if> //数据总数，从服务端得到
            , limit: ${pageSize!10}
            , curr: ${pageNum!1}
            , layout: ['count', 'prev', 'page', 'next', 'limit', 'refresh', 'skip']
            , jump: function (obj, first) {
                //obj包含了当前分页的所有参数，比如：
                console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
                console.log(obj.limit); //得到每页显示的条数

                //首次不执行
                if (!first) {
                    //do something
                    var title = $('#searchText').val()
                    var href = '/?title=' + title;
                    href += '&pageNum=' + obj.curr;
                    href += '&pageSize=' + obj.limit;
                    console.log(href)
                    location.href = href;
                }
            }
        });
    })
</script>
<#include 'common/foot.ftl'>