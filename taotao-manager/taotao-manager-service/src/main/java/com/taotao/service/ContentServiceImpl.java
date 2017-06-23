package com.taotao.service;

import com.taotao.dao.TbContentCategoryMapper;
import com.taotao.dto.JSTree;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import com.taotao.utils.ParentTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${chenlunwei} on 2017/6/21.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;


    @Override
    public List<TbContent> queryContent() {
        return null;
    }

    @Override
    public List<JSTree> queryJstreeNode() {
        List<JSTree> jsTreeList = getContentCatTree(0l);
        return jsTreeList;
    }


    public List<JSTree> getContentCatTree(Long id) {
        List<JSTree> list = new ArrayList<>();
        JSTree jsTree;
        List<TbContentCategory> tbs = tbContentCategoryMapper.queryByParentId(id);
        for(TbContentCategory tb:tbs){
            if(tb.getIsParent()){
                jsTree = new JSTree(tb.getId().toString(), tb.getName(),
                        "fa fa-folder fw", tb.getIsParent());
                jsTree.setChildren(getContentCatTree(tb.getId()));
            }else{
                jsTree = new JSTree(tb.getId().toString(), tb.getName(),
                        "fa fa-file fw", tb.getIsParent());
            }
            list.add(jsTree);
        }
        return list;
    }

    /*动态加载节点
    @Override
    public List<JSTree> queryJstreeNode(String id) {
        //因为jstree初始化之后会发起一个get请求，id参数为‘#’
        if (id == null || "".equals(id.trim()) || "#".equals(id)) {
            id = "0";
        }
        //把String参数转成int型
        long parentId = Long.parseLong(id);
        //根据id查询此Id所对应的所有子类
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.queryByParentId(parentId);
        //把子类信息转成JStree所需要的POJO
        List<JSTree> jsTrees = new ArrayList<>();
        for (TbContentCategory tbContentCategory : tbContentCategories) {
            boolean state = tbContentCategory.getIsParent();
            JSTree jsTree;
            if (state) {
                jsTree = new JSTree(tbContentCategory.getId().toString(), tbContentCategory.getName(),
                        "fa fa-folder fw", state);
            } else {
                jsTree = new JSTree(tbContentCategory.getId().toString(), tbContentCategory.getName(),
                        "fa fa-file fw", state);
            }
            jsTrees.add(jsTree);
        }
        return jsTrees;
    }*/

    @Override
    public List<TbContentCategory> queryContentCat() {
        /* 树状下拉菜单，此处只取所有根节点较好
        ParentTree<TbContentCategory> PT = new ParentTree<>();
        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.queryList();
        for(TbContentCategory tb:tbContentCategories){
            PT.addNode(tb,tb.getId(),tb.getParentId(),tb.getIsParent());
        }
        List<TbContentCategory> result =  PT.Traversal(0l);
        result.remove(0);*/

        List<TbContentCategory> tbContentCategories = tbContentCategoryMapper.queryByParentId(0l);
        return tbContentCategories;
    }
}
