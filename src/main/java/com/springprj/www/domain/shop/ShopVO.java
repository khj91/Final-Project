package com.springprj.www.domain.shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ShopVO {
	private String email;
	private long sid;
	private int category;
	private String sname;
	private String value;
	private int price;
	private String purchDate;
}
