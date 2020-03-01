package com.spring.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// junit은 반드시 4.10이상 버전 사용
// 현재 테스트 코드가 스프링을 실행하는 역할을 할 것이라는 어노테이션
@RunWith(SpringJUnit4ClassRunner.class)
// 지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// lombok을 이용해서 log를 기록하는 logger를 변수로 생성
@Log4j
public class SampleTest {
	@Setter(onMethod_= {@Autowired})
	private Restaurant restaurant;
	
	@Test
	public void testExist() {
		// restaurant 변수가 null이 아니어야만 테스트가 성공
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("------------------------");
		log.info(restaurant.getChef());
	}
}
