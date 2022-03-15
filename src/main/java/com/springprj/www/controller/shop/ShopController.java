package com.springprj.www.controller.shop;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.shop.ShopVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.shop.ShopService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/shop/*")
public class ShopController {

	@Inject
	private ShopService ssv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(ShopVO svo, RedirectAttributes reAttr) {
		int isUp = ssv.register(svo);
		reAttr.addFlashAttribute("isUp", isUp);
		return "redirect:/shop/list";
		
	}
	
	@GetMapping("/list")
	public void list(Model model, PagingVO pgvo) {
		model.addAttribute("list", ssv.getList(pgvo));
		int totalCount = ssv.getTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	
	@GetMapping("/detail")
	public void detail(Model model, @RequestParam("sid")long sid, @ModelAttribute("pgvo")PagingVO pgvo) {
		model.addAttribute("svo", ssv.getDetail(sid));
	}
	
	@PostMapping("/remove")
	public String remove(@RequestParam("sid") long sid, RedirectAttributes reAttr, PagingVO pgvo) {
		reAttr.addFlashAttribute("isDel", ssv.remove(sid) > 0 ? "1":"0");
		reAttr.addFlashAttribute("pageNo", pgvo.getPageNo());
		reAttr.addAttribute("qty", pgvo.getQty());
		reAttr.addAttribute("type", pgvo.getType());
		reAttr.addAttribute("keyword", pgvo.getKeyword());
		return "redirect:/shop/list";
	}
	
	
	
}
