package com.thestore.eam.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;

import com.thestore.eam.common.Constants;
import com.thestore.eam.domain.DmpLabel;
import com.thestore.eam.domain.DmpLabelValue;
import com.thestore.eam.service.IDmpLabelService;
import com.thestore.eam.utils.GridData;

/**
 * DMP标签管理控制器
 * 
 * @author Wu Wenqi(wuwenqi@yihaodian.com)
 * @version Revision: 1.00 Date: 2015-07-21
 */
@Namespace("/")
@Results({ @Result(name = "showDmpLabelDetail", location = "/dmpLabel/dmpLabelDetail.jsp"),
		@Result(name = "showDmpLabelCategoryDetail", location = "/dmpLabel/dmpLabelCateDetail.jsp"),
		@Result(name = "dmpLabel", location = "/dmpLabel/dmpLabelMain.jsp"), })
public class DmpLabelAction extends BaseAction {
	private static final long serialVersionUID = 5838662157923910080L;
	private IDmpLabelService dmpLabelService;
	private DmpLabel dmpLabel = new DmpLabel();

	private List<DmpLabel> labelList;

	@Action(value = "dmpLabel")
	public String dmpLabel() {
		return "dmpLabel";
	}

	@Action(value = "testArray")
	public void testArray() {
		JSONArray arr = JSONArray.fromObject(labelList);
		this.writeJSON(arr.toString());
	}

	@Action(value = "jsonp")
	public void jsonp() {
		DmpLabel dl = new DmpLabel();
		dl.setId(dmpLabel.getId());
		dl.setLabelName("wuwenqi_你好吗_jsonp_" + dmpLabel.getId());
		this.writeJSONP(JSONObject.fromObject(dl).toString());
	}

	/**
	 * 打开查看标签详情页
	 * 
	 * @return
	 */
	@Action(value = "showDmpLabelCategoryDetail")
	public String showDmpLabelCategoryDetail() {
		dmpLabel = dmpLabelService.findLabelById(dmpLabel.getId());

		Map<Long, List<DmpLabel>> childrenMap = dmpLabelService.getDmpLabelCategoryChildrenMap();
		List<DmpLabel> childList = childrenMap.get(dmpLabel.getId());
		if (CollectionUtils.isNotEmpty(childList)) {
			StringBuilder ids = new StringBuilder();
			for (int i = 0; i < childList.size(); i++) {
				DmpLabel label = childList.get(i);
				ids.append(label.getId());
				if (i < childList.size() - 1) {
					ids.append(",");
				}
			}
			dmpLabel.setIds(ids.toString());
		}

		Integer countDmpLabel = dmpLabelService.countDmpLabel(dmpLabel);
		dmpLabel.setValueCount(countDmpLabel);
		return "showDmpLabelCategoryDetail";
	}

	/**
	 * 打开查看标签详情页
	 * 
	 * @return
	 */
	@Action(value = "showDmpLabelDetail")
	public String showDmpLabelDetail() {
		dmpLabel = dmpLabelService.findLabelById(dmpLabel.getId());

		Map<Long, List<DmpLabel>> parentMap = dmpLabelService.getDmpLabelCategoryParentMap();
		List<DmpLabel> parentList = parentMap.get(dmpLabel.getParentId());
		if (CollectionUtils.isNotEmpty(parentList)) {
			StringBuilder labelCategoryDesc = new StringBuilder();
			for (int i = parentList.size() - 1; i >= 0; i--) {
				DmpLabel dl = parentList.get(i);
				labelCategoryDesc.append(dl.getLabelName());
				if (i > 0) {
					labelCategoryDesc.append(" → ");
				}
			}
			dmpLabel.setLabelCategoryDesc(labelCategoryDesc.toString());
		}

		List<DmpLabel> labelTotalList = dmpLabelService.findDmpLabelTotal(dmpLabel.getId());
		if (CollectionUtils.isNotEmpty(labelTotalList)) {
			dmpLabel.setValueCount(labelTotalList.get(0).getValueCount());
			dmpLabel.setUserCount(labelTotalList.get(0).getUserCount());
		}
		return "showDmpLabelDetail";
	}

	/**
	 * 根据parentId获取子标签类目或标签
	 */
	@Action(value = "loadDmpLabelTree")
	public void loadDmpLabelTree() {
		Long dmpLabelId = dmpLabel.getParentId();
		List<DmpLabel> labelList = dmpLabelService.findLabelByParentId(dmpLabelId);
		JSONArray array = new JSONArray();
		if (CollectionUtils.isNotEmpty(labelList)) {

			for (DmpLabel dmpLabel : labelList) {
				JSONObject secondObj = new JSONObject();

				secondObj.put("id", dmpLabel.getId());
				secondObj.put("text", dmpLabel.getLabelName());

				Map<String, String> attributes = new HashMap<String, String>();
				Integer isLabel = dmpLabel.getIsLabel();
				if (Integer.valueOf(1).equals(isLabel)) {
					secondObj.put("state", "open");
					attributes.put("type", "label");
				} else {
					secondObj.put("state", "closed");
					attributes.put("type", "labelCategory");
				}

				secondObj.put("attributes", attributes);

				array.add(secondObj);
			}
		}
		writeJSON(array.toString());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value = "findDmpLabelValue")
	public void findDmpLabelValue() {
		List<DmpLabelValue> list = dmpLabelService.findDmpLabelValue(dmpLabel);
		GridData gridData = new GridData(getPageCtrl().getRecordCount(), list);
		JSONObject jsonObject = JSONObject.fromObject(gridData);
		this.writeHTML(jsonObject.toString());
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Action(value = "findDmpLabel")
	public void findDmpLabel() {
		Map<Long, List<DmpLabel>> childrenMap = dmpLabelService.getDmpLabelCategoryChildrenMap();
		List<DmpLabel> childList = childrenMap.get(dmpLabel.getParentId());
		if (CollectionUtils.isNotEmpty(childList)) {
			StringBuilder ids = new StringBuilder();
			for (int i = 0; i < childList.size(); i++) {
				DmpLabel label = childList.get(i);
				ids.append(label.getId());
				if (i < childList.size() - 1) {
					ids.append(",");
				}
			}
			dmpLabel.setIds(ids.toString());
		}
		List<DmpLabel> list = dmpLabelService.findDmpLabel(dmpLabel);
		GridData gridData = new GridData(getPageCtrl().getRecordCount(), list);
		JSONObject jsonObject = JSONObject.fromObject(gridData);
		this.writeHTML(jsonObject.toString());
	}

	/**
	 * 上移一个排名
	 */
	@Action(value = "stepUpRanking")
	public void stepUpRanking() {
		String updator = Constants.DEFAULT_USER;
		int result = dmpLabelService.stepUpdateRanking(Constants.LABEL_UP_RANKING, dmpLabel.getId(), updator);
		this.writePlainText("" + result);
	}

	/**
	 * 下移一个排名
	 */
	@Action(value = "stepDownRanking")
	public void stepDownRanking() {
		String updator = Constants.DEFAULT_USER;
		int result = dmpLabelService.stepUpdateRanking(Constants.LABEL_DOWN_RANKING, dmpLabel.getId(), updator);
		this.writePlainText("" + result);
	}

	public void setDmpLabelService(IDmpLabelService dmpLabelService) {
		this.dmpLabelService = dmpLabelService;
	}

	public void setDmpLabel(DmpLabel dmpLabel) {
		this.dmpLabel = dmpLabel;
	}

	public DmpLabel getDmpLabel() {
		return dmpLabel;
	}

	public void setLabelList(List<DmpLabel> labelList) {
		this.labelList = labelList;
	}

	public List<DmpLabel> getLabelList() {
		return labelList;
	}

}
