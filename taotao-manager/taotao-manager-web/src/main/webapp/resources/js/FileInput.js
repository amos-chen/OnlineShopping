
var FileInput = {

    URL:{
        picture : function () {
            //地址不能用双引号
            return '/taotao/manager/picture/upload';
      }
    },

    initFileInput:function () {
            console.log(FileInput.URL.picture());
        $("#imageInputFile").fileinput({
            //ajax上传的地址
            uploadUrl:FileInput.URL.picture(),
            uploadAsync:true,
            language:'zh',
            allowedPreviewTypes: ['image'],
            allowedFileTypes: ['image'],
            // allowedFileExtensions:  ['jpg', 'png'],
            // maxFileSize : 2000,
            browseClass:'btn btn-default',
            maxFileCount:'5',
            // showPreview:true,
            enctype: 'multipart/form-data',
        });
    }


}