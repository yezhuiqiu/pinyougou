package com.pinyougou.mapper.mymapper;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.vo.BrandVo;

public interface BrandMapper {
		
		
	List<TbBrand> findAll();
	
	
	int insert(TbBrand brand);
	
	void update(TbBrand brand);
	
	TbBrand findOne(Long id);
	
	
	void delete(Long id);
	
	void deleteList(BrandVo vo);
	
	List<TbBrand>search(TbBrand brand);
	
	List<Map>selectOptionList();
}
