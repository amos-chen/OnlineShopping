var editable = {
    URL: {
        insertParamsModel: function (cid) {
            return "/taotao/manager/insert/" + cid + "/itemParam";
        },
        updateParamsModel: function (cid) {
            return "/taotao/manager/update/" + cid + "/itemParam";
        },
    },
    //初始化
    initEditable: function () {
        $.fn.editable.defaults.mode = 'inline';
        $('#param-group a.editable').editable({
            type: 'text',
            pk: 1,
            //如果设置了url属性，则会发起ajax请求，否则为本地修改
            // url:'/post',
            title: 'Enter username',
            // disabled: true,
            //本地修改的成功回调函数
            success: function (response, newValue) {
                $(this).html(newValue);
            }
        });
        //当modal隐藏时，移除btn-remove上绑定的事件
        $('#deleteModal').on("hidden.bs.modal", function () {
            $('#deleteConfirm').unbind();
        });
        //当modal隐藏时，移除btn-remove上绑定的事件
        $('#saveModal').on("hidden.bs.modal", function () {
            $('#saveConfrim').unbind();
        });

    },
    //新增参数行功能
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
                '<a href="javascript:void(0)" id="paramValue" class="' + panelTitleStatus + '">参数名</a>',
                '<a href="javascript:void(0)" class="deleteLine" id="deleteLine"><span class="fa fa-close fa-lg"></span></a>',
                '</div>',
                '</div>'].join('');
            $(paramBody).append(newHtml)
            if (panelTabIndex !== '-1') {
                // $(parent).find('a#paramHeading').attr('class','editable editable-click editable-disabled');
                // $(parent).find('a.editable').editable('toggleDisabled');
            }
            //增加行之后再次初始化一下x-editable
            editable.initEditable();
            //增加行后给新增的行绑定deleteLine事件
            $('a#deleteLine').unbind();
            editable.deleteLine();
        });
    },
    //删除参数行功能
    deleteLine: function () {
        //删除列绑定事件
        $('a#deleteLine').on('click', function () {
            $('#deleteModal').modal('show');
            var LineParent = $(this).parent().parent();
            $('#deleteConfirm').one('click', function () {
                $(LineParent).remove();
                $('#deleteModal').modal('hide');
            });
        });
    },

    //删除组功能
    deleteGroup: function () {
        //删除组绑定事件
        $('a#deleteGroup').on('click', function () {
            $('#deleteModal').modal('show');
            var GroupParent = $(this).parent().parent().parent().parent().parent();
            $('#deleteConfirm').one('click', function () {
                $(GroupParent).remove();
                $('#deleteModal').modal('hide');
            });
        });
    },

    //新增组功能
    addGroup: function (itemParams) {
        $('#add-group').on('click', function () {
            var parent = $(this).parent().parent().parent().parent();
            var paramGroup = $(parent).find('div#param-group');
            var Rows = $(parent).find('div.row');
            var lastRow = $(Rows).last();
            var Lines = $(lastRow).find("div.col-sm-4");
            var newHTML = ['<div class="col-sm-4">',
                '<div class="panel panel-primary">',
                '<div class="panel-heading">',
                '<h3 class="panel-title">',
                '<a id="paramHeading" href="javascript:void(0)" class="editable editable-click">组名</a>',
                '<div class="edit-group">',
                '<a href="javascript:void(0)" id="addLine" title="新增参数"><span class="fa fa-plus-square fa-fw"></span></a>',
                '<a href="javascript:void(0)" id="deleteGroup" title="删除组"><span class="fa fa-trash fa-fw"></span></a>',
                '</div></h3></div>',
                '<div class="panel-body">',
                '<div class="param-body">',
                '<div class="param-row">',
                '<div class="param-name">参数</div>',
                '<div class="param-value">',
                '<a href="javascript:void(0)" id="paramValue" class="editable" tabindex="-1">参数名</a>',
                '<a href="javascript:void(0)" class="deleteLine" id="deleteLine"><span class="fa fa-close fa-lg"></span></a>',
                '</div></div></div></div></div></div>'].join('');
            if (Lines.length === 3) {
                newHTML = '<div class="row">' + newHTML + '</div>';
                $(paramGroup).append(newHTML);
            } else {
                if (itemParams === null) {
                    paramGroup.append(newHTML);
                } else {
                    $(lastRow).append(newHTML);
                }
            }
            editable.initEditable();
            $('a#addLine').unbind();
            editable.addLine();
            $('a#deleteLine').unbind();
            editable.deleteLine();
            $('a#deleteGroup').unbind();
            editable.deleteGroup();
        })
    },

    //保存数据到tb_item_cat表格
    paramSave: function (itemParams, cid) {
        $('#param-save').on('click', function () {
            var itemParamJson = new Array();
            $('.col-sm-4').each(function () {
                var paramArr = new Array();
                var group = $(this).find('a#paramHeading');
                var goupValue = $(group).text();
                $(this).find('div.param-row').each(function () {
                    var param = $(this).find('a#paramValue');
                    var paramValue = $(param).text();
                    paramArr.push(paramValue);
                });
                var paramjson = {group: goupValue, params: paramArr};
                itemParamJson.push(paramjson);
            });
            if (itemParamJson.length === 0) {
                toastr.warning('请新增参数模板!');
            } else {
                $('#saveModal').modal('show');
                $('#saveConfrim').on('click', function () {
                    var data = new FormData;
                    data.append('paramData', JSON.stringify(itemParamJson));
                    if (itemParams === null) {
                        $.ajax({
                            url: editable.URL.insertParamsModel(cid),
                            method: 'post',
                            data: data,
                            cache: false,
                            contentType: false,
                            processData: false,
                            success: function (result) {
                                if (result && result['success']) {
                                    toastr.success('保存成功!');
                                } else {
                                    toastr.warning(result.error);
                                }
                            }
                        });
                        $('#saveModal').modal('hide');
                    } else {
                        $.ajax({
                            url: editable.URL.updateParamsModel(cid),
                            method: 'post',
                            data: data,
                            cache: false,
                            contentType: false,
                            processData: false,
                            success: function (result) {
                                if (result && result['success']) {
                                    toastr.success('更新成功!');
                                } else {
                                    toastr.warning(result.error);
                                }
                            }
                        });
                        $('#saveModal').modal('hide');
                    }
                });
            }
        })
    },

    init: function (params) {
        editable.initEditable();
        /*
         //当点击修改按键时，参数变成可修改状态，再次点击又变成不可修改状态
         $('a#changeEditStatus').on('click', function () {
         $('a.editable').editable('toggleDisabled');
         });
         */
        editable.addGroup(params.itemParams);
        editable.addLine();
        editable.deleteLine();
        editable.deleteGroup();
        editable.paramSave(params.itemParams, params.cid);
    }


}