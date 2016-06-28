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
    <table class="table table-bordered">
        <thead>
            <tr>
                <th>Username</th>
                <th>Date registered</th>
                <th>Role</th>
                <th>Status</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td>Muhammad Usman</td>
                <td class="center">2012/01/01</td>
                <td class="center">Member</td>
                <td class="center">
                    <span class="label-success label label-default">Active</span>
                </td>
            </tr>
            <tr>
                <td>White Horse</td>
                <td class="center">2012/02/01</td>
                <td class="center">Staff</td>
                <td class="center">
                    <span class="label-default label label-danger">Banned</span>
                </td>
            </tr>
        </tbody>
    </table>
    <ul class="pagination pagination-centered">
        <li><a href="#">Prev</a></li>
        <li class="active">
            <a href="#">1</a>
        </li>
        <li><a href="#">2</a></li>
        <li><a href="#">3</a></li>
        <li><a href="#">4</a></li>
        <li><a href="#">Next</a></li>
    </ul>
</div>
</div>