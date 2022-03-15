package com.springprj.www.controller.tv;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springprj.www.domain.movietv.LikeVO;
import com.springprj.www.domain.movietv.RatingVO;
import com.springprj.www.domain.movietv.ReviewVO;
import com.springprj.www.domain.movietv.TVDTO;
import com.springprj.www.domain.movietv.TVVO;
import com.springprj.www.security.UserVO;
import com.springprj.www.service.tv.TVService;
import com.springprj.www.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/tv/*")
public class TVController {

	@Inject
	private TVService tsv;
	
	@Inject UserService usv;

	@GetMapping("/netflix")
	public String netflixList(Model model, Principal principal) {
		if(principal != null) {
			UserVO uvo = usv.getUserDetail(principal.getName());
			model.addAttribute("isAdult", uvo.isAdult());
			}
		model.addAttribute("platform", "8");
		return "tv/list";
	}

	@GetMapping("/amazon")
	public String amazonList(Model model, Principal principal) {
		if(principal != null) {
			UserVO uvo = usv.getUserDetail(principal.getName());
			model.addAttribute("isAdult", uvo.isAdult());
			}
		model.addAttribute("platform", "119");
		return "tv/list";
	}

	@GetMapping("/watcha")
	public String watchaList(Model model, Principal principal) {
		if(principal != null) {
			UserVO uvo = usv.getUserDetail(principal.getName());
			model.addAttribute("isAdult", uvo.isAdult());
			}
		model.addAttribute("platform", "97");
		return "tv/list";
	}

	@GetMapping("/waave")
	public String wavveList(Model model, Principal principal) {
		if(principal != null) {
			UserVO uvo = usv.getUserDetail(principal.getName());
			model.addAttribute("isAdult", uvo.isAdult());
			}
		model.addAttribute("platform", "356");
		return "tv/list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") long id) {
		model.addAttribute("id", id);
		model.addAttribute("category", "tv");
		return "detail";
	}

	@GetMapping(value = "/{tvid}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<TVDTO> getTVdata(@PathVariable("tvid") long tvid, Principal principal) {
		
		String loggedInEmail = principal != null ? principal.getName() : "";
		TVDTO dto = tsv.getTVData(tvid, loggedInEmail);
		return new ResponseEntity<TVDTO>(dto, HttpStatus.OK);
	}

	// ============================== review ================================

	@PostMapping(value = "/review/{tvid}", consumes = "application/json", produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> registerReview(@PathVariable("tvid") long tvid,@RequestBody TVDTO dto) {
		if(dto.getTvvo() != null) {
			tsv.registerTVIfNotExists(dto.getTvvo());
		}
		return tsv.registerReview(dto.getRvvo()) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PatchMapping(value = "/review/{tvid}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modifyReview(@PathVariable("tvid") long tvid, @RequestBody ReviewVO rvvo){
		return tsv.modifyReview(rvvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/review/{tvid}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> deleteReview(@PathVariable("tvid") long tvid, @RequestBody ReviewVO rvvo){
		return tsv.deleteReview(rvvo.getTvid(), rvvo.getWriter()) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// ============================= like ===============================
	
	@PostMapping(value = "/like/{tvid}" , consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> addLike(@PathVariable("tvid") long tvid, @RequestBody TVDTO dto){
		if(dto.getTvvo() != null) {
			tsv.registerTVIfNotExists(dto.getTvvo());
		}
		int isUp = tsv.registerLike(dto.getLvo());
		log.debug("TVController >>> isregistered : {}",isUp);
		return isUp > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/like/{tvid}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> removeLike(@PathVariable("tvid") long tvid, @RequestBody LikeVO lvo){
		return tsv.deleteLike(lvo.getTvid(), lvo.getEmail()) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// ============================= rating ================================
	
	@PostMapping(value = "/rating/{tvid}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> registerRating(@PathVariable("tvid") long tvid , @RequestBody TVDTO dto){
		if(dto.getTvvo() != null) {
			tsv.registerTVIfNotExists(dto.getTvvo());
		}
		Double changedRating = tsv.registerRating(dto.getRtvo());
		log.debug("TVService >>> changedRating : {}", changedRating);
		return new ResponseEntity<String>(changedRating.toString(), HttpStatus.OK);
	}
	
	@PatchMapping(value = "/rating/{tvid}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> modifyRating(@PathVariable("tvid") long tvid, @RequestBody RatingVO rtvo){
		Double changedRating = tsv.modifyRating(rtvo);
		return new ResponseEntity<String>(changedRating.toString(), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/rating/{tvid}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> deleteRating(@PathVariable("tvid") long tvid, @RequestBody RatingVO rtvo){
		Double changedRating = tsv.deleteRating(rtvo.getTvid(), rtvo.getEmail());
		return	new ResponseEntity<String>(changedRating == null ? "NoData" : changedRating.toString(), HttpStatus.OK);
	}
	//======================== 유저가 평점,즐겨찾기,평점 남긴 tv 리스트 ==========================
	
	@GetMapping(value = "/{email}/reviewed", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<TVVO>> getUserReviewedList(@PathVariable("email") String email){
		return new ResponseEntity<List<TVVO>>(tsv.getUserReviewdList(email), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{email}/liked", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<TVVO>> getUserLikedList (@PathVariable("email") String email){
		return new ResponseEntity<List<TVVO>>(tsv.getUserLikedList(email), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{email}/rated", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<TVVO>> getUserRatedList(@PathVariable("email") String email){
		return new ResponseEntity<List<TVVO>>(tsv.getUserRatedList(email), HttpStatus.OK);
	}
	
}
