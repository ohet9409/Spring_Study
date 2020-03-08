package org.zerock.controller;

import java.sql.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
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
	
	// get, post ��� ��� �����ؾ��ϴ� ��� �迭�� ó��
	@RequestMapping(value="/basic", method= {RequestMethod.GET, RequestMethod.POST})
	public void basicGet() {
		log.info("basic get.............");
	}
	
	// ���� get��ĸ� ���
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get......");
	}
	
	// ���: /sample/ex01
	@GetMapping("/ex01")
	public String ex01(SampleDTO dto) {
		log.info("" + dto);
		return "ex01";
	}
	
	@GetMapping("/ex02")
	//@RequestParam: ���� ������ �̸��� ���޵Ǵ� �Ķ������ �̸��� �ٸ� ��쿡 �����ϰ� ����
	public String ex02(@RequestParam("name1") String name, @RequestParam("age1") int age) {
		
		log.info("name: " +name);
		log.info("age: " +age);
		
		return "ex02";
	}
	
	@GetMapping("ex02List")
	// ������ �̸��� �Ķ���Ͱ� ������ ���޵Ǵ� ��쿡�� arrayList<> ���� �̿��ؼ� ó���� ����
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
	// �Ķ���͸� ��ȯ�ؼ� ó���ؾ� �ϴ� ���
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
	
	@GetMapping("/ex04")
	//@ModelAttribute: �Ķ���ͷ� ���޵� �����͸� �ٽ� ȭ�鿡�� ����ؾ� �� ��쿡 �����ϰ� ��� 
	public String ex04(SampleDTO dto, @ModelAttribute("page") int page) {
		
		log.info("dto: " + dto);
		log.info("page: " + page);
		
		return "/sample/ex04";
	}
	
	@GetMapping("/ex05")
	public void ex05() {
		log.info("/ex05..........");
	}
	
	@GetMapping("/ex06")
	// jsonŸ������ ����
	public @ResponseBody SampleDTO ex06() {
		log.info("/ex06..........");
		SampleDTO dto = new SampleDTO();
		dto.setAge(10);
		dto.setName("ȫ�浿");
		
		return dto;
	}
	
	@GetMapping("/ex07")
	//ResponseEntity: HttpHeaders ��ü�� ���ؼ� ���ϴ� http ��� �޽����� �����ϴ� ���� ����
	public ResponseEntity<String> ex07() {
		log.info("/ex07.......");
		
		// {"name" : "ȫ�浿"}
		String msg = "{\"name\": \"ȫ�浿\"}";
		
		
		HttpHeaders header = new HttpHeaders();
		// jsonŸ������ ����
		header.add("Content-Type", "application/json;charset=UTF-8");
		
		return new ResponseEntity<String>(msg, header, HttpStatus.OK);
	}
	
	@GetMapping("/exUpload")
	public void exUpload() {
		log.info("/exUpload.............");
	}
	
	@PostMapping("/exUploadPost")
	public void exUploadPost(ArrayList<MultipartFile> files) {
		
		files.forEach(file ->{
			log.info("--------------------------");
			log.info("name: " + file.getOriginalFilename());
			log.info("size: " + file.getSize());
		});
	}
}
