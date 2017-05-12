var itemAddSubmit = {
    URL: {
        itemAddURL: function () {
            return '/taotao/manager/add/item';
        }
    },
    submit: function () {
        $('#submit').click(function () {
            //提交前先进行表单验证
            $('#itemAddForm').bootstrapValidator('validate');

            //同步summernote的内容到textarea中内
            var summernoteText = $('#summernote').summernote('code');
            $('#descriptionValue').val(summernoteText);
            //如果表单验证通过，则发起post请求
            if ($('#itemAddForm').data("bootstrapValidator").isValid()) {
                $.post(itemAddSubmit.URL.itemAddURL(),
                    $('#itemAddForm').serialize(), function (result) {
                        if (result && result['success']) {
                            $('#submitMsg').attr('class', 'text-success text-center');
                            $('#submitMsg').html('商品信息保存成功！');
                            $('#submitModal').modal({
                                show: true,
                                keyboard: false,
                                backdrop: 'static'
                            });
                            $('#confirm').click(function () {
                                window.location.reload();
                            });
                        } else {
                            $('#submitMsg').attr('class', 'text-danger text-center');
                            $('#submitMsg').html(result['error']);
                            $('#submitModal').modal({
                                show: true,
                                keyboard: false,
                                backdrop: 'static'
                            });
                            $('#confirm').click(function () {
                                $('#submitModal').modal('hide');
                            });
                        }
                    })
            }

        })
    }
}