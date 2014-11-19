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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.igame.app.entity.GoodsEntity;
import com.igame.app.service.GoodsService;
import com.igame.app.vo.GoodsVO;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/shop")
public class AppController {
	@Autowired
	private GoodsService goodsService;

	private static final Logger log = LoggerFactory.getLogger(AppController.class);

	// @RequestMapping(value = "/createGoods", method =
	// RequestMethod.POST,produces="application/json")
	// @ResponseBody
	// public String createGoods(@RequestBody GoodsVO goodsVO) {
	// log.debug("====== +++++++++" );
	// // System.out.println(goodsVO.toJSONString());
	// System.out.println(JSON.toJSON(goodsVO));
	// return "hello";
	// }

	@RequestMapping(value = "/hot", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject createGoods() {
		List<GoodsEntity> goods = goodsService.getGoodsForHot(1);
		JSONObject object = new JSONObject();
		object.put("productList", goods);
		return object;
	}

}
