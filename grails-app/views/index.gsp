<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>平台管理系统</title>
    <meta name="description" content="平台管理系统">
    <meta name="author" content="杨拔纲">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The styles -->
    <asset:stylesheet id="bs-css" src="bootstrap-cerulean.min.css" />

    <asset:stylesheet src="charisma-app.css" />
    <link href='static/bower_components/fullcalendar/dist/fullcalendar.css' rel='stylesheet'>
    <link href='static/bower_components/fullcalendar/dist/fullcalendar.print.css' rel='stylesheet' media='print'>
    <link href='static/bower_components/chosen/chosen.min.css' rel='stylesheet'>
    <link href='static/bower_components/colorbox/example3/colorbox.css' rel='stylesheet'>
    <link href='static/bower_components/responsive-tables/responsive-tables.css' rel='stylesheet'>
    <link href='static/bower_components/bootstrap-tour/build/css/bootstrap-tour.min.css' rel='stylesheet'>
    <asset:stylesheet src="jquery.noty.css" />
    <asset:stylesheet src="noty_theme_default.css" />
    <asset:stylesheet src="elfinder.min.css" />
    <asset:stylesheet src="elfinder.theme.css" />
    <asset:stylesheet src="jquery.iphone.toggle.css" />
    <asset:stylesheet src="uploadify.css" />
    <asset:stylesheet src="animate.min.css" />
    <asset:stylesheet src="jquery.dataTables.min.css" />

    <!-- jQuery -->
    <script src="static/bower_components/jquery/jquery.min.js"></script>
    <!-- The HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
    <asset:javascript src="html5.js" />
    <![endif]-->

    <!-- The fav icon -->
    <link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}">
</head>
<body>
<!-- topbar starts -->
<div class="navbar navbar-default" role="navigation">

    <div class="navbar-inner">
        <button type="button" class="navbar-toggle pull-left animated flip">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="${createLink(uri: '/')}"> <asset:image alt="Charisma Logo" src="logo20.png" class="hidden-xs"/>
            <span>平台管理系统</span></a>

        <!-- user dropdown starts -->
        <div class="btn-group pull-right">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-user"></i><span class="hidden-sm hidden-xs"><sec:loggedInUserInfo field='realName'/></span>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="#">设置</a></li>
                <li class="divider"></li>
                <li><a href="${createLink(uri: '/login/logout')}">退出</a></li>
            </ul>
        </div>
        <!-- user dropdown ends -->

        <!-- theme selector starts -->
        <div class="btn-group pull-right theme-container animated tada">
            <button class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                <i class="glyphicon glyphicon-tint"></i><span
                    class="hidden-sm hidden-xs">选择主题</span>
                <span class="caret"></span>
            </button>
            <ul class="dropdown-menu" id="themes">
                <li><a data-value="classic" href="#"><i class="whitespace"></i> Classic</a></li>
                <li><a data-value="cerulean" href="#"><i class="whitespace"></i> Cerulean</a></li>
                <li><a data-value="cyborg" href="#"><i class="whitespace"></i> Cyborg</a></li>
                <li><a data-value="simplex" href="#"><i class="whitespace"></i> Simplex</a></li>
                <li><a data-value="darkly" href="#"><i class="whitespace"></i> Darkly</a></li>
                <li><a data-value="lumen" href="#"><i class="whitespace"></i> Lumen</a></li>
                <li><a data-value="slate" href="#"><i class="whitespace"></i> Slate</a></li>
                <li><a data-value="spacelab" href="#"><i class="whitespace"></i> Spacelab</a></li>
                <li><a data-value="united" href="#"><i class="whitespace"></i> United</a></li>
            </ul>
        </div>
        <!-- theme selector ends -->
    </div>
</div>
<!-- topbar ends -->
<div class="ch-container">
    <div class="row">

        <!-- left menu starts -->
        <div class="col-sm-2 col-lg-2">
            <div class="sidebar-nav">
                <div class="nav-canvas">
                    <div class="nav-sm nav nav-stacked">

                    </div>
                    <ul class="nav nav-pills nav-stacked main-menu">
                        <li class="nav-header">Main</li>
                        <li><a class="ajax-link" href="${createLink(uri: '/main')}"><i class="glyphicon glyphicon-home"></i><span> Dashboard</span></a>
                        </li>
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-user"></i><span> 系统管理</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a class="ajax-link" href="${createLink(uri: '/systemUser')}">管理员设置</a></li>
                                <li><a class="ajax-link" href="${createLink(uri: '/systemRole')}">角色设置</a></li>
                                <li><a class="ajax-link" href="${createLink(uri: '/systemLog')}">系统日志</a></li>
                            </ul>
                        </li>
                        <li><a class="ajax-link" href="form.html"><i
                                class="glyphicon glyphicon-edit"></i><span> Forms</span></a></li>
                        <li><a class="ajax-link" href="chart.html"><i class="glyphicon glyphicon-list-alt"></i><span> Charts</span></a>
                        </li>
                        <li><a class="ajax-link" href="typography.html"><i class="glyphicon glyphicon-font"></i><span> Typography</span></a>
                        </li>
                        <li><a class="ajax-link" href="gallery.html"><i class="glyphicon glyphicon-picture"></i><span> Gallery</span></a>
                        </li>
                        <li class="nav-header hidden-md">Sample Section</li>
                        <li><a class="ajax-link" href="table.html"><i
                                class="glyphicon glyphicon-align-justify"></i><span> Tables</span></a></li>
                        <li class="accordion">
                            <a href="#"><i class="glyphicon glyphicon-plus"></i><span> Accordion Menu</span></a>
                            <ul class="nav nav-pills nav-stacked">
                                <li><a href="#">Child Menu 1</a></li>
                                <li><a href="#">Child Menu 2</a></li>
                            </ul>
                        </li>
                        <li><a class="ajax-link" href="calendar.html"><i class="glyphicon glyphicon-calendar"></i><span> Calendar</span></a>
                        </li>
                        <li><a class="ajax-link" href="grid.html"><i
                                class="glyphicon glyphicon-th"></i><span> Grid</span></a></li>
                        <li><a href="tour.html"><i class="glyphicon glyphicon-globe"></i><span> Tour</span></a></li>
                        <li><a class="ajax-link" href="icon.html"><i
                                class="glyphicon glyphicon-star"></i><span> Icons</span></a></li>
                        <li><a href="error.html"><i class="glyphicon glyphicon-ban-circle"></i><span> Error Page</span></a>
                        </li>
                        <li><a href="login.html"><i class="glyphicon glyphicon-lock"></i><span> Login Page</span></a>
                        </li>
                    </ul>
                    <label id="for-is-ajax" for="is-ajax"><input id="is-ajax" type="checkbox"> Ajax on menu</label>
                </div>
            </div>
        </div>
        <!--/span-->
        <!-- left menu ends -->

        <noscript>
            <div class="alert alert-block col-md-12">
                <h4 class="alert-heading">Warning!</h4>

                <p>You need to have <a href="http://en.wikipedia.org/wiki/JavaScript" target="_blank">JavaScript</a>
                    enabled to use this site.</p>
            </div>
        </noscript>

        <div id="content" class="col-lg-10 col-sm-10">
            <!-- content starts -->
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">Home</a>
                    </li>
                    <li>
                        <a href="#">Dashboard</a>
                    </li>
                </ul>
            </div>
            <div class="box-inner">
                <div class="box-header well">
                    <h2><i class="glyphicon glyphicon-bullhorn"></i> Alerts</h2>

                    <div class="box-icon">
                        <a href="#" class="btn btn-setting btn-round btn-default"><i
                                class="glyphicon glyphicon-cog"></i></a>
                        <a href="#" class="btn btn-minimize btn-round btn-default"><i
                                class="glyphicon glyphicon-chevron-up"></i></a>
                        <a href="#" class="btn btn-close btn-round btn-default"><i
                                class="glyphicon glyphicon-remove"></i></a>
                    </div>
                </div>
                <div class="box-content alerts">
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Oh snap!</strong> Change a few things up and try submitting again.
                    </div>
                    <div class="alert alert-success">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Well done!</strong> You successfully read this important alert message.
                    </div>
                    <div class="alert alert-info">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Heads up!</strong> This alert needs your attention, but it's not super important.
                    </div>
                </div>
            </div>
            <!-- content ends -->
        </div><!--/#content.col-md-0-->
    </div><!--/fluid-row-->

    <hr>

    <footer class="row">
        <p class="col-md-9 col-sm-9 col-xs-12 copyright">&copy; 平台管理系统 2016 </p>

        <p class="col-md-3 col-sm-3 col-xs-12 powered-by">Powered by: <a
                href="mailto:81667842@qq.com">YBG</a></p>
    </footer>

</div><!--/.fluid-container-->

<!-- external javascript -->
<script src="static/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
<!-- library for cookie management -->
<asset:javascript src="jquery.cookie.js" />
<!-- calender plugin -->
<script src='static/bower_components/moment/min/moment.min.js'></script>
<script src='static/bower_components/fullcalendar/dist/fullcalendar.min.js'></script>
<!-- data table plugin -->
<asset:javascript src="jquery.dataTables.min.js" />
<!-- select or dropdown enhancer -->
<script src="static/bower_components/chosen/chosen.jquery.min.js"></script>
<!-- plugin for gallery image view -->
<script src="static/bower_components/colorbox/jquery.colorbox-min.js"></script>
<!-- notification plugin -->
<asset:javascript src="jquery.noty.js" />
<!-- library for making tables responsive -->
<script src="static/bower_components/responsive-tables/responsive-tables.js"></script>
<!-- tour plugin -->
<script src="static/bower_components/bootstrap-tour/build/js/bootstrap-tour.min.js"></script>
<!-- star rating plugin -->
<asset:javascript src="jquery.raty.min.js" />
<!-- for iOS style toggle switch -->
<asset:javascript src="jquery.iphone.toggle.js" />
<!-- autogrowing textarea plugin -->
<asset:javascript src="jquery.autogrow-textarea.js" />
<!-- multiple file upload plugin -->
<asset:javascript src="jquery.uploadify-3.1.min.js" />
<!-- history.js for cross-browser state change on ajax -->
<asset:javascript src="jquery.history.js" />
<!-- application script for Charisma demo -->
<asset:javascript src="charisma.js" />
</body>
</html>
