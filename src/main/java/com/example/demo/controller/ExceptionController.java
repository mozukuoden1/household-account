package com.example.demo.controller;

import java.util.NoSuchElementException;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

@RestControllerAdvice
public class ExceptionController {

	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView idNotFound(WebRequest request) {
		String value = request.getParameter("action");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(value);
		mav.addObject("errermessage", "エラー：指定したIDは存在していません");
		return mav;
	}
	@ExceptionHandler(BindException.class)
	public ModelAndView idByNull(WebRequest request) {
		String value = request.getParameter("action");
		ModelAndView mav = new ModelAndView();
		mav.setViewName(value);
		mav.addObject("errermessage", "エラー：IDが未入力です");
		return mav;
	}
}
