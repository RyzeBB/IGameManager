package com.igame.security.manager;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.igame.app.exception.AppException;
import com.igame.app.vo.ResponseVO;
import com.igame.commons.util.BusinessException;

/**
 * 全局异常处理
 * 
 * @author Allen
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);

	@Override
	protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
		logger.error("doResolveException", ex);
		// Expose ModelAndView for chosen error view.
		String viewName = determineViewName(ex, request);
		if (viewName != null) {
			// JSP格式返回
			if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request.getHeader("X-Requested-With") != null && request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") > -1))) {
				// 如果不是异步请求
				// Apply HTTP status code for error views, if specified.
				// Only apply it if we're processing a top-level request.
				Integer statusCode = determineStatusCode(request, viewName);
				if (statusCode != null) {
					applyStatusCodeIfPossible(request, response, statusCode);
				}
				return getModelAndView(viewName, ex, request);
			} else {// 异步请求
				try {
					response.setContentType("text/html;charset=UTF-8");
					PrintWriter writer = response.getWriter();
					if (ex instanceof BindException) {
						writer.write("参数异常...");
					} else {
						writer.write(ex.getMessage());
					}
					writer.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;

			}
		} else {// ajax请求方式
			// JSP格式返回
			// if (!((request.getHeader("accept") != null &&
			// request.getHeader("accept").indexOf("application/json") > -1) ||
			// (request.getHeader("X-Requested-With") != null &&
			// request.getHeader("X-Requested-With").indexOf("XMLHttpRequest") >
			// -1))) {
			//
			// Map<String, Object> model = new HashMap<String, Object>();
			// model.put("ex", ex);
			// // 根据不同错误转向不同页面
			// if (ex instanceof BusinessException) {
			// return new ModelAndView("commons/error/errorpage", model);
			// } else if (ex instanceof SystemException) {
			// return new ModelAndView("commons/error/500", model);
			// } else {
			// return new ModelAndView("commons/error/errorpage", model);
			// }
			// } else {// JSON格式返回
			try {
				response.setContentType("application/json;charset=UTF-8");
				PrintWriter writer = response.getWriter();
				if (ex instanceof AppException) {// app请求异常处理
					ResponseVO responseVO = new ResponseVO();
					responseVO.setResultCode(ResponseVO.CODE_ERROR);
					responseVO.setActionCode(((AppException) ex).getActionCode());
					responseVO.setErrorInfo(ex.getMessage());
					writer.write(JSON.toJSONString(responseVO));
				} else if (ex instanceof BusinessException) {
					JSONObject object = new JSONObject();
					object.put("err", ex.getMessage());
					writer.write(object.toJSONString());
				} else {
					JSONObject object = new JSONObject();
					object.put("err", ex.getMessage());
					writer.write(object.toJSONString());
				}
				writer.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return null;

			// }
		}
	}
}