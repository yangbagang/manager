<g:set var="entityName" value="${message(code: 'systemLog.label', default: 'SystemLog')}" />
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">系统管理</a>
        </li>
        <li>
            <a href="#">系统日志</a>
        </li>
    </ul>
</div>
<div class="box-inner">
    <div class="box-header well" data-original-title="">
        <h2><i class="glyphicon glyphicon-user"></i> <g:message code="default.list.label" args="[entityName]" /></h2>
        <div class="box-icon">
            <a href="#" class="btn btn-plus btn-round btn-default"><i
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
    <table class="table table-striped table-bordered search_table" id="dataTable"></table>
</div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
     aria-hidden="true">

    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">×</button>
                <h3>Settings</h3>
            </div>
            <div class="modal-body">
                <p>Here settings can be configured...</p>
            </div>
            <div class="modal-footer">
                <a href="#" class="btn btn-default" data-dismiss="modal">Close</a>
                <a href="#" class="btn btn-primary" data-dismiss="modal">Save changes</a>
            </div>
        </div>
    </div>
</div>

<script>
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
                "url":"systemLog/list",
                "dataSrc": "data",
                "data": function ( d ) {
                    //添加额外的参数传给服务器
                    d.name = $("#name").val();
                }
            },
            "order": [[0, 'desc']], // 默认排序(第三列降序, asc升序)
            "columns": [
                { "title": "时间", "data" : "operationDate", "orderable": true, "searchable": false },
                { "title": "操作人", "data" : "operator", "orderable": true, "searchable": false },
                { "title": "操作内容", "data" : "operationMark", "orderable": true, "searchable": false },
                { "title": "IP", "data" : "loginIp", "orderable": true, "searchable": false },
                { "title": "类型", "data" : "type", "orderable": false, "searchable": false },
                { "title": "操作", "data" : function (data) {
                    return '<a class="btn btn-success" href="showDatils(this.id)" title="查看" id="'+data.id+'">' +
                           '<i class="glyphicon glyphicon-zoom-in icon-white"></i></a>&nbsp;&nbsp;' +
                           '<a class="btn btn-info" href="showDatils(this.id)" title="编辑" id="'+data.id+'">' +
                           '<i class="glyphicon glyphicon-edit icon-white"></i></a>&nbsp;&nbsp;' +
                           '<a class="btn btn-danger" href="deleteInfo(this.id)" title="删除" id="'+data.id+'">' +
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
        //查询 重新加载
        $("#sercher").click(function(){
            table.ajax.reload(null, false);
        });

        $("#buttonmonth").click(function () {
            window.location.reload();
        });

        $("#buttonName").click(function () {
            window.location.href = "systemLog/list";
        });
    });

    function showDatils(incomeMonth,incomeYear) {
        $.ajax({
            url:'building/center/dataCenter/getbuildingOpenearList',
            data:{'incomeMonth' : incomeMonth, 'incomeYear' : incomeYear},
            type:'get',
            dataType:'json',
            success:function(data){
                if(data.success){
                    var _html = '';
                    $("#shopDatilTable").html("");
                    $.each(data.data,function (index,datils){
                        _html += '<tr>';
                        _html += '<td>'+datils.incomeYear+'年'+datils.incomeMonth+'月</td>';
                        _html += '<td>'+datils.cloud8Name+'</td>';
                        _html += '<td>'+datils.buildMoney+'</td>';
                        _html += '<td>'+datils.saleMoney+'</td>';
                        _html += '<td>'+datils.detailName+'</td>';
                        _html += '<td>'+datils.detailCity+'</td>';
                        var stutas = "";
                        if(datils.incomeStatus == 1){
                            stutas = "等待结算";
                        }
                        if(datils.incomeStatus == 2){
                            stutas = "已结算";
                        }
                        _html += '<td>'+stutas+'</td>';
                        _html += '</tr>';
                    });
                    $("#shopDatilTable").html(_html);
                }else{
                    alert(data.msg);
                }
            }
        });
        //弹窗
        $("#shopDatilsButton").click();
    }

</script>