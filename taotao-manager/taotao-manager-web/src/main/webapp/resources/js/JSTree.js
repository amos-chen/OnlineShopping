var JSTree = {

    URL: {
        JSTreeJsonURL: function (node) {
            return "/taotao/manager/itemCat";
        },
        queryItemParam: function (cid) {
            return "/taotao/manager/" + cid + "/itemParam";
        }

    },
    initTree: function () {
        //当关闭模态弹窗时隐藏警告信息
        $('#mymodal').on('hidden.bs.modal', function (e) {
            $('#jstreeMsg').hide();
        }),

            //这个是关键，如果不清空实例，jstree不会重新生成
            $('#jstree').data('jstree', false).empty();
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
                $('#jstreeMsg').hide().html('<label class="label label-danger label-lg">请选择子节点！</label>').show(300);
            } else {
                var value = $(".jstree-clicked").text();
                var itemParam = JSTree.getItemParam(id);
                if (itemParam === null || itemParam === undefined) {
                    $('#modalValue').html(value);
                    $('#redirectMoadal').modal('show');
                    $('#redirectConfirm').on('click', function () {
                        window.location.href = '/taotao/manager/itemParam?cid=' + id;
                    });
                } else {
                    $("#cid_choosed").html(value);
                    $('#cid').val(id);
                    JSTree.writeParamBody(itemParam);
                    $('#jstreeMsg').hide();
                    $('#mymodal').modal('hide');

                    //当span的HTML发生改变时，重新验证span(validator里的内容)
                    $('#itemAddForm').bootstrapValidator('revalidateField', 'itemCat');
                }

            }
        });

    },

    getItemParam: function (cid) {
        var data = null;
        $.ajax({
            url: JSTree.URL.queryItemParam(cid),
            method: 'get',
            async: false,
            success: function (result) {
                if (result && result['success']) {
                    data = result['data'];
                }
            }
        })
        return data;
    },

    writeParamBody: function (itemParam) {
        $('#parameters').remove();
        var arr = new Array();
        arr.push(['<div class="form-group parameters" id="parameters">',
            '<label for="params" class="col-sm-2 control-label">规格参数:</label>',
            '<div class="col-sm-6">'].join(''));
        var jsonData = JSON.parse(itemParam.paramData);
        for (var i = 0; i < jsonData.length; i++) {
            arr.push(['<fieldset>',
                '<legend><small id="group">' + $(jsonData[i])[0].group + '</small></legend>'].join(''));
            var paramList = $(jsonData[i])[0].params;
            for (var j = 0; j < paramList.length; j++) {
                arr.push(['<div class="row">',
                    '<span class="col-sm-4 control-label" id="key">' + paramList[j] +  '</span>',
                    '<div class="col-sm-8">',
                    '<input id="value" class="form-control" type="text"/>',
                    '</div></div>'].join(''))
            }
            arr.push('</fieldset>');
        }
        arr.push('</div></div>');
        $('#HTMLeditor').after(arr.join(''));
    }
}