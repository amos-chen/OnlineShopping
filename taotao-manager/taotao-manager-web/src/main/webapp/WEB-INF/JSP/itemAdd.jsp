<%--
  Created by IntelliJ IDEA.
  User: chenlunwei
  Date: 2017/5/6
  Time: 19:23
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
                            <li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;<span class="fa fa-align-left fa-fw"></span>参数规格</a>
                            </li>
                        </ul>
                    </li>
                    <li>
                        <a href="#" aria-expanded="false">
                            <span class="fa fa-folder fa-fw"></span>网站内容管理<span class="fa arrow"></span></a>
                        <ul class="nav">
                            <li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;<span class="fa fa-th-list fa-fw"></span>内容分类管理</a>
                            </li>
                            <li><a href="#">&nbsp;&nbsp;&nbsp;&nbsp;<span class="fa fa-book fa-fw"></span>内容管理</a></li>
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
            <li class="active">添加商品</li>
        </ol>
        <div class="wrapper-content">
            <div class="row">
                <div class="col-lg-12">
                    <div class="panel panel-default">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <h3 class="panel-title">添加商品</h3>
                            </div>
                            <div class="panel-body container-fluid">
                                <form action="" method="post" class="form-horizontal" id="itemAddForm" role="form">
                                    <div class="form-group">
                                        <label for="cid" class="col-sm-2 control-label"></span>商品类目:</label>
                                        <div class="col-sm-10">
                                            <a class="btn btn-default" id="cid" data-toggle="modal" href="#mymodal">选择类目</a>
                                            <%--<span id="cid_choosed" name="itemCat"></span>--%>
                                            <input type="text" id="cid_choosed" readonly="readonly" name="itemCat"/>
                                            <%--商品类目跳出框--%>
                                            <div class="modal fade" id="mymodal">
                                                <div class="modal-dialog">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <button type="button" class="close" data-dismiss="modal"
                                                                    aria-hidden="true">&times;
                                                            </button>
                                                            <h4 class="modal-title">选择类目</h4>
                                                        </div>
                                                        <div class="modal-body">
                                                            <div id="jstree"></div>
                                                        </div>
                                                        <div class="modal-footer">
                                                            <span id="jstreeMsg"></span>
                                                            <button type="button" class="btn btn-default"
                                                                    data-dismiss="modal">关闭
                                                            </button>
                                                            <button id="jstreeChoosed" type="button" class="btn btn-primary">确定
                                                            </button>
                                                        </div>
                                                    </div><!-- /.modal-content -->
                                                </div><!-- /.modal-dialog -->
                                            </div><!-- /.modal -->
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="title" class="col-sm-2 control-label">商品标题:</label>
                                        <div class="col-sm-6">
                                            <input name="title" class="form-control" type="text" id="title"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="sell_point" class="col-sm-2 control-label">商品卖点:</label>
                                        <div class="col-sm-6">
                                            <textarea name="sell_point" class="form-control" id="sell_point" rows="3"></textarea>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="price" class="col-sm-2 control-label">商品价格:</label>
                                        <div class="col-sm-4">
                                            <input name="price" class="form-control" type="text" id="price"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="num" class="col-sm-2 control-label">库存数量:</label>
                                        <div class="col-sm-4">
                                            <input name="num" class="form-control" type="text" id="num"/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="barcode" class="col-sm-2 control-label">条形码:</label>
                                        <div class="col-sm-4">
                                            <input name="barcode" class="form-control" type="text" id="barcode"/>
                                        </div>
                                    </div>
                                    <%--图片上传功能--%>
                                    <div class="form-group">
                                        <label for="barcode" class="col-sm-2 control-label">上传图片:</label>
                                        <div class="col-sm-6">
                                            <input name="imageInputFile" id="imageInputFile" type="file" multiple class="file-loading">
                                        </div>
                                    </div>
                                    <%--富文本编辑器--%>
                                    <div class="form-group">
                                        <label for="barcode" class="col-sm-2 control-label">详细描述:</label>
                                        <div class="col-sm-6">
                                            <div name="description" class="summernote" id="summernote"></div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-sm-10 col-sm-offset-2">
                                            <button type="submit" class="btn btn-primary">保存</button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<%@include file="common/footer.jsp" %>
<script type="text/javascript">
    $("#menu").metisMenu({
//        toggle: false
    });
    JSTree.initTree();
    FileInput.initFileInput();
    HTMLEditor.initEditor();
    FormValidator.initValidator();
</script>
</body>
</html>
