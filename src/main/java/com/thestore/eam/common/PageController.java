/**
 * Copyright (C), 2011-2016 The Store
 * File Name: BaseDao.java
 * Encoding: UTF-8
 * Date: Sep 7, 2011
 * History: 
 */
package com.thestore.eam.common;

/**
 * 
 * @author Wayne Wan (bestirwiny@gmail.com)
 * @version Revision: 1.00 Date: Sep 7, 2011
 */
public class PageController
{

    public static final int FIRST_PAGE  = 1;

    private int             RecordCount;             // 总的记录数
    private int             PageSize;                // 每页的记录数
    private int             PageCount   = FIRST_PAGE; // 总的页数
    private int             CurrentPage = FIRST_PAGE; // 当前第几页
    private String          DoAction    = "";
    private boolean         Enabled     = false;
    
    private String orderColumn = null;
	private SortMode orderMode      = SortMode.CLOSE;
    
	/**
     * @return Returns the doAction.
     */
    public String getDoAction()
    {
        return DoAction;
    }

    /**
     * @param doAction
     *            The doAction to set.
     */
    public void setDoAction(String doAction)
    {
        DoAction = doAction;
    }

    /**
     * @return Returns the currentPage.
     */
    public int getCurrentPage()
    {
        return CurrentPage;
    }

    /**
     * @param currentPage
     *            The currentPage to set.
     */
    public void setCurrentPage(int currentPage)
    {
        CurrentPage = currentPage;
    }

    /**
     * @return Returns the pageCount.
     */
    public int getPageCount()
    {
        return PageCount;
    }

    /**
     * @param pageCount
     *            The pageCount to set.
     */
    public void setPageCount(int pageCount)
    {
        PageCount = pageCount;

    }

    /**
     * @return Returns the pageSize.
     */
    public int getPageSize()
    {
        return PageSize;
    }

    /**
     * @param recordCount
     *            The recordCount to set.
     */
    public void setRecordCount(int recordCount)
    {
        RecordCount = recordCount;

        if(PageSize > 0){
        	//这里无需用到Math.ceil函数就能达到向上取整的目的
            //PageCount = (int)Math.ceil((RecordCount - 1) / PageSize) + 1;
            PageCount = (RecordCount - 1) / PageSize + 1;

            if(CurrentPage > PageCount){
            	CurrentPage = PageCount;
            }

            if(CurrentPage < FIRST_PAGE){
            	CurrentPage = FIRST_PAGE;
            }
        }
    }

    public int getRecordCount()
    {
        return RecordCount;
    }

    /**
     * @param pageSize
     *            The pageSize to set.
     */
    public void setPageSize(int pageSize)
    {
        PageSize = pageSize;
    }

    /**
     * 获取首页页码。
     * 
     * @return
     */
    public int getFirstPage()
    {
        return FIRST_PAGE;
    }

    /**
     * 获取下一页页码。
     * 
     * @return
     */
    public int getPrePage()
    {
        return (CurrentPage - 1 >= FIRST_PAGE) ? CurrentPage - 1 : 1;
    }

    /**
     * 获取上一页页码。
     * 
     * @return
     */
    public int getNextPage()
    {
        return (CurrentPage + 1 > PageCount) ? PageCount : CurrentPage + 1;
    }

    /**
     * 当前页是否为首页。
     * 
     * @return
     */
    public boolean isFirst()
    {
        return CurrentPage == FIRST_PAGE;
    }

    /**
     * 当前页是否为末页。
     * 
     * @return
     */
    public boolean isLast()
    {
        return CurrentPage == PageCount;
    }

    /**
     * 根据当前分页及页码，计算出目前的分页的首条记录。
     * 
     * @return
     */
    public long getStartRow()
    {
        return (CurrentPage - 1) * PageSize + 1;
    }

    public String getPageNavigation()
    {
        if(!this.Enabled){
        	return "";
        }
        String PageNavigation1;
        String PageNavigation2;
        String PageJumper;

        String strFirstPage;
        String strPrePage;
        String strNextPage;
        String strLastPage;

        // 分页信息部份1
        StringBuffer sbTmp = new StringBuffer();
        sbTmp.append("<span class='l12'>每页&nbsp;<input type='text' ");
        sbTmp.append("id='pageSize' name='pageSize' size='2' maxlength='2' class='text-box' value='");
        sbTmp.append(PageSize);
        sbTmp.append("' >&nbsp;条记录</span>\n");
        PageNavigation1 = sbTmp.toString();

        // 跳转页面信息；
        sbTmp = new StringBuffer();
        sbTmp.append("<span class='l12'>转到&nbsp;<input type='text' id='pageNo' name='pageNo' size='3' class='text-box' ");
        sbTmp.append("maxlength='3' value='");
        sbTmp.append(CurrentPage);
        sbTmp.append("'>&nbsp;页\n");
        sbTmp.append("<input type='button' class='hrefGoButton' id='go' value='GO' ");
        sbTmp.append("onClick=\"javaScript:PageCtrl.goPage(this,'");
        sbTmp.append(this.getCurrentPage());
        sbTmp.append("','','");
        sbTmp.append(this.getDoAction());
        sbTmp.append("')\"></span>\n");

        PageJumper = sbTmp.toString();

        // 分页信息部份2
        sbTmp = new StringBuffer();
        sbTmp.append("<input type='button' class='hrefButton' onclick=\"PageCtrl.queryData(this,'");
        sbTmp.append(FIRST_PAGE);
        sbTmp.append("','");
        sbTmp.append(this.getDoAction());
        sbTmp.append("')\" value='首页'>\n");
        strFirstPage = sbTmp.toString();

        sbTmp = new StringBuffer();
        sbTmp.append("<input type='button' class='hrefButton' onclick=\"PageCtrl.queryData(this,'");
        sbTmp.append(this.getPrePage());
        sbTmp.append("','");
        sbTmp.append(this.getDoAction());
        sbTmp.append("')\" value='上一页'>\n");
        strPrePage = sbTmp.toString();

        sbTmp = new StringBuffer();
        sbTmp.append("<input type='button' class='hrefButton' onclick=\"PageCtrl.queryData(this,'");
        sbTmp.append(this.getNextPage());
        sbTmp.append("','");
        sbTmp.append(this.getDoAction());
        sbTmp.append("')\" value='下一页'>\n");
        strNextPage = sbTmp.toString();

        sbTmp = new StringBuffer();
        sbTmp.append("<input type='button' class='hrefButton' onclick=\"PageCtrl.queryData(this,'");
        sbTmp.append(PageCount);
        sbTmp.append("','");
        sbTmp.append(this.getDoAction());
        sbTmp.append("')\" value='末页'>\n");
        strLastPage = sbTmp.toString();

        // 如果当前页不在第一页也不在最后一页
        sbTmp = new StringBuffer();
        if(CurrentPage > FIRST_PAGE && CurrentPage < PageCount)
        {
            sbTmp.append(strFirstPage);
            sbTmp.append(strPrePage);
            sbTmp.append(strNextPage);
            sbTmp.append(strLastPage);
        }
        else
            // 如果当前页是第一页
            if(CurrentPage == FIRST_PAGE)
            {
                if(CurrentPage != PageCount) // 如果当不是唯一页
                {
                    sbTmp.append(strNextPage);
                    sbTmp.append(strLastPage);
                }
            }
            else
            {
                sbTmp.append(strFirstPage);
                sbTmp.append(strPrePage);
            }
        PageNavigation2 = sbTmp.toString();

        return PageNavigation1 + PageNavigation2 + PageJumper;

    }

    public String getPagingHTML()
    {
        if(!this.Enabled){
        	return "";
        }
        String PageTitle;
        // 分页信息头
        StringBuffer sbTmp = new StringBuffer();
        sbTmp.append("&nbsp;共&nbsp;<span>");
        sbTmp.append(RecordCount);
        sbTmp.append("</span>&nbsp;条记录 ");
        sbTmp.append("当前页面是第&nbsp;<span>");
        sbTmp.append(CurrentPage);
        sbTmp.append("</span>&nbsp;页");
        sbTmp.append("<input type='hidden' name='currentPage' value='");
        sbTmp.append(CurrentPage);
        sbTmp.append("'> 共&nbsp;<span>");
        sbTmp.append(PageCount);
        sbTmp.append("</span>&nbsp;页");
        PageTitle = sbTmp.toString();
        return PageTitle;
    }

    /**
     * 
     * @return
     */
    public boolean isEnabled()
    {
        return Enabled;
    }

    /**
     * 
     * @param enabled
     */
    public void setEnabled(boolean enabled)
    {
        Enabled = enabled;
    }

	public int getPosition()
	{
		return PageSize*(CurrentPage-1);
	}
	
    private Object pagingObject; //temp object that can used for any purpose
    
    /**
     * get temp object that can used for any purpose
     * @param pagingObject
     */
    public Object getPagingObject() {
		return pagingObject;
	}

    /**
     * set temp object that will used for any purpose
     * @param pagingObject
     */
	public void setPagingObject(Object pagingObject) {
		this.pagingObject = pagingObject;
	}
	
	public String getOrderColumn() {
		return orderColumn;
	}

	/**
	 * "1" - asc, "0" - desc, "-1" - disable
	 * 
	 * @param orderColumn
	 */
	public void setOrderColumn(String orderColumn) {
		this.orderColumn = orderColumn;
	}

	/**
	 * "1" - asc, "0" - desc, "-1" - disable
	 * 
	 * @return
	 */
	public SortMode getOrderMode() {
		return orderMode;
	}

	public void setOrderMode(SortMode orderMode) {
		this.orderMode = orderMode;
	}
}