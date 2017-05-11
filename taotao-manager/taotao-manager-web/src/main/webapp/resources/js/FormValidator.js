var FormValidator = {


    initValidator: function () {
        $('#itemAddForm').bootstrapValidator({
            excluded:[],
            container: 'tooltip',
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                itemCat:{
                    message: '商品类目验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品类目不能为空'
                        }
                    }
                },
                title: {
                    message: '商品标题验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品标题不能为空'
                        }
                    }
                },
                sell_point: {
                    message: '商品卖点验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品卖点不能为空'
                        }
                    }
                },
                price: {
                    message: '商品价格验证失败',
                    validators: {
                        notEmpty: {
                            message: '商品价格不能为空'
                        },
                        numeric: {
                            message: '商品价格只能为数字',
                        },
                        between: {
                            min: 0,
                            max: 999999999,
                            message: '商品价格不能为小于0',
                        }
                    },
                },
                num: {
                    message: '库存数量验证失败',
                    validators: {
                        notEmpty: {
                            message: '库存数量不能为空'
                        },
                        numeric: {
                            message: '库存数量只能为数字',
                        },
                        between: {
                            min: 0,
                            max: 999999999,
                            message: '库存数量不能为小于0',
                        }
                    }
                },
                barcode: {
                    validators: {
                        message: '条形码验证失败',
                        notEmpty: {
                            message: '条形码不能为空'
                        },
                        numeric: {
                            message: '条形码只能为数字',
                        },
                        stringLength: {
                            max: 13,
                            min: 13,
                            message: '条形码为13位数字'
                        }
                    }
                }

            }
        })
    }


}