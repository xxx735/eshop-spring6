package com.xhxy.eshop.service.Impl.mybatis;

import java.util.List;

import com.xhxy.eshop.entity.Evaluation;
import com.xhxy.eshop.mapper.EvaluationMapper;
import com.xhxy.eshop.service.EvaluationService;
import com.xhxy.eshop.util.MybatisUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("evaluationService")
public class EvaluationServiceImpl implements EvaluationService {

	@Resource
	private EvaluationMapper evaluationMapper ;
	
	@Override
	public List<Evaluation> findByProductId(Integer productId) {
		return evaluationMapper.findByProductId(productId);
	}

}
