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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.imgscalr.Scalr;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.igame.app.entity.Image;
import com.igame.app.service.ImageService;
import com.igame.commons.util.IDGenerator;
import com.igame.security.entity.SecUser;

/**
 *
 * @author jdmr
 */
@Controller
@RequestMapping
public class Image2Controller {

	private static final Logger log = LoggerFactory.getLogger(Image2Controller.class);
	@Autowired
	private ImageService imageService;
	// private String fileUploadDirectory = "/data/cdn/app-65655558";
//	private String fileUploadDirectory = "D:/temp";
	private String fileUploadDirectory = "/data/cdn";

	@RequestMapping(value = "/upload", method = RequestMethod.GET)
	public @ResponseBody Map list(@RequestParam("pageNumber") int pageNumber, @RequestParam("pageSize") int pageSize) {
		log.debug("uploadGet called");
		long appid = 1;
		List<Image> list = imageService.list(appid, pageNumber, pageSize);
		for (Image image : list) {
			image.setUrl("app-" + appid + "/" + image.getNewFilename());
			image.setThumbnailUrl("app-" + appid + "/" + image.getThumbnailFilename());
			image.setDeleteUrl("delete/" + image.getId());
			image.setDeleteType("DELETE");
		}
		long count = imageService.getSize(appid);
		Map<String, Object> files = new HashMap<>();
		files.put("files", list);
		files.put("count", count);
		log.debug("Returning: {}", files);
		return files;
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody Map upload(MultipartHttpServletRequest request, HttpServletResponse response) {
		// HttpSession
		SecUser user = (SecUser) request.getSession().getAttribute("user");
		long appid = user.getAppid();
		log.debug("uploadPost called appid:{}" + appid);
		Iterator<String> itr = request.getFileNames();
		MultipartFile mpf;
		List<Image> list = new LinkedList<>();

		while (itr.hasNext()) {
			mpf = request.getFile(itr.next());
			log.debug("Uploading {}", mpf.getOriginalFilename());

			long id = IDGenerator.getKey();
			// String newFilenameBase = String.valueOf(id);
			String originalFileExtension = mpf.getOriginalFilename().substring(mpf.getOriginalFilename().lastIndexOf("."));
			String newFilename = id + originalFileExtension;
			String storageDirectory = fileUploadDirectory;
			String contentType = mpf.getContentType();

			File newFile = new File(storageDirectory + "/" + "app-" + appid + "/" + newFilename);

			if (!newFile.getParentFile().exists()) {
				log.debug("图片文件存放目录 {}"+newFile.getParentFile().getPath());
				newFile.getParentFile().mkdirs();
			}

			try {
				mpf.transferTo(newFile);

				BufferedImage thumbnail = Scalr.resize(ImageIO.read(newFile), 150);
				String thumbnailFilename = id + "-thumbnail.png";
				File thumbnailFile = new File(storageDirectory + "/" + "app-" + appid + "/" + thumbnailFilename);
				ImageIO.write(thumbnail, "png", thumbnailFile);

				Image image = new Image();
				image.setId(id);
				image.setAppid(appid);
				image.setName(mpf.getOriginalFilename());
				image.setThumbnailFilename(thumbnailFilename);
				image.setNewFilename(newFilename);
				image.setContentType(contentType);
				image.setSize(mpf.getSize());
				image.setThumbnailSize(thumbnailFile.length());
				image = imageService.create(image);

				image.setUrl("app-" + appid + "/" +newFilename);
				image.setThumbnailUrl("app-" + appid + "/" + thumbnailFilename);
				image.setDeleteUrl("delete/" + image.getId());
				image.setDeleteType("DELETE");

				list.add(image);

			} catch (IOException e) {
				log.error("Could not upload file " + mpf.getOriginalFilename(), e);
			}

		}

		Map<String, Object> files = new HashMap<>();
		files.put("files", list);
		return files;
	}

	@RequestMapping(value = "/app-{appid:\\d+}/{id:\\d+}.{type}", method = RequestMethod.GET)
	public void picture(HttpServletResponse response, @PathVariable("appid") Long appid, @PathVariable("id") long id) {
		log.debug("picture --> appid:{} , id:{}", appid, id);
		Image image = imageService.get(id);
		File imageFile = new File(fileUploadDirectory + "/" + "app-" + appid + "/" + image.getNewFilename());
		response.setContentType(image.getContentType());
		response.setContentLength(image.getSize().intValue());
		try {
			InputStream is = new FileInputStream(imageFile);
			IOUtils.copy(is, response.getOutputStream());
		} catch (IOException e) {
			log.error("Could not show picture " + id, e);
		}
	}

	@RequestMapping(value = "/app-{appid:\\d+}/{id:\\d+}-thumbnail.{type}", method = RequestMethod.GET)
	public void thumbnail(HttpServletResponse response, @PathVariable("appid") Long appid, @PathVariable("id") Long id) {
		log.debug("thumbnail {}", id);
		Image image = imageService.get(id);
		System.out.println("======= "+fileUploadDirectory + "/" + "app-" + appid + "/" + image.getThumbnailFilename());
		File imageFile = new File(fileUploadDirectory + "/" + "app-" + appid + "/" + image.getThumbnailFilename());
		response.setContentType(image.getContentType());
		response.setContentLength(image.getThumbnailSize().intValue());
		try {
			InputStream is = new FileInputStream(imageFile);
			IOUtils.copy(is, response.getOutputStream());
		} catch (IOException e) {
			log.error("Could not show thumbnail " + id, e);
		}
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public @ResponseBody List delete(@PathVariable Long id) {
		int appid = 1;
		Image image = imageService.get(id);
		File imageFile = new File(fileUploadDirectory + "/app-" + appid + image.getNewFilename());
		imageFile.delete();
		File thumbnailFile = new File(fileUploadDirectory + "/app-" + appid + image.getThumbnailFilename());
		thumbnailFile.delete();
		imageService.delete(id);
		List<Map<String, Object>> results = new ArrayList<>();
		Map<String, Object> success = new HashMap<>();
		success.put("success", true);
		results.add(success);
		return results;
	}
}
