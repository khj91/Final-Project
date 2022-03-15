package com.springprj.www.service.user;

import java.util.List;

import com.springprj.www.domain.user.UserRateData;
import com.springprj.www.handler.RateDataHandler;
import com.springprj.www.handler.WatchedMovieDataHandler;
import com.springprj.www.handler.WatchedTVDataHandler;
import com.springprj.www.security.AuthVO;
import com.springprj.www.security.UserVO;




public interface UserService {
	int registerUser(UserVO uvo);
	String findEmailByNameAndNickName(String name, String nickName);
	boolean checkValidNickName(String nickName); // 회원가입시 닉네임 중복체크 
	UserVO login(String email, String pwd); //	로그인시 유저 vo 반환
	UserVO getUserDetail(String email); 
	List<UserVO> getUsersList(String sortBy); // 유저 리스트 -> 추후 변경(평점 갯수, 즐겨찾기, 게시글 높은 순으로 정렬 기능 필요)	
	List<AuthVO> getUsersAuth(String email); // security 관련
	int getUsersCurrPoints(String email); //현존 포인트 반환
	Double getUsersAvgTVRating(String email);
	Double getUsersAvgMovieRating(String email);
	RateDataHandler getUsersMovieRateData(String email); // 유저 평점 분포 	
	RateDataHandler getUsersTVRateData(String email); // 유저 평점 분포 	
	WatchedMovieDataHandler getUsersWatchedMovieGenres(String email);
	WatchedTVDataHandler getUsersWatchedTVGenres(String email);
	
//	int updateUser(UserVO uvo); // 한꺼번에 묶을수 있으면 묶는쪽으로..
	
	int gainPoint(String email, int point); //	잔여포인트반환
	int spendPoint(String email, int point); // 잔여 포인트 반환
	int updateUserSetting(String email, boolean slang, boolean adult );
	int updateUserGrade(String email, int grade);
	int updateUserNickName(String email, String nickName);
	int updateUserPwd(String email, String pwd);
	int updateUserProfileImg(String email, String url);
	int updateUserFontColor(String email, String color);

	boolean updateLastLogin(String email);
	
	
	int removeUser(String email, String pwd);
}

