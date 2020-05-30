package org.zerock.config;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletRegistration.Dynamic;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class WebConfig extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {RootConfig.class};
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// TODO Auto-generated method stub
		return new Class[] {ServletConfig.class};
	}

	@Override
	protected String[] getServletMappings() {
		// TODO Auto-generated method stub
		return new String[] {"/"};	// mvc 기본경로 설정
	}
	
	@Override
	protected void customizeRegistration(Dynamic registration) {
		// TODO Auto-generated method stub
		registration.setInitParameter("throwExceptionIfNoHandlerFound", "true");
		
		MultipartConfigElement multipartConfig = new MultipartConfigElement("C:\\upload\\tmp", 20971520, 41943040, 20971520);
		registration.setMultipartConfig(multipartConfig);
	}

	// 한글 깨짐 처리
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		return new Filter[] {characterEncodingFilter};
	}
}
