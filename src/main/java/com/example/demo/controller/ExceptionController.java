package com.example.demo.controller;

import java.util.NoSuchElementException;

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
		mav.addObject("errermessage", "指定したIDは存在していません");
		return mav;
	}
}
