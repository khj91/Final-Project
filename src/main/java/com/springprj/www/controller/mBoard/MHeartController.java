package com.springprj.www.controller.mBoard;

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
import com.springprj.www.domain.mBoard.MHeartVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.mBoard.MHeartService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/mHeart/*")
@Controller
public class MHeartController {
	@Inject
	private MHeartService mhsv;
	
	@PostMapping(value = "/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody MHeartVO mhvo) {
		return mhsv.register(mhvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{mbId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PagingHandler> list(@PathVariable("mbId") long mbId, @PathVariable("page") int page){
		PagingVO pgvo = new PagingVO(page, 10);
		return new ResponseEntity<PagingHandler>(mhsv.getList(mbId, pgvo), HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{mbId}/{authEmail}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> erase(@PathVariable("mbId") long mbId, @PathVariable("authEmail") String authEmail){
		return mhsv.removeOne(mbId, authEmail) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
