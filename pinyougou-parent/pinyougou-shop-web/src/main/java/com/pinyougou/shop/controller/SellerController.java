package com.pinyougou.shop.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.serivce.SellerService;

import entity.ResponseResult;

@RestController
@RequestMapping("/seller")
public class SellerController {
	
	@Reference
	private SellerService service;
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public ResponseResult add(@RequestBody TbSeller seller) {
		
		try {
			
			//加密
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encode = encoder.encode(seller.getPassword());
			seller.setPassword(encode);
			
			
			service.add(seller);
			return ResponseResult.build(true, "注册成功");
		} catch (Exception e) {
			return ResponseResult.build(false, "注册失败");
		}
	}

}
