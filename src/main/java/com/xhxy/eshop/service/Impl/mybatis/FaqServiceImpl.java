package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Faq;
import com.xhxy.eshop.mapper.FaqMapper;
import com.xhxy.eshop.service.FaqService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("faqService")
public class FaqServiceImpl implements FaqService {

	@Resource
	private FaqMapper faqMapper ;
	
	@Override
	public List<Faq> findAll(){
		return faqMapper.findAll();
	}

}
