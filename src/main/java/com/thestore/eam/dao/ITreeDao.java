package com.thestore.eam.dao;

import java.util.List;

import com.thestore.eam.domain.Tree;

public interface ITreeDao {

	/**
	 * 获取树中所有结点信息
	 * 
	 * @param tree
	 * @return
	 */
	List<Tree> findAllTree();

}
