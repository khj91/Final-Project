package com.springprj.www.controller.tvBoard;

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
import com.springprj.www.domain.tvBoard.TvBoardVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.tvBoard.TvBoardService;
import com.springprj.www.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/tvBoard/*")
@Controller
public class TvBoardController {
	@Inject
	private TvBoardService tvbsv;
	
	@Inject
	private UserService usv;
	
	@GetMapping("/registerTv")
	public void registerTv() {}
	
	@PostMapping("/registerTv")
	public String resgisterTv(RedirectAttributes reAttr, @RequestParam("tvid")long tvid, @RequestParam("poster")String poster, @RequestParam("regDate")String regDate, @RequestParam("tvTitle")String tvTitle ) {
		reAttr.addFlashAttribute("tvid", tvid);
		reAttr.addFlashAttribute("poster", poster);
		reAttr.addFlashAttribute("regDate", regDate);
		reAttr.addFlashAttribute("tvTitle", tvTitle);
		return "redirect:/tvBoard/register";
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(TvBoardVO tvbvo, RedirectAttributes reAttr) {
		reAttr.addFlashAttribute("isUp", tvbsv.register(tvbvo) > 0 ? "1" : "0");
		usv.gainPoint(tvbvo.getWriter(), 5);
		return "redirect:/tvBoard/" + (tvbvo.getLikeHate() == 1 ? "like" : "hate") + "List";
	}
	
	@GetMapping("/likeList")
	public void likeList(Model model, PagingVO pgvo) {
		model.addAttribute("list", tvbsv.getLikeList(pgvo));
		int totalCount = tvbsv.getLikeTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	
	@GetMapping("/hateList")
	public void hateList(Model model, PagingVO pgvo) {
		model.addAttribute("list", tvbsv.getHateList(pgvo));
		int totalCount = tvbsv.getHateTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model model, @RequestParam("tvbId")long tvbId, @RequestParam("authEmail") String authEmail,
			@ModelAttribute("pgvo")PagingVO pgvo) {
		model.addAttribute("tvbdto", tvbsv.getDetail(tvbId, authEmail));
	}
	
	@PostMapping("/modify")
	public String modify(RedirectAttributes reAttr, TvBoardVO tvbvo, PagingVO pgvo, @RequestParam("authEmail") String authEmail) {
		reAttr.addFlashAttribute("isMod", tvbsv.modify(tvbvo));
		reAttr.addFlashAttribute("pageNo", pgvo.getPageNo());
		reAttr.addFlashAttribute("qty", pgvo.getQty());
		reAttr.addFlashAttribute("type", pgvo.getType());
		reAttr.addFlashAttribute("keyword", pgvo.getKeyword());
		return "redirect:/tvBoard/detail?tvbId=" + tvbvo.getTvbId() + "&authEmail=" + authEmail;
	}
	
	@PostMapping("/remove")
	public String remove(RedirectAttributes reAttr, @RequestParam("tvbId")long tvbId, @RequestParam("likeHate") int likeHate, PagingVO pgvo) {
		reAttr.addFlashAttribute("isDel", tvbsv.remove(tvbId));
		reAttr.addFlashAttribute("pageNo", pgvo.getPageNo());
		reAttr.addFlashAttribute("qty", pgvo.getQty());
		reAttr.addFlashAttribute("type", pgvo.getType());
		reAttr.addFlashAttribute("keyword", pgvo.getKeyword());
		return "redirect:/tvBoard/" + (likeHate == 1 ? "like" : "hate") + "List";
	}
}
