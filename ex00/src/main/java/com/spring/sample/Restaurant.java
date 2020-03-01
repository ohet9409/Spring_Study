package com.spring.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Setter;

// 스프링에서 관리해야 하는 대상임을 표시
@Component
//lombok의 setter를 생성하는 기능과 생성자, toString()을 자동으로 생성하는 어노테이션
@Data
public class Restaurant {
	
	@Setter(onMethod_= @Autowired)
	private Chef chef;
}
