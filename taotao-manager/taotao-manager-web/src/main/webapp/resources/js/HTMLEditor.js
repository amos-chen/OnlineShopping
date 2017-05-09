
var HTMLEditor={

    initEditor:function () {
        $('#summernote').summernote({
            lang:'zh-CN',
            minHeight :200,
            placeholder:'请填写商品类目的详细信息...',
            // //对话框功能
            dialogsFade:true,
            dialogsInBody: true,
            disableDragAndDrop: false,
            onImageUpload:HTMLEditor.uploadImage(),
        });
    },

    uploadImage:function (files) {
        
    }


}