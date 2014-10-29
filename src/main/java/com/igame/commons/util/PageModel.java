package com.igame.commons.util;

import java.util.List;
/**
 * 分页载体
 * @author Allen
 *
 * @param <T>
 */
public class PageModel<T> {
	/**
	 * 总页数
	 */
	private int total;
	/**
	 * 传输的数据(包括查询出的结果集)
	 */
	private List<T> rows;
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public List<T> getRows() {
		return rows;
	}
	public void setRows(List<T> rows) {
		this.rows = rows;
	}
	
}
