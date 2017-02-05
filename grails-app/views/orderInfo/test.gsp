<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">测试</a>
        </li>
        <li>
            <a href="#">订单测试</a>
        </li>
    </ul>
</div>
<div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 订单测试</h2>
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
            <label class="control-label" for="themeStoreId">选择主题店:</label>
            <select class="form-control" id="themeStoreId" name="themeStoreId"></select>
            <input type="button" class="btn btn-primary" value="查询" id="search"/>
        </div>
    </form><br />
    <div id="msgInfo" class="box-content alerts"></div>
    <table class="table table-striped table-bordered search_table" id="dataTable"></table>
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
                "url":"vendLayerTrackGoods/listGoods",
                "dataSrc": "data",
                "data": function ( d ) {
                    //添加额外的参数传给服务器
                    d.storeId = $("#themeStoreId").val();
                }
            },
            "order": [[0, 'desc']], // 默认排序(第三列降序, asc升序)
            "columns": [
                { "title": "商品名称", "data" : "goodsName", "orderable": false, "searchable": false },
                { "title": "机器编号", "data" : "machineCode", "orderable": false, "searchable": false },
                { "title": "轨道编号", "data" : "orbitalNo", "orderable": true, "searchable": false },
                { "title": "当前库存", "data" : "currentInventory", "orderable": true, "searchable": false },
                { "title": "最大库存", "data" : "largestInventory", "orderable": true, "searchable": false },
                { "title": "操作", "data" :function (data) {
                    return  '<a href="javascript:createOrder('+data.id+', 2);" title="生成微信订单">' +
                            '<img src="/manager/assets/icon_weixin.png" width=33 height=33/></a>&nbsp;'+
                            '<a href="javascript:createOrder('+data.id+', 1);" title="生成支付宝订单">' +
                            '<img src="/manager/assets/icon_zhifubao.png" width=33 height=33/></a>';
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
        $("#search").click(function () {
            table.ajax.reload(null, false);
        });

        loadThemeStores();
    });

    function loadThemeStores() {
        var url = '${createLink(controller: "themeStoreBaseInfo", action: "listThemeStores")}';
        $.ajax({
            type: "get",
            dataType: "json",
            url: url,
            success: function (result) {
                $("#themeStoreId").empty();
                $.each(result, function (index, item) {
                    $("#themeStoreId").append("<option value='"+item.id+"'>"+item.name+"</option>");
                });
                gridTable.ajax.reload(null, false);
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

    function createOrder(vendLayerId, payWay) {
        var url = '${createLink(controller: "orderInfo", action: "createOrder")}';
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: "vendLayerId=" + vendLayerId + "&payWay=" + payWay,
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