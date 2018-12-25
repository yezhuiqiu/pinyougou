package com.pinyougou.manager.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.serivce.TypeTemplateService;

import entity.ResponseResult;

@RestController
@RequestMapping("/typeTemplate")
public class TypeTemplateController {
	
	@Reference
	private TypeTemplateService service;
	
	@RequestMapping("/findOne")
	public TbTypeTemplate findOne(Long id) {
		return service.findById(id);
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ResponseResult search(int page,int rows,@RequestBody TbTypeTemplate template) {
		PageInfo<TbTypeTemplate> search = service.search(page, rows, template);
		return new ResponseResult().build(1, null, search.getList()).setTotal(search.getTotal());
	}
	
	@RequestMapping("/findAll")
	public ResponseResult findAll(int page,int rows) {
		return search(page, rows, null);
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseResult add(@RequestBody TbTypeTemplate template) {
		try {
			service.insert(template);
			return ResponseResult.build(true, "增加成功");
		}catch(Exception e) {
			return ResponseResult.build(false, "增加失败");
		}
	}
	
	@RequestMapping("/dele")
	public ResponseResult delete(Long[]ids) {
		
		try {
			for (Long id : ids) {
				service.delete(id);
			}
			
			return ResponseResult.build(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			
			return ResponseResult.build(false, "删除失败");
		}
	}
	

}
