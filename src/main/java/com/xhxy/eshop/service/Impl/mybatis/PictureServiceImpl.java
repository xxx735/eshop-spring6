package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.entity.Picture;
import com.xhxy.eshop.mapper.FaqMapper;
import com.xhxy.eshop.mapper.PictureMapper;
import com.xhxy.eshop.service.FaqService;
import com.xhxy.eshop.service.PictureService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;


@Service("pictureService")
public class PictureServiceImpl implements PictureService {

	@Resource
	private PictureMapper pictureMapper ;
	
	@Override
	public List<Picture> findByProductId(Integer productId) {
		return pictureMapper.findByProductId(productId);
	}

}
