<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">基础设定</a>
        </li>
        <li>
            <a href="#">商品基本信息</a>
        </li>
    </ul>
</div>
<div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 商品基本信息</h2>
        <div class="box-icon">
            <a href="javascript:addInfo();" class="btn btn-plus btn-round btn-default"><i
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
            <label class="control-label" for="name">名称:</label>
            <input type="text" class="form-control" id="name">
            <input type="button" class="btn btn-primary" value="查询" id="sercher"/>
        </div>
    </form><br />
    <div id="msgInfo" class="box-content alerts"></div>
    <table class="table table-striped table-bordered search_table" id="dataTable"></table>
</div>

<div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2 id="typeTitleInfo"><i class="glyphicon glyphicon-user"></i> 商品分类信息</h2>
        <div class="box-icon">
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
            选择大类<select id="typeOneId" name="typeOneId" onchange="loadTypeTwo()"></select>
            选择小类<select id="typeTwoId" name="typeTwoId"></select>
            <input type="button" value="添加分类" onclick="addTypeInfo()">
        </div>
    </form><br />
    <table class="table table-striped table-bordered search_table" id="typeTable"></table>
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
    var typeTable;
    var goodsId = 0;
    var serverPath = 'http://183.57.41.230/FileServer/file/';
    $(document).ready(function(){
        gridTable=$('#dataTable').DataTable({
            "bLengthChange": true,
            "bFilter": false,
            "lengthMenu": [10, 20, 50, 100],
            "paginate": true,
            "processing": true,
            "pagingType": "full_numbers",
            "serverSide": true,
            "bAutoWidth": true,
            "ajax": {
                "url":"goodsBaseInfo/list",
                "dataSrc": "data",
                "data": function ( d ) {
                    //添加额外的参数传给服务器
                    d.name = $("#name").val();
                }
            },
            "order": [[0, 'desc']], // 默认排序(第三列降序, asc升序)
            "columns": [
                { "title": "ID", "data" : "id", "orderable": true, "searchable": false },
                { "title": "名称", "data" : "name", "orderable": true, "searchable": false },
                { "title": "品牌", "data" : "brand", "orderable": true, "searchable": false },
                { "title": "规格", "data" : "specifications", "orderable": true, "searchable": false },
                { "title": "指导价格(元)", "data" : "basePrice", "orderable": true, "searchable": false },
                { "title": "图片", "data" : function (data) {
                    var picPath = '';
                    var picFileId = data.picId;
                    if (picFileId != null && picFileId != "" && picFileId != "null" && picFileId != "NULL") {
                        picPath = serverPath + 'preview/' + picFileId;
                    } else {
                        picPath = '/manager/assets/goods_default_pic.png';
                    }
                    return '<img src="' + picPath + '" width=44 height=44 onclick="selectGoodsPic('+data.id+')"/>';
                }, "orderable": false, "searchable": false },
                { "title": "操作", "data" : function (data) {
                    return  '<a class="btn btn-info" href="javascript:editInfo('+data.id+');" title="编辑">' +
                            '<i class="glyphicon glyphicon-edit icon-white"></i></a>&nbsp;&nbsp;' +
                            '<a class="btn btn-danger" href="javascript:removeInfo('+data.id+');" title="删除">' +
                            '<i class="glyphicon glyphicon-trash icon-white"></i></a>&nbsp;&nbsp' +
                            '<a class="btn btn-info" href="javascript:editTypeInfo('+data.id+',\''+data.name+'\');" title="分类">' +
                            '<i class="glyphicon glyphicon-edit icon-white"></i></a>';
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
        //查询 重新加载
        $("#sercher").click(function(){
            gridTable.ajax.reload(null, false);
        });
        typeTable=$('#typeTable').DataTable({
            "bLengthChange": true,
            "bFilter": false,
            "paginate": false,
            "processing": true,
            "serverSide": true,
            "bAutoWidth": true,
            "ajax": {
                "url":"goodsTypeInfo/list",
                "dataSrc": "data",
                "data": function ( d ) {
                    //添加额外的参数传给服务器
                    d.goodsId = goodsId;
                }
            },
            "ordering": false, // 不排序
            "columns": [
                { "title": "ID", "data" : "id", "orderable": true, "searchable": false },
                { "title": "商品名称", "data" : "goodsName", "orderable": false, "searchable": false },
                { "title": "大类", "data" : "typeOneName", "orderable": false, "searchable": false },
                { "title": "小类", "data" : "typeTwoName", "orderable": false, "searchable": false },
                { "title": "操作", "data" : function (data) {
                    return  '<a class="btn btn-danger" href="javascript:removeTypeInfo('+data.id+');" title="删除">' +
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

        loadTypeOne();
    });

    function addInfo() {
        var content = "" +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal">×</button>' +
                '<h3>新建商品</h3>' +
                '</div>' +
                '<div class="modal-body">' +
                '<form id="infoForm" role="form">' +
                '<div class="form-group">' +
                '<label for="name">名称</label>' +
                '<input type="text" class="form-control" id="name" name="name" placeholder="名称">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="brand">品牌</label>' +
                '<input type="text" class="form-control" id="brand" name="brand" placeholder="品牌">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="specifications">规格</label>' +
                '<input type="text" class="form-control" id="specifications" name="specifications" placeholder="规格">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="basePrice">指导价格(元)</label>' +
                '<input type="text" class="form-control" id="basePrice" name="basePrice" placeholder="指导价格">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="picId">图片</label>' +
                '<input type="text" class="form-control" id="picId" name="picId">' +
                '</div>' +
                '<div class="form-group">' +
                '<label for="letter">首字母</label>' +
                '<input type="text" class="form-control" id="letter" name="letter">' +
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

    function editInfo(id) {
        var url = '${createLink(controller: "goodsBaseInfo", action: "show")}';
        $.ajax({
            type: "GET",
            url: url,
            data: "id=" + id,
            success: function (result) {
                var content = "" +
                        '<div class="modal-header">' +
                        '<button type="button" class="close" data-dismiss="modal">×</button>' +
                        '<h3>编辑商品</h3>' +
                        '</div>' +
                        '<div class="modal-body">' +
                        '<form id="infoForm" role="form">' +
                        '<input type="hidden" id="id" name="id" value="' + result.id + '">' +
                        '<div class="form-group">' +
                        '<label for="name">名称</label>' +
                        '<input type="text" class="form-control" id="name" name="name" value="'+result.name+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="brand">品牌</label>' +
                        '<input type="text" class="form-control" id="brand" name="brand" value="'+result.brand+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="specifications">规格</label>' +
                        '<input type="text" class="form-control" id="specifications" name="specifications" value="'+result.specifications+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="basePrice">指导价格(元)</label>' +
                        '<input type="text" class="form-control" id="basePrice" name="basePrice" value="'+result.basePrice+'">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="picId">图片</label>' +
                        '<input type="text" class="form-control" id="picId" name="picId" value="' + result.picId + '">' +
                        '</div>' +
                        '<div class="form-group">' +
                        '<label for="letter">首字母</label>' +
                        '<input type="text" class="form-control" id="letter" name="letter" value="' + result.letter + '">' +
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
        var url = '${createLink(controller: "goodsBaseInfo", action: "delete")}/' + id;
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
        var url = '${createLink(controller: "goodsBaseInfo", action: "save")}';
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

    function editTypeInfo(id, name) {
        goodsId = id;
        var info = '<i class="glyphicon glyphicon-user"></i> ' + name + '分类信息';
        $("#typeTitleInfo").html(info);
        typeTable.ajax.reload(null, false);
    }

    function loadTypeOne() {
        var url = '${createLink(controller: "goodsTypeOne", action: "list")}';
        $.ajax({
            type: "get",
            dataType: "json",
            url: url,
            data: "length=100&draw=1",
            success: function (result) {
                $("#typeOneId").empty();
                $.each(result.data, function (index, item) {
                    $("#typeOneId").append("<option value='"+item.id+"'>"+item.name+"</option>");
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

    function loadTypeTwo() {
        var url = '${createLink(controller: "goodsTypeTwo", action: "listByTypeOne")}';
        var typeOne = $("#typeOneId").val();
        $.ajax({
            type: "get",
            dataType: "json",
            url: url,
            data: "typeOneId=" + typeOne,
            success: function (result) {
                $("#typeTwoId").empty();
                $.each(result, function (index, item) {
                    $("#typeTwoId").append("<option value='"+item.id+"'>"+item.name+"</option>");
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

    function addTypeInfo() {
        if (goodsId == 0) {
            return;//没有选择商品
        }
        var url = '${createLink(controller: "goodsTypeInfo", action: "save")}';
        var typeTwo = $("#typeTwoId").val();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: "goodsId=" + goodsId + "&typeTwoId=" + typeTwo,
            success: function (result) {
                var isSuccess = result.success;
                var errorMsg = result.msg;
                var content = "";
                typeTable.ajax.reload(null, false);
            },
            error: function(data) {
                alert(data.responseText);
            }
        });
    }

    function removeTypeInfo(id) {
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
                '<a href="javascript:postAjaxRemoveType('+id+');" class="btn btn-primary">删除</a>' +
                '</div>';
        $("#modal-content").html("");
        $("#modal-content").html(content);
        $('#myModal').modal('show');
    }

    function postAjaxRemoveType(id) {
        var url = '${createLink(controller: "goodsTypeInfo", action: "delete")}/' + id;
        $.ajax({
            type: "DELETE",
            dataType: "json",
            url: url,
            success: function (result) {
                $("#myModal").modal('hide');
                typeTable.ajax.reload(null, false);
            },
            error: function (data) {
                alert(data.responseText);
            }
        });
    }

    function selectGoodsPic(goodsId) {
        var content = "" +
                '<div class="modal-header">' +
                '<button type="button" class="close" data-dismiss="modal">×</button>' +
                '<h3>设置商品图片</h3>' +
                '</div>' +
                '<div class="modal-body">' +
                '<form id="picForm" role="form" enctype="multipart/form-data" method="POST">' +
                '<div class="form-group">' +
                '<label for="name">图片</label>' +
                '<input type="file" class="form-control" id="Filedata" name="Filedata" placeholder="图片">' +
                '</div>' +
                '</form>' +
                '</div>' +
                '<div class="modal-footer">' +
                '<div class="alert alert-danger" id="uploadMsgDiv"></div>' +
                '<a href="#" class="btn btn-default" data-dismiss="modal">关闭</a>' +
                '<a href="javascript:postAjaxPic('+goodsId+');" class="btn btn-primary">上传</a>' +
                '</div>';
        $("#modal-content").html("");
        $("#modal-content").html(content);
        $('#myModal').modal('show');
    }

    function postAjaxPic(goodsId) {
        var url = serverPath + 'upload';
        var data = new FormData($("#picForm")[0]);
        data.append('folder', 'goodsPic');
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: data,
            cache: false,
            contentType: false,
            processData: false,
            success: function (result) {
                if (result.status == 200) {
                    updateGoodsPic(goodsId, result.fid);
                } else {
                    $("#uploadMsgDiv").html(result.message);
                }
            },
            error: function(data) {
                $("#uploadMsgDiv").html(data.responseText);
            }
        });
    }

    function updateGoodsPic(goodsId, picId) {
        var url = '${createLink(controller: "goodsBaseInfo", action: "savePic")}';
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: 'goodsId='+ goodsId + '&picId=' + picId,
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
</script>