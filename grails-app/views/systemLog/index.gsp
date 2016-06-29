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
            <label class="control-label" for="inputSearch">名称:</label>
            <input type="text" class="form-control" id="inputSearch">
            <input type="button" class="btn btn-primary" value="查询" id="sercher"/>
        </div>
    </form>
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
            "bAutoWidth": true,
            "ajax": {
                "url":"building/center/dataCenter/getIncomeSettlementByMonth"
            },
            "fnServerParams": function (aoData) {
                aoData.push(
                        { "name": "incomeMonth", "value": $("#incomeMonth").val()})
            },
            "order": [[2, 'desc']], // 默认排序(第三列降序, asc升序)
            "columns": [
                { "title": "月份", "data" : function (data) {
                    return data.incomeYear + "年" + data.incomeMonth + "月";
                }, "orderable": false, "searchable": false },
                { "title": "我的收益", "data" : "buildMoney", "orderable": false, "searchable": false },
                { "title": "云8销售金额", "data" : "saleMoney", "orderable": true, "searchable": false },
                { "title": "收益结算日期", "data" : "incomeTime", "orderable": false, "searchable": false },
                { "title": "详情", "data" : function (data) {

                    return '<a href="#" onclick="showDatils(this.title,this.id)" id="'+data.incomeYear+'" title="'+data.incomeMonth+'" data-toggle="modal" >查看每店明细</a>';

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
            window.location.href = "building/center/dataCenter/tobuildingBenifitName";
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

    $(function () {
        $('.form_date').datetimepicker({
            language: 'zh-CN',
            format: 'yyyy-mm',
            autoclose: 1,
            todayHighlight: 1,
            startView: 3,
            minView: 3,
            forceParse: 0
        });
    });

</script>