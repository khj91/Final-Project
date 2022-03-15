package com.springprj.www.controller.user;

import java.io.File;
import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.springprj.www.handler.ProfileImgHandler;
import com.springprj.www.security.UserVO;
import com.springprj.www.service.movie.MovieService;
import com.springprj.www.service.shop.PurchaseService;
import com.springprj.www.service.shop.ShopService;
import com.springprj.www.service.tv.TVService;
import com.springprj.www.service.user.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/user/*")
public class UserController {

	@Inject
	private UserService usv;

	@Inject
	private MovieService msv;

	@Inject
	private TVService tsv;

	@Inject
	private BCryptPasswordEncoder bcpEncoder;

	@Inject
	private PurchaseService psv;

	@Inject
	private ProfileImgHandler phd;

	@GetMapping("/register")
	public void register() {
	}

	@PostMapping("/register")
	public String register(UserVO uvo, RedirectAttributes reAttr) {
		uvo.setPwd(bcpEncoder.encode(uvo.getPwd()));
		int isSuccess = usv.registerUser(uvo);
		reAttr.addFlashAttribute("isSuccess", isSuccess);
		return "redirect:/user/login";
	}

	@PostMapping(value = "/email", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> availableEmailCheck(@RequestBody Map<String, String> data) {

		return new ResponseEntity<String>(usv.getUserDetail(data.get("email")) == null ? "1" : "0", HttpStatus.OK);
	}

	@PostMapping(value = "/nickName", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> availableNickNameCheck(@RequestBody Map<String, String> data) {
		return new ResponseEntity<String>(usv.checkValidNickName(data.get("nickName")) ? "1" : "0", HttpStatus.OK);
	}

	@GetMapping("/findId")
	public void findId() {
	}

	@PostMapping("/findId")
	public String findId(String name, String nickName, RedirectAttributes reAttr) {
		reAttr.addFlashAttribute("email", usv.findEmailByNameAndNickName(name, nickName));
		return "redirect:/user/login";
	}

	@GetMapping("/findPwd")
	public void findPwd() {
	}

	@PostMapping("findPwd")
	public String findPwd(String email, String name, String nickName, String pwd, RedirectAttributes reAttr) {
		UserVO uvo = usv.getUserDetail(email);
		int isMatch = 1;
		isMatch *= uvo.getEmail().equals(email) ? 1 : 0;
		isMatch *= uvo.getName().equals(name) ? 1 : 0;
		isMatch *= uvo.getNickName().equals(nickName) ? 1 : 0;
		if (isMatch > 0) {
			isMatch *= usv.updateUserPwd(email, bcpEncoder.encode(pwd));
		}
		reAttr.addFlashAttribute("isUp", isMatch);
		return "redirect:/user/login";
	}

	@GetMapping("/login")
	public void login() {
	}

	@PostMapping("/login")
	public String login(HttpServletRequest request, RedirectAttributes reAttr) {

		reAttr.addFlashAttribute("email", request.getAttribute("email"));
		reAttr.addFlashAttribute("errMsg", request.getAttribute("errMsg"));
		log.debug(">>>>>>>>logged in");
		return "redirect:/user/login";
	}

	@PostMapping("/logout")
	public String logout(String email, HttpSession session) {
		session.invalidate();

		return "redirect:/user/login";
	}

	@GetMapping("/{email}")
	public String detail(HttpSession session, Model model, @PathVariable("email") String email,
			RedirectAttributes reAttr) {
		ObjectMapper mapper = new ObjectMapper();
		log.debug("{}'s main detail page", email);
		if (usv.getUserDetail(email) == null) {
			return "error/noUser";
		}
		model.addAttribute("list", "main");
		model.addAttribute("tvAvg", usv.getUsersAvgTVRating(email));
		model.addAttribute("movieAvg", usv.getUsersAvgMovieRating(email));
		model.addAttribute("uvo", usv.getUserDetail(email));

		model.addAttribute("mLikedCnt", msv.getUserLikedList(email).size());
		model.addAttribute("tLikedCnt", tsv.getUserLikedList(email).size());
		model.addAttribute("mRatedCnt", msv.getUserRatedList(email).size());
		model.addAttribute("tRatedCnt", tsv.getUserRatedList(email).size());
		model.addAttribute("mReviewedCnt", msv.getUserReviewedList(email).size());
		model.addAttribute("tReviewCnt", tsv.getUserReviewdList(email).size());

		try {
			model.addAttribute("movieGenres",
					mapper.writeValueAsString(usv.getUsersWatchedMovieGenres(email).getGenres()));
			model.addAttribute("tvGenres", mapper.writeValueAsString(usv.getUsersWatchedTVGenres(email).getGenres()));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		model.addAttribute("mRateData", usv.getUsersMovieRateData(email));
		model.addAttribute("tRateData", usv.getUsersTVRateData(email));
		model.addAttribute("fontList", psv.getUsersFontColorList(email));
		return "user/detail";
	}

	@GetMapping(value = { "/{email}/likedList", "/{email}/likedList/{tv}" })
	public String likedList(@PathVariable(name = "email") String email,
			@PathVariable(name = "tv", required = false) String tv, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		if (usv.getUserDetail(email) == null) {
			return "error/noUser";
		}
		model.addAttribute("platform", tv != null ? "tv" : "movie");
		model.addAttribute("list", "liked");
		model.addAttribute("tvAvg", usv.getUsersAvgTVRating(email));
		model.addAttribute("movieAvg", usv.getUsersAvgMovieRating(email));
		model.addAttribute("uvo", usv.getUserDetail(email));
		model.addAttribute("mLikedCnt", msv.getUserLikedList(email).size());
		model.addAttribute("tLikedCnt", tsv.getUserLikedList(email).size());
		try {
			model.addAttribute("moviesData", mapper.writeValueAsString(msv.getUserLikedList(email)));
			model.addAttribute("tvsData", mapper.writeValueAsString(tsv.getUserLikedList(email)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "user/detail";
	}

	@GetMapping(value = { "/{email}/ratedList", "/{email}/ratedList/{tv}" })
	public String ratedList(@PathVariable(name = "email") String email,
			@PathVariable(name = "tv", required = false) String tv, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		if (usv.getUserDetail(email) == null) {
			return "error/noUser";
		}
		model.addAttribute("platform", tv != null ? "tv" : "movie");
		model.addAttribute("list", "rated");
		model.addAttribute("tvAvg", usv.getUsersAvgTVRating(email));
		model.addAttribute("movieAvg", usv.getUsersAvgMovieRating(email));
		model.addAttribute("uvo", usv.getUserDetail(email));
		model.addAttribute("mRatedCnt", msv.getUserRatedList(email).size());
		model.addAttribute("tRatedCnt", tsv.getUserRatedList(email).size());

		// 영화 id 리스트를 자바스크립트로 주면, 자바스크립트에서 각각 getDetail로 정보 받아오기..?
		// 받아온 영화 리스트에 좋아요여부를 어떻게 넣을지..?
		try {
			model.addAttribute("moviesData", mapper.writeValueAsString(msv.getUserRatedList(email)));
			model.addAttribute("tvsData", mapper.writeValueAsString(tsv.getUserRatedList(email)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "user/detail";
	}

	@GetMapping(value = { "/{email}/reviewedList", "/{email}/reviewedList/{tv}" })
	public String reviewedList(@PathVariable(name = "email") String email,
			@PathVariable(name = "tv", required = false) String tv, Model model) {
		ObjectMapper mapper = new ObjectMapper();
		if (usv.getUserDetail(email) == null) {
			return "error/noUser";
		}
		model.addAttribute("platform", tv != null ? "tv" : "movie");
		model.addAttribute("list", "reviewed");
		model.addAttribute("tvAvg", usv.getUsersAvgTVRating(email));
		model.addAttribute("movieAvg", usv.getUsersAvgMovieRating(email));
		model.addAttribute("uvo", usv.getUserDetail(email));
		model.addAttribute("mReviewedCnt", msv.getUserReviewedList(email).size());
		model.addAttribute("tReviewedCnt", tsv.getUserReviewdList(email).size());

		try {
			model.addAttribute("moviesData", mapper.writeValueAsString(msv.getUserReviewedList(email)));
			model.addAttribute("tvsData", mapper.writeValueAsString(tsv.getUserReviewdList(email)));
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return "user/detail";
	}

	@GetMapping("/{email}/poster")
	public String posterList(Model model, @PathVariable("email") String email) {
		if (usv.getUserDetail(email) == null) {
			return "error/noUser";
		}
		model.addAttribute("list", "poster");
		model.addAttribute("tvAvg", usv.getUsersAvgTVRating(email));
		model.addAttribute("movieAvg", usv.getUsersAvgMovieRating(email));
		model.addAttribute("uvo", usv.getUserDetail(email));
		model.addAttribute("posterList", psv.getUsersPosterList(email));
		return "user/detail";
	}

	@GetMapping("/{email}/modify")
	public String modify(@PathVariable("email") String email, Model model) {

		model.addAttribute("uvo", usv.getUserDetail(email));
		model.addAttribute("fontList", psv.getUsersFontColorList(email));
		return "user/modify";
	}

	@GetMapping("/modify/nickName")
	public String modifyNickName(Principal principal, Model model) {
		model.addAttribute("uvo", usv.getUserDetail(principal.getName()));
		return "user/changeNickName";
	}

	@PostMapping("/modify/nickName")
	public String modifyEmail(String email, String nickName, String pwd, RedirectAttributes reAttr) {
		int isUp = 0;
		if (bcpEncoder.matches(pwd, usv.getUserDetail(email).getPwd())) {
			isUp = usv.updateUserNickName(email, nickName);
		}
		reAttr.addFlashAttribute("isUp", isUp);
		return "redirect:/user/" + email + "/modify";
	}

	@GetMapping("/modify/pwd")
	public String modifyPwd(Principal principal, Model model) {
		if (principal != null) {
			String email = principal.getName();
			model.addAttribute("email", email);
		}

		return "user/changePassword";
	}

	@PostMapping("/modify/pwd")
	public String modifyPwd(HttpServletRequest request, HttpServletResponse response, Authentication authentication,
			String email, String pwd, String newPwd, RedirectAttributes reAttr) {
		int isUp = 0;
		if (bcpEncoder.matches(pwd, usv.getUserDetail(email).getPwd())) {
			isUp = usv.updateUserPwd(email, bcpEncoder.encode(newPwd));
			if (isUp > 0) {
				reAttr.addFlashAttribute("isUp", isUp);
				new SecurityContextLogoutHandler().logout(request, response, authentication);
				return "redirect:/user/login";
			}
		} else {
			reAttr.addFlashAttribute("wrongPwd", "1");
		}
		reAttr.addFlashAttribute("isUp", isUp);
		return "redirect:/user/" + email + "/modify";
	}

	@PostMapping("/modify/profileImg")
	public String modifyProfileImg(String email, String url, @RequestParam("file") MultipartFile file,
			RedirectAttributes reAttr) {
		String fileName = phd.uploadFile(file);
		phd.removeFile(usv.getUserDetail(email).getProfileImg());
		int isUp = usv.updateUserProfileImg(email, fileName);
		reAttr.addFlashAttribute("isUp", isUp);
		return "redirect:/user/" + email + "/modify";
	}

	@PostMapping("/modify/fontColor")
	public String modiftFontColor(String email, String color, RedirectAttributes reAttr) {
		reAttr.addFlashAttribute("isUp", usv.updateUserFontColor(email, color));
		return "redirect:/user/" + email + "/modify";
	}

	@GetMapping("/{email}/setting")
	public String setting(Model model, @PathVariable("email") String email) {
		UserVO uvo = usv.getUserDetail(email);
		model.addAttribute("uvo", uvo);
		model.addAttribute("adult", uvo.isAdult());
		model.addAttribute("slang", uvo.isSlang());
		return "user/setting";
	}

	@PostMapping("/{email}/setting")
	public String setting(@PathVariable("email") String email, boolean slang, boolean adult,
			RedirectAttributes reAttr) {
		log.debug("{}'s adult setting: {}", email, adult);
		int isSuccess = usv.updateUserSetting(email, slang, adult);
		reAttr.addFlashAttribute("isSuccess", isSuccess);
		return "redirect:/user/" + email + "/setting";
	}

	@PostMapping(value = "/remove", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> remove(@RequestBody UserVO uvo) {
		return usv.removeUser(uvo.getEmail(), uvo.getPwd()) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping(value = "/{email}/gainPoints", consumes = "application/json", produces = {
			MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> gainPoints(@RequestBody Map<String, Integer> map, @PathVariable("email") String email) {

		return usv.gainPoint(email,map.get("point")) > 0
				? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>("0", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	// ====================== admin =======================

	@GetMapping("/userList")
	public void userList(Authentication authentication, Model model) {
		// @@@@@@@@@@@@@@@@@ ROLE_MANAGER(grade 50 이상) 인지 @@@@@@@@@@@@@@@@@@
		//
//		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
//		System.out.println("role = " + userDetails.getAuthorities().stream().map(r -> String.valueOf(r)).collect(Collectors.joining(",")));
//		
		List<UserVO> list = usv.getUsersList("grade");
		log.info("userList: {}", list);
		model.addAttribute("list", list);

	}

	@PostMapping(value = "/modGrade", consumes = "application/json", produces = { MediaType.TEXT_PLAIN_VALUE })
	public ResponseEntity<String> modifyGrade(@RequestBody UserVO uvo) {
		return usv.updateUserGrade(uvo.getEmail(), uvo.getGrade()) > 0 ? new ResponseEntity<String>("1", HttpStatus.OK)
				: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}

	// 프로필 가져오는 주소
	@PostMapping(value = "/profileImg/{email}", consumes = "application/json", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<UserVO> profileImgAndNick(@PathVariable("email") String email) {
		return new ResponseEntity<UserVO>(usv.getUserDetail(email), HttpStatus.OK);
	}
}
