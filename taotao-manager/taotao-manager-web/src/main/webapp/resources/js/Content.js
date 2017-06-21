var Content = {

    URL: {
        ContentCatJstreeURL: function (node) {
            return "/taotao/manager/contentCat";
        },
        queryContentURL: function (cid) {
            return "/taotao/manager/" + cid + "/itemParam";
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
                    'url': Content.URL.ContentCatJstreeURL(this),
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


        });
    },




};