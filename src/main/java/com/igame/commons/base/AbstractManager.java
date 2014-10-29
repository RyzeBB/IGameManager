package com.igame.commons.base;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class AbstractManager<T> {
	protected SqlSessionTemplate sessionTemplate;

	@Resource
	public void setSessionTemplate(SqlSessionTemplate sessionTemplate) {
		this.sessionTemplate = sessionTemplate;
	}
//
//	/**
//	 * 只带一个hql条件的查询且hql语句无参数
//	 * 
//	 * @param hql
//	 * @return
//	 */
//	public PageModel<T> searchPaginated(String id) {
//		return searchPaginated(id, null);
//	}
//
//	/**
//	 * 查询的条件为hql但参数的语句 查询起始的位置、每页显示的数目从SystemContext类中取得
//	 * 
//	 * @param hql
//	 * @param params
//	 * @return
//	 */
//	public PageModel<T> searchPaginated(String id, Map<String, Object> params) {
//		int offset = SystemContext.getOffset();
//		int pageSize = SystemContext.getPageSize();
//		return searchPaginated(id, params, (offset - 1) * pageSize, pageSize);
//	}
//
//	/**
//	 * 查询的条件为无参数的hql语句、查询起始的位置、每页显示的数目
//	 * 
//	 * @param hql
//	 * @param offset
//	 * @param pagesize
//	 * @return
//	 */
//	public PageModel<T> searchPaginated(String id, int offset, int pagesize) {
//		return searchPaginated(id, null, offset, pagesize);
//	}
//
//	/**
//	 * 分页查询的业务逻辑方法
//	 * 
//	 * @param hql
//	 * @param params
//	 * @param offset
//	 * @param pageSize
//	 * @return
//	 */
//	public PageModel<T> searchPaginated(String id, Map<String, Object> params, int offset, int pageSize) {
//		String countId = id + "-count";
//		int total = sessionTemplate.selectOne(countId);
//		if (params == null) {
//			params = new HashMap<String, Object>();
//		}
//		params.put("offset", offset);
//		params.put("pageSize", pageSize);
//		List<T> datas = sessionTemplate.selectList(id, params);
//		PageModel<T> pageModel = new PageModel<T>();
//		pageModel.setTotal(total);
//		pageModel.setRows(datas);
//		return pageModel;
//	}

}
