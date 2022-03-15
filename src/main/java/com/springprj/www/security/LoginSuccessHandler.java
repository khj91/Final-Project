package com.springprj.www.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.springprj.www.service.user.UserService;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Getter
	@Setter
	private String authEmail;

	@Getter
	@Setter
	private String authUrl;

	@Inject
	private UserService usv;
	
	private RedirectStrategy reStg = new DefaultRedirectStrategy();

	private RequestCache reqCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		setAuthEmail(authentication.getName());
		setAuthUrl("/home");
		boolean isUp = usv.updateLastLogin(getAuthEmail()); // 질문 : 같은 클래스 안인데 게터 쓰는이유

		HttpSession ses = request.getSession(false); // 기존에 존재하는 세션 받아오기.
		if (!isUp || ses == null) {
			return;
		} else {
			// 인증 실패 기록 삭제 
			ses.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
		}
		SavedRequest savedReq = reqCache.getRequest(request, response);
		log.debug("login success, redirect to : {}", savedReq);
		reStg.sendRedirect(request, response, (savedReq != null ? savedReq.getRedirectUrl() : getAuthUrl()) );
	}
	
}
