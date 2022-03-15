package com.springprj.www.domain.mBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class MCommentVO {
	private long mcId;
	private long mbId;
	private String writer;
	private String content;
	private String heart;
	private String regAt;
	private String modAt;
}
