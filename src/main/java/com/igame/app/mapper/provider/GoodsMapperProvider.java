package com.igame.app.mapper.provider;

import java.util.List;
import java.util.Map;

public class GoodsMapperProvider {
	public String listByIds(Map map) {
		List<Long> ids = (List<Long>) map.get("list");
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT id,appid,type,name,introduce,price,offer,unit,mulVa1Json,icon,titlePicJson,detailePicJson,paramsJson,address,stock,saleCount,shippingType,shippingCost,disStyle,serviecType,serviecTel FROM t_goods where id in(");
		int i = 0;
		for (long id : ids) {
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
