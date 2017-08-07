package com.thestore.eam.service;

import java.util.List;
import java.util.Map;

import com.thestore.eam.domain.DmpLabel;
import com.thestore.eam.domain.DmpLabelValue;

public interface IDmpLabelService {
	int saveDmpLabel(DmpLabel dmpLable);

	/**
	 * 获取一级类目
	 * 
	 * @Title: getOneLevelDmpLabel
	 * @Description:
	 * @param
	 * @return List<DmpLabel>
	 * @author zhangping2(zhangping2@yhd.com)
	 * @date 2015-7-23
	 */
	List<DmpLabel> getOneLevelDmpLabel();

	/**
	 * 根据父一级ID查询下一级
	 * 
	 * @param dmpLabelId
	 * @return
	 */
	List<DmpLabel> findLabelByParentId(Long dmpLabelId);

	/**
	 * 查询指定ID的标签或标签类目
	 * 
	 * @param id
	 * @return
	 */
	DmpLabel findLabelById(Long id);

	/**
	 * 组装系统所有标签类目Map：<br>
	 * key，是标签ID<br>
	 * value,是从标签本身到最上一级标签类目的list，顺序是本身，父级，。。。，第一级
	 * 
	 * @return
	 */
	Map<Long, List<DmpLabel>> getDmpLabelCategoryParentMap();

	/**
	 * 组装系统所有标签类目Map：<br>
	 * key，是标签ID<br>
	 * value,是从标签本身其所有下一级标签类目的list，没有固定的顺序
	 * 
	 * @return
	 */
	Map<Long, List<DmpLabel>> getDmpLabelCategoryChildrenMap();

	/**
	 * 页面分页查询DMP标签值
	 * 
	 * @param dmpLabel
	 * @return
	 */
	List<DmpLabelValue> findDmpLabelValue(DmpLabel dmpLabel);

	/**
	 * 页面分页查询DMP标签
	 * 
	 * @param dmpLabel
	 * @return
	 */
	List<DmpLabel> findDmpLabel(DmpLabel dmpLabel);

	/**
	 * 获取以标签维度统计的值数量和用户数量
	 * 
	 * @param id
	 * @return
	 */
	List<DmpLabel> findDmpLabelTotal(Long id);

	/**
	 * 查询某个标签类目下标签数量
	 * 
	 * @param dmpLabel
	 * @return
	 */
	Integer countDmpLabel(DmpLabel dmpLabel);

	/**
	 * 为指定标签上移或下移一个排名<br>
	 * type == 0 ，上移一个排名<br>
	 * type == 1，下移一个排名
	 * 
	 * @param type
	 * @param dmpLabelId
	 * @param updator
	 * @return
	 */
	int stepUpdateRanking(int type, Long dmpLabelId, String updator);
}
