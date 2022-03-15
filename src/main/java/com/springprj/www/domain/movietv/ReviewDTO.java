package com.springprj.www.domain.movietv;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class ReviewDTO {
	private long mid;
	private long tvid;
	private String writer;
	private String profileImg;
	private String fontColor;
	private String regAt;
	private String modAt;
	private String content;
}
