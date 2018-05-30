<#include 'common/head.ftl'>
<div class="layui-fluid" id="LAY-component-timeline">
    <div class="layui-row layui-col-space15">
        <div class="layui-col-md12">
            <div class="layui-card">
                <div class="layui-card-body" id="layui-card-body">
                    <ul class="layui-timeline">
                        <li class="layui-timeline-item">
                            <i class="layui-icon layui-timeline-axis"></i>
                            <div class="layui-timeline-content layui-text">
                                <h3 class="layui-timeline-title">
                                    <a href="${Knowledge.url!""}" target="_blank">${Knowledge.title}</a>
                                </h3>
                                <h5 class="layui-timeline-title">
                                ${Knowledge.keyWords!""}
                                ${Knowledge.gmtCreate?string("yyyy-MM-dd HH:mm:ss")}
                                </h5>
                                <textarea class="content" id="content-${Knowledge.id?string("#")}">
                                ${Knowledge.content}
                                </textarea>
                            </div>
                        </li>
                    </ul>

                </div>
            </div>
        </div>
    </div>
</div>
<style>
    .layui-layedit {
        border-width: 0px;
        border-style: solid;
        border-radius: 2px;
    }
    .layui-layedit-tool {
        border-bottom-width: 0;
    }
</style>
<script src="/assets/layui/layui.all.js"></script>
<script>
    $(function () {
        layui.use('layedit', function () {
            var layedit = layui.layedit;
            layedit.build('content-${Knowledge.id?string("#")}',
                    {
                        height: 1000,
                        tool: []
                    }
            );
        });
    })
</script>
<#include 'common/foot.ftl'>