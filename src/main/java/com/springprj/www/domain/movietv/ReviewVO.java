package com.springprj.www.domain.movietv;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReviewVO {
	private long mid;
	private long tvid;
	private String writer;
	private String regAt;
	private String modAt;
	private String content;
}
