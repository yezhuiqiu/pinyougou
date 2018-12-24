package com.pinyougou.sellergoods.service.impl;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.pinyougou.mapper.mymapper.SpecificationOptionMapper;
import com.pinyougou.pojo.TbSpecificationOption;
import com.pinyougou.sellergoods.serivce.SpecificationOptionService;

@Service
@Transactional
public class SpecificationOptionServiceImpl implements SpecificationOptionService{
	
	@Resource
	private  SpecificationOptionMapper mapper;

	@Override
	public Long insert(TbSpecificationOption option) {
		// TODO Auto-generated method stub
		return mapper.insert(option);
	}

}
