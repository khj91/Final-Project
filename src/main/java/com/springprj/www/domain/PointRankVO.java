package com.springprj.www.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PointRankVO {
	private String profileImg;
	private String nickName;
	private int totalPoints;
	private String regAt;
}
