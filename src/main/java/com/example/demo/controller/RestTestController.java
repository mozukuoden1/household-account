package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.HouseholdAccountData;
import com.example.demo.service.HouseholdAccountService;

@RestController
public class RestTestController {
	
	@Autowired
	HouseholdAccountService service;
	
	@RequestMapping("/test")
	public HouseholdAccountData test() {
		System.out.println("通信成功");
		Optional<HouseholdAccountData> result = service.findById(1);
		HouseholdAccountData test = result.get();
		return test;
	}
}
