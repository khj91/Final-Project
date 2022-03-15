package com.springprj.www.controller.shop;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.shop.ColorVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.shop.ColorService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/color/*")
public class ColorController {
	
	@Inject
	private ColorService csv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(ColorVO cvo, RedirectAttributes reAttr) {
		log.debug(">>>{}", cvo);
		int isUp = csv.register(cvo);
		reAttr.addFlashAttribute("isUp", isUp);
		return "redirect:/color/list";
	}
	
	@GetMapping("/list")
	public void list(Model model, PagingVO pgvo) {
		model.addAttribute("list", csv.getList(pgvo));
		int totalCount = csv.getTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("sid") long sid, RedirectAttributes reAttr, PagingVO pgvo) {
		log.debug(">>> {}", sid);
		reAttr.addAttribute("pageNo", pgvo.getPageNo());
		reAttr.addAttribute("qty", pgvo.getQty());
		reAttr.addFlashAttribute("isDel", csv.remove(sid) > 0 ? "1":"0");
		return "redirect:/color/list";
	}
	

}
