var Content = {

    URL: {},
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
        $('#jstree').on("loaded.jstree", function (e,data) {
            $('#jstree').jstree('open_all');
            var inst = data.instance;
            var obj = inst.get_node(e.target.firstChild.firstChild.lastChild.firstChild);
            inst.select_node(obj);
        });
        $("#jstree").jstree({
            // 'plugins': ["checkbox"], //出现选择框
            // 'checkbox': {cascade: "", three_state: false}, //不级联
            'core': {
                'data': Content.getJsonTree(),
                'check_callback': true
            },
            'types': {
                'root': {'icon': 'fa fa-folder fw'},
                'file': {'icon': 'fa fa-file fw'}
            },
            'conditionalselect': function (node, event) {
                //event代表的是node绑定的所有事件
                // console.log(event);
                if(node.parent !=='#'){
                    //node属性及json数据属性
                    return true;
                }
            },
            'plugins': ['conditionalselect'],
        });

    },

    getJsonTree: function () {
        var data = null;
        $.ajax({
            method: 'get',
            url: "/taotao/manager/contentCat",
            async: false,
            success: function (result) {
                data = result;
            }
        });
        return data;
    }


};