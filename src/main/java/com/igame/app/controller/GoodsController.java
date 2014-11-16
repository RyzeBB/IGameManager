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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.igame.app.vo.GoodsVO;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

	private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

//	@RequestMapping(value = "/createGoods", method = RequestMethod.POST,produces="application/json")
//	@ResponseBody
//	public String createGoods(@RequestBody GoodsVO goodsVO) {
//		log.debug("====== +++++++++" );
////		System.out.println(goodsVO.toJSONString());
//		System.out.println(JSON.toJSON(goodsVO));
//		return "hello";
//	}
	
	@RequestMapping(value = "/createGoods", method = RequestMethod.POST)
	public String createGoods(@ModelAttribute("ff") GoodsVO goodsVO) {
		log.debug("====== +++++++++" );
		String json = JSON.toJSONString(goodsVO);
		System.out.println(json);
		return "goods/success";
	}

}
