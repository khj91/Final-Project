package com.springprj.www.handler;

import java.util.List;

import com.springprj.www.domain.user.UserRateData;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class RateDataHandler {
	
	private int one;
	private int two;
	private int three;
	private int four;
	private int five;
	private int six;
	private int seven;
	private int eight;
	private int nine;
	private int ten;
	
	public RateDataHandler(List<UserRateData> urd) {
		for (UserRateData data : urd) {
			if (data.getRate() == 1)  this.one = data.getCnt() ;
			if (data.getRate() == 2)  this.two = data.getCnt() ;
			if (data.getRate() == 3)  this.three = data.getCnt() ;
			if (data.getRate() == 4)  this.four = data.getCnt() ;
			if (data.getRate() == 5)  this.five = data.getCnt() ;
			if (data.getRate() == 6)  this.six = data.getCnt() ;
			if (data.getRate() == 7)  this.seven = data.getCnt() ;
			if (data.getRate() == 8)  this.eight= data.getCnt() ;
			if (data.getRate() == 9)  this.nine = data.getCnt() ;
			if (data.getRate() == 10)  this.ten = data.getCnt() ;
		}
	}

}
