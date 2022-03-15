package com.springprj.www.domain.movietv;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class LikeVO {
	private long tvid;
	private long mid;
	private String email;
	private String regAt;
}
