package com.igame.app.vo;

/**
 * 查询天天惠产品列表
 * 
 * @author Allen
 *
 */
public class SaleRequest extends RequestVO {
	private int pageCount;
	private int pageNum;

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

}
