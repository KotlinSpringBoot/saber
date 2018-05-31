$(function () {
    $.extend($.fn.bootstrapTable.defaults, $.fn.bootstrapTable.locales['zh-CN'])

    var searchText = $('.search').find('input').val()

    var columns = []

    columns.push(
        // {
        //     title: '分类',
        //     field: 'category',
        //     align: 'center',
        //     valign: 'middle',
        //     width: '5%',
        //     formatter: function (value, row, index) {
        //         return value
        //     }
        // },
        {
            title: '美图',
            field: 'url',
            align: 'center',
            valign: 'middle',
            formatter: function (value, row, index) {
                return "<img data-original='" + value + "' onclick=slideShow(" + index + ") width='100%' src='" + value + "'>"
            }
        }, {
            title: ' 操作',
            field: 'id',
            align: 'center',
            width: '5%',
            formatter: function (value, row, index) {
                var html = ""
                html += "<div onclick=addFavorite(" + value + ") name='addFavorite' id='addFavorite" + value + "' class='btn btn-success'>收藏</div><p>"
                html += "<div onclick=downBase64Image('data:image/jpg;base64," + row.imageBlob + "') class='btn btn-primary'>下载</div><p>"
                html += "<div onclick=deleteById(" + value + ") name='delete' id='delete" + value + "' class='btn btn-warning'>删除</div><p>"

                return html
            }
        })


    $('#sotu_favorite_table').bootstrapTable({
        url: 'sotuSearchFavoriteJson',
        sidePagination: "server",
        queryParamsType: 'page,size',
        contentType: "application/x-www-form-urlencoded",
        method: 'get',
        striped: false,     //是否显示行间隔色
        cache: false,      //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        pagination: true,  //是否显示分页（*）
        paginationLoop: true,
        paginationHAlign: 'right', //right, left
        paginationVAlign: 'bottom', //bottom, top, both
        paginationDetailHAlign: 'left', //right, left
        paginationPreText: ' 上一页',
        paginationNextText: '下一页',
        search: true,
        searchText: searchText,
        searchTimeOut: 500,
        searchAlign: 'right',
        searchOnEnterKey: false,
        trimOnSearch: true,
        sortable: true,    //是否启用排序
        sortOrder: "desc",   //排序方式
        sortName: "id",
        pageNumber: 1,     //初始化加载第一页，默认第一页
        pageSize: 10,      //每页的记录行数（*）
        pageList: [20, 50, 100, 200, 500, 1000], // 可选的每页数据
        totalField: 'totalElements',
        dataField: 'content', //后端 json 对应的表格数据 key
        columns: columns,
        queryParams: function (params) {
            return {
                size: params.pageSize,
                page: params.pageNumber - 1,
                sortName: params.sortName,
                sortOrder: params.sortOrder,
                searchText: params.searchText
            }
        },
        classes: 'table table-responsive full-width',
    });


    $(document).on('keydown', function (event) {
        // 键盘翻页事件
        var e = event || window.event || arguments.callee.caller.arguments[0];

        if (e && e.keyCode == 37 || e.keyCode == 38) {//左
            // 上一页
            $('.page-pre').click()
        }

        if (e && e.keyCode == 39 || e.keyCode == 40) {//右
            // 下一页
            $('.page-next').click()

        }
    });


})
