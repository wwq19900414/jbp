package com.thestore.eam.service;

import java.util.List;
import java.util.Map;

import com.thestore.eam.domain.Tree;

public interface ITreeService {
	/**
	 * 
	 * @param tree
	 * @return
	 */
	List<Tree> findAllTree();

	/**
	 * 获取所有结点id 到 其path中所有结点list的map
	 * 
	 * @return
	 */
	Map<Long, List<Tree>> treePathMap();

	/**
	 * 获取所有parentId 到 其下所有子节点的map
	 * 
	 * @return
	 */
	Map<Long, List<Tree>> parentIdTotreeListMap();
}
