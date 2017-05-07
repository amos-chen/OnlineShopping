<%--
  Created by IntelliJ IDEA.
  User: chenlunwei
  Date: 2017/5/4
  Time: 00:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Page Description">
    <meta name="author" content="chenlunwei">
    <title>表格</title>
    <%@include file="common/header.jsp" %>
</head>
<body style="background: white">
<div class="container-fluid">
    <div class="panel panel-default">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h3 class="panel-title">条件查询</h3>
            </div>
            <div class="panel-body">
                <form action="" method="post" class="form-inline" role="form">
                    <div class="form-group">
                        <label class="sr-only" for="txt_search_id">商品ID:</label>
                        <input type="email" class="form-control" id="txt_search_id" placeholder="商品id ...">
                    </div>
                    <div class="form-group">
                        <label class="sr-only" for="txt_search_title">商品标题:</label>
                        <input type="email" class="form-control" id="txt_search_title" placeholder="商品标题 ...">
                    </div>
                    <button type="submit" class="btn btn-default"><span class="fa fa-search"></span></button>
                </form>
            </div>
        </div>
    </div>
    <div class="table-content">
        <div class="btn-group">
            <button type="button" id="btn-add" class="btn btn-default">
                <span class="fa fa-plus fa-fw"></span>新增
            </button>
            <button type="button" id="btn-edit" class="btn btn-default">
                <span class="fa fa-edit fa-fw"></span>编辑
            </button>
            <button type="button" id="btn-delete" class="btn btn-default">
                <span class="fa fa-trash fa-fw"></span>删除
            </button>
        </div>
        <table id="Items-table"></table>
    </div>
</div>


<%@include file="common/footer.jsp" %>
<script>
    $(function(){
        table.showTabale();
    })
</script>
</body>
</html>
