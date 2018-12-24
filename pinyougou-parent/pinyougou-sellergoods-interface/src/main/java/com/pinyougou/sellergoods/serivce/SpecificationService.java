package com.pinyougou.sellergoods.serivce;



import com.github.pagehelper.PageInfo;
import com.pinyougou.pojo.TbSpecification;
import com.pinyougou.pojo.group.Specification;

public interface SpecificationService {
	
	
	PageInfo<TbSpecification> searchByVo(TbSpecification specification,Integer page,Integer size);
	
	void add(Specification specification);
	
	Specification findOne(Long id);
	
	void update(Specification specification);
	
	void delete(Long id);

}
