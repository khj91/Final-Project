package com.springprj.www.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class UserRateData {
	
	private int rate;
	private int cnt;
}
