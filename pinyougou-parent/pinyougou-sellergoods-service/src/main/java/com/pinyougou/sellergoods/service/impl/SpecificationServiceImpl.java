package com.pinyougou.sellergoods.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.mapper.mymapper.SpecificationMapper;
import com.pinyougou.mapper.mymapper.SpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.pojo.group.Specification;
import com.pinyougou.sellergoods.serivce.SpecificationService;
@Service
@Transactional
public class SpecificationServiceImpl implements SpecificationService {
	
	@Resource
	private SpecificationMapper mapper;
	
	@Resource
	private SpecificationOptionMapper optionMapper;

	@Override
	public PageInfo<TbSpecification> searchByVo(TbSpecification specification,Integer page,Integer size) {
		PageHelper.startPage(page, size);
		List<TbSpecification> selectBySearch = mapper.selectBySearch(specification);
		PageInfo<TbSpecification> info = new PageInfo<>(selectBySearch);
		
		return info;
	}

	@Override
	public void add(Specification specification) {
		TbSpecification tbSpecification = specification.getSpecification();
		mapper.insert(tbSpecification);
		
		for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
			option.setSpecId(tbSpecification.getId());
			optionMapper.insert(option);
			
		}
		
	}
	
	@Override
	public Specification findOne(Long id) {
		
		
		Specification specification = new Specification();
		specification.setSpecification(mapper.findOne(id));
		List<TbSpecificationOption> findBySpecId = optionMapper.findBySpecId(id);
		specification.setSpecificationOptionList(findBySpecId);
		
		return specification;
	}
	
	@Override
	public void update(Specification specification) {
		mapper.update(specification.getSpecification());
		
		optionMapper.deleteBySpecId(specification.getSpecification().getId());
		
		for (TbSpecificationOption option : specification.getSpecificationOptionList()) {
			option.setSpecId(specification.getSpecification().getId());
			optionMapper.insert(option);
		}
		
	}

	@Override
	public void delete(Long id) {
		
		mapper.deleteById(id);
		
		optionMapper.deleteBySpecId(id);
		
	}

	@Override
	public List<Map> selectOptionList() {
		// TODO Auto-generated method stub
		return mapper.selectOptionList();
	}
	
	
	

}
