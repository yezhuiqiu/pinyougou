package com.pinyougou.vo;

import java.util.List;

import com.pinyougou.pojo.TbBrand;

public class BrandVo extends TbBrand{
	
	private List<Long>ids;

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}
	
		
}
