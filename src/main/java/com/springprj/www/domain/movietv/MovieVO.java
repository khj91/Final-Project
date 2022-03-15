package com.springprj.www.domain.movietv;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MovieVO {
	private long mid;
	private String title;
	private String poster;
	private String genres;
	// 즐겨찾기 & 리뷰 작성일 가져올때 
	private String regAt;
	private Double rating;
	private int isLiked;

}
