package com.springprj.www.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
@Setter
@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
	
	private String authEmail;
	private String errorMessage;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		log.debug("login failure");
		setAuthEmail(request.getParameter("email")); // 로그인 실패한 이메일
		if(exception instanceof BadCredentialsException || exception instanceof InternalAuthenticationServiceException) {
			setErrorMessage(exception.getMessage().toString());
		}
		log.debug(">>> Login Failure : {}", errorMessage);
		request.setAttribute("failedemail" , getAuthEmail());
		request.setAttribute("errMsg", errorMessage);
		request.getRequestDispatcher("/user/login?error").forward(request, response);
	}

}
