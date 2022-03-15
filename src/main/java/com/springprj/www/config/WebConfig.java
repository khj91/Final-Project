package com.springprj.www.config;


import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class, 
				SecurityConfig.class
				};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {ServletConfiguration.class};
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}

	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
		encodingFilter.setEncoding("UTF-8");
		encodingFilter.setForceEncoding(true);
		return new Filter[] {encodingFilter};
	}

	@Override
	protected void customizeRegistration(ServletRegistration.Dynamic registration) {
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		String uploadLocation = "/Users/jhs/Desktop/ezenSpring/profile_img";
		int maxFileSize = 1024 * 1024 ;
		int maxReqSize = maxFileSize * 5;
		int fileSizeThreshold = maxReqSize; //메모리 에서 파일을 전송할때 만들어지는 임시 공간 	
		MultipartConfigElement multipartConfigElement = new MultipartConfigElement(uploadLocation, maxFileSize, maxReqSize, fileSizeThreshold);
		registration.setMultipartConfig(multipartConfigElement);
	}
	

}
