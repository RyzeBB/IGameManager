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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.igame.app.entity.GoodsEntity;
import com.igame.app.entity.GoodsTypeEntity;
import com.igame.app.entity.OrderEntity;
import com.igame.app.exception.AppException;
import com.igame.app.service.BuyerService;
import com.igame.app.service.GoodsService;
import com.igame.app.vo.GoodsVO;
import com.igame.app.vo.OrderResponeVO;
import com.igame.app.vo.RequestVO;
import com.igame.app.vo.ResponseVO;
import com.igame.commons.util.BusinessException;
import com.igame.security.entity.SecUser;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private BuyerService buyerService;

	private static final Logger log = LoggerFactory.getLogger(GoodsController.class);

	// @RequestMapping(value = "/createGoods", method =
	// RequestMethod.POST,produces="application/json")
	// @ResponseBody
	// public String createGoods(@RequestBody GoodsVO goodsVO) {
	// log.debug("====== +++++++++" );
	// // System.out.println(goodsVO.toJSONString());
	// System.out.println(JSON.toJSON(goodsVO));
	// return "hello";
	// }

	@RequestMapping(value = "/createGoods", method = RequestMethod.POST)
	public String createGoods(@ModelAttribute("ff") GoodsVO goodsVO, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		log.debug("modifyGoods --> appid:{}", appid);
		goodsService.addGoods(goodsVO, appid);
		return "goods/success";
	}

	@RequestMapping(value = "/modify", method = RequestMethod.POST)
	public String modifyGoods(@ModelAttribute("ff") GoodsVO goodsVO, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		log.debug("modifyGoods --> appid:{}", appid);
		goodsService.modifyGoods(goodsVO, appid);
		return "goods/success";
	}

	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject delGoods(@RequestBody List<Long> ids, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		log.debug("modifyGoods --> appid:{},ids:{}", appid, ids);
		goodsService.delete(appid, ids);
		JSONObject object = new JSONObject();
		object.put("success", true);
		object.put("msg", "成功删除 " + ids.size() + " 条记录  ");
		return object;
	}

	/**
	 * 获取商品分类
	 * 
	 * @param requestVO
	 * @return
	 */
	@RequestMapping(value = "/type", method = RequestMethod.GET)
	@ResponseBody
	public List<GoodsTypeEntity> getGoodsType(HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		return goodsService.getGoodsType(appid);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject save(@RequestParam("name") String name, @RequestParam("pic_url") String pic_url, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		goodsService.addType(appid, name, pic_url);
		JSONObject object = new JSONObject();
		object.put("ret", 0);
		return object;
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject update(@ModelAttribute("fm") GoodsTypeEntity goodsTypeEntity, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		goodsTypeEntity.setAppid(appid);
		goodsService.modifyType(goodsTypeEntity);
		JSONObject object = new JSONObject();
		object.put("ret", 0);
		return object;
	}

	@RequestMapping(value = "/delType", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject deleteType(@RequestBody List<Long> ids, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		goodsService.deleteType(appid, ids);
		JSONObject object = new JSONObject();
		object.put("ret", 0);
		return object;
	}

	/**
	 * 分页查询商品
	 * 
	 * @param saleRequest
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> getGoodsByPage(@RequestParam(required = false, value = "rows", defaultValue = "10") int pageSize,
			@RequestParam(required = false, value = "page", defaultValue = "1") int pageNum, HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		List<GoodsEntity> goods = goodsService.getGoodsByPage(appid, pageNum, pageSize);
		Map<String, Object> object = new HashMap<String, Object>(2);
		object.put("total", goodsService.listSize(appid));
		object.put("rows", goods);
		return object;
	}

	@RequestMapping(value = "/listone/{id}", method = RequestMethod.GET)
	public String getGoodsById(@PathVariable long id, HttpSession session, Model model) {
		// long appid = ((SecUser) session.getAttribute("user")).getAppid();
		GoodsVO goods = goodsService.getGoodsById(id);
		model.addAttribute("goods", goods);
		return "goods/modify";
	}

	/**
	 * 获取订单信息
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "order", method = RequestMethod.POST)
	@ResponseBody
	public List<OrderEntity> getOrders(HttpSession session) {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		List<OrderEntity> orderEntities = buyerService.getOrderByApp(appid);
		return orderEntities;
	}

	@RequestMapping(value = "morder", method = RequestMethod.POST)
	@ResponseBody
	public JSONObject modifyOrder(@RequestParam("id") long id, @RequestParam("state") int state, @RequestParam("descrip") String descrip, HttpSession session) throws BusinessException {
		long appid = ((SecUser) session.getAttribute("user")).getAppid();
		buyerService.modifyOrder(id, appid, state, descrip);
		JSONObject object = new JSONObject(1);
		object.put("state", "success");
		return object;
	}

}
