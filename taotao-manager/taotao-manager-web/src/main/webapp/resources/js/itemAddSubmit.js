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

            //把规格参数写入到隐藏的input里
            itemAddSubmit.itemParamsSaveToInput();

            //如果表单验证通过，则发起post请求
            if ($('#itemAddForm').data("bootstrapValidator").isValid()) {
                $.post(itemAddSubmit.URL.itemAddURL(),
                    $('#itemAddForm').serialize(), function (result) {
                        //如果保存成功
                        if (result && result['success']) {
                            //修改Modal信息
                            $('#submitMsg').attr('class', 'text-success text-center');
                            $('#submitMsg').html('商品信息保存成功！');
                            $('#submitModal').modal({
                                show: true,
                                keyboard: false,
                                backdrop: 'static'
                            });
                            //刷新页面
                            $('#confirm').click(function () {
                                window.location.reload();
                            });
                        } else {
                            //修改modal信息
                            $('#submitMsg').attr('class', 'text-danger text-center');
                            //弹出错误信息
                            $('#submitMsg').html(result['error']);
                            $('#submitModal').modal({
                                show: true,
                                keyboard: false,
                                backdrop: 'static'
                            });
                            //隐藏modal
                            $('#confirm').click(function () {
                                $('#submitModal').modal('hide');
                            });
                        }
                    })
            }

        })
    },

    itemParamsSaveToInput:function () {
        var itemParams = new Array();
        $('fieldset').each(function () {
            var groupValue = $('#group').text();
            var Jsonparams = new Array();
            $(this).find('div.row').each(function () {
                var k = $(this).find('span#key').text();
                // console.log(k);
                var v = $(this).find('input#value').val();
                var keyValueJson = {key:k,value:v};
                Jsonparams.push(keyValueJson);
            });
            var parameterJson = {group:groupValue,params:Jsonparams};
            itemParams.push(parameterJson);
        })
        $('#itemParameter').val(JSON.stringify(itemParams));
    }
}