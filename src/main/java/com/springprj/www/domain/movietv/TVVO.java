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
public class TVVO {
	private long tvid;
	private String title;
	private String poster;
	private String genres;
	
	private String regAt;
	private Double rating;
	private int isLiked;
	
}
