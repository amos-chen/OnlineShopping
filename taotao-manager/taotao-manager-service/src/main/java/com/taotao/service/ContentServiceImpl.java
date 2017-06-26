package com.taotao.service;

import com.taotao.dao.TbContentCategoryMapper;
import com.taotao.dao.TbContentMapper;
import com.taotao.dto.JSTree;
import com.taotao.exception.*;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentCategory;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ${chenlunwei} on 2017/6/21.
 */
@Service
public class ContentServiceImpl implements ContentService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TbContentCategoryMapper tbContentCategoryMapper;

    @Autowired
    private TbContentMapper tbContentMapper;


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

    @Override
    @Transactional
    public List<Integer> addContentCat(String[] ParentId,String ContentName) throws DataInsertFailException, TaotaoException{
        try {
            String parent = ParentId[0];
            boolean isNumber = StringUtils.isNumeric(parent);
            List<Integer> result = new ArrayList<>();
            if(isNumber){
                //根据网页的form表单创建tbContentCategory对象
                TbContentCategory tb = new TbContentCategory();
                Date date = new Date();
                tb.setName(ContentName);
                tb.setIsParent(false);
                tb.setSortOrder(1);
                tb.setStatus(1);
                tb.setCreated(date);
                tb.setUpdated(date);
                tb.setParentId(Long.parseLong(parent));
                //把对象存到数据库中
                int data = tbContentCategoryMapper.insertWithoutId(tb);
                result.add(data);
                if (data != 0 ) {
                    return result;
                } else {
                    throw new DataInsertFailException("类目插入失败！");
                }
            }else{
                //如果form表单传来的父类在数据库中不存在，则先创建父类
                TbContentCategory parentTb = new TbContentCategory();
                Date date = new Date();
                parentTb.setName(parent);
                parentTb.setIsParent(true);
                parentTb.setSortOrder(1);
                parentTb.setStatus(1);
                parentTb.setCreated(date);
                parentTb.setUpdated(date);
                parentTb.setParentId(0l);
                //把父类目存入数据库中
                int addParentResult = tbContentCategoryMapper.insertWithoutId(parentTb);
                result.add(addParentResult);
                //查询数据库中最大的ID值获取父类id
                long Pid = tbContentCategoryMapper.queryMaxId();
                //再根据父类目的Id创建子类目
                TbContentCategory childTbcontentCat = new TbContentCategory();
                Date childDate = new Date();
                childTbcontentCat.setName(ContentName);
                childTbcontentCat.setIsParent(false);
                childTbcontentCat.setSortOrder(1);
                childTbcontentCat.setStatus(1);
                childTbcontentCat.setCreated(childDate);
                childTbcontentCat.setUpdated(childDate);
                childTbcontentCat.setParentId(Pid);
                //把子类目存入数据库中
                int addChildResult = tbContentCategoryMapper.insertWithoutId(childTbcontentCat);
                result.add(addChildResult);
                if (addParentResult != 0 && addChildResult!=0) {
                    return result;
                } else {
                    throw new DataInsertFailException("类目插入失败！");
                }
            }
        }catch (DataInsertFailException e) {
            throw e;
        } catch (Exception e) {
            logger.error(e.getMessage());
            throw new TaotaoException("系统内部错误:" + e.getMessage());
        }
    }

    @Override
    public TbContentCategory queryContentCat(Long id) throws DataNotFindException,TaotaoException{
        try {
            TbContentCategory tb = tbContentCategoryMapper.selectByPrimaryKey(id);
            return tb;
        }catch (DataNotFindException e){
            throw new DataNotFindException("未找到相应数据！");
        }catch (TaotaoException e){
            logger.error(e.getMessage());
            throw new TaotaoException("系统内部错误:" + e.getMessage());
        }
    }

    @Override
    public int updateContentCat(String id, String name) throws DataUpdateFailException,TaotaoException{
        try {
            //根据传入的id查询相应的TbContentCategory对象
            TbContentCategory tb = tbContentCategoryMapper.selectByPrimaryKey(Long.parseLong(id));
            //更新TbContentCategory的名称
            tb.setName(name);
            Date date = new Date();
            //更新TbContentCategory的更新时间
            tb.setUpdated(date);
            int reslut = tbContentCategoryMapper.updateByPrimaryKey(tb);
            if(reslut==0){
                throw new DataUpdateFailException("数据更新失败");
            }
            return reslut;
        }catch (DataUpdateFailException e){
            throw e;
        }catch (TaotaoException e){
            logger.error(e.getMessage());
            throw new TaotaoException("系统内部错误:" + e.getMessage());
        }

    }

    @Override
    public int deleteContentCat(String id) throws DataDeleteFailException,TaotaoException {
        try {
            int reslut = tbContentCategoryMapper.deleteByPrimaryKey(Long.parseLong(id));
            if(reslut==0){
                throw new DataDeleteFailException("数据删除失败");
            }
            return reslut;
        }catch (DataDeleteFailException e){
            throw e;
        }catch (TaotaoException e){
            logger.error(e.getMessage());
            throw new TaotaoException("系统内部错误:" + e.getMessage());
        }
    }

    @Override
    public List<TbContent> queryContentList(String categoryId) {
        List<TbContent> tbContents = tbContentMapper.queryListByCid(Long.parseLong(categoryId));
        return tbContents;
    }
}
