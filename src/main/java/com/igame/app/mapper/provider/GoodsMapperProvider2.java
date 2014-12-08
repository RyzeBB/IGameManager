package com.igame.app.mapper.provider;

import java.util.List;
import java.util.Map;

public class GoodsMapperProvider2 {
	public String delByIds(Map<String,List<Long>> map) {
		List<Long> ids = map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("delete FROM t_goods where id in(");
		int i = 0;
		for (Long id : ids) {
			i++;
			sb.append(id);
			if (i < ids.size()) {
				sb.append(",");
			}
		}
		sb.append(")");
		return sb.toString();
	}

}
