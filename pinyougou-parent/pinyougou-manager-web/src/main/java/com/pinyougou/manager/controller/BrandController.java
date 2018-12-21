package com.pinyougou.manager.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.serivce.BrandService;
import com.pinyougou.vo.BrandVo;

import entity.PageResult;
import entity.Result;

@RestController
@RequestMapping("/brand")
public class BrandController {
	
	private String name;
	
	@Reference
	private BrandService brandService;
	
	@RequestMapping("/findAll")
	public List<TbBrand> findAll(){
		
		return brandService.findAll();
	}
	
	@RequestMapping("/findpage")
	public PageResult findByPage(int page,int size) {
		
		return brandService.findByPage(page, size);
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public Result add(@RequestBody TbBrand brand) {
		
		try {
			int num = brandService.add(brand);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
		
	}
	
	
	@RequestMapping("/findone")
	public TbBrand findOne(Long id) {
		
		return brandService.findOne(id);
	}
	
	
	@RequestMapping("/update")
	public Result update(@RequestBody TbBrand brand) {
		
		try {
			brandService.update(brand);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}
	
	@RequestMapping("/del")
	public Result delete(Long id) {
		
		try {
			brandService.delete(id);
			
			return new Result(true,"删除成功");
		} catch (Exception e) {
			return new Result(false,"删除失败");
		}
		
	}
	
	@RequestMapping("/delList")
	public Result deleteList(Long[] ids) {
		
		
		try {
			
			System.out.println(ids+"---------------id");
			
			BrandVo vo = new BrandVo();
			
			vo.setIds(Arrays.asList(ids));
			brandService.deleteList(vo);
			
			return new Result(true,"删除成功");
			
		} catch (Exception e) {
			
			return new Result(false,"删除失败");
		}
	}
	
	@RequestMapping(value="/search",method=RequestMethod.POST)
	public PageResult search(int page,int size,@RequestBody TbBrand brand) {
		
			
			
			return brandService.search(brand, page, size);
			
	
	}
	
}
