package com.pinyougou.mapper.mymapper;

import java.util.List;

import com.pinyougou.pojo.TbBrand;

public interface BrandMapper {
		
		
	List<TbBrand> findAll();
	
	
	int insert(TbBrand brand);
	
	void update(TbBrand brand);
	
	TbBrand findOne(Long id);
	
	
	void delete(Long id);
	 
}
