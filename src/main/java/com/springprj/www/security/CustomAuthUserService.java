package com.springprj.www.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.springprj.www.repository.user.UserDAO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CustomAuthUserService implements UserDetailsService {

	@Inject 
	private UserDAO udao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		UserVO uvo = udao.selectOneUserByEmail(email);
		uvo.setAuthList(udao.selectAuths(email));
		log.debug("{}",uvo);
		return new AuthUser(uvo);
	}

}
