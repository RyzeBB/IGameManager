package com.igame.commons.dialect;

public class MySQL5Dialect implements Dialect {
	@Override
	public String getPaginationSql(String sql, int offset, int limit) {
		return sql + " limit " + offset + "," + limit;
	}
}
