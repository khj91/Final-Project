package com.springprj.www.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class PosterVO {
	private long sid;
	private int category; // select -> string
	private String sname;
	private int price;
	private String value;

}
