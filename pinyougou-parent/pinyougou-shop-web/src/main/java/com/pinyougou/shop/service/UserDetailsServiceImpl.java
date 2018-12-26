package com.pinyougou.shop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.alibaba.dubbo.config.annotation.Reference;
import com.pinyougou.pojo.TbSeller;
import com.pinyougou.sellergoods.serivce.SellerService;
/**
 * 认证类
 * @author 001
 *
 */
public class UserDetailsServiceImpl implements UserDetailsService{
	
	private SellerService service;

	public void setService(SellerService service) {
		this.service = service;
	}




	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		List<GrantedAuthority> grantAuths = new ArrayList<>();
		grantAuths.add(new SimpleGrantedAuthority("ROLE_SELLER"));
		
		//商家对象
		TbSeller seller = service.findOne(username);
		

		if(seller!=null) {
			
			if(seller.getStatus().equals("1")) {
			
			return new User(username,seller.getPassword(),grantAuths);
			}else {
				return null;
			}
		}else {
			return null;
		}
		
		
		
		}

}
