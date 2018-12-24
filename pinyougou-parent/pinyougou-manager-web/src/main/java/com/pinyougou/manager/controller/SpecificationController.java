package com.pinyougou.manager.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.group.Specification;
import com.pinyougou.sellergoods.serivce.SpecificationService;

import entity.ResponseResult;

@RestController
@RequestMapping("/specification")
public class SpecificationController {
	
	@Reference
	private SpecificationService service;
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public ResponseResult search(int page,int rows,@RequestBody TbSpecification specification) {
		
		PageInfo<TbSpecification> searchByVo = service.searchByVo(specification, page, rows);
		
		
		return ResponseResult.build(1, null, searchByVo.getList()).setTotal(searchByVo.getTotal());
		
	}
	@RequestMapping(value="/findPage",method=RequestMethod.GET)
	public ResponseResult search(int page,int rows) {
		
		return search(page, rows);
		
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseResult add(@RequestBody Specification specification) {
		try {
		service.add(specification);
		return ResponseResult.build(true, "插入数据成功");
		}catch(Exception e) {
			
			return ResponseResult.build(false, "插入数据失败");
		}
	}
	
	@RequestMapping("/findOne")
	public Specification findOne(Long id) {
		return service.findOne(id);
	}
	
	@RequestMapping(value="/update",method=RequestMethod.POST)
	public ResponseResult update(@RequestBody Specification specification) {
	
	
		try {
			service.update(specification);
			return ResponseResult.build(true, "更新数据成功");
			}catch(Exception e) {
				
				return ResponseResult.build(false, "更新数据失败");
			}
	}
	
	@RequestMapping("/delete")
	public ResponseResult delete(Long[] ids) {

		try {
			for (Long id : ids) {
				service.delete(id);
			}
			
			
			
			return ResponseResult.build(true, "删除数据成功");
			}catch(Exception e) {
				
				return ResponseResult.build(false, "删除数据失败");
			}
		
	}

}
