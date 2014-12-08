package com.igame.commons.util;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Description：
 * 
 * @date 2014年3月20日 上午10:31:44
 * @version igame
 * @author Allen
 * @email allen.ime@gmail.com
 */

public class IDGenerator {

	private static final String format = "1%13d%03d";;
	private static final int max = 1000;
	private static final AtomicInteger ATOMIC_INTEGER = new AtomicInteger(0);

	private static final long nextLong() {
		while (true) {
			int current = ATOMIC_INTEGER.get();
			int next = current + 1;
			if (next >= max) {
				next = 1;
			}
			if (ATOMIC_INTEGER.compareAndSet(current, next))
				return Long.parseLong(String.format(format, System.currentTimeMillis(), current));
		}
	}

	public static final long getKey() {
		return nextLong();
	}

}
