package com.springprj.www.domain.mBoard;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class MBoardDTO {
	private MBoardVO mbvo;
	private int check;
}
