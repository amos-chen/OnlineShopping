var ContentFileInput = {
    URL: {
        picture : function () {
            //地址不能用双引号
            return '/taotao/manager/picture/upload';
        },
        deletePic: function () {
            return '/taotao/manager/picture/delete'
        }
    },

    init: function () {
        $('input.imageInputFile').fileinput({
            //ajax上传的地址
            uploadUrl: ContentFileInput.URL.picture(),
            uploadAsync: true,
            language: 'zh',
            allowedPreviewTypes: ['image'],
            allowedFileTypes: ['image'],
            //不显示本地的移除按键，只保留从数据库删除的按键
            showRemove: false,
            // allowedFileExtensions:  ['jpg', 'png'],
            // maxFileSize : 2000,
            browseClass: 'btn btn-default',
            //文件最大数量
            maxFileCount: '1',
            // showPreview:true,
            enctype: 'multipart/form-data',
        });
        //禁用进度条kv进度条
        // $('.kv-upload-progress').hide();
        //TODO 如果上传出现错误时，取消上传结束后的显示完成信息，
        $('.imageInputFile').on('fileuploaderror', function (event, data, msg) {
            // $(progressbar).hide();
            setTimeout(changeCompleteMsg, 200);
            // $(progressbar).show();
            function changeCompleteMsg() {
                if ($('.has-error').length !== 0) {
                    var progressbar = $('.has-error').find('.progress-bar');
                    $(progressbar).removeClass('progress-bar-success').addClass('progress-bar-danger');
                    $(progressbar).html('上传出现错误！');
                }
            }
        });
        //文件上传服务器成功后触发事件
        $("input.imageInputFile").on('fileuploaded', function (event, data) {
            var response = data.response;
            var initialPreviewConfig = response.initialPreviewConfig;
            var imageURL = initialPreviewConfig[0].extra.imageURL;
            var baseroot = initialPreviewConfig[0].extra.baseroot;
            var key = initialPreviewConfig[0].key;
            ContentFileInput.uploadSuccess(event, imageURL);
            ContentFileInput.deleteImage(event, baseroot, key);
        });

    },


    //上传成功后显示图片
    uploadSuccess: function (event, imageURL) {
        var fileInputDiv = $(event.currentTarget).parent().parent().parent().parent()[0];
        $(fileInputDiv).attr('hidden', 'hidden');
        var array = new Array();
        array.push('<img class="" src="' + imageURL + '" style="width: 50px;height: auto;position: relative;">'
            + '<div class="tools" style="position: absolute;top: 0;left: 70px;">'
            + '<a id="removeImage" href="javascript:void(0)" style="color: #DD5A43!important;">'
            + '<i class="fa fa-times"></i>'
            + '</a>'
            + '</div>');
        $(fileInputDiv).parent().append(array.join(''));
    },

    deleteImage: function (event, baseroot, key) {
        var colDiv = $(event.currentTarget).parent().parent().parent().parent().parent()[0];
        var deleteIcon = $(colDiv).find('a#removeImage');
        $(deleteIcon).one('click', function () {
            var divParent = $(this).parent().parent();
            var deletDiv = $(this).parent();
            var data = new FormData();
            data.append('baseroot', baseroot);
            data.append('key', key);
            $.ajax({
                url: ContentFileInput.URL.deletePic(),
                data: data,
                type: 'POST',
                cache: false,
                contentType: false,
                processData: false,
                success: function () {
                    var fileInput = $(divParent).find('.imageInputFile');
                    var img = $(divParent).find('img');
                    $(fileInput).fileinput('refresh');
                    $(img).remove();
                    $(deletDiv).remove();
                }
            });
        })
    },


}