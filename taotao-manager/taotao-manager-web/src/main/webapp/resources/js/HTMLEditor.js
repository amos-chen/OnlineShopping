var HTMLEditor = {

    URL: {
        FileUpload: function () {
            return "/taotao/manager/HTMLEditor/Add/Image";
        }
    },

    initEditor: function () {
        //页面完成后加载
        $(function () {
            //默认accpt为'image/*',所以验证会很慢
            $('.note-image-input').attr("accept", "image/jpg," +
                "image/jpeg,image/png,image/gif");
            // var name = $(".note-image-input").attr('name');
            // console.log(name);
        })

        $('#summernote').summernote({
            lang: 'zh-CN',
            minHeight: 200,
            placeholder: '请填写商品类目的详细信息...',
            // //对话框功能
            dialogsFade: true,
            dialogsInBody: true,
            disableDragAndDrop: false,
            fontNames: ['Helvetica', 'Arial', 'Arial Black', 'Comic Sans MS',
                'Tahoma', 'Times New Roman', 'Courier New', '宋体', '微软雅黑', '黑体', '隶书'],
            fontNamesIgnoreCheck: ['宋体', '微软雅黑', '黑体', '隶书'],
            callbacks: {
                onImageUpload: function (files) {
                    //遍历选取的每一张图片
                    console.log(HTMLEditor.URL.FileUpload());
                    for (var i = 0; i < files.length; i++) {
                        //FormData，新的form表单封装
                        var data = new FormData();
                        //把图片加入FormData中，后台接收到的文件名为imageFile
                        data.append("EditorImage", files[i]);
                        //向后台发起Ajax请求，把图片存入服务器中
                        $.ajax({
                            data: data,
                            type: 'POST',
                            //储存文件的路径
                            url: HTMLEditor.URL.FileUpload(),
                            cache: false,
                            contentType: false,
                            processData: false,
                            //成功时的回调函数
                            success: function (result) {
                                if (result && result['success']) {
                                    var resultMap = result['data'];
                                    $('#summernote').summernote('insertImage',
                                        resultMap['url'], function ($image) {
                                            $image.attr('name', resultMap['filename']);
                                            $image.attr('data-extra', resultMap['extra']);
                                            $image.attr('width', '100%');
                                        })
                                } else {
                                    console.log(result);
                                }
                            }

                        })

                    }
                },
            }

        });
    },

    // uploadImage: function (files) {
    //     //遍历选取的每一张图片
    //     console.log(HTMLEditor.URL.FileUpload());
    //     for (var i = 0; i < files.length; i++) {
    //         //FormData，新的form表单封装
    //         var data = new FormData();
    //         //把图片加入FormData中，后台接收到的文件名为imageFile
    //         data.append("EditorImage", files[i]);
    //         //向后台发起Ajax请求，把图片存入服务器中
    //         $.ajax({
    //             data: data,
    //             type: 'POST',
    //             //储存文件的路径
    //             url: HTMLEditor.URL.FileUpload(),
    //             cache: false,
    //             contentType: false,
    //             processData: false,
    //             //成功时的回调函数
    //             success: function (result) {
    //                 if (result && result['success']) {
    //                     var resultMap = result['data'];
    //                     $('#summernote').summernote('insertImage',
    //                         resultMap['url'], function ($image) {
    //                             $image.attr('name', resultMap['filename']);
    //                             $image.attr('data-extra', resultMap['extra']);
    //                         })
    //                 } else {
    //                     console.log(result);
    //                 }
    //             }
    //
    //         })
    //
    //     }
    // }


}