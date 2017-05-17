var editable = {


    initEditable: function () {
        $.fn.editable.defaults.mode = 'inline';
        $('#param-group a.editable').editable({
            type: 'text',
            pk: 1,
            //如果设置了url属性，则会发起ajax请求，否则为本地修改
            // url:'/post',
            title: 'Enter username',
            disabled: true,
            //本地修改的成功回调函数
            success: function (response, newValue) {
                $(this).html(newValue);
            }
        });
        //当modal隐藏时，移除btn-remove上绑定的事件
        $('#deleteModal').on("hidden.bs.modal", function () {
            $('#deleteConfirm').unbind();
        });

    },

    addLine: function () {
        //绑定增加行事件
        $('a#addLine').on('click', function () {
            var parent = $(this).parent().parent().parent().parent();
            var panelTitleStatus = $(parent).find('a#paramHeading').attr('class');
            var panelTabIndex = $(parent).find('a#paramHeading').attr('tabindex');
            var paramBody = $(parent).find('div.param-body');
            var newHtml = ['<div class="param-row">',
                '<div class="param-name">参数</div>',
                '<div class="param-value">',
                '<a href="#" class="' + panelTitleStatus + '">参数名</a>',
                '<a href="#" class="deleteLine" id="deleteLine"><span class="fa fa-close fa-lg"></span></a>',
                '</div>',
                '</div>'].join('');
            $(paramBody).append(newHtml)
            if (panelTabIndex !== '-1') {
                // $(parent).find('a#paramHeading').attr('class','editable editable-click editable-disabled');
                $(parent).find('a.editable').editable('toggleDisabled');
            }
            //增加行之后再次初始化一下x-editable
            editable.initEditable();
            //增加行后给新增的行绑定deleteLine事件
            $('a#deleteLine').unbind();
            editable.deleteLine();
        });
    },
    deleteLine: function () {
        //删除列绑定事件
        $('a#deleteLine').on('click', function () {
            $('#deleteModal').modal('show');
            var LineParent = $(this).parent().parent();
            $('#deleteConfirm').one('click', function () {
                $(LineParent).html('');
                $('#deleteModal').modal('hide');
            });
        });
    },
    deleteGroup: function () {
        //删除组绑定事件
        $('a#deleteGroup').on('click', function () {
            $('#deleteModal').modal('show');
            var GroupParent = $(this).parent().parent().parent().parent().parent();
            $('#deleteConfirm').one('click', function () {
                $(GroupParent).html('');
                $(GroupParent).attr('hidden', 'hidden');
                $('#deleteModal').modal('hide');
            });
        });
    },

    addGroup: function () {
        $('#add-group').on('click', function () {
            var parent = $(this).parent().parent().parent().parent();
            var paramGoup = $(parent).find('div#param-group');
            var newHTML = ['<div class="col-sm-4">',
                '<div class="panel panel-primary">',
                '<div class="panel-heading">',
                '<h3 class="panel-title">',
                '<a id="paramHeading" href="#" class="editable editable-click editable-disabled" tabindex="-1">组名</a>',
                '<div class="edit-group">',
                '<a href="#" id="editStatus" title="修改"><span class="fa fa-pencil fa-fw"></span></a>',
                '<a href="#" id="addLine" title="新增参数"><span class="fa fa-plus-square fa-fw"></span></a>',
                '<a href="#" id="deleteGroup" title="删除组"><span class="fa fa-trash fa-fw"></span></a>',
                '</div></h3></div>',
                '<div class="panel-body">',
                '<div class="param-body">',
                '<div class="param-row">',
                '<div class="param-name">参数</div>',
                '<div class="param-value">',
                '<a href="#" class="editable" tabindex="-1">参数名</a>',
                '<a href="#" class="deleteLine" id="deleteLine"><span class="fa fa-close fa-lg"></span></a>',
                '</div></div></div></div></div></div>'].join('');
            $(paramGoup).append(newHTML);
            editable.initEditable();
            $('a#editStatus').unbind();
            //当点击修改按键时，参数变成可修改状态，再次点击又变成不可修改状态
            $('a#editStatus').on('click', function () {
                var panelParent = $(this).parent().parent().parent().parent();
                $(panelParent).find('a.editable').editable('toggleDisabled');
            });
            $('a#addLine').unbind();
            editable.addLine();
            $('a#deleteLine').unbind();
            editable.deleteLine();
            $('a#deleteGroup').unbind();
            editable.deleteGroup();
        })
    },

    init: function () {
        editable.initEditable();
        //当点击修改按键时，参数变成可修改状态，再次点击又变成不可修改状态
        $('a#editStatus').on('click', function () {
            var panelParent = $(this).parent().parent().parent().parent();
            $(panelParent).find('a.editable').editable('toggleDisabled');
        });
        editable.addGroup();
        editable.addLine();
        editable.deleteLine();
        editable.deleteGroup();
    }


}