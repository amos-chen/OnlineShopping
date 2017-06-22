package com.taotao.web;

import com.taotao.dto.JSTree;
import com.taotao.pojo.TbContentCategory;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ${chenlunwei} on 2017/6/21.
 */

@Controller
@RequestMapping(value = "/taotao/manager")
public class ContentController {

    @Autowired
    private ContentService contentService;

    @RequestMapping(value = "/contentCat",method = RequestMethod.GET)
    @ResponseBody
    public List<JSTree> getJstree(String id){
        return contentService.queryJstreeNode(id);
    }


    @RequestMapping(value = "/contentCat/list",method = RequestMethod.GET)
    @ResponseBody
    public List<TbContentCategory> getContentCatList(){
        return contentService.queryContentCat();
    }

}
