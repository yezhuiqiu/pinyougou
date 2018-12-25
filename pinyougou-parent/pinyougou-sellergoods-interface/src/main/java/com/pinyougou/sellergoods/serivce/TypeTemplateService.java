package com.pinyougou.sellergoods.serivce;

import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbTypeTemplate;

public interface TypeTemplateService {
	
	TbTypeTemplate findById(Long id);
	PageInfo<TbTypeTemplate> search(Integer page,Integer size,TbTypeTemplate template);
	Long insert(TbTypeTemplate template);
	void delete(Long id);
	
}
