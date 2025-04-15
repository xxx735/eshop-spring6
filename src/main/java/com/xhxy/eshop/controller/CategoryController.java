package com.xhxy.eshop.controller;

import java.util.List;

import jakarta.annotation.Resource;

import com.xhxy.eshop.entity.Category;
import com.xhxy.eshop.entity.Product;
import com.xhxy.eshop.interceptor.Page;
import com.xhxy.eshop.service.CategoryService;
import com.xhxy.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 分类 控制器类
 */
@Controller
@RequestMapping("/category")
public class CategoryController  {

	@Resource
	private CategoryService categoryService ;
	@Resource
	private ProductService productService ;

	// 默认为展示全部分类的商品(调用下面的all()方法)

	
	// 1.展示全部分类的商品
	@GetMapping("/all")
	public String all(@RequestParam(required = false) Integer pageIndex,
					  @RequestParam(required = false) Integer pageSize,
					  Model model) {
		
		// 1.获得请求参数

		if(pageIndex ==null) {	// 有pageIndex参数
			pageIndex = 1;
		}
			
		if(pageSize == null) {	// 有pageSize参数
			pageSize = 6;
		}
		
		Page page = new Page(pageSize,pageIndex);	// 每页显示pageSize条，当前为第pageIndex页
		
		// 2.获取全部的顶层分类
		List<Category> topCategoryList = categoryService.findTopCategory();
				
		// 3.获取所有的product
//		List<Product> productList = productService.findAll();			// 未分页
		List<Product> productList = productService.findAllPage(page);	// 分页
				
		// 4.设置属性
		model.addAttribute("page", page);			// 将page传送到前端
		model.addAttribute("topCategoryList", topCategoryList);
		model.addAttribute("productList", productList);
					
		return "category-all";
	}
	
	// 2.展示指定id分类的商品
	@GetMapping("/list/{id}")
	public String list(@PathVariable(required = true) Integer id,
					   @RequestParam(required = false) Integer pageIndex,
					   @RequestParam(required = false) Integer pageSize,
					   Model model) {
		
		// 获取id对应的分类
		Category category = categoryService.findById(id);//当前的分类
		
		// 2.获得请求参数：分页
		if(pageIndex ==null ) {	// 有pageIndex参数
			pageIndex = 1;
		}

		if(pageSize == null ) {	// 有pageSize参数
			pageSize = 6;
		}
		
		Page page = new Page(pageSize,pageIndex);	// 每页显示pageSize条，当前为第pageIndex页
		
		// 3.获取某个id号的Category里所有的product
//		List<Product> productList = productService.findListByCategoryId(id);	// 不分页
		List<Product> productList = productService.findListByCategoryIdPage(id,page);	// 分页
		
		
		// 4.获取全部的顶层分类：用于左侧的分类列表
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 5.设置属性
		model.addAttribute("page", page);			// 将page传送到前端
		model.addAttribute("id",id);				// 请求参数id，再传回去给分页栏
		model.addAttribute("topCategoryList", topCategoryList);
		model.addAttribute("category", category);
		model.addAttribute("productList", productList);
			
		return "category-list";
	}
	
	// 3.依据搜索查询到的商品
	@PostMapping("/search")
	public String search(String name,String brief,String detail,
						 @RequestParam(required = false) Integer pageIndex,
						 @RequestParam(required = false) Integer pageSize,
						 Model model) {
		
		// 1.获得请求参数：分页
		if(pageIndex ==null ) {	// 有pageIndex参数
			pageIndex = 1;
		}

		if(pageSize == null ) {	// 有pageSize参数
			pageSize = 6;
		}

		Page page = new Page(pageSize,pageIndex);	// 每页显示pageSize条，当前为第pageIndex页


		// 2. 获取请求参数：搜索词
		if (name.isBlank()) {
			name = null;
		}
		if (brief.isBlank()) {
			brief = null;
		}
		if (detail.isBlank()) {
			detail = null;
		}
		// 3.按搜索词进行product搜索
//		List<Product> productList = productService.findByKeywords(name,brief,detail);// 不分页
		List<Product> productList = productService.findByKeywordsPage(name,brief,detail,page);// 分页
		
		// 4.获取全部的顶层分类：用于左侧的分类列表
		List<Category> topCategoryList = categoryService.findTopCategory();
		
		// 5.设置属性
		model.addAttribute("page", page);					// 将page传送到前端
		model.addAttribute("name",name);					// 请求参数name，再传回去给分页栏
		model.addAttribute("brief",brief);				// 请求参数brief，再传回去给分页栏
		model.addAttribute("detail",detail);				// 请求参数detail，再传回去给分页栏
		model.addAttribute("topCategoryList", topCategoryList);
		model.addAttribute("productList", productList);
		
		// 6.请求转发
		return "category-search";
	}
}
