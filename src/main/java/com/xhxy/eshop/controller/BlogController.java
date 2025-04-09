package com.xhxy.eshop.controller;

import java.util.List;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.xhxy.eshop.entity.Blog;
import com.xhxy.eshop.entity.Comment;
import com.xhxy.eshop.interceptor.Page;
import com.xhxy.eshop.service.BlogService;
import com.xhxy.eshop.service.CommentService;
import com.xhxy.eshop.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/blog")
public class BlogController {

	@Resource
	private BlogService blogService ;
	@Resource
	private UserService userService ;
	@Resource
	private CommentService commentService ;
	

	@GetMapping("/list")
	public String list(Integer pageIndex, Integer pageSize, Model model) {
		if(pageIndex ==null) {	// 没有pageIndex参数
			pageIndex=1;
		}

		if(pageSize == null ) {	// 没有pageSize参数
			pageSize=6;
		}
		
		Page page = new Page(pageSize,pageIndex);	// 每页显示pageSize条，当前为第pageIndex页
		
		List<Blog> blogList = blogService.findByPage(page);
		
		model.addAttribute("page", page);			// 将page传送到前端
		model.addAttribute("blogList", blogList);
		return "blog-list";
	}
	
	// 文章详情
	@GetMapping("/detail/{id}")
	public String detail(@PathVariable(required = true) Integer id, Model model) {
		Blog blog = blogService.findById(id);
		
		model.addAttribute("blog", blog);
		return "blog-detail";
	}
	// 提交评论
	@PostMapping("/saveComment")
	public String saveComment(@RequestParam(required = true) Integer blogId,
							  @RequestParam(required = true) Integer userId,
							  @RequestParam(required = true) String content) {
		
		Comment comment = new Comment();
		comment.setBlog(blogService.findById(blogId));
		comment.setUser(userService.findById(userId));
		comment.setContent(content);
		comment.setCreateTime(new java.util.Date());
		
		commentService.save(comment);
		
		return "redirect:/blog/detail/"+blogId;	// 使用重定向
	}
}
