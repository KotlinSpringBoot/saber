<#include 'common/head.ftl'>
<div class="layui-fluid" style="margin: 3em 6em 0em 0em;">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
            </div>
            <form id="gd-knowledge-form" class="layui-form" action="" lay-filter="component-form-element">
                <div class="layui-row layui-col-space10 layui-form-item">
                    <div class="layui-col-lg12">
                        <div class="layui-input-block">
                            <input id="title" name="title" type="text"
                                   lay-verify="required"
                                   placeholder="请输入标题"
                                   autocomplete="on"
                                   class="layui-input">
                        </div>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                    <textarea id="answer" name="answer"
                              placeholder="请输入解决方案"
                              class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item" style="text-align: center">
                    <div class="layui-input-block">
                        <button id="submit-btn" class="layui-btn" lay-submit="" lay-filter="component-form-element">提交
                        </button>
                        <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                    </div>
                </div>
            </form>
        </div>

        <div class="layui-col-md12" style="text-align: right">
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
</div>


<div class="layui-fluid" id="LAY-component-timeline">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-header">
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
                                <textarea class="content" id="answer-${e.id}">
                                    ${e.answer}
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
        var layer;
        var layedit;
        var layeditIndex;

        layui.use('layedit', function () {
            layedit = layui.layedit;
            layeditIndex = layedit.build('answer', {
                height: 180, //设置编辑器高度
                tool: [
                    'strong' //加粗
                    , 'italic' //斜体
                    , 'underline' //下划线
                    , 'del' //删除线

                    , '|' //分割线

                    , 'left' //左对齐
                    , 'center' //居中对齐
                    , 'right' //右对齐
                    , 'link' //超链接
                    , 'unlink' //清除链接
                    , 'face' //表情
                    , 'image' //插入图片
                    , 'help' //帮助
                ]
            }); //建立编辑器: http://www.layui.com/doc/modules/layedit.html
        });

        $('#submit-btn').on('click', (e) => {
            e.preventDefault()

            layui.use('layer', function () {
                layer = layui.layer;
            });

            var title = $('#title').val();
            var answer = layedit.getContent(layeditIndex);

            $.ajax({
                url: '/addKnowledge',
                data: {title: title, answer: answer},
                method: 'POST',
                async: false,
                success: (result) => {
                    layer.msg(result.msg)
                    //alert(JSON.stringify(result.msg))
                    location.reload()
                },
                error: (err) => {
                    layer.msg(err)
                    //alert(JSON.stringify(err))
                }
            });
        });

        $('#search-btn').on('click', () => {
            var title = $('#searchText').val()
            location.href = '/?title=' + title;
        });

        layui.use('layedit', function () {
            layedit = layui.layedit;
            <#if feeds?exists>
                <#list feeds as e>
                layedit.build('answer-${e.id}', {height: 300});
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