package com.igame.app.vo;

/**
 * 根据分类查询产品列表
 * 
 * @author Allen
 *
 */
public class TypeListRequest extends RequestVO {
	private int type;
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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
