package com.springprj.www.controller.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/poster/*")
public class PosterController {
	
@GetMapping("/index")
public void index() {
	log.debug("index 페이지");
}

@GetMapping("/movie")
public void movie() {
	log.debug("movie");
}

@GetMapping("/tv")
public void tv() {
	log.debug("tv");
}

@RequestMapping(value="/https://api.themoviedb.org/3/search/movie?api_key=c00b6bc84cbdb4e899121dcdfa60dac6&language=ko-KR&", method=RequestMethod.GET)
public String result(Model model, HttpServletRequest request) {
	String poster_path = request.getParameter("poster_path");
	String title = request.getParameter("title");
	model.addAttribute("poster_path", poster_path);
	model.addAttribute("title", title);
	
	return "/poster/movieResult";
	
	
}
	
	

}
