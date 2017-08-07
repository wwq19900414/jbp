package com.thestore.eam.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thestore.eam.dao.ITreeDao;
import com.thestore.eam.domain.Tree;
import com.thestore.eam.service.ITreeService;

public class TreeService implements ITreeService {
	private ITreeDao treeDao;

	@Override
	public List<Tree> findAllTree() {
		return treeDao.findAllTree();
	}

	@Override
	public Map<Long, List<Tree>> treePathMap() {
		Map<Long, List<Tree>> treeAllMap = new HashMap<Long, List<Tree>>();

		// 获取全部菜单项目
		List<Tree> treeList = treeDao.findAllTree();

		Map<Long, Tree> treeMap = new HashMap<Long, Tree>();
		for (Tree t : treeList) {
			treeMap.put(t.getId(), t);
		}

		for (Tree tree : treeList) {
			Long id = tree.getId();
			Long parentMenuId = tree.getParentId();

			List<Tree> pathTreeList = new ArrayList<Tree>();
			pathTreeList.add(tree);

			if (parentMenuId == null || parentMenuId == 0) {
				treeAllMap.put(id, pathTreeList);
			} else {
				Tree parentMenu = treeMap.get(parentMenuId);
				while (parentMenu != null) {
					pathTreeList.add(parentMenu);
					parentMenu = treeMap.get(parentMenu.getParentId());
				}

				treeAllMap.put(id, pathTreeList);

			}

		}
		return treeAllMap;
	}

	@Override
	public Map<Long, List<Tree>> parentIdTotreeListMap() {
		// 获取全部菜单项目
		List<Tree> treeList = treeDao.findAllTree();

		// 将同属于一个parentId的结点分组
		Map<Long, List<Tree>> parentIdTotreeListMap = new HashMap<Long, List<Tree>>();
		for (Tree t : treeList) {
			Long parentId = t.getParentId();

			List<Tree> sameParentList = parentIdTotreeListMap.get(parentId);
			if (sameParentList == null) {
				sameParentList = new ArrayList<Tree>();
				parentIdTotreeListMap.put(parentId, sameParentList);
			}

			sameParentList.add(t);
		}

		return parentIdTotreeListMap;
	}

	public List<Tree> getTree() {
		Map<Long, List<Tree>> parentIdTotreeListMap = this.parentIdTotreeListMap();
		return null;
	}
}
