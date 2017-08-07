package com.thestore.eam.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.thestore.eam.common.Constants;
import com.thestore.eam.dao.IDmpLabelDao;
import com.thestore.eam.domain.DmpLabel;
import com.thestore.eam.domain.DmpLabelValue;
import com.thestore.eam.service.IDmpLabelService;

public class DmpLabelService implements IDmpLabelService {
	private IDmpLabelDao dmpLabelDao;

	@Override
	public int saveDmpLabel(DmpLabel dmpLable) {
		// TODO Auto-generated method stub
		return dmpLabelDao.saveDmpLabel(dmpLable);
	}

	@Override
	public List<DmpLabel> findLabelByParentId(Long dmpLabelId) {
		return dmpLabelDao.findLabelByParentId(dmpLabelId);
	}

	@Override
	public List<DmpLabel> getOneLevelDmpLabel() {
		return dmpLabelDao.getOneLevelDmpLabel();
	}

	@Override
	public DmpLabel findLabelById(Long id) {
		return dmpLabelDao.findLabelById(id);
	}

	@Override
	public Map<Long, List<DmpLabel>> getDmpLabelCategoryParentMap() {
		Map<Long, List<DmpLabel>> resultMap = new HashMap<Long, List<DmpLabel>>();

		// 查询出所有的标签类目
		DmpLabel condition = new DmpLabel();
		condition.setIsLabel(Constants.LABEL_IS_LABEL_CATEGORY);
		List<DmpLabel> labelCategoryList = dmpLabelDao.findLabelByCondition(condition);

		Map<Long, DmpLabel> categoryMap = new HashMap<Long, DmpLabel>();
		for (DmpLabel dmpLabel : labelCategoryList) {
			categoryMap.put(dmpLabel.getId(), dmpLabel);

			List<DmpLabel> routeDmpLabelCategorys = new ArrayList<DmpLabel>();
			routeDmpLabelCategorys.add(dmpLabel);
			resultMap.put(dmpLabel.getId(), routeDmpLabelCategorys);
		}

		for (DmpLabel dmpLabel : labelCategoryList) {
			Long dmpLabelId = dmpLabel.getId();
			List<DmpLabel> routeDmpLabelCategorys = resultMap.get(dmpLabelId);
			if (routeDmpLabelCategorys == null) {
				routeDmpLabelCategorys = new ArrayList<DmpLabel>();
				routeDmpLabelCategorys.add(dmpLabel);
				resultMap.put(dmpLabel.getId(), routeDmpLabelCategorys);
			}

			Long parentId = dmpLabel.getParentId();
			while (parentId != null && parentId > 0) {
				DmpLabel parentDmpLabel = categoryMap.get(parentId);

				if (parentDmpLabel != null) {
					routeDmpLabelCategorys.add(parentDmpLabel);
				}

				parentId = parentDmpLabel.getParentId();
			}
		}
		return resultMap;
	}

	@Override
	public Map<Long, List<DmpLabel>> getDmpLabelCategoryChildrenMap() {
		Map<Long, List<DmpLabel>> resultMap = new HashMap<Long, List<DmpLabel>>();

		// 在parentMap里，每一个类目都存放了包括其自身在内的路径中的父类目list
		// 在此基础上，对于每个类目对应的父类list，将其本身加入到父类目list中所有类目的list中
		Map<Long, List<DmpLabel>> parentMap = getDmpLabelCategoryParentMap();
		Set<Map.Entry<Long, List<DmpLabel>>> entry = parentMap.entrySet();
		for (Map.Entry<Long, List<DmpLabel>> labelEntry : entry) {
			List<DmpLabel> parentList = labelEntry.getValue();

			if (parentList != null && !parentList.isEmpty()) {
				DmpLabel selfLabel = parentList.get(0);
				for (int i = 0; i < parentList.size(); i++) {
					Long labelCateId = parentList.get(i).getId();
					List<DmpLabel> list = resultMap.get(labelCateId);
					if (list == null) {
						list = new ArrayList<DmpLabel>();
						resultMap.put(labelCateId, list);
					}
					list.add(selfLabel);
				}

			}

		}

		return resultMap;
	}

	@Override
	public List<DmpLabelValue> findDmpLabelValue(DmpLabel dmpLabel) {
		return dmpLabelDao.findDmpLabelValue(dmpLabel);
	}

	@Override
	public List<DmpLabel> findDmpLabel(DmpLabel dmpLabel) {
		return dmpLabelDao.findDmpLabel(dmpLabel);
	}

	@Override
	public List<DmpLabel> findDmpLabelTotal(Long dmpLabelId) {
		return dmpLabelDao.findDmpLabelTotal(dmpLabelId);
	}

	@Override
	public Integer countDmpLabel(DmpLabel dmpLabel) {
		return dmpLabelDao.countDmpLabel(dmpLabel);
	}

	@Override
	public int stepUpdateRanking(int type, Long dmpLabelId, String updator) {
		if (dmpLabelId == null) {
			return Constants.NO_NECESSARY;
		}
		DmpLabel dmpLabel = dmpLabelDao.findLabelById(dmpLabelId);
		if (dmpLabel == null) {
			return Constants.NOT_EXIST;
		}

		Long parentId = dmpLabel.getParentId();
		List<DmpLabel> sameParentLabelList = dmpLabelDao.findLabelByParentId(parentId);
		if (sameParentLabelList == null || sameParentLabelList.isEmpty() || sameParentLabelList.size() == 1) {
			return Constants.SUCCESS;
		}
		int size = sameParentLabelList.size();
		int pos = 0;
		for (int i = 0; i < size; i++) {
			DmpLabel dl = sameParentLabelList.get(i);
			if (dmpLabelId.equals(dl.getId())) {
				pos = i;
				break;
			}
		}

		switch (type) {
		case Constants.LABEL_UP_RANKING:// 上移一个排名
			// 已经是第一名了，不需要上移排名了
			if (pos == 0) {
				break;
			}
			DmpLabel beforeLabel = sameParentLabelList.get(pos - 1);
			if (beforeLabel != null) {// 和排名前一个的交换排名字段的值
				dmpLabelDao.updateDmpLabelRanking(beforeLabel.getId(), dmpLabel.getSort(), updator);
				dmpLabelDao.updateDmpLabelRanking(dmpLabelId, beforeLabel.getSort(), updator);
			}

			break;
		case Constants.LABEL_DOWN_RANKING:// 下移一个排名
			// 已经是最后一名了，不需要再下移排名了
			if (pos == size - 1) {
				break;
			}

			DmpLabel afterLabel = sameParentLabelList.get(pos + 1);
			if (afterLabel != null) {// 和排名后一个的交换排名字段的值
				dmpLabelDao.updateDmpLabelRanking(afterLabel.getId(), dmpLabel.getSort(), updator);
				dmpLabelDao.updateDmpLabelRanking(dmpLabelId, afterLabel.getSort(), updator);
			}
			break;
		}
		return Constants.SUCCESS;
	}

	public void setDmpLabelDao(IDmpLabelDao dmpLabelDao) {
		this.dmpLabelDao = dmpLabelDao;
	}
}
