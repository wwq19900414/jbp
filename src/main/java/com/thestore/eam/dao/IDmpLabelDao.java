package com.thestore.eam.dao;

import java.util.List;

import com.thestore.eam.domain.DmpLabel;
import com.thestore.eam.domain.DmpLabelValue;

public interface IDmpLabelDao {

	int saveDmpLabel(DmpLabel dmpLable);

	/**
	 * 根据父一级查询子一级
	 * 
	 * @param dmpLabelId
	 * @return
	 */
	List<DmpLabel> findLabelByParentId(Long dmpLabelId);

	/**
	 * 获取所有一级类目
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
	 * 获取所有类目及标签
	 * 
	 * @Title: getAllDmpLabel
	 * @Description:
	 * @param
	 * @return List<DmpLabel>
	 * @author zhangping2(zhangping2@yhd.com)
	 * @date 2015-7-23
	 */
	List<DmpLabel> getAllDmpLabel();

	/**
	 * 获取常用标签
	 * 
	 * @Title: getCommonDmpLabels
	 * @Description:
	 * @param
	 * @return List<DmpLabel>
	 * @author zhangping2(zhangping2@yhd.com)
	 * @date 2015-7-23
	 */
	List<DmpLabel> getCommonDmpLabels();

	/**
	 * 获取指定ID的标签（或标签类目）
	 * 
	 * @param id
	 * @return
	 */
	DmpLabel findLabelById(Long id);

	/**
	 * 单表条件查询标签
	 * 
	 * @param conditon
	 * @return
	 */
	List<DmpLabel> findLabelByCondition(DmpLabel condition);

	/**
	 * 分页条件查询标签值信息
	 * 
	 * @param dmpLabel
	 * @return
	 */
	List<DmpLabelValue> findDmpLabelValue(DmpLabel dmpLabel);

	/**
	 * 分页条件查询标签信息
	 * 
	 * @param dmpLabel
	 * @return
	 */
	List<DmpLabel> findDmpLabel(DmpLabel dmpLabel);

	/**
	 * 根据标签id查询有效的标签值
	 * 
	 * @Title: findDmpLabelValueByLabelIds
	 * @Description:
	 * @param
	 * @return List<DmpLabelValue>
	 * @author zhangping2(zhangping2@yhd.com)
	 * @date 2015-7-23
	 */
	List<DmpLabelValue> findDmpLabelValueByLabelIds(List<DmpLabel> list);

	/**
	 * 查询指定id标签的标签值数量和用户数量
	 * 
	 * @param dmpLabelId
	 * @return
	 */
	List<DmpLabel> findDmpLabelTotal(Long dmpLabelId);

	/**
	 * 查询某个标签类目下标签的数量
	 * 
	 * @param dmpLabel
	 * @return
	 */
	Integer countDmpLabel(DmpLabel dmpLabel);

	/**
	 * 获取将要新增的标签的sort的值<br>
	 * 需要传入标签所属的标签类目的ID : parentLabelCategoryId
	 * 
	 * @param parentLabelCategoryId
	 * @return
	 */
	int getSort(Long parentLabelCategoryId);

	/**
	 * 调整排名
	 * 
	 * @param dmpLabelId
	 * @param sort
	 * @param updator
	 * @return
	 */
	int updateDmpLabelRanking(Long dmpLabelId, Integer sort, String updator);
}
