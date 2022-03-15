package com.springprj.www.domain.notice;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NoticeVO {
	private long nid;
	private String email;
	private String title;
	private String content;
	private String regAt;
	private String modAt;
	private int readCount;
	private String nickName;
}
