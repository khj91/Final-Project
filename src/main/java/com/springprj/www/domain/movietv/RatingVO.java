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
public class RatingVO {
	private long tvid;
	private long mid;
	private String email;
	private double rating;
}
