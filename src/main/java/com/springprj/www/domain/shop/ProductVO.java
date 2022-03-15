package com.springprj.www.domain.shop;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@ToString
public class ProductVO {
	private long sid;
	private int category; // 1 = font-color, 2 = poster
	private String email;
	private String sname; 
	private int price;
	private String value;
}
