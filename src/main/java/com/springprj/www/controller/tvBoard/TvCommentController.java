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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springprj.www.domain.PagingVO;
import com.springprj.www.domain.tvBoard.TvCommentVO;
import com.springprj.www.handler.PagingHandler;
import com.springprj.www.service.tvBoard.TvCommentService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/tvComment/*")
@Controller
public class TvCommentController {
	@Inject
	private TvCommentService tvcsv;
	
	@PostMapping(value = "/post", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> post(@RequestBody TvCommentVO tvcvo){
		log.debug(">>> {}", tvcvo);
		return tvcsv.register(tvcvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping(value = "/{tvbId}/{page}", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<PagingHandler> spread(@PathVariable("tvbId") long tvbId, @PathVariable("page") int page){
		PagingVO pgvo = new PagingVO(page, 10);
		return new ResponseEntity<PagingHandler>(tvcsv.getList(tvbId, pgvo), HttpStatus.OK);
	}
	
	@PutMapping(value = "/{tvcId}", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> edit(@PathVariable("tvcId") long tvcId, @RequestBody TvCommentVO tvcvo){
		return tvcsv.modify(tvcvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/{tvcId}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> eraseOne(@PathVariable("tvcId") long tvcId){
		return tvcsv.removeOne(tvcId) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@DeleteMapping(value = "/all/{tvbId}", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> eraseAll(@PathVariable("tvbId") long tvbId){
		return tvcsv.removeAll(tvbId) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
