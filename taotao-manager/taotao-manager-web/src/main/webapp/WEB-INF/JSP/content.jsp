<%--
  Created by IntelliJ IDEA.
  User: Amos
  Date: 2017/5/3
  Time: 10:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Page Description">
    <meta name="author" content="Amos">
    <title>淘淘商城后台管理系统</title>
    <%@include file="common/header.jsp" %>
    <%--select2.css--%>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/css/select2.min.css" rel="stylesheet"/>
    <link href="https://cdnjs.cloudflare.com/ajax/libs/select2-bootstrap-theme/0.1.0-beta.6/select2-bootstrap.min.css"
          rel="stylesheet"/>

    <%--bootstrap-table.css--%>
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.css">

    <%--FileInput.css--%>
    <link href="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/css/fileinput.min.css" rel="stylesheet">

    <%--summernote.css--%>
    <link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.css" rel="stylesheet">

    <%--bootstrapValidator.css--%>
    <link href="https://cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css" rel="stylesheet">
</head>
<body>
<div class="wrapper">
    <div class="top-navbar">
        <nav class="navbar navbar-default navbar-static-top navbar-inverse" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse"
                        data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">
                    <span class="fa fa-leaf fa-fw"></span>淘淘商城后台管理系统
                </a>
            </div>

            <ul class="nav taotao-top-nav navbar-right">
                <li class="dropdown taotao-task">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="fa fa-tasks"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-caret" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="dropdown taotao-alarm">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="fa fa-bell"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-caret" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="dropdown taotao-message">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <span class="fa fa-comments"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-caret" role="menu">
                        <li><a href="#">Action</a></li>
                        <li><a href="#">Another action</a></li>
                        <li><a href="#">Something else here</a></li>
                    </ul>
                </li>
                <li class="dropdown taotao-user">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        USER <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu dropdown-menu-caret" role="menu">
                        <li><a href="#"><span class="fa fa-cog fa-fw"></span>设置</a></li>
                        <li><a href="#"><span class="fa fa-user fa-fw"></span>个人中心</a></li>
                        <li class="divider"></li>
                        <li><a href="#"><span class="fa fa-sign-out fa-fw"></span>注销</a></li>
                    </ul>
                </li>
            </ul>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav metisFolder" id="menu">
                    <div class="input-group sidebar-search">
                        <input type="text" class="form-control" placeholder="search.."/>
                        <span class="input-group-btn">
                            <button type="button" class="btn btn-default">
                                <span class="fa fa-search"></span>
                            </button>
                        </span>
                    </div>
                    <li>
                        <a href="#" aria-expanded="false">
                            <span class="fa fa-folder fa-fw"></span>商品管理
                            <span class="fa arrow"></span></a>
                        <ul class="nav">
                            <li><a href="/taotao/manager/itemAdd">&nbsp;&nbsp;&nbsp;&nbsp;<span
                                    class="fa fa-plus fa-fw"></span>添加商品</a></li>
                            <li><a href="/taotao/manager/list">&nbsp;&nbsp;&nbsp;&nbsp;<span
                                    class="fa fa-search fa-fw"></span>查询商品</a></li>
                            <li><a href="/taotao/manager/itemParam">&nbsp;&nbsp;&nbsp;&nbsp;<span
                                    class="fa fa-align-left fa-fw"></span>参数规格</a></li>
                        </ul>
                    </li>
                    <li class="active">
                        <a href="#" aria-expanded="true">
                            <span class="fa fa-folder fa-fw"></span>网站内容管理<span class="fa arrow"></span></a>
                        <ul class="nav">
                            <li><a href="/taotao/manager/contentCategory">&nbsp;&nbsp;&nbsp;&nbsp;<span
                                    class="fa fa-th-list fa-fw"></span>内容分类管理</a></li>
                            <li><a href="/taotao/manager/content">&nbsp;&nbsp;&nbsp;&nbsp;<span
                                    class="fa fa-book fa-fw"></span>内容管理</a></li>
                        </ul>
                    </li>
                </ul>
            </div><!-- /.navbar-collapse -->
        </nav>
    </div>
    <div class="page-wrapper">
        <ol class="breadcrumb bread-navbar">
            <li>
                <a href="/taotao/manager/index"><span class="fa fa-home fa-lg fa-fw"></span>首页</a>
            </li>
            <li><a href="#">网站内容管理</a></li>
            <li class="active">内容管理</li>
        </ol>

        <div class="wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">内容分类管理</h3>
                            </div>
                            <div class="panel-body">
                                <div id="CatTree" class="CatTree col-lg-3">
                                    <div class="btn-group">
                                        <button type="button" id="catAdd" class="btn btn-default">
                                            <span class="fa fa-plus fa-fw"></span>新增
                                        </button>
                                        <button type="button" id="catEdit" class="btn btn-default">
                                            <span class="fa fa-edit fa-fw"></span>修改
                                        </button>
                                        <button type="button" id="catDel" class="btn btn-default">
                                            <span class="fa fa-trash fa-fw"></span>删除
                                        </button>
                                    </div>
                                    <hr>
                                    <div id="jstree"></div>

                                    <%--当新增或删除父类节点时，需要重新加载select里的值--%>
                                    <input type="hidden" id="booleanLoadContentCat">
                                </div>
                                <div class="col-lg-9">
                                    <%--增删改的工具条--%>
                                    <div class="toolbar">
                                        <div class="btn-group">
                                            <a href="#" id="btn-add" type="button"
                                               class="btn btn-default">
                                                <span class="fa fa-plus fa-fw"></span>新增内容</a>
                                            <a id="delete-selected" type="button" class="btn btn-default">
                                                <span class="fa fa-trash fa-fw"></span>删除已选</a>
                                        </div>
                                    </div>

                                    <div class="table-content">
                                        <table id="Items-table"></table>
                                    </div>
                                </div>
                            </div>

                            <%--新增以及修改内容类目modal--%>
                            <div class="modal" id="CatManage">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title" id="CatManageModalTitle"></h4>
                                        </div>
                                        <div class="modal-body">
                                            <form class="form-horizontal" id="contentCatForm" role="form">
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label"
                                                           for="ParentId">父类名称：</label>
                                                    <div class=" col-sm-8">
                                                        <p>
                                                            <select class="form-control" name="ParentId"
                                                                    id="ParentId" style="width: 100%;"
                                                                    multiple="multiple"></select>
                                                        </p>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label class="col-sm-4 control-label"
                                                           for="ContentName">类目名称：</label>
                                                    <div class=" col-sm-8">
                                                        <input type="text" class="form-control"
                                                               name="ContentName"
                                                               id="ContentName">
                                                    </div>
                                                </div>
                                            </form>
                                            <div class="modal-footer">
                                                <button type="submit" id="ContentCatSave" class="btn btn-primary">保存
                                                </button>
                                                <button type="button" class="btn btn-default" data-dismiss="modal">
                                                    取消
                                                </button>
                                            </div>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->

                            <%--内容类目删除提示--%>
                            <div class="modal fade deleteModal" id="deleteModal">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="modal-title"><span class="fa fa-warning fa-fw"></span>操作提示:</h4>
                                        </div>
                                        <div class="modal-body">
                                            <h2 class="text-center text-danger">是否进行删除操作?</h2>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-success" data-dismiss="modal">取消
                                            </button>
                                            <button id="deleteConfirm" type="button" class="btn btn-danger">确定</button>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /.modal -->

                            <%--点击删除按键时的弹出框--%>
                            <div class="modal fade" id="modal-delete">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-body">
                                            <div class="container-fluid text-center modal-delete">
                                                <div class="col-sm-12">
                                                    <div class="warnning-icon">
                                                        <span class="fa fa-warning fa-lg"></span>
                                                    </div>
                                                    <h1><strong>操作提示</strong></h1>
                                                    <p>确定要删除吗?</p>
                                                    <a href="#" class="btn btn-success" data-dismiss="modal">取消</a>
                                                    <a href="#" id="btn-remove" class="btn btn-danger">确认</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /.modal -->

                            <%--内容新增/修改弹出框--%>
                            <a class="btn btn-primary" data-toggle="modal" href="#contentManager">Trigger modal</a>
                            <div class="modal fade" id="contentManager">
                                <div class="modal-dialog modal-lg">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                                &times;
                                            </button>
                                            <h4 class="contentManagerTitle"></h4>
                                        </div>
                                        <div class="modal-body">
                                            <form id="contentForm" class="form-horizontal" role="form">
                                                <div class="form-group">
                                                    <label for="title" class="col-sm-4 control-label">内容标题<span
                                                            class="text-danger">*</span>:</label>
                                                    <div class="col-sm-6">
                                                        <input name="title" class="form-control" type="text"
                                                               id="title"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="subTitle" class="col-sm-4 control-label">内容副标题<span
                                                            class="text-danger">*</span>:</label>
                                                    <div class="col-sm-6">
                                                        <input name="subTitle" class="form-control" type="text"
                                                               id="subTitle"/>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="titleDesc" class="col-sm-4 control-label">内容描述<span
                                                            class="text-danger">*</span>:</label>
                                                    <div class="col-sm-6">
                                                        <textarea name="titleDesc" class="form-control" id="titleDesc"
                                                                  rows="3"></textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group">
                                                    <label for="contentURL" class="col-sm-4 control-label">类目地址<span
                                                            class="text-danger">*</span>:</label>
                                                    <div class="col-sm-6">
                                                        <input name="contentURL" class="form-control" type="text"
                                                               id="contentURL"/>
                                                    </div>
                                                </div>

                                                <%--图片上传功能--%>
                                                <div class="form-group">
                                                    <label for="bigImage" class="col-sm-4 control-label">大图片:</label>
                                                    <div class="col-sm-6">
                                                        <input name="imageInputFile" class="imageInputFile" id="bigImageInputFile" type="file"
                                                        data-show-preview="false"
                                                        class="file-loading">
                                                        <input type="hidden" id="bigImage" class="fileinputImange" name="bigImage"/>
                                                    </div>
                                                </div>
                                                <%--图片上传功能--%>
                                                <div class="form-group">
                                                    <label for="smallImage" class="col-sm-4 control-label">小图片:</label>
                                                    <div class="col-sm-6">
                                                        <input name="imageInputFile" class="imageInputFile"
                                                               id="smallImageInputFile" type="file"
                                                               data-show-preview="false"
                                                               class="file-loading">
                                                        <input type="hidden" id="smallImage" class="fileinputImange"
                                                               name="smallImage"/>
                                                    </div>
                                                </div>
                                                <%--富文本编辑器--%>
                                                <div class="form-group" id="HTMLeditor">
                                                    <label for="descriptionValue"
                                                           class="col-sm-4 control-label">内容详情:</label>
                                                    <div class="col-sm-6">
                                                        <div class="summernote" id="summernote"></div>
                                                        <input hidden="hidden" name="description"
                                                               id="descriptionValue"/>
                                                    </div>
                                                </div>

                                                <%--summernote提交表单用--%>
                                                <input id="itemParameter" name="itemParameter" type="hidden">

                                                <div class="form-group">
                                                    <div class="col-sm-6 col-sm-offset-4">
                                                        <p class="help-block"><span class="text-danger">*</span>为必填项</p>
                                                    </div>
                                                </div>
                                            </form>
                                        </div>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-default" data-dismiss="modal">取消
                                            </button>
                                            <button type="button" class="btn btn-primary">保存</button>
                                        </div>
                                    </div><!-- /.modal-content -->
                                </div><!-- /.modal-dialog -->
                            </div><!-- /.modal -->
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<%@include file="common/footer.jsp" %>
<%--select2.js--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/select2.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.3/js/i18n/zh-CN.js"></script>

<%--树形下拉框，暂时不使用--%>
<%--<script type="text/javascript" src="/resources/js/select2tree.js"></script>--%>

<!--bootstrap-table.js-->
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/bootstrap-table.min.js"></script>
<script src="//cdnjs.cloudflare.com/ajax/libs/bootstrap-table/1.11.1/locale/bootstrap-table-zh-CN.min.js"></script>

<!--FileInput.js-->
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/js/fileinput.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/js/locales/zh.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-fileinput/4.4.2/js/locales/fa.min.js"></script>

<!--summernote.js-->
<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.3/summernote.min.js"></script>
<script src="https://cdn.bootcss.com/summernote/0.8.3/lang/summernote-zh-CN.min.js"></script>

<!--bootstrapValidator.js-->
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap-validator/0.5.3/js/language/zh_CN.min.js"></script>


<%--自定义js--%>
<script type="text/javascript" src="/resources/js/Content.js"></script>
<script type="text/javascript" src="/resources/js/select2.js"></script>
<script type="text/javascript" src="/resources/js/ContentTable.js"></script>
<script type="text/javascript" src="/resources/js/ContentFileInput.js"></script>
<script type="text/javascript" src="/resources/js/ContentSummernote.js"></script>
<script type="text/javascript" src="/resources/js/ContentValidate.js"></script>

<script type="text/javascript">
    $("#menu").metisMenu({
//        toggle: false
    });
    Content.initTree();
    select2.init();
    $(document).ready(function () {
        //延时加载
        setTimeout(selectFirst, 200);
        function selectFirst() {
            $($('body').find('li.jstree-leaf')[0]).find('a.jstree-anchor').addClass('jstree-clicked');
            var id = $('.jstree-clicked')[0].id.split('_')[0];
            ContentTable.showTabale({
                id: id
            });
        }
    });
    ContentFileInput.init();
    ContentSummernote.init();
    ContentValidate.init();
    //图片加载失败，指定默认加载图片
//    $('img').onerror(function () {
//        $(this).attr('src', '/resources/images/picfail.svg');
//    });
</script>
</body>
</html>
