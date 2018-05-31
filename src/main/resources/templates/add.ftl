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
                    <textarea id="content" name="content"
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

    </div>
</div>
<#include 'common/js.ftl'>
<script>
    $(function () {
        var layer;
        var layedit;
        var layeditIndex;

        layui.use('layedit', function () {
            layedit = layui.layedit;
            layeditIndex = layedit.build('content', {
                height: 400, //设置编辑器高度
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
            var content = layedit.getContent(layeditIndex);
            var data = {title: title, content: content};
            console.log(data)

            $.ajax({
                url: '/addKnowledge',
                data: data,
                method: 'POST',
                async: false,
                success: (result) => {
                    if (result.success === true) {
                        layer.msg(result.msg)
                        location.reload()
                    } else {
                        layer.msg(result.msg)
                    }

                },
                error: (err) => {
                    layer.msg(err)
                    //alert(JSON.stringify(err))
                }
            });
        });
    })
</script>
<#include 'common/foot.ftl'>