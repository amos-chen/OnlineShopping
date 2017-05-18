package com.taotao.service;

import com.taotao.dao.TbItemCatMapper;
import com.taotao.dao.TbItemDescMapper;
import com.taotao.dao.TbItemMapper;
import com.taotao.dao.TbItemParamMapper;
import com.taotao.dto.JSTree;
import com.taotao.exception.*;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemCat;
import com.taotao.pojo.TbItemDesc;
import com.taotao.pojo.TbItemParam;
import com.taotao.utils.IDUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenlunwei on 2017/4/30.
 */
@Service
public class ItemsServiceImpl implements ItemsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TbItemMapper tbItemMapper;

	@Autowired
	private TbItemCatMapper tbItemCatMapper;

	@Autowired
	private TbItemDescMapper tbItemDescMapper;

	@Autowired
	private TbItemParamMapper tbItemParamMapper;

	@Override
	public TbItem queryById(@Param("id") long id) {
		return tbItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TbItem> queryList(String search) {
		return tbItemMapper.queryList(search);
	}

	@Override
	public List<JSTree> queryJSTrees(String id) {    //根据id查询所有子类
		//因为jstree初始化之后会发起一个get请求，id参数为‘#’
		if (id == null || "".equals(id.trim()) || "#".equals(id)) {
			id = "0";
		}
		//把String参数转成int型
		int parentId = Integer.parseInt(id);
		//根据id查询此Id所对应的所有子类
		List<TbItemCat> tbItemCats = tbItemCatMapper.queryListByParentId(parentId);
		//把子类信息转成JStree所需要的POJO
		List<JSTree> jsTrees = new ArrayList<>();
		for (TbItemCat ItemCat : tbItemCats) {
			boolean state = ItemCat.getIsParent();
			JSTree jsTree;
			if (state) {
				jsTree = new JSTree(ItemCat.getId().toString(), ItemCat.getName(),
						"fa fa-folder fw", state);
			} else {
				jsTree = new JSTree(ItemCat.getId().toString(), ItemCat.getName(),
						"fa fa-file fw", state);
			}
			jsTrees.add(jsTree);
		}
		return jsTrees;

	}

	@Override
	@Transactional
	public int insertItem(TbItem tbItem, String description) throws DataInsertFailException, TaotaoException {
		try {
			//补全tbItem信息
			Date date = new Date();
			tbItem.setUpdated(date);
			tbItem.setCreated(date);
			tbItem.setStatus((byte) 1);
			//价格单位:分
			tbItem.setPrice(tbItem.getPrice() * 1000);
			Long id = IDUtils.genItemId();
			tbItem.setId(id);

			int data = tbItemMapper.insertSelective(tbItem);
			int InsertDescData = insertItemDesc(tbItem, description);
			if (data != 0 && InsertDescData != 0) {
				return data;
			} else {
				throw new DataInsertFailException("商品信息插入失败！");
			}
		} catch (DataInsertFailException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}
	}

	@Override
	@Transactional
	public List<Integer> deleteItem(String[] itemIdList) throws DataDeleteFailException, TaotaoException {
		try {
			List<Integer> reslutList = new ArrayList();
			for (String id : itemIdList) {
				long itemId = Long.parseLong(id);
				int result = tbItemMapper.deleteByPrimaryKey(itemId);
				if (result == 0) {
					throw new DataDeleteFailException("商品删除失败!");
				} else {
					reslutList.add(result);
				}
			}
			if (reslutList != null && reslutList.size() > 0 && !reslutList.contains(0)) {
				return reslutList;
			} else {
				throw new DataDeleteFailException("商品删除失败!");
			}

		} catch (DataDeleteFailException e) {
			throw e;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new TaotaoException("系统内部错误:" + e.getMessage());
		}
	}

	@Override
	public TbItemCat queryItemCat(Long cid) {
		try {
			TbItemCat tbItemCat = tbItemCatMapper.selectByPrimaryKey(cid);
			if(tbItemCat==null){
				throw new DataNotFindException("没有查到相应数据");
			}else{
				return tbItemCat;
			}
		}catch (DataNotFindException e){
			throw e;
		}catch (Exception e){
			throw new TaotaoException("系统内部错误:"+e.getMessage());
		}
	}

	@Override
	public TbItemParam queryItemParam(Long cid) throws DataNotFindException,TaotaoException{
		try {
			TbItemParam tbItemParam = tbItemParamMapper.selectByCid(cid);
			if(tbItemParam==null){
				throw new DataNotFindException("没有查到相应数据");
			}else{
				return tbItemParam;
			}
		}catch (DataNotFindException e){
			throw e;
		}catch (Exception e){
			throw new TaotaoException("系统内部错误:"+e.getMessage());
		}

	}

	@Override
	public int insertTbitemParam(TbItemParam tbItemParam) throws DataInsertFailException,TaotaoException{
		try{
			int result = tbItemParamMapper.insert(tbItemParam);
			if(result==0){
				throw new DataInsertFailException("商品参数模板插入失败!");
			}else{
				return result;
			}
		}catch (DataInsertFailException e){
			throw e;
		}catch (Exception e){
			throw new TaotaoException("系统内部错误:"+e.getMessage());
		}
	}

	@Override
	public int updateTbitemParam(TbItemParam tbItemParam) {
		try {
			int result = tbItemParamMapper.updateByPrimaryKeyWithBLOBs(tbItemParam);
			if(result==0){
				throw new DataUpdateFailException("商品参数模板更新失败!");
			}else{
				return result;
			}
		}catch (DataUpdateFailException e){
			throw e;
		} catch (Exception e){
			throw new TaotaoException("系统内部错误:"+e.getMessage());
		}
	}

	private int insertItemDesc(TbItem tbItem, String description) {
		TbItemDesc tbItemDesc = new TbItemDesc();
		tbItemDesc.setItemId(tbItem.getId());
		tbItemDesc.setItemDesc(description);
		tbItemDesc.setUpdated(tbItem.getUpdated());
		tbItemDesc.setCreated(tbItem.getCreated());
		int data = tbItemDescMapper.insertSelective(tbItemDesc);
		return data;
	}
}