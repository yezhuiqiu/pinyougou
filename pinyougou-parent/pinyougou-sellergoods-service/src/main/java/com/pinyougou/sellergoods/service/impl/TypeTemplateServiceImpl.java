package com.pinyougou.sellergoods.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.mymapper.TypeTemplateMapper;
import com.pinyougou.pojo.TbTypeTemplate;
import com.pinyougou.sellergoods.serivce.TypeTemplateService;

@Service
@Transactional
public class TypeTemplateServiceImpl implements TypeTemplateService{
	
	@Resource
	private TypeTemplateMapper mapper;

	@Override
	public TbTypeTemplate findById(Long id) {
		return mapper.selectById(id);
	}

	@Override
	public PageInfo<TbTypeTemplate> search(Integer page,Integer size,TbTypeTemplate template) {
		PageHelper.startPage(page, size);
		List<TbTypeTemplate> search = mapper.search(template);
		PageInfo<TbTypeTemplate> info = new PageInfo<>(search);
		return info;
	}

	@Override
	public Long insert(TbTypeTemplate template) {
		// TODO Auto-generated method stub
		return mapper.insert(template);
	}

	@Override
	public void delete(Long id) {
		mapper.delete(id);
	}

}
