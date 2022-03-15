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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.mBoard.MCommentVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.mBoard.MCommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/mComment/*")
@Controller
public class MCommentController {
	@Inject
	private MCommentService mcsv;
	
	@PostMapping(value = "/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody MCommentVO mcvo){
		log.debug(">>> {}", mcvo);
		return mcsv.register(mcvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{mbId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PagingHandler> spread(@PathVariable("mbId") long mbId, @PathVariable("page") int page){
		PagingVO pgvo = new PagingVO(page, 10);
		return new ResponseEntity<PagingHandler>(mcsv.getList(mbId, pgvo), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{mcId}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> edit(@PathVariable("mcId") long mcId, @RequestBody MCommentVO mcvo){
		return mcsv.modify(mcvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{mcId}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> eraseOne(@PathVariable("mcId") long mcId){
		return mcsv.removeOne(mcId) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	@DeleteMapping(value = "/all/{mbId}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> eraseAll(@PathVariable("mbId") long mbId){
		return mcsv.removeAll(mbId) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
