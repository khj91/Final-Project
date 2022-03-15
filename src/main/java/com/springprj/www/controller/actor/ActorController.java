package com.springprj.www.controller.actor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/actor/*")
public class ActorController {
	
	@GetMapping("/{id}")
	public String actorDetail(@PathVariable("id") long id, Model model) {
		model.addAttribute("id", id);
		return "actor/detail";
	}

}
