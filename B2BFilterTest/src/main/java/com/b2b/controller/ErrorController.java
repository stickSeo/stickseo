package com.b2b.controller;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.b2b.pagecomponent.PageComponent;

@Controller
public class ErrorController {

	private static final Logger logger = LoggerFactory.getLogger(B2bController.class);

	/**
	 * 404 error ������
	 */
	@RequestMapping(value = PageComponent.error404)
	public String error404(Locale locale, Model model) {
		
		
		return PageComponent.error404;
	}
	
	/**
	 * ũ�ν� ����Ʈ ��û ���� error ������
	 */
	@RequestMapping(value = PageComponent.errorCsrf)
	public String errorCsrf(Locale locale, Model model) {
		
		
		return PageComponent.errorCsrf;
	}
	
}
