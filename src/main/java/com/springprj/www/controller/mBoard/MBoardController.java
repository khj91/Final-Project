package com.springprj.www.controller.mBoard;

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
import com.springprj.www.domain.mBoard.MBoardVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.mBoard.MBoardService;
import com.springprj.www.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/mBoard/*")
@Controller
public class MBoardController {
	@Inject
	private MBoardService mbsv;
	
	@Inject
	private UserService usv;
	
	@GetMapping("/registerMovie")
	public void registerMovie() {}
	
	@PostMapping("/registerMovie")
	public String resgisterMovie(RedirectAttributes reAttr, @RequestParam("mid")long mid, @RequestParam("poster")String poster, @RequestParam("regDate")String regDate, @RequestParam("movieTitle")String movieTitle ) {
		reAttr.addFlashAttribute("mid", mid);
		reAttr.addFlashAttribute("poster", poster);
		reAttr.addFlashAttribute("regDate", regDate);
		reAttr.addFlashAttribute("movieTitle", movieTitle);
		return "redirect:/mBoard/register";
	}
	
	@GetMapping("/register")
	public void register() {}
	
	@PostMapping("/register")
	public String register(MBoardVO mbvo, RedirectAttributes reAttr) {
		reAttr.addFlashAttribute("isUp", mbsv.register(mbvo) > 0 ? "1" : "0");
		usv.gainPoint(mbvo.getWriter(), 5);
		return "redirect:/mBoard/" + (mbvo.getLikeHate() == 1 ? "like" : "hate") + "List";
	}
	
	@GetMapping("/likeList")
	public void likeList(Model model, PagingVO pgvo) {
		model.addAttribute("list", mbsv.getLikeList(pgvo));
		int totalCount = mbsv.getLikeTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	@GetMapping("/hateList")
	public void hateList(Model model, PagingVO pgvo) {	
		model.addAttribute("list", mbsv.getHateList(pgvo));
		int totalCount = mbsv.getHateTotalCount(pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	
	@GetMapping("/relatedMovie")
	public void relatedMovie(Model model, @RequestParam("tvid")int tvid, PagingVO pgvo) {
		model.addAttribute("list", mbsv.getRelatedMovieList(pgvo));
		int totalCount = mbsv.getRelatedTotalCount(tvid, pgvo);
		model.addAttribute("pgn", new PagingHandler(pgvo, totalCount));
	}
	
	@GetMapping({"/detail", "/modify"})
	public void detail(Model model, @RequestParam("mbId")long mbId, @RequestParam("authEmail") String authEmail,
			@ModelAttribute("pgvo")PagingVO pgvo) {
		model.addAttribute("mbdto", mbsv.getDetail(mbId, authEmail));
	}
	
	@PostMapping("/modify")
	public String modify(RedirectAttributes reAttr, MBoardVO mbvo, PagingVO pgvo, @RequestParam("authEmail") String authEmail) {
		reAttr.addFlashAttribute("isMod", mbsv.modify(mbvo));
		reAttr.addFlashAttribute("pageNo", pgvo.getPageNo());
		reAttr.addFlashAttribute("qty", pgvo.getQty());
		reAttr.addFlashAttribute("type", pgvo.getType());
		reAttr.addFlashAttribute("keyword", pgvo.getKeyword());
		return "redirect:/mBoard/detail?mbId=" + mbvo.getMbId() + "&authEmail=" + authEmail;
	}
	
	@PostMapping("/remove")
	public String remove(RedirectAttributes reAttr, @RequestParam("mbId")long mbId, @RequestParam("likeHate") int likeHate, PagingVO pgvo) {
		reAttr.addFlashAttribute("isDel", mbsv.remove(mbId));
		reAttr.addFlashAttribute("pageNo", pgvo.getPageNo());
		reAttr.addFlashAttribute("qty", pgvo.getQty());
		reAttr.addFlashAttribute("type", pgvo.getType());
		reAttr.addFlashAttribute("keyword", pgvo.getKeyword());
		return "redirect:/mBoard/" + (likeHate == 1 ? "like" : "hate") + "List";
	}
}
