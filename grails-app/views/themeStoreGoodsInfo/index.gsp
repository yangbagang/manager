<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">主题店</a>
        </li>
        <li>
            <a href="#">定制商品</a>
        </li>
    </ul>
</div>
<div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 定制商品</h2>
        <div class="box-icon">
            <a href="javascript:importGoodsInfo();" class="btn btn-plus btn-round btn-default"><i
                    class="glyphicon glyphicon-plus"></i></a>
            <a href="#" class="btn btn-minimize btn-round btn-default"><i
                    class="glyphicon glyphicon-chevron-up"></i></a>
            <a href="#" class="btn btn-close btn-round btn-default"><i
                    class="glyphicon glyphicon-remove"></i></a>
        </div>
    </div>
</div>
<div class="box-content">
    <form class="form-inline" role="form" action="#">
        <div class="form-group">
            <label class="control-label" for="themeStoreId">选择主题店:</label>
            <select class="form-control" id="themeStoreId" name="themeStoreId"></select>
            <input type="button" class="btn btn-primary" value="查询" id="sercher"/>
        </div>
    </form><br />
    <div id="msgInfo" class="box-content alerts"></div>
    <table class="table table-striped table-bordered search_table" id="dataTable"></table>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">

    <div class="modal-dialog">
        <div class="modal-content" id="modal-content">

        </div>
    </div>
</div>
<script>
    var gridTable;
    $(document).ready(function(){
        var table=$('#dataTable').DataTable({
            "bLengthChange": true,
            "bFilter": false,
            "lengthMenu": [10, 20, 50, 100],
            "paginate": true,
            "processing": true,
            "pagingType": "full_numbers",
            "serverSide": true,
            "bAutoWidth": true,
            "ajax": {
                "url":"themeStoreGoodsInfo/list",
                "dataSrc": "data",
                "data": function ( d ) {
                    //添加额外的参数传给服务器
                    d.themeStoreId = $("#themeStoreId").val();
                }
            },
            "order": [[0, 'desc']], // 默认排序(第三列降序, asc升序)
            "columns": [
                { "title": "ID", "data" : "id", "orderable": true, "searchable": false },
                { "title": "名称", "data" : "realName", "orderable": true, "searchable": false },
                { "title": "品牌", "data" : "email", "orderable": true, "searchable": false },
                { "title": "规格", "data" : "createTime", "orderable": true, "searchable": false },
                { "title": "售价", "data" : "updateTime", "orderable": true, "searchable": false },
                { "title": "是否在售", "data" : function (data) {
                    return data.status == 1 ? "在售" : "停售";
                }, "orderable": false, "searchable": false},
                { "title": "操作", "data" : function (data) {
                    return '<a class="btn btn-success" href="javascript:showInfo('+data.id+');" title="查看">' +
                            '<i class="glyphicon glyphicon-zoom-in icon-white"></i></a>&nbsp;&nbsp;' +
                            '<a class="btn btn-info" href="javascript:editInfo('+data.id+');" title="编辑">' +
                            '<i class="glyphicon glyphicon-edit icon-white"></i></a>&nbsp;&nbsp;' +
                            '<a class="btn btn-danger" href="javascript:removeInfo('+data.id+');" title="删除">' +
                            '<i class="glyphicon glyphicon-trash icon-white"></i></a>';
                }, "orderable": false, "searchable": false }
            ],
            "language": {
                "zeroRecords": "没有数据",
                "lengthMenu" : "_MENU_",
                "info": "显示第 _START_ 至 _END_ 条记录，共 _TOTAL_ 条",
                "loadingRecords": "加载中...",
                "processing": "加载中...",
                "infoFiltered": "",
                "infoEmpty": "暂无记录",
                "paginate": {
                    "first": "首页",
                    "last": "末页",
                    "next": "下一页",
                    "previous": "上一页"
                }
            }
        });
        gridTable = table;
        //查询 重新加载
        $("#themeStoreId").change(function () {
            table.ajax.reload(null, false);
        });
        loadThmeStores();
    });

    function importGoodsInfo() {
        var content = "" +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal">×</button>' +
                '<h3>新建管理员</h3>' +
                '</div>' +
                '<div class="modal-body">' +
                '<form id="infoForm" role="form">' +
                '<div class="form-group">' +
                '<label for="username">用户名</label>' +
                '<input type="text" class="form-control" id="username" name="username" placeholder="用户名">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="realName">姓名</label>' +
                '<input type="text" class="form-control" id="realName" name="realName" placeholder="姓名">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="password">密码</label>' +
                '<input type="password" class="form-control" id="password" name="password" placeholder="密码">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="loginIp">邮箱</label>' +
                '<input type="email" class="form-control" id="email" name="email" placeholder="邮箱">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="enabled">启用</label>' +
                '<input type="checkbox" id="enableAccount" name="enableAccount" value="1">' +
                '</div>' +
                '</form>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>' +
                '<a href="javascript:postAjaxForm();" class="btn btn-primary">保存</a>' +
                '</div>';
        $("#modal-content").html("");
        $("#modal-content").html(content);
        $('#myModal').modal('show');
    }

    function showInfo(id) {
        var url = "../themeStoreGoodsInfo/show/" + id;
        $.ajax({
            type: "GET",
            url: url,
            success: function (result) {
                var themeStoreName = $("#themeStoreId").text();
                var content = "" +
                        '<div class="modal-header">' +
                        '<button type="button" class="close" data-dismiss="modal">×</button>' +
                        '<h3>'+themeStoreName+'-商品详情</h3>' +
                        '</div>' +
                        '<div class="modal-body">' +
                        '<form id="infoForm" role="form">' +
                        '<div class="form-group">' +
                        '<label for="name">名称</label>' +
                        '<input type="text" class="form-control" id="name" name="name" readonly="readonly" value="'+result.name+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="brand">品牌</label>' +
                        '<input type="text" class="form-control" id="brand" name="brand" readonly="readonly" value="'+result.brand+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="specifications">规格</label>' +
                        '<input type="text" class="form-control" id="specifications" name="specifications" readonly="readonly" value="'+result.specifications+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="basePrice">指导价格</label>' +
                        '<input type="text" class="form-control" id="basePrice" name="basePrice" readonly="readonly" value="'+result.basePrice+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="realPrice">实际售价</label>' +
                        '<input type="text" class="form-control" id="realPrice" name="realPrice" readonly="readonly" value="'+result.realPrice+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="status">启用</label>' +
                        '<select id="status" name="status" class="form-control" disabled="disabled">' +
                        '<option value="1"></option>在售<option value="0">停售</option>' +
                        '</select>' +
                        '</div>' +
                        '</form>' +
                        '</div>' +
                        '<div class="modal-footer">' +
                        '<a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>' +
                        '</div>';
                $("#modal-content").html("");
                $("#modal-content").html(content);
                $('#myModal').modal('show');
                $('#status').val(result.status);
            },
            error: function (data) {
                showErrorInfo(data.responseText);
            }
        });
    }

    function editInfo(id) {
        var url = "../themeStoreGoodsInfo/show/" + id;
        $.ajax({
            type: "GET",
            url: url,
            success: function (result) {
                var themeStoreName = $("#themeStoreId").text();
                var content = "" +
                        '<div class="modal-header">' +
                        '<button type="button" class="close" data-dismiss="modal">×</button>' +
                        '<h3>'+themeStoreName+'-商品详情</h3>' +
                        '</div>' +
                        '<div class="modal-body">' +
                        '<form id="infoForm" role="form">' +
                        '<input type="hidden" id="id" name="id" value="' + result.id + '">' +
                        '<input type="hidden" id="themeStoreId" name="themeStore.id" value="' + result.themeStore.id + '">' +
                        '<div class="form-group">' +
                        '<label for="username">名称</label>' +
                        '<input type="text" class="form-control" id="username" name="username" value="'+result.username+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="realName">品牌</label>' +
                        '<input type="text" class="form-control" id="realName" name="realName" value="'+result.realName+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="email">规格</label>' +
                        '<input type="text" class="form-control" id="email" name="email" value="'+result.email+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="password">指导价格</label>' +
                        '<input type="password" class="form-control" id="password" name="password" value="'+result.password+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="password">实际价格</label>' +
                        '<input type="password" class="form-control" id="password" name="password" value="'+result.password+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="status">状态</label>' +
                        '<select class="form-control" id="status" name="status">' +
                        '<option value="1">在售</option>' +
                        '<option value="0">停售</option>' +
                        '</select>' +
                        '</div>' +
                        '</form>' +
                        '</div>' +
                        '<div class="modal-footer">' +
                        '<a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>' +
                        '<a href="javascript:postAjaxForm();" class="btn btn-primary">更新</a>' +
                        '</div>';
                $("#modal-content").html("");
                $("#modal-content").html(content);
                $('#myModal').modal('show');
                $('#status').val(result.status);
            },
            error: function (data) {
                showErrorInfo(data.responseText);
            }
        });
    }

    function removeInfo(id) {
        var content = "" +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal">×</button>' +
                '<h3>提示</h3>' +
                '</div>' +
                '<div class="modal-body">' +
                '<p>删除后信息将无法恢复,是否继续?</p>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<a href="#" class="btn btn-default" data-dismiss="modal">取消</a>' +
                '<a href="javascript:postAjaxRemove('+id+');" class="btn btn-primary">删除</a>' +
                '</div>';
        $("#modal-content").html("");
        $("#modal-content").html(content);
        $('#myModal').modal('show');
    }

    function postAjaxRemove(id) {
        var url = "../themeStoreGoodsInfo/delete/" + id;
        $.ajax({
            type: "DELETE",
            dataType: "json",
            url: url,
            success: function (result) {
                var isSuccess = result.success;
                var errorMsg = result.msg;
                var content = "";
                if (isSuccess) {
                    content = "" +
                            '<div class="alert alert-success">' +
                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                            '删除完成' +
                            '</div>';
                } else {
                    content = "" +
                            '<div class="alert alert-danger">' +
                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                            JSON.stringify(errorMsg) +
                            '</div>';
                }
                $("#myModal").modal('hide');
                gridTable.ajax.reload(null, false);
                $("#msgInfo").html(content);
                $("#msgInfo").html(content).fadeIn(300).delay(2000).fadeOut(300);
            },
            error: function (data) {
                var errorContent = "" +
                        '<div class="alert alert-danger">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        data.responseText +
                        '</div>';
                $("#msgInfo").html(errorContent);
                $("#msgInfo").html(errorContent).fadeIn(300).delay(2000).fadeOut(300);
            }
        });
    }

    function postAjaxForm() {
        var url = "../themeStoreGoodsInfo/save";
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: $('#infoForm').serialize(),
            success: function (result) {
                var isSuccess = result.success;
                var errorMsg = result.msg;
                var content = "";
                if (isSuccess) {
                    content = "" +
                            '<div class="alert alert-success">' +
                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                            '操作完成' +
                            '</div>';
                } else {
                    content = "" +
                            '<div class="alert alert-danger">' +
                            '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                            JSON.stringify(errorMsg) +
                            '</div>';
                }
                $("#myModal").modal('hide');
                gridTable.ajax.reload(null, false);
                $("#msgInfo").html(content).fadeIn(300).delay(2000).fadeOut(300);
            },
            error: function(data) {
                var errorContent = "" +
                        '<div class="alert alert-danger">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        data.responseText +
                        '</div>';
                $("#msgInfo").html(errorContent);
                $("#msgInfo").html(content).fadeIn(300).delay(2000).fadeOut(300);
            }
        });
    }

    function loadThmeStores() {
        var url = "../themeStoreBaseInfo/listThemeStores";
        $.ajax({
            type: "get",
            dataType: "json",
            url: url,
            success: function (result) {
                $("#themeStoreId").empty();
                $.each(result, function (index, item) {
                    $("#themeStoreId").append("<option value='"+item.id+"'>"+item.name+"</option>");
                });
            },
            error: function (data) {
                var errorContent = "" +
                        '<div class="alert alert-danger">' +
                        '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                        data.responseText +
                        '</div>';
                $("#msgInfo").html(errorContent);
                $("#msgInfo").html(content).fadeIn(300).delay(2000).fadeOut(300);
            }
        });
    }
</script>