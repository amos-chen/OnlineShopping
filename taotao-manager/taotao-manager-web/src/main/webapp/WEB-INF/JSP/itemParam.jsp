<%--
  Created by IntelliJ IDEA.
  User: chenlunwei
  Date: 2017/5/6
  Time: 19:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <%--x-editable.css--%>
    <link href="https://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/css/bootstrap-editable.css"
          rel="stylesheet">
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
                    <li class="active">
                        <a href="#" aria-expanded="true">
                            <span class="fa fa-folder fa-fw"></span>商品管理
                            <span class="fa arrow"></span></a>
                        <ul class="nav">
                            <li><a href="/taotao/manager/itemAdd">&nbsp;&nbsp;&nbsp;&nbsp;<span
                                    class="fa fa-plus fa-fw"></span>添加商品</a></li>
                            <li><a href="/taotao/manager/list">&nbsp;&nbsp;&nbsp;&nbsp;<span
                                    class="fa fa-search fa-fw"></span>查询商品</a></li>
                            <li><a href="/taotao/manager/itemParam">&nbsp;&nbsp;&nbsp;&nbsp;<span
                                    class="fa fa-align-left fa-fw"></span>参数规格</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" aria-expanded="false">
                            <span class="fa fa-folder fa-fw"></span>网站内容管理<span class="fa arrow"></span></a>
                        <ul class="nav">
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
            <li><a href="#">商品管理</a></li>
            <li class="active">参数规格</li>
        </ol>
        <div class="wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <%--表单，提交参数规格模板--%>
                    <form id="itemParamForm" method="post" class="form-horizontal" role="form">
                        <input type="hidden" id="itemParamJson"/>
                        <%--商品类目--%>
                        <div class="form-group" id="itemCat">
                            <div class="page-header" id="page-header">
                                <%--<h1 name="itemTitle" id="itemTitle"><span class="fa fa-angle-double-right"></span></h1>--%>
                            </div>
                            <div class="param-group" id="param-group"></div>
                            <%--商品类目选择弹窗--%>
                            <div id="cidModal" class="modal fade">
                                <div class="modal-dialog">
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <h4 class="modal-title">请选择类目</h4>
                                        </div>
                                        <%--商品类目选择tree--%>
                                        <div class="modal-body">
                                            <div id="jstree"></div>
                                        </div>
                                        <div class="modal-footer">
                                            <span id="jstreeMsg"></span>
                                            <button id="jstreeChoosed" type="button"
                                                    class="btn btn-success">确定
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </div><!-- /.modal -->
                        </div>

                        <%--删除提示--%>
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

                        <%--保存提示--%>
                        <div class="modal fade" id="saveModal">
                            <div class="modal-dialog">
                                <div class="modal-content">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                            &times;
                                        </button>
                                        <h4 class="modal-title"><span class="fa fa-save fa-fw"></span>操作提示</h4>
                                    </div>
                                    <div class="modal-body">
                                        <h2 class="text-center text-danger">是否保存修改？</h2>
                                    </div>
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-success" data-dismiss="modal">取消
                                        </button>
                                        <button id="saveConfrim" type="button" class="btn btn-danger">确定</button>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /.modal -->
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<%@include file="common/footer.jsp" %>
<%--x-editabel.js--%>
<script src="https://cdn.bootcss.com/x-editable/1.5.1/bootstrap3-editable/js/bootstrap-editable.min.js"></script>
<%--自定义的js--%>
<script type="text/javascript" src="/resources/js/itemParam.js"></script>
<script type="text/javascript" src="/resources/js/x-editable.js"></script>
<script type="text/javascript">
    $("#menu").metisMenu({
//        toggle: false
    });
    //在js中使用EL表达式一定要用双引号，否则如果EL取不到值，会报错
    itemParam.initParam({
        cid: "${param.cid}"
    });

    //在itemParam.js里进行初始化
    //editable.initEditable();
</script>
</body>
</html>
