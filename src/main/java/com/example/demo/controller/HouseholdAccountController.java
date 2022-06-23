package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.entity.CategoryData;
import com.example.demo.entity.HouseholdAccountData;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.HouseholdAccountRepository;

@Controller
public class HouseholdAccountController {
	
	@Autowired
	HouseholdAccountRepository repository;
	@Autowired
	CategoryRepository categoryRepository;
	
	@RequestMapping(value = "/main", method = RequestMethod.GET)
	public ModelAndView main(ModelAndView mav) {
		//repository.findByDateBetween("20220620", "20220630");
		List<HouseholdAccountData> list = repository.findAll();
		mav.addObject("datalist", list);
		System.out.println("main");
		mav.setViewName("main");
		return mav;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.GET)
	public ModelAndView regist(ModelAndView mav) {
		//repository.findByDateBetween("20220620", "20220630");
		System.out.println("regist:get");
		mav.setViewName("regist");
		return mav;
	}
	
	@RequestMapping(value = "/regist", method = RequestMethod.POST)
	public ModelAndView regist(@ModelAttribute() HouseholdAccountData data, ModelAndView mav) {
		//repository.findByDateBetween("20220620", "20220630");
		repository.saveAndFlush(data);
		System.out.println("regist:post");
		mav.setViewName("regist");
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(ModelAndView mav) {
		System.out.println("regist:post");
		mav.setViewName("update");
		return mav;
	}
	
	@RequestMapping(value = "/selectid", method = RequestMethod.POST)
	public ModelAndView selectid(@ModelAttribute() HouseholdAccountData data, @RequestParam(value = "action") String value, ModelAndView mav) {
		Optional<HouseholdAccountData> result = repository.findById(data.getId());
		System.out.println("selectid");
		mav.addObject("result", result.get());
		System.out.println(value);
		mav.setViewName(value);
		return mav;
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@Transactional
	public ModelAndView update(@ModelAttribute() HouseholdAccountData data, ModelAndView mav) {
		System.out.println("update");
		repository.saveAndFlush(data);
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
		System.out.println(data);
		repository.deleteById(data.getId());
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
			categoryRepository.saveAndFlush(categoryDatas[i]);
		}
		
		HouseholdAccountData data = new HouseholdAccountData();
		data.setDate("2022-06-22");
		data.setExpense(100);
		data.setMemo("ダミー");
		data.setIncome(100);
		data.setCategoryId(1);
		repository.saveAndFlush(data);
		
		/*categoryData2.setHouseholdAccountData(Arrays.asList(data));
		categoryRepository.saveAndFlush(categoryData2);*/
		
		HouseholdAccountData data2 = new HouseholdAccountData();
		data2.setDate("2022-06-23");
		data2.setIncome(100);
		data2.setMemo("ダミー22");
		data2.setExpense(100);
		data2.setCategoryId(7);
		repository.saveAndFlush(data2);

	}
}
