package com.xhxy.eshop.mapper;

import java.util.List;

import com.xhxy.eshop.entity.Picture;

public interface PictureMapper {
	public List<Picture> findByProductId(Integer productId);
}
