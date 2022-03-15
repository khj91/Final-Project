package com.springprj.www.controller.tvBoard;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvHeartVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.tvBoard.TvHeartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/tvHeart/*")
@Controller
public class TvHeartController {
	@Inject
	private TvHeartService tvhsv;
	
	@PostMapping(value = "/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody TvHeartVO tvhvo) {
		return tvhsv.register(tvhvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{tvbId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PagingHandler> list(@PathVariable("tvbId") long tvbId, @PathVariable("page") int page){
		PagingVO pgvo = new PagingVO(page, 10);
		return new ResponseEntity<PagingHandler>(tvhsv.getList(tvbId, pgvo), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{tvbId}/{authEmail}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> erase(@PathVariable("tvbId") long tvbId, @PathVariable("authEmail") String authEmail){
		return tvhsv.removeOne(tvbId, authEmail) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
