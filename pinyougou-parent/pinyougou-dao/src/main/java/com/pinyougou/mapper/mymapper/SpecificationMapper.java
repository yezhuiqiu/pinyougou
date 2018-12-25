package com.pinyougou.mapper.mymapper;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbSpecification;

public interface SpecificationMapper {
	
	List<TbSpecification> selectBySearch(TbSpecification specification);
	Long insert(TbSpecification specification);
	TbSpecification findOne(Long id);
	void update(TbSpecification specification);
	
	void deleteById(Long id);
	List<Map>selectOptionList();
}
