package com.springprj.www.controller;

import java.security.Principal;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.springprj.www.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/search/*")
public class SearchController {
	
	@Inject
	private UserService usv;
	
	@GetMapping("/tv")
	public String searchtv(String query,@RequestParam(required = false, defaultValue = "1") int page, Principal principal, Model model) {
		if(principal != null) {
			model.addAttribute("isAdult",usv.getUserDetail(principal.getName()).isAdult());
		}else {
			model.addAttribute("isAdult",false);
		}
		model.addAttribute("query", query);
		model.addAttribute("page", page);
		model.addAttribute("platform", "tv");
		return "search";
	}
	
	@GetMapping("/movie")
	public String searchmovie(String query,@RequestParam(required = false, defaultValue = "1") int page, Principal principal, Model model) {
		if(principal != null) {
			model.addAttribute("isAdult",usv.getUserDetail(principal.getName()).isAdult());
		}else {
			model.addAttribute("isAdult",false);
		}
		model.addAttribute("query", query);
		model.addAttribute("page", page);
		model.addAttribute("platform", "movie");
		return "search";
	}
	
}
