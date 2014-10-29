package com.igame.commons.util;
/**
 * 
 * @author Allen
 *将每个线程的分页参数封装起来
 *
 */
public class SystemContext {
	private static ThreadLocal<Integer> offset=new ThreadLocal<Integer>();
	private static ThreadLocal<Integer> pageSize =new ThreadLocal<Integer>();
	public static int getOffset() {
		Integer os = offset.get();
		if(os == null){
			return 0;
		}
		return os;
	}
	public static void setOffset(int _offset) {
		offset.set(_offset);
	}
	public static void removeOffset(){
		offset.remove();
	}
	
	public static int getPageSize() {
		Integer ps = pageSize.get();
		if(ps == null){
			return Integer.MAX_VALUE;
		}
		return ps;
	}
	public static void setPageSize(int _pageSize) {
		pageSize.set(_pageSize);
	}
	public static void removePageSize(){
		pageSize.remove();
	}
}
