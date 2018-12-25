package com.pinyougou.sellergoods.serivce;

import java.util.List;
import java.util.Map;

import com.pinyougou.pojo.TbBrand;
import com.pinyougou.vo.BrandVo;

import entity.PageResult;

/**
 * 品牌接口
 * @author 001
 *
 */
public interface BrandService {
	
	public List<TbBrand> findAll();
	
	public PageResult findByPage(Integer pageNum,Integer pageSize);
	
	
	public int add(TbBrand brand);
	
	
	public TbBrand findOne(Long id);
	
	public void update(TbBrand brand);
	
	public void delete(Long id);
	
	public  void deleteList(BrandVo vo);
	
	public PageResult search(TbBrand brand,int page ,int size);
	
	public List<Map> selectOptionList();
}
