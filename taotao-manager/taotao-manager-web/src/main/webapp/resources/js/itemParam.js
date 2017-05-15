var itemParam = {
    URL:{
        queryCid:function (cid) {
            return "/taotao/manager/"+cid+"/itemCat";
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
                $('#itemTitle').text(value);
            }
        });
        var itemCat = itemParam.getItemCat(params['cid']);
        if ('' == params['cid'] || params['cid'] == null
            || params['cid'] == undefined||itemCat.isParent) {
            $('#cidModal').modal({
                show: true,
                backdrop: 'static',
                keyboard: false
            });
        }else{
            $('#itemTitle').text(itemCat.name);
        }
    },
    //如果传来的cid参数对应的是子类目，返回true，如果是父类目，返回false
    getItemCat:function (cid) {
        var itemCat = null;
        $.ajax({
            url: itemParam.URL.queryCid(cid), 
            method:'GET',
            //asyn为true时，ajax请求为异步，这样会导致函数先返回了一个undefined，之后才执行完ajax
            //所以设为false，同步，按顺序执行
            async:false,
            success:function (result) {
                if(result&&result['success']){
                    itemCat = result.data;
                }else{
                    itemCat = null;
                }
            }
        });
        return itemCat;
    }


}