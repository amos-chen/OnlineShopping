
var FileInput = {

    URL:{
      PICURL:function () {
          return "/taotao/manager/pic/upload"
      }
    },

    initFileInput:function () {
        $("#imageInputFile").fileinput({
            language:'zh',
            allowedPreviewTypes: ['image'],
            allowedFileTypes: ['image'],
            // allowedFileExtensions:  ['jpg', 'png'],
            // maxFileSize : 2000,
            browseClass:'btn btn-default',
            maxFileCount:'5',
            //ajax上传的地址
            uploadUrl:FileInput.URL.PICURL,

        });
    }


}