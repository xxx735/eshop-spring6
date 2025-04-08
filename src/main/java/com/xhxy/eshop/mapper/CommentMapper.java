package com.xhxy.eshop.mapper;

import java.util.List;

import com.xhxy.eshop.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface CommentMapper {
	// 查询某个评论comment
	public Comment findById(Integer id);
	
	// 查询某篇推荐文章的全部评论comment
	public List<Comment> findByBlogId(@Param("blogId") Integer blogId);
	
	// 保存某用户对某文章的评论 comment
	public int save(Comment comment);
}
