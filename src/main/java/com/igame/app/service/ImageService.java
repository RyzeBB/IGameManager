package com.igame.app.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.igame.app.entity.Image;
import com.igame.app.mapper.ImageMapper;

@Service
public class ImageService {
	private ImageMapper imageMapper;

	@Resource
	public void setImageMapper(ImageMapper imageMapper) {
		this.imageMapper = imageMapper;
	}

	public List<Image> list(long appid, int pageNumber, int pageSize) {
		int start = (pageNumber - 1) * pageSize;
		int end = pageNumber * pageSize;
		return imageMapper.list(appid, start, end);
	}

	public Image create(Image image) {
		imageMapper.create(image);
		return image;
	}

	public long getSize(long appid) {
		return imageMapper.getSize(appid);
	}

	public Image get(Long id) {
		return imageMapper.get(id);
	}

	public void delete(long id) {
		imageMapper.delete(id);
	}

}
