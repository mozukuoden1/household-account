package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService service;
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView loginpage(@AuthenticationPrincipal UserDetails user, ModelAndView mav) {
		mav.setViewName("login");
		if(user != null) {
			mav.addObject("loginUserName", user.getUsername());
		}
		System.out.println("loginpage");
		return mav;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ModelAndView login(ModelAndView mav) {
		System.out.println("success");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping("/index")
	public ModelAndView index(ModelAndView mav) {
		mav.setViewName("index");
		System.out.println("index");
		return mav;
	}
	
	@RequestMapping("/")
	public ModelAndView index2(ModelAndView mav) {
		mav.setViewName("index");
		System.out.println("index");
		return mav;
	}
	
	/*@RequestMapping("/github_redirect")
	public String github_redirect(ModelAndView mav) {
		return "redirect:https://github.com/mozukuoden1/Practice2";
	}*/
 	/*@RequestMapping("/")
	public ModelAndView defoult(ModelAndView mav) {
		mav.setViewName("index");
		
		System.out.println("/");
		return mav;
	}*/
	
	/*@RequestMapping(value = "/index", method = RequestMethod.POST)
	public ModelAndView loginsuccess(ModelAndView mav) {
		mav.setViewName("main");
		
		System.out.println("success");
		return mav;
	}*/
}
