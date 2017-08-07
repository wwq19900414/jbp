/**
 * Copyright (C), 2011-2016 The Store
 * File Name: ExceptionCode.java
 * Encoding: UTF-8
 * Date: Oct 23, 2011
 * History: 
 */
package com.thestore.eam.exception;

/**
 * the exception code definition
 * @author Wayne Wan(bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Oct 23, 2011
 */
public class ExceptionCode {
	/***   Contract module exception definition  **/
	
	public static final String ERR_CONTRACT_NAMEEXIST = "err.contract.nameExist";
	public static final String ERR_CONTRACT_CONTRACNUMEXIST = "err.contract.contractNumExist";
	public static final String ERR_CONTRACT_OUTERNUMEXIST = "err.contract.outerNumExist";
	public static final String ERR_CONTRACT_HASCAMPAIGN = "err.contract.hasCampaign";
	public static final String ERR_PUBLISHER_NAMEEXIST = "err.publish.nameExist";
	public static final String ERR_PAGE_NAMEEXIST = "err.page.nameExist";
	public static final String ERR_CAMPAIGN_OVERSIZE = "err.campaign.oversize";
	public static final String ERR_CAMPAIGN_OVERONEM = "err.campaign.overoneM";
	public static final String ERR_REPORT_REPORTDATE_ERR = "err.report.reportDateErr";
	public static final String ERR_REPORT_REGENERATE_ERR = "err.report.regenerateReportErr";
	
	public static final String ERR_CHANNELAPI_404 = "err.channelAPI.notFount";
	public static final String ERR_CHANNELAPI_500 = "err.channelAPI.servError";
	public static final String ERR_CHANNELAPI_503 = "err.channelAPI.overloaded";
	
	public static final String ERR_PARAM_NOTEXIST = "err.common.paramNotExist";
	public static final String ERR_PARAM_INVALID = "err.common.paramInvalid";
	
	public static final String ERR_ADSPACE_BALANCEDATEINVALID = "err.balanceCollcate.dateInvalid";
}
