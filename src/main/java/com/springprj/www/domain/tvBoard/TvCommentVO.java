package com.springprj.www.domain.tvBoard;

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
public class TvCommentVO {
	private long tvcId;
	private long tvbId;
	private String writer;
	private String content;
	private String heart;
	private String regAt;
	private String modAt;
}
