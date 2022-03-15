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
public class CommentRankVO {
	private String nickName;
	private int commentQty;
	private String profileImg;
	private String regAt;
}
