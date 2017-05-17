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
                console.log(response);
                console.log(newValue);
                $('#username').html(newValue);
            }
        });

        //当点击修改按键时，参数变成可修改状态，再次点击又变成不可修改状态
        $('a#editStatus').on('click', function () {
            var panelParent = $(this).parent().parent().parent().parent();
            $(panelParent).find('a.editable').editable('toggleDisabled');

        });

        //删除列绑定事件
        $('a#deleteLine').on('click', function () {
            $('#deleteModal').modal('show');
            var parent = $(this).parent().parent();
            $('#deleteConfirm').one('click', function () {
                $(parent).html('');
                $('#deleteModal').modal('hide');
            });
        });
        //删除组绑定事件
        $('a#deleteGroup').on('click', function () {
            $('#deleteModal').modal('show');
            var parent = $(this).parent().parent().parent().parent().parent();
            $('#deleteConfirm').one('click', function () {
                $(parent).html('');
                $(parent).attr('hidden', 'hidden');
                $('#deleteModal').modal('hide');
            });
        });


    }


}