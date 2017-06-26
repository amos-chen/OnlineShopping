var select2 = {

    URL: {
        getContentCatList: function () {
            return "/taotao/manager/contentCat/list"
        },
        addContentCat: function () {
            return '/taotao/manager/add/contentCat'
        },
        updateContentCat: function (id) {
            return '/taotao/manager/update/' + id + '/contentCat';
        },
        deleteContentCat: function (id) {
            return '/taotao/manager/delete/' + id + '/contentCat';
        }
    },

    init: function () {

        select2.addFunction();
        select2.editFunction();
        select2.deleteFunction();
        $('#ParentId').select2({
            tags: true,
            maximumSelectionLength: 1,
            language: "zh-CN",
            theme: "bootstrap",
            // allowClear:true
        });
    },

    addFunction: function () {
        $("#catAdd").on('click', function () {
            //启用select
            $('#ParentId').prop("disabled", false);


            //修改modal的header为新增类目
            $('#CatManageModalTitle').text('新增类目');
            //初始化父类目select
            if ($('#ParentId').html() === "" || $('#ParentId').length === 0 ||
                $('#booleanLoadContentCat').val()==='true') {
                //先清空select里的内容
                $('#ParentId').html('');
                $.ajax({
                    url: select2.URL.getContentCatList(),
                    method: 'get',
                    success: function (result) {
                        for (var i = 0; i < result.length; i++) {
                            $('#ParentId').append('<option id="child' + result[i].id + '" parent="' + result[i].parentId + '" value="' + result[i].id + '">' + result[i].name + '</option>');
                        }
                    }
                });
                $('#booleanLoadContentCat').val('false');
            };
            $("#CatManage").modal('show');
            //保存类目
            $('#ContentCatSave').on('click', function () {
                //判断是否选择或者输入父类名称
                if ($("select#ParentId").val().length <= 0 || $("select#ParentId").val() === null
                    || $("select#ParentId").val() === undefined) {
                    toastr.warning("请选择/输入父类名称！");
                } else {
                    //判断是否输入新增的类目名称
                    if ($("input#ContentName").val() === '' || $("input#ContentName").val() === null
                        || $("input#ContentName").val() === undefined) {
                        toastr.warning("请输入类目名称！");
                    } else {
                        //把输入的表单数据传到后台，进行数据插入操作
                        $.post(select2.URL.addContentCat(), $('form#contentCatForm').serialize(),
                            function (result) {
                                //如果插入成功，则提示成功信息，并且刷新类目树
                                if (result && result['success']) {
                                    if(result.data.length>1){
                                        $('#booleanLoadContentCat').val('true');
                                    }else {
                                        $('#booleanLoadContentCat').val('true');
                                    }

                                    toastr.success('类目保存成功!');
                                    $("#CatManage").modal('hide');
                                    // 重新加载类目数的数据
                                    // $("#jstree").jstree(true).settings.core.data = Content.getJsonTree();
                                    // 刷新类目树
                                    // $("#jstree").jstree(true).refresh();
                                    Content.initTree();
                                } else {
                                    //如果插入失败，则提示错误信息
                                    toastr.danger(result.error);
                                }
                            });
                    }
                }

            });

            //模态框隐藏式清空表单数据
            $("#CatManage").one('hidden.bs.modal', function (e) {
                $("input#ContentName").val('');

                //清除已选标签的方法
                $('#ParentId').val('').trigger('change');
                // $('.select2-selection__choice').remove();
                //取消保存按键绑定的事件
                $("#ContentCatSave").unbind();

            });

        })
    },

    editFunction: function () {
        $("#catEdit").on('click', function () {
            if ($('.jstree-clicked').length === 0) {
                toastr.warning('请选择需要修改的类目！');
            } else {
                var parentInnerText = $('.jstree-clicked').parent().parent().parent()[0].innerText;
                var parentName = parentInnerText.split(/\r?\n/)[0];
                var id = $('.jstree-clicked')[0].id.split('_')[0];
                var sortName = $('.jstree-clicked')[0].innerText;
                $('#CatManageModalTitle').text('修改类目');

                //修改select框中的内容
                $('.select2-selection__rendered').prepend(
                    '<li class="select2-selection__choice" title="' + parentName + '">' +
                    '<span class="select2-selection__choice__remove" role="presentation">' +
                    '×</span>' + parentName + '</li>'
                );
                //禁用选择框
                $('#ParentId').prop("disabled", true);
                $('#ContentName').val(sortName);
                $("#CatManage").modal('show');

                //修改类目
                $('#ContentCatSave').on('click', function () {
                    //判断是否输入新增的类目名称
                    if ($("input#ContentName").val() === '' || $("input#ContentName").val() === null
                        || $("input#ContentName").val() === undefined) {
                        toastr.warning("请输入类目名称！");
                    } else {
                        $.post(select2.URL.updateContentCat(id), $('form#contentCatForm').serialize(),
                            function (result) {
                                //如果插入成功，则提示成功信息，并且刷新类目树
                                if (result && result['success']) {
                                    toastr.success('类目更新成功!');
                                    $("#CatManage").modal('hide');
                                    Content.initTree();
                                } else {
                                    //如果更新失败，则提示错误信息
                                    toastr.danger(result.error);
                                }
                            })
                    }

                });
                //模态框隐藏式清空表单数据
                $("#CatManage").one('hidden.bs.modal', function (e) {
                    $("input#ContentName").val('');
                    //清除已选标签的方法
                    $('#ParentId').val('').trigger('change');
                    //取消保存按键绑定的事件
                    $("#ContentCatSave").unbind();
                });
            }
        })
    },

    deleteFunction: function () {
        $("#catDel").on('click', function () {
                if ($('.jstree-clicked').length === 0) {
                    toastr.warning('请选择需要删除的类目！');
                } else {
                    var id = $('.jstree-clicked')[0].id.split('_')[0];
                    var parent = $('.jstree-clicked').parent().parent().parent();
                    $('#deleteModal').modal('show');
                    $('#deleteConfirm').on('click', function () {
                        $.post(select2.URL.deleteContentCat(id), function (result) {

                            if (result && result['success']) {
                                //如果删除成功，则提示成功信息，并且刷新类目树
                                if (result && result['success']) {
                                    //如果子类目删除干净之后，把父类目也同时删除,先删除后刷新，所以当刷新前子节点
                                    //只有一个的时候就可以删除父类目了
                                    if ($(parent).find('li.jstree-leaf').length === 1) {
                                        var parentId = parent[0].id;
                                        $.post(select2.URL.deleteContentCat(parentId), function (result) {
                                            if (result && result['success']) {
                                                toastr.success('类目删除成功!');
                                                $('#booleanLoadContentCat').val('true');
                                                $("#deleteModal").modal('hide');
                                                Content.initTree();
                                            } else {
                                                //如果删除失败，则提示错误信息
                                                toastr.danger(result.error);
                                            }
                                        })
                                    } else {
                                        toastr.success('类目删除成功!');
                                        $("#deleteModal").modal('hide');
                                        Content.initTree();
                                    }
                                } else {
                                    //如果删除失败，则提示错误信息
                                    toastr.danger(result.error);
                                }
                            }
                        })
                    });
                    //模态框隐藏式清空表单数据
                    $("#deleteModal").one('hidden.bs.modal', function (e) {
                        //取消保存按键绑定的事件
                        $("#deleteConfirm").unbind();
                    });
                }
            }
        )
    }
};