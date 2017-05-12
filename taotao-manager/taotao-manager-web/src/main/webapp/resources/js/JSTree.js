var JSTree = {

    URL: {
        JSTreeJsonURL: function (node) {
            return "/taotao/manager/itemCat";
        }

    },
    initTree: function () {
        //当关闭模态弹窗时隐藏警告信息
        $('#mymodal').on('hidden.bs.modal', function (e) {
            console.log('test');
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
            if (choosedText.indexOf("folder")>0) {
                $('#jstreeMsg').hide().html('<label class="label label-danger label-lg">请选择子节点！</label>').show(300);
            } else {
                var value = $(".jstree-clicked").text();
                $("#cid_choosed").html(value);
                $('#cid').val(id);
                $('#jstreeMsg').hide();
                $('#mymodal').modal('hide');

                //当span的HTML发生改变时，重新验证span(validator里的内容)
                $('#itemAddForm').bootstrapValidator('revalidateField', 'itemCat');

            }
        });

    },
    

}