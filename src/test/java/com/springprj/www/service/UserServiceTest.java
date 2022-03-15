package com.springprj.www.service;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springprj.www.config.RootConfig;
import com.springprj.www.security.UserVO;
import com.springprj.www.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class UserServiceTest {

	@Inject
	private UserService usv;

	@Test
	public void registerUser() {
		UserVO uvo = new UserVO();
		uvo.setEmail("admin@admin.com");
		uvo.setName("adminName");
		uvo.setNickName("adminNName");
		uvo.setPwd("1234");
		uvo.setGrade(100);
		usv.registerUser(uvo);
		usv.getUserDetail("admin@admin.com");
	}

	@Test
	public void findEmail() {
		usv.findEmailByNameAndNickName("adminName", "adminNName");
	}

	@Test
	public void checkValidNickName() {
		log.info("{}", usv.checkValidNickName("dsf"));
	}

	@Test
	public void login() {
		log.info("{}", usv.login("123@123.com", "123"));
	}

	@Test
	public void getUserDetail() {
		usv.getUserDetail("123@123.com");
	}
	
	@Test
	public void getUserList() {
		usv.getUsersList("curr_points");
	}
	
	@Test 
	public void getUsersAuth() {
		usv.getUsersAuth("123@123.com");
	}
	
	@Test
	public void getUsersCurrPoints() {
		usv.getUsersCurrPoints("123@123.com");
	}
	
	@Test
	public void gainPoint() {
		usv.gainPoint("admin@admin.com", 999);
	}
	
	@Test
	public void spendPoint() {
		usv.spendPoint("123@123.com", 234);
	}
	
	@Test
	public void updateUserSetting()	{
		usv.updateUserSetting("admin@admin.com", true, true);
	}
	
	@Test
	public void updateUserGrade() {
		usv.updateUserGrade("123@123.com", 60);
	}
}
