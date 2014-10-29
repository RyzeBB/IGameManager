package com.igame.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.igame.commons.util.SystemContext;

public class PageFilter implements Filter {
	public static final Logger LOGGER = LoggerFactory.getLogger(PageFilter.class);
	public static final int PAGE_SIZE_MAX = 18;// 默认每页显示多少行

	public void destroy() {

	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		SystemContext.setOffset(getOffset(httpRequest));
		SystemContext.setPageSize(getPageSize(httpRequest));

		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			LOGGER.error("",e);
		} finally {
			SystemContext.removeOffset();
			SystemContext.removePageSize();
		}
	}

	public void init(FilterConfig arg0) throws ServletException {

	}

	private int getOffset(HttpServletRequest httpRequest) {
		int offset = 1;
		try {
			offset = Integer.parseInt(httpRequest.getParameter("pager.offset"));
			System.err.println("offset =========" + offset);
		} catch (Exception ingore) {
		}
		httpRequest.setAttribute("offset", offset);
		return offset;
	}

	private int getPageSize(HttpServletRequest httpRequest) {
		String pageSize = httpRequest.getParameter("pagesize");// 得到用户自己的每页显示的行数
		Integer ps = PAGE_SIZE_MAX;
		if (pageSize != null) {
			try {
				ps = Integer.parseInt(pageSize);
			} catch (Exception ignore) {

			}
		}
		httpRequest.getSession().setAttribute("pagesize", ps);
		return ps;
	}
}
