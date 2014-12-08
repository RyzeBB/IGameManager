/*
 * The MIT License
 *
 * Copyright 2013 jdmr.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.igame.app.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.igame.app.entity.CouponEntity;
import com.igame.app.service.CouponService;
import com.igame.security.entity.SecUser;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/coupon")
public class CouponController {
	@Autowired
	private CouponService couponService;

	private static final Logger log = LoggerFactory.getLogger(CouponController.class);

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject getCoupon(HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		log.debug("CouponController --> CouponEntity appid:{}", appid);
		List<CouponEntity> goods = couponService.getCoupon(appid);

		JSONObject object = new JSONObject(2);
		if (goods != null && !goods.isEmpty()) {
			object.put("total", goods.size());
		}
		object.put("rows", goods);
		return object;
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject addCoupon(@ModelAttribute("fm") CouponEntity couponEntity, BindingResult result, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		log.debug("CouponController --> addCoupon {}", JSON.toJSONString(couponEntity));
		couponService.addCoupon(appid,couponEntity);
		JSONObject jsonObject = new JSONObject(1);
		jsonObject.put("ret", 0);
		return jsonObject;
	}
	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject modifyCoupon(@ModelAttribute("fm") CouponEntity couponEntity, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		log.debug("CouponController --> addCoupon {}", JSON.toJSONString(couponEntity));
		couponService.modifyCoupon(appid,couponEntity);
		JSONObject jsonObject = new JSONObject(1);
		jsonObject.put("ret", 0);
		return jsonObject;
	}
}
