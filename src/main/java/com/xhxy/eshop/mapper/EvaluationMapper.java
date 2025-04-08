package com.xhxy.eshop.mapper;

import java.util.List;

import com.xhxy.eshop.entity.Evaluation;

public interface EvaluationMapper {
	// 查询某商品的全部评价evaluation
	public List<Evaluation> findByProductId(Integer productId);
}
