var Content = {

    URL: {
        ContentCatJstreeURL: function () {
            return "/taotao/manager/contentCat";
        }
    },
    initTree: function () {

        //这个是关键，如果不清空实例，jstree不会重新生成
        $('#jstree').data('jstree', false).empty();

        /*异步加载树
         $("#jstree").jstree({
         // 'plugins': ["checkbox"], //出现选择框
         // 'checkbox': {cascade: "", three_state: false}, //不级联
         'core': {
         'data': {
         'url': Content.URL.ContentCatJstreeURL,
         'data': function (node) {
         return {'id': node.id};
         }
         },
         'check_callback':true,
         },
         'types':{
         'root':{'icon':'fa fa-folder fw'},
         'file':{'icon':'fa fa-file fw'}
         }
         });*/
        $('#jstree').on("loaded.jstree", function () {
            $('#jstree').jstree('open_all');
        });
        $("#jstree").jstree({
            // 'plugins': ["checkbox"], //出现选择框
            // 'checkbox': {cascade: "", three_state: false}, //不级联
            'core': {
                'data': Content.getJsonTree(),
                'check_callback': true,
            },
            'types': {
                'root': {'icon': 'fa fa-folder fw'},
                'file': {'icon': 'fa fa-file fw'}
            }
        });

    },

    getJsonTree: function () {
        var data = null;
        $.ajax({
            method: 'get',
            url: Content.URL.ContentCatJstreeURL,
            async: false,
            success: function (result) {
                data = result;
            }
        });
        return data;
    }


};