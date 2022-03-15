package com.springprj.www.domain.tvBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TvBoardVO {
	private long tvbId;
	private String writer;
	private long tvid;
	private String title;
	private int likeHate;
	private String content;
	private String regAt;
	private String modAt;
	private int readCount;
	private String heart;
	private String poster;
	private String tvTitle;
	private String regDate;
	private int cmt_qty;
	private String nickName;
}
