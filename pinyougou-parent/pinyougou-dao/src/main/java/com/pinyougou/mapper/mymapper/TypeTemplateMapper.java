package com.pinyougou.mapper.mymapper;

import java.util.List;

import com.pinyougou.pojo.TbTypeTemplate;

public interface TypeTemplateMapper {
	
	TbTypeTemplate selectById(Long id);
	List<TbTypeTemplate> search(TbTypeTemplate template);
	Long insert(TbTypeTemplate template);
	void delete(Long id);
}
