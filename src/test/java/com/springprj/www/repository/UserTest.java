package com.springprj.www.repository;

import javax.inject.Inject;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.springprj.www.config.RootConfig;
import com.springprj.www.repository.user.UserDAO;
import com.springprj.www.security.UserVO;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
@Slf4j
public class UserTest {

	@Inject
	private UserDAO udao;

	@Test
	public void insertUser() {
		UserVO uvo = new UserVO();
		uvo.setEmail("123@123.com");
		uvo.setPwd("123");
		uvo.setGrade(100);
		uvo.setName("adminName");
		uvo.setAdult(true);
		uvo.setSlang(true);
		uvo.setNickName("adminNickName");
		udao.insertUser(uvo);
	}

	@Test
	public void insertAuthInit() {
		udao.insertAuthInit("123@123.com");
	}

	@Test
	public void selectOneUserByEmail() {
		udao.selectOneUserByEmail("123@123.com");
	}

	@Test
	public void selectEmailByNameAndNickName() {
		udao.selectEmailByNameAndNickName("adminName", "NickName");
	}
	
	@Test
	public void selectNickNameCount() {
		udao.selectNickNameCount("NickNdame");
	}
	
	@Test
	public void selectUserList() {
		udao.selectUserList("currPoints");
	}

	@Test
	public void selectAuths() {
		udao.selectAuths("123@123.com");

	}

	@Test
	public void selectPoints() {
		udao.selectPoint("123@123.com");
	}
	
	@Test
	public void updateNickName() {
		udao.updateNickName("123@123.com", "NickName");
		udao.selectOneUserByEmail("123@123.com");
	}

	@Test
	public void updatePointUp() {
		udao.updatePointUp("123@123.com", 100);
		udao.selectOneUserByEmail("123@123.com");
	}

	@Test
	public void updatePointDown() {
		udao.updatePointDown("123@123.com", 100);
		udao.selectOneUserByEmail("123@123.com");
	}

	@Test
	public void updateProfileImg() {
		udao.updateProfileImg("123@123.com", "default2.img");
		udao.selectOneUserByEmail("123@123.com");
	}

	@Test
	public void updateSlang() {
		udao.updateSlang("123@123.com", 1);
		udao.selectOneUserByEmail("123@123.com");
	}

	@Test
	public void updateAdult() {
		udao.updateAdult("123@123.com", 1);
		udao.selectOneUserByEmail("123@123.com");
	}

	@Test
	public void updateLastLogin() {
		udao.updateLastLogin("123@123.com");
		udao.selectOneUserByEmail("123@123.com");
	}

	@Test
	public void updateFontColor() {
		udao.updateFontColor("123@123.com", "red");
	}

	@Test
	public void updateGrade() {
		udao.updateGrade("123@123.com", 60);
		udao.selectOneUserByEmail("123@123.com");
	}
}
