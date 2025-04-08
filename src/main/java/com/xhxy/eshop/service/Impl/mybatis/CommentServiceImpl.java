package com.xhxy.eshop.service.Impl.mybatis;

import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.mapper.CommentMapper;
import com.xhxy.eshop.service.CommentService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service("commentService")
public class CommentServiceImpl implements CommentService {

	@Resource
	private CommentMapper commentMapper ;
	
	@Override
	public Integer save(Comment comment) {
		return commentMapper.save(comment);
	}

}
