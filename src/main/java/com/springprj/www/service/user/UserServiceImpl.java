package com.springprj.www.service.user;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.springprj.www.domain.user.UserRateData;
import com.springprj.www.handler.RateDataHandler;
import com.springprj.www.handler.WatchedMovieDataHandler;
import com.springprj.www.handler.WatchedTVDataHandler;
import com.springprj.www.repository.user.UserDAO;
import com.springprj.www.security.AuthVO;
import com.springprj.www.security.UserVO;

import lombok.extern.slf4j.Slf4j;

// 스프링 설정 어노테이션
@Service
@Slf4j
public class UserServiceImpl implements UserService {

	// 매퍼와 연결된 다오를 가져와서 쓸수있게 의존성 주입하는부
	@Inject
	private UserDAO udao;

	@Override
	public int registerUser(UserVO uvo) { // 회원가입 로직. 회원정보를 웹에서 가져와 db에 넣으려고 만들어놓은 UserVO 이용.
		// 회원가입란에 정보들을 입력해서 보냈다고 가정 할때,
		// uvo에는 회원 정보들(저희가 받아오기로 정한) 이 있음.
		// dao에서 만들어놓은 회원가입 로직을 이용 -> 
		udao.insertUser(uvo); // db에 정상적으로 저장이 되었다면 1을, 실패했다면 0 을 반환하도록 설계했기 때문에 성공/실패 여부를 isUp에 저장
		if (uvo.getGrade() >= 100) {
			return udao.insertAuthAdmin(uvo.getEmail());
		} else if (uvo.getGrade() >= 50) {
			return udao.insertAuthManager(uvo.getEmail());
		} else {
			return udao.insertAuthInit(uvo.getEmail()); // 회원가입 성공/실패여부 반환
		}
	}

	// 로그인 화면에 이메일 찾기 기능.실명(name)이랑 닉네임(nickName)을 입력해 일치하는 이메일이 있다면 해당 이메일을 반환해줌
	@Override
	public String findEmailByNameAndNickName(String name, String nickName) { // 유저가 입력한 이름, 닉네임 값
		String email = udao.selectEmailByNameAndNickName(name, nickName); // dao에 만들어놓은 selectEmailByNameAndNickName 메서드
																			// 이용해 이메일을 받아옴.
																			// 이때 해당 닉네임과 이름이 일치하는 이메일이 있으면 이메일이 올거고,
																			// 없다면 null이 옴.
		return email; // 해당 닉네임 혹은 null값 반환.
	}

	// 회원가입 시에 닉네임 중복검사하는 로직.
	@Override
	public boolean checkValidNickName(String nickName) { // 유저가 작성한 닉네임 받아옴.
		int isDuple = udao.selectNickNameCount(nickName); // 제가 임의로 정한 로직.(우리 db에 해당 닉네임을 가진 유저가 몇명이냐? 라는 기능)

		return isDuple > 0 ? false : true; // 받아온 수를 반환 -> 0이라면 사용가능합니다! 문구가, 1이상이면 이미 사용중인 닉네임입니다! 라는식의 구현을 위해 해당 정보를
											// 컨트롤러로 전송.
	}

	// 로그인을 하면 해당 유저의 정보를 반환해줌.
	@Override
	public UserVO login(String email, String pwd) {
		UserVO user = udao.selectOneUserByEmailAndPwd(email, pwd);
		if (user != null) {
			
			
			udao.updateLastLogin(email);
			return user;
		} else {
			return null;
		}

	}

	@Override
	public Double getUsersAvgTVRating(String email) {

		return udao.selectUserTVAvgRating(email);
	}

	@Override
	public Double getUsersAvgMovieRating(String email) {

		return udao.selectUserMovieAvgRating(email);
	}

	@Override
	public UserVO getUserDetail(String email) {
		return udao.selectOneUserByEmail(email);
	}

	// 어드민이 회원 관리할때, 혹은 랭킹에 사용될 전체 유저 목록 반환해줌.
	@Override
	public List<UserVO> getUsersList(String sortBy) {

		return udao.selectUserList(sortBy);
	}

	@Override
	public List<AuthVO> getUsersAuth(String email) {

		return udao.selectAuths(email);
	}

	// 해당 이메일을 가진 유저의 현존 포인트 반환.
	@Override
	public int getUsersCurrPoints(String email) {

		return udao.selectPoint(email);
	}
	
	
	// 유저 평점 분포
	@Override
	public RateDataHandler getUsersMovieRateData(String email) {
		
		return new RateDataHandler(udao.selectUserMovieRateData(email));
	}
	@Override
	public RateDataHandler getUsersTVRateData(String email) {
		
		return new RateDataHandler(udao.selectUserTVRateData(email));
	}

	// 유저가 본 영화 장르들
	
	@Override
	public WatchedMovieDataHandler getUsersWatchedMovieGenres(String email) {
		
		return new WatchedMovieDataHandler(udao.selectUserMovieWatchedList(email));
	}

	@Override
	public WatchedTVDataHandler getUsersWatchedTVGenres(String email) {
		
		return new WatchedTVDataHandler(udao.selectUserTVWatchedList(email));
	}
	
	@Override
	public int updateUserNickName(String email,  String nickName) {
		if (udao.selectOneUserByEmail(email) != null) {
			return udao.updateNickName(email,  nickName);
		} else {
			return 0;
		}
	}



	@Override
	public int updateUserPwd(String email, String pwd) {
		if (udao.selectOneUserByEmail(email) != null) {
			return udao.updatePwd(email, pwd);
		} else {

			return 0;
		}
	}

	@Override
	public int updateUserProfileImg(String email, String url) {
		if (udao.selectOneUserByEmail(email) != null) {
			return udao.updateProfileImg(email, url);
		} else {

			return 0;
		}
	}

	@Override
	public int updateUserFontColor(String email, String color) {
		if (udao.selectOneUserByEmail(email) != null) {
			return udao.updateFontColor(email, color);
		} else {

			return 0;
		}
	}

	// 해당 이메일을 가진 유저가 포인트를 얻음
	@Override
	@Transactional
	public int gainPoint(String email, int point) {
		udao.updatePointUp(email, point);
		return 1;
	}

	// 해당 이메일을 가진 유저가 포인트를 사용 
	@Override
	@Transactional
	public int spendPoint(String email, int point) {
		int currPoint = udao.selectPoint(email);
		if (currPoint < point) {
			return -1;
		} else {
			udao.updatePointDown(email, point);
			return 1;
		}
	}

	// 유저의 설정(성인필터링, 비속어 필터링)을 바꿔주는 역활. 허용하면 1이고 아니면 0으로.
	@Override
	public int updateUserSetting(String email, boolean slang, boolean adult) {
		int isUp = udao.updateAdult(email, adult == true ? 1 : 0);
		isUp *= udao.updateSlang(email, slang == true ? 1 : 0);
		return isUp;
	}

	// 유저등급 설정.grade 값으로 .
	@Override
	public int updateUserGrade(String email, int grade) {
		if (grade >= 100) {
			udao.insertAuthAdmin(email);
		} else if (grade >= 50) {
			udao.insertAuthManager(email);
		}
		return udao.updateGrade(email, grade);
	}

	// 유저 삭제
	@Override
	public int removeUser(String email, String pwd) {

		return udao.deleteUser(email, pwd);
	}

	@Override
	public boolean updateLastLogin(String email) {

		return udao.updateLastLogin(email) > 0 ? true : false;
	}

}
