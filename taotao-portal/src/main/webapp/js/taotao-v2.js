/**
 * Created by chenlunwei on 2017/7/13.
 */
var taotaoV2={
    url:{
        itemDescURL:function (itemId) {
            return "http://localhost:8082/item/"+itemId+"/desc.html";
        }
    },

    init: function (param) {
        setTimeout(taotaoV2.getDescription(param.itemId),1000);

    },



    getDescription:function (itemId) {
        $.get(taotaoV2.url.itemDescURL(itemId),function (result) {
            if(result!==""){
                $('#item-desc').html(result);
            }
        })
    },

    haveParam:false,

    getItemParam:function(itemId) {
        //如果没有查询过规格参数，就做请求
        if (!itemControl.haveParam) {
            $.get(itemControl.param.paramUrl+itemId+".html", function(data){
                //返回商品规格的html，直接显示到页面
                $("#product-detail-2").append(data);
                //更改flag状态
                itemControl.haveParam = true;
            });
        }
    }



}