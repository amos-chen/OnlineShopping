
var FileInput = {

    URL:{
        picture : function () {
            //地址不能用双引号
            return '/taotao/manager/picture/upload';
      }
    },

    initFileInput:function () {
        $("#imageInputFile").fileinput({
            //ajax上传的地址
            uploadUrl:FileInput.URL.picture(),
            uploadAsync:true,
            language:'zh',
            allowedPreviewTypes: ['image'],
            allowedFileTypes: ['image'],
            //不显示本地的移除按键，只保留从数据库删除的按键
            showRemove:false,
            // allowedFileExtensions:  ['jpg', 'png'],
            // maxFileSize : 2000,
            browseClass:'btn btn-default',
            maxFileCount:'5',
            // showPreview:true,
            enctype: 'multipart/form-data',
        });
        //文件上传服务器成功后触发事件
        $("#imageInputFile").on('fileuploaded',function (event, data) {
            var response = data.response;
            var initialPreviewConfig = response.initialPreviewConfig;
            var imageURL = initialPreviewConfig[0].extra.imageURL;
            var value = $('#image').val();
            if(value==null||""==value){
                $('#image').val(imageURL);
            }else{
                $('#image').val(value+";"+imageURL);
            }
        });

        //删除缩略图后的回调事件
        $('#imageInputFile').on('filedeleted',function (event, key, jqXHR, data) {
            var value = $('#image').val();
            var newValue="";
            var deleteURL = data.imageURL;
            if(value.indexOf(";")>0){
                //含有";"说明有两张及以上的图片
                var array = value.split(";");
                //遍历所有元素，删除需要删除的元素，重新拼接
                for(var i=0;i<array.length;i++){
                    if(array[i].indexOf(deleteURL)==-1){
                        if(""==newValue){
                            newValue = array[i];
                        }else{
                            newValue = newValue+";"+array[i];
                        }
                    }
                }
                $('#image').val(newValue);
            }else{
                //如果只有一张图片，删除是直接清空value值
                $('#image').val("");
            }
        })

    }


}