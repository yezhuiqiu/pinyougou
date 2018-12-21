package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.TbBrandMapper;
import com.pinyougou.mapper.mymapper.BrandMapper;
import com.pinyougou.pojo.TbBrand;
import com.pinyougou.sellergoods.serivce.BrandService;

import entity.PageResult;

@Service
@Transactional
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	private BrandMapper mapper;

	@Override
	public List<TbBrand> findAll() {
		
		List<TbBrand> list = mapper.findAll();
		
		return list;
	}

	@Override
	public PageResult findByPage(Integer pageNum, Integer pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		Page<TbBrand>list = (Page<TbBrand>) mapper.findAll();
		
		PageResult result =new PageResult(list.getTotal(), list.getResult());
		
		
		System.out.println(pageNum+"------"+pageSize+"-----"+list.getTotal());
		
		return result;
	}

	@Override
	public int add(TbBrand brand) {
		return mapper.insert(brand);
	}

	@Override
	public TbBrand findOne(Long id) {
		return mapper.findOne(id);
	}

	@Override
	public void update(TbBrand brand) {
		mapper.update(brand);
		
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

}
