package org.zerock.controller;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
	
	@RequestMapping("")
	public void basic() {
		log.info("basic........");
	}
	
	// get, post 방식 모두 지원해야하는 경우 배열로 처리
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.............");
	}
	
	// 오직 get방식만 사용
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get......");
	}
	
	// 경로: /sample/ex01
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	//@RequestParam: 사용된 변수의 이름과 전달되는 파라미터의 이름이 다를 경우에 유용하게 사용됨
	public String ex02(@RequestParam("name1") String name, @RequestParam("age1") int age) {
		
		log.info("name: " +name);
		log.info("age: " +age);
		
		return "ex02";
	}
	
	@GetMapping("ex02List")
	// 동일한 이름의 파라미터가 여러개 전달되는 경우에는 arrayList<> 등을 이용해서 처리가 가능
	public String ex02List(@RequestParam("ids")ArrayList<String> ids) {
		log.info("ids: " + ids);
		return "ex02List";
	}
	
	@GetMapping("/ex02Array")
	public String ex02Array(@RequestParam("ids") String[] ids) {
		log.info("array ids: " + Arrays.toString(ids));
		return "ex02Array";
	}
	
	@GetMapping("/ex02Bean")
	public String ex02Bean(SampleDTOList list) {
		log.info("list dtos: " + list);
		return "ex02Bean";
	}
	/*
	// 파라미터를 변환해서 처리해야 하는 경우
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// 2018-01-01
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	*/
	@GetMapping("/ex03")
	public String ex03(TodoDTO todo) {
		log.info("todo: " + todo);
		return "ex03";
	}
}
