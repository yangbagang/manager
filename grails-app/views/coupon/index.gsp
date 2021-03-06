<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">营销管理</a>
        </li>
        <li>
            <a href="#">优惠券</a>
        </li>
    </ul>
</div>
<div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> 优惠券</h2>
        <div class="box-icon">
            <a href="${createLink(uri: '/coupon/exportExcel')}" target="_blank" class="btn btn-round btn-default"><i
                    class="glyphicon glyphicon-file"></i></a>
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
            <label class="control-label" for="name">编号:</label>
            <input type="text" class="form-control" id="name">
            <input type="button" class="btn btn-primary" value="查询" id="sercher"/>
        </div>
    </form><br />
    <label class="control-label" for="prefix">前缀:</label>
    <input type="text" id="prefix" value="0000" size="4">
    <label class="control-label" for="type">类型:</label>
    <select id="type"><option value="1">满减</option><option value="2">折扣</option></select>
    <label class="control-label" for="begin">开始号码:</label>
    <input type="text" id="begin" size="4" value="1">
    <label class="control-label" for="end">结束号码:</label>
    <input type="text" id="end" size="4" value="9999">
    <label class="control-label" for="length">位数:</label>
    <input type="text" id="length" size="1" value="4">
    <label class="control-label" for="minMoney">最小金额:</label>
    <input type="text" id="minMoney" size="3" value="100">
    <label class="control-label" for="yhMoney">抵扣金额:</label>
    <input type="text" id="yhMoney" size="3" value="10">
    <label class="control-label" for="discount">折扣:</label>
    <input type="text" id="discount" size="3" value="0.8">
    <label class="control-label" for="dayOfWeek">星期限定:</label>
    <select id="dayOfWeek">
        <option value="0">不限</option>
        <option value="1">周日</option>
        <option value="2">周一</option>
        <option value="3">周二</option>
        <option value="4">周三</option>
        <option value="5">周四</option>
        <option value="6">周五</option>
        <option value="7">周六</option>
    </select>
    <label class="control-label" for="dayOfMonth">日期限定:</label>
    <select id="dayOfMonth">
        <option value="0">不限</option>
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
        <option value="5">5</option>
        <option value="6">6</option>
        <option value="7">7</option>
        <option value="8">8</option>
        <option value="9">9</option>
        <option value="10">10</option>
        <option value="11">11</option>
        <option value="12">12</option>
        <option value="13">13</option>
        <option value="14">14</option>
        <option value="15">15</option>
        <option value="16">16</option>
        <option value="17">17</option>
        <option value="18">18</option>
        <option value="19">19</option>
        <option value="20">20</option>
        <option value="21">21</option>
        <option value="22">22</option>
        <option value="23">23</option>
        <option value="24">24</option>
        <option value="25">25</option>
        <option value="26">26</option>
        <option value="27">27</option>
        <option value="28">28</option>
        <option value="29">29</option>
        <option value="30">30</option>
        <option value="31">31</option>
    </select>
    <label class="control-label" for="maxCount">最大重用次数:</label>
    <input type="text" id="maxCount" size="3" value="1">
    <input type="button" class="btn btn-primary" value="批量生成" onclick="postAjaxForm()"/>
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
                "url":"coupon/list",
                "dataSrc": "data",
                "data": function ( d ) {
                    //添加额外的参数传给服务器
                    d.name = $("#name").val();
                }
            },
            "order": [[0, 'asc']], // 默认排序(第三列降序, asc升序)
            "columns": [
                { "title": "编号", "data" : "code", "orderable": true, "searchable": false },
                { "title": "类型", "data" : function (data) {
                    return data.type == 1 ? "满减券" : "折扣券";
                }, "orderable": false, "searchable": false },
                { "title": "折扣率", "data" : "discount", "orderable": true, "searchable": false },
                { "title": "最小金额", "data" : "minMoney", "orderable": true, "searchable": false },
                { "title": "抵扣金额", "data" : "yhMoney", "orderable": true, "searchable": false },
                { "title": "星期限定", "data" : function (data) {
                    return data.dayOfWeek == 0 ? "不限" : data.dayOfWeek - 1
                }, "orderable": false, "searchable": false },
                { "title": "日期限定", "data" : function (data) {
                    return data.dayOfMonth == 0 ? "不限" : data.dayOfMonth
                }, "orderable": false, "searchable": false },
                { "title": "剩余次数", "data" : "maxCount", "orderable": true, "searchable": false },
                { "title": "状态", "data" : function (data) {
                    return data.flag == 1 ? "有效" : "无效";
                }, "orderable": false, "searchable": false },
                { "title": "操作", "data" : function (data) {
                    return  '<a class="btn btn-danger" href="javascript:removeInfo('+data.id+');" title="删除">' +
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
        $("#sercher").click(function(){
            table.ajax.reload(null, false);
        });

    });

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
        var url = '${createLink(controller: "coupon", action: "delete")}/' + id;
        $.ajax({
            type: "POST",
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
                            errorMsg +
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
        var url = '${createLink(controller: "coupon", action: "createWithPrefix")}';
        var p = $("#prefix").val();
        var b = $("#begin").val();
        var e = $("#end").val();
        var l = $("#length").val();
        var t = $("#type").val();
        var d = $("#discount").val();
        var m = $("#minMoney").val();
        var y = $("#yhMoney").val();
        var dayOfWeek = $("#dayOfWeek").val();
        var dayOfMonth = $("#dayOfMonth").val();
        var maxCount = $("#maxCount").val();
        $.ajax({
            type: "POST",
            dataType: "json",
            url: url,
            data: 'prefix='+p+'&begin='+b+'&end='+e+'&length='+l+'&type='+t+'&discount='+d+'&minMoney='+m+'&yhMoney='+y+'&dayOfWeek='+dayOfWeek+'&dayOfMonth='+dayOfMonth+'&maxCount='+maxCount,
            success: function (result) {
                var isSuccess = result.success;
                var errorMsg = result.msg;
                var content = "";
                if (eval(isSuccess)) {
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