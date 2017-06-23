var select2 = {

    URL: {
        getContentCatList: function () {
            return "/taotao/manager/contentCat/list"
        }
    },

    init: function () {
        select2.addFunction();
        $('#ParentId').select2();
    },


    addFunction: function () {
        $("#catAdd").on('click', function () {
            $('#CatManageModalTitle').text('新增类目');
            if ($('#ParentId').html() ==="" || $('#ParentId').length === 0) {
                $.ajax({
                    url: select2.URL.getContentCatList(),
                    method: 'get',
                    success: function (result) {
                        for (var i = 0; i < result.length; i++) {
                            $('#ParentId').append('<option parent="' + result[i].parentId + '" value="' + result[i].id + '">' + result[i].name + '</option>');
                        }
                    }
                });
            };
            $("#CatManage").modal('show');

        })
    },

    editFunction: function () {
        $("#catEdit").on('click', function () {
            $('#CatManageModalTitle').text('修改类目');
            $("#CatManage").modal('show');
        })
    }


}