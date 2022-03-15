package com.springprj.www.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springprj.www.service.rank.RankService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/rank/*")
@Slf4j
public class RankController {

	@Inject
	private RankService rsv;
	
	@GetMapping("/pointlist")
	public void pointlist(Model model) {
		model.addAttribute("list", rsv.getPointList());
		model.addAttribute("lowlist", rsv.getPointLowList());
	}
	
	@GetMapping("/boardlist")
	public void boardlist(Model model) {
		model.addAttribute("bmlist", rsv.getMreadcountList());
		model.addAttribute("btlist", rsv.getTreadcountList());
	}
	
	@GetMapping("/commentlist")
	public void commentlist(Model model) {
		model.addAttribute("cmlist", rsv.getMCommentList());
		model.addAttribute("cmlist", rsv.getTCommentList());
	}
}
