var itemParam = {
    URL: {
        queryItemCat: function (cid) {
            return "/taotao/manager/" + cid + "/itemCat";
        },
        queryItemParam: function (cid) {
            return "/taotao/manager/" + cid + "/itemParam";
        }
    },

    initParam: function (params) {
        //这个是关键，如果不清空实例，jstree不会重新生成
        $('#jstree').data('jstree', false).empty();
        //初始化加载商品类目tree
        $("#jstree").jstree({
            // 'plugins': ["checkbox"], //出现选择框
            // 'checkbox': {cascade: "", three_state: false}, //不级联
            'core': {
                'data': {
                    'url': JSTree.URL.JSTreeJsonURL(this),
                    'data': function (node) {
                        return {'id': node.id};
                    }
                }
            }
        });
        $("#jstreeChoosed").click(function () {
            //value为所选节点的ID
            var tree = $("#jstree").jstree(true);
            var id = tree.get_top_selected().join(':');
            //获取选中的节点
            var node = $("a.jstree-clicked").children("i").get(0);
            //获取选中节点的class值
            var choosedText = $(node).attr("class");
            //判断class值中是否含有folder
            if (choosedText.indexOf("folder") > 0) {
                //如果选择的不是子节点，显示提示信息
                $('#jstreeMsg').hide().html('<label class="label label-danger label-lg">请选择子节点！</label>').show(300);
            } else {
                //如果选中的是子节点，把节点的cid和text赋值给页面相应的地方
                var value = $(".jstree-clicked").text();
                $('#jstreeMsg').hide();
                $('#cidModal').modal('hide');
                itemParam.writePageHeader(value);
                //根据id获取itemCat
                var itemCat = itemParam.getItemCat(id);
                //如果itemCat为子类目，根据类目id查询itemParam，获取itemParam的ParamData参数
                var paramData = itemParam.getItemParam(itemCat);
                //把paramData转成json数组
                var JsonParamData = JSON.parse(paramData);
                //遍历json数组，通过数据自动生成HTML
                $(JsonParamData).each(function () {
                    itemParam.writePageBody(JsonParamData);
                });
            }
        });
        //根据URL传来的cid获取itemCat
        var itemCat = itemParam.getItemCat(params['cid']);
        //判断id,itemCat是否为空，或者是否为父类目
        if ('' == params['cid'] || params['cid'] == null
            || params['cid'] == undefined || itemParam.validateItemCat(itemCat)) {
            //如果URL没有传递cid，或者itemCat为空，或者itemCat为父类目，则弹出类目选择树，重新选择
            //并且弹窗不可关闭
            $('#cidModal').modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        } else {
            itemParam.writePageHeader(itemCat.name);
            //如果itemCat为子类目，根据类目id查询itemParam，获取itemParam的ParamData参数
            var paramData = itemParam.getItemParam(itemCat);
            //把paramData转成json数组
            var JsonParamData = JSON.parse(paramData);
            //遍历json数组，通过数据自动生成HTML
            $(JsonParamData).each(function () {
                itemParam.writePageBody(JsonParamData);
            });
        }
    },


    //如果传来的cid参数对应的是子类目，返回true，如果是父类目，返回false
    getItemCat: function (cid) {
        var itemCat = null;
        $.ajax({
            url: itemParam.URL.queryItemCat(cid),
            method: 'GET',
            //asyn为true时，ajax请求为异步，这样会导致函数先返回了一个undefined，之后才执行完ajax
            //所以设为false，同步，按顺序执行
            async: false,
            success: function (result) {
                if (result && result['success']) {
                    itemCat = result.data;
                } else {
                    itemCat = null;
                }
            }
        });
        return itemCat;
    },
    //判断itemCat是否含有子树，如果为空也为true
    validateItemCat: function (itemCat) {
        if (itemCat) {
            if (itemCat.isParent) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    },

    getItemParam: function (itemCat) {
        var paramData = null;
        $.ajax({
            url: itemParam.URL.queryItemParam(itemCat.id),
            method: 'get',
            async: false,
            success: function (result) {
                if (result && result['success']) {
                    paramData = result.data.paramData;
                }
            }
        });
        return paramData;
    },

    addParamPanel: function () {


    },

    writePageHeader: function (name) {
        $('#page-header').html([
            '<h1 name="itemTitle" id="itemTitle">',
            name,
            '<small>',
            '<span class="fa fa-angle-double-right"></span>',
            '参数规格模板设置',
            '</small>',
            '<div class="btn-group">',
            '<a class="btn btn-success" id="add-group"><span class="fa fa-plus-circle fa-fw"></span>新增组</a>',
            '<a class="btn btn-success" id="param-save"><span class="fa fa-save fa-fw"></span>保存</a>',
            '</div>',
            '</h1>'].join(''));
    },

    writePageBody: function (JsonParamData) {
        var arr = new Array();
        $(JsonParamData).each(function () {
            //添加组
            arr.push(['<div class="col-sm-4">',
                '<div class="panel panel-primary">',
                '<div class="panel-heading">',
                '<h3 class="panel-title">' + $(this)[0].group].join(''));
            arr.push(['<div class="edit-group">',
                '<a href="#" title="修改"><span class="fa fa-pencil fa-fw"></span></a>',
                '<a href="#" title="新增参数"><span class="fa fa-plus-square fa-fw"></span></a>',
                '</div>',
                '</h3>',
                '</div>'].join(''));
            var paramList = $(this)[0].params;
            arr.push(['<div class="panel-body">',
                '<div class="param-body">'].join(''));
            for (var i = 0; i < paramList.length; i++) {
                arr.push(['<div class="param-row">'].join(''));
                arr.push(['<div class="param-name">参数',
                    '</div>',
                    '<div class="param-value">' + paramList[i],
                    '</div>'].join(''));
                arr.push('</div>');
            }

            arr.push(['</div>',
                '</div>',
                '</div>',
                '</div>'].join(''));
        });
        $('#param-group').html(arr.join(''));
    }


}