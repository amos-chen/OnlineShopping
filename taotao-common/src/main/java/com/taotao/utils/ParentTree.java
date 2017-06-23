package com.taotao.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlunwei on 2017/6/22.
 */
public class ParentTree<E> {
	private List<Node<E>> nodelist = new ArrayList<>();

	private static class Node<T> {
		T data;
		int parentIndex;
		int id;
		boolean isParent;

		//无参构造方法
		public Node() {

		}

		public Node(T data, int parentIndex, Long id,boolean isParent) {
			this.data = data;
			this.parentIndex = parentIndex;
			this.id = id.intValue();
			this.isParent = isParent;
		}
	}


	//指定根节点创建树
	public ParentTree() {
		Node<E> node = new Node<>(null, -1,0l,true);
		nodelist.add(node);
	}

	//新增节点
	public void addNode(E data, Long id, Long ParentId,boolean isParent) {
		int parentIndex = getIndex(ParentId);
		Node<E> node = new Node<E>(data, parentIndex, id,isParent);
		nodelist.add(node);
	}

	//获取指定id节点的索引
	public int getIndex(Long id) {
		for (int i=0;i<nodelist.size();i++) {
			if (nodelist.get(i).id == id.intValue()) {
				return i+1;
			}
		}
		return -1;
	}

	//获取指定id的节点
	public Node<E> getNode(Long id) {
		for (Node<E> node : nodelist) {
			if (node.id == id.intValue()) {
				return node;
			}
		}
		throw new RuntimeException("未找到节点");
	}

	//获取指定id的节点的数据
	public E getData(Long id) {
		for (Node<E> node : nodelist) {
			if (node.id == id.intValue()) {
				return node.data;
			}
		}
		throw new RuntimeException("未找到数据");
	}

	//获取指定id的节点的所有子节点
	public List<Node<E>> getChildrenNode(Long id) {
		List<Node<E>> list = new ArrayList<>();
		int index = getIndex(id);
		for (Node<E> node : nodelist) {
			if (node.parentIndex == index) {
				list.add(node);
			}
		}
		return list;
	}

	//获取指定id的节点的所有子节点的数据
	public List<E> getChildrenData(Long id) {
		List<E> list = new ArrayList<>();
		int index = getIndex(id);
		for (Node<E> node : nodelist) {
			if (node.parentIndex == index) {
				list.add(node.data);
			}
		}
		return list;
	}

	//判断节点是否有子节点
	public boolean isParent(Node<E> node){
		return node.isParent;
	}

	//获取prentTree容量大小
	public int getSize(){
		return nodelist.size();
	}

	//根据索引获取节点
	public Node<E> get(int index){
		return nodelist.get(index);
	}


	//先根遍历
	public List<E> Traversal(Long rootId){
		List result = new ArrayList();
		Node<E> node1 = getNode(rootId);
		result.add(node1.data);
		if(node1.isParent){
			List<Node<E>> nodelist = getChildrenNode((long)node1.id);
			for (Node<E> node2 : nodelist){
				result.addAll(Traversal((long)node2.id));
			}
		}
		return result;
	}
}
