package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.Authorities;
import com.example.demo.entity.CategoryData;
import com.example.demo.entity.HouseholdAccountData;
import com.example.demo.entity.UserData;
import com.example.demo.service.HouseholdAccountService;
import com.example.demo.service.UserService;

@Controller
public class HouseholdAccountController {
	
	@Autowired
	HouseholdAccountService service;
	@Autowired
	UserService userService;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	/*@RequestMapping(value = "/index")
	public ModelAndView index(ModelAndView mav) {
		System.out.println("index");
		mav.setViewName("index");
		return mav;
	}*/
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(ModelAndView mav) {
		List<HouseholdAccountData> list = service.carrentDate();
		mav.addObject("datalist", list);
		mav.addObject("datemessage", "今月のデータ");
		System.out.println("main");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value = "/main", method = RequestMethod.POST)
	public ModelAndView main(@RequestParam("kikan_toshi") int year,@RequestParam("kikan_tsuki") int month, ModelAndView mav) {
		
		List<HouseholdAccountData> list = service.selectDate(year, month);
		mav.addObject("datalist", list);
		mav.addObject("year", year);
		mav.addObject("month", month);
		mav.addObject("datemessage", year + "年" + month + "月のデータ");
		System.out.println("main");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public ModelAndView regist(ModelAndView mav) {
		System.out.println("regist:get");
		mav.setViewName("regist");
		return mav;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute() HouseholdAccountData data, ModelAndView mav) {
		service.saveAndFlush(data);
		System.out.println("regist:post");
		mav.setViewName("regist");
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(ModelAndView mav) {
		System.out.println("update:get");
		mav.setViewName("update");
		return mav;
	}
	
	@RequestMapping(value = "/selectid", method = RequestMethod.POST)
	public ModelAndView selectid(@ModelAttribute() HouseholdAccountData data, @RequestParam("action") String value, ModelAndView mav) {
		Optional<HouseholdAccountData> result = service.findById(data, value);
		System.out.println("selectid");
		System.out.println(value);
		mav.addObject("result", result.get());
		System.out.println("action:" + value);
		mav.setViewName(value);
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute() HouseholdAccountData data, ModelAndView mav) {
		System.out.println("update");
		service.saveAndFlush(data);
		mav.setViewName("update");
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(ModelAndView mav) {
		System.out.println("delete:get");
		mav.setViewName("delete");
		return mav;
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public ModelAndView delete(@ModelAttribute() HouseholdAccountData data,ModelAndView mav) {
		System.out.println("delete:post");
		service.deleteById(data);
		mav.setViewName("delete");
		return mav;
	}
	@PostConstruct
	public void init() {
		String[] categoryes = {"給与", "家賃", "食費", "日用品", "水道光熱費", "交際費", "その他"};
		CategoryData[] categoryDatas = new CategoryData[categoryes.length];
		for(int i = 0; i < categoryes.length;i++) {
			categoryDatas[i] = new CategoryData();
			categoryDatas[i].setCategoryname(categoryes[i]);
			service.saveAndFlush(categoryDatas[i]);
		}
		
		HouseholdAccountData data = new HouseholdAccountData();
		data.setDate("2022-06-22");
		data.setExpense(0);
		data.setMemo("ダミー");
		data.setIncome(100);
		data.setCategoryId(1);
		service.saveAndFlush(data);
		
		/*categoryData2.setHouseholdAccountData(Arrays.asList(data));
		categoryRepository.saveAndFlush(categoryData2);*/
		
		HouseholdAccountData data2 = new HouseholdAccountData();
		data2.setDate("2022-06-23");
		data2.setIncome(100);
		data2.setMemo("ダミー22");
		data2.setExpense(100);
		data2.setCategoryId(7);
		service.saveAndFlush(data2);
		
		UserData user = new UserData();
		user.setUsername("user1");
		user.setPassword(passwordEncoder.encode("password1"));
		user.setEnable(true);
		userService.saveAndFlush(user);
		
		Authorities auth = new Authorities();
		auth.setUsername("user1");
		auth.setAuthority("ROLE_USER");
		auth.setUserdata(user);
		userService.saveAndFlush(auth);
		/*user.setAuthorities(auth);
		userService.saveAndFlush(user);*/
	}
}
