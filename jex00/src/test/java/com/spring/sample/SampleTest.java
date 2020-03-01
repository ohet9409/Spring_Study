package com.spring.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.config.RootConfig;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {RootConfig.class})
@Log4j
public class SampleTest {
	
	@Setter(onMethod_= {@Autowired})
	private Restaurant restaurant;
	
	@Test
	public void SampleTests() {
		assertNotNull(restaurant);
		log.info(restaurant);
		log.info("---------------------");
		log.info(restaurant.getChef());
	}
}
