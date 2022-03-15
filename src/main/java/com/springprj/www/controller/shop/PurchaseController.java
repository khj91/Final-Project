package com.springprj.www.controller.shop;

import java.security.Principal;
import java.util.List;

import javax.inject.Inject;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.springprj.www.domain.shop.ProductVO;
import com.springprj.www.service.shop.PurchaseService;
import com.springprj.www.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/purchase/*")
public class PurchaseController {

	@Inject
	private PurchaseService psv;
	
	@Inject
	private UserService usv;
	
	@GetMapping("/font")
	public void font(Model model, Principal principal) {
		if(principal != null) {
			List<ProductVO> list = psv.getUsersFontColorList(principal.getName());
			String csv = "";
			for (ProductVO pvo : list) {
				csv += (pvo.getSname()+",");
			}
			model.addAttribute("list", list);
			model.addAttribute("currPoints", usv.getUsersCurrPoints(principal.getName()));
			model.addAttribute("fontCsv", csv);
			model.addAttribute("email", principal.getName());
		}
	}
	
	@GetMapping("/poster")
	public void poster(Model model, String email) {
		List<ProductVO> list =  psv.getUsersPosterList(email);
		
	}
	
	@PostMapping(value = "/register", consumes = "application/json", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> register(@RequestBody ProductVO pdvo) {
		if(usv.spendPoint(pdvo.getEmail(), pdvo.getPrice()) > 0) {
			return psv.buyProduct(pdvo) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return new ResponseEntity<String>("-1", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping(value = "/{email}/poster", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ProductVO>> getPoster(@PathVariable("email") String email){
		List<ProductVO> posterList = psv.getUsersPosterList(email);
		return new ResponseEntity<List<ProductVO>>(posterList != null ? posterList : null, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{email}/font", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ProductVO>> getFontColor (@PathVariable("email") String email){
		List<ProductVO> fontColorList = psv.getUsersFontColorList(email);
		return new ResponseEntity<List<ProductVO>>(fontColorList != null ? fontColorList : null, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{email}/all", produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<List<ProductVO>> getAll (@PathVariable("email") String email){
		List<ProductVO> productList = psv.getUsersAllProduct(email);
		return new ResponseEntity<List<ProductVO>>(productList != null ? productList : null, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/{sid}/del", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> deleteOneProduct(@PathVariable("sid") long sid){
		return psv.removeProduct(sid) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	@DeleteMapping(value = "/{email}/delAll", produces = {MediaType.TEXT_PLAIN_VALUE})
	public ResponseEntity<String> deleteAllProduct(@PathVariable("email") String email){
		return psv.removeAllProduct(email) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK) : new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
