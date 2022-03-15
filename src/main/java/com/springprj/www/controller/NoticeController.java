package com.springprj.www.controller;

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
import com.springprj.www.domain.notice.NoticeVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.notice.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/notice/*")
@Controller
public class NoticeController {
	@Inject
	private NoticeService nsv;
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(NoticeVO nvo, RedirectAttributes reAttr) {
		reAttr.addFlashAttribute("isUp", nsv.register(nvo) > 0 ? "1" : "0");
		return "redirect:/notice/list";
	}
	
	@GetMapping("/list")
	public void list(Model model, PagingVO pgvo) {
		model.addAttribute("list", nsv.getList(pgvo));
		int totalCount = nsv.getTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model model, @RequestParam("nid")long nid, @ModelAttribute("pgvo")PagingVO pgvo) {
		model.addAttribute("nvo", nsv.getDetail(nid));
	}

	@PostMapping("/modify")
	public String modify(RedirectAttributes reAttr, NoticeVO nvo, PagingVO pgvo) {
		reAttr.addFlashAttribute("isMod", nsv.modify(nvo));
		reAttr.addFlashAttribute("pageNo", pgvo.getPageNo());
		reAttr.addFlashAttribute("qty", pgvo.getQty());
		reAttr.addFlashAttribute("type", pgvo.getType());
		reAttr.addFlashAttribute("keyword", pgvo.getKeyword());
		return "redirect:/notice/detail?nid="+nvo.getNid();
	}
	
	@PostMapping("/remove")
	public String remove(RedirectAttributes reAttr, @RequestParam("nid")long nid, PagingVO pgvo) {
		reAttr.addFlashAttribute("isDel", nsv.remove(nid));
		reAttr.addFlashAttribute("pageNo", pgvo.getPageNo());
		reAttr.addFlashAttribute("qty", pgvo.getQty());
		reAttr.addFlashAttribute("type", pgvo.getType());
		reAttr.addFlashAttribute("keyword", pgvo.getKeyword());
		return "redirect:/notice/list";
	}
}
