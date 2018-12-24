package com.pinyougou.mapper.mymapper;

import java.util.List;

import com.pinyougou.pojo.TbSpecificationOption;

public interface SpecificationOptionMapper {
	
	Long insert(TbSpecificationOption option);
	
	TbSpecificationOption findOne(Long id);
	
	List<TbSpecificationOption>findBySpecId(Long id);
	
	void deleteBySpecId(Long id);

}
