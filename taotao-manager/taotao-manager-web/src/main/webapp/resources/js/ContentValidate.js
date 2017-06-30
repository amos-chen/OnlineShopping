var ContentValidate = {


    init: function () {


        $('#contentForm').bootstrapValidator({
            // container: 'tooltip',
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                title: {
                    message: '内容标题验证失败',
                    validators: {
                        notEmpty: {
                            message: '内容标题不能为空'
                        }
                    }
                },
                subTitle: {
                    message: '内容副标题验证失败',
                    validators: {
                        notEmpty: {
                            message: '内容副标题不能为空'
                        }
                    }
                },
                titleDesc: {
                    message: '内容描述验证失败',
                    validators: {
                        notEmpty: {
                            message: '内容描述不能为空'
                        }
                    }
                },
                contentURL: {
                    message: '类目地址验证失败',
                    validators: {
                        notEmpty: {
                            message: '类目地址不能为空'
                        }
                    }
                }
            }
        });

    },


}