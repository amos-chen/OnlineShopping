var JSTree = {

    URL: {
        JSTreeJsonURL: function (node) {
            return "/taotao/manager/itemCat";
        }

    },
    initTree: function () {
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
            var value = $(".jstree-clicked").text();
            $("#cid_choosed").html(value);
            console.log(value);
        });

    }

}