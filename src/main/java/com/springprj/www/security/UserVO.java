package com.springprj.www.security;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class UserVO {
	
	private String email;
	private String name;
	private String pwd; 
	private String nickName;
	private String profileImg;
	private int totalPoints;
	private int currPoints;
	private int grade;
	private String regAt;
	private String lastLogin;
	private boolean slang;
	private boolean adult;
	private String fontColor;
	private List<AuthVO> authList;
	private int likeCnt;
	private int reviewCnt;
	private int ratingCnt;
}
