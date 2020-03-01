package com.spring.sample;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

// junit�� �ݵ�� 4.10�̻� ���� ���
// ���� �׽�Ʈ �ڵ尡 �������� �����ϴ� ������ �� ���̶�� ������̼�
@RunWith(SpringJUnit4ClassRunner.class)
// ������ Ŭ������ ���ڿ��� �̿��ؼ� �ʿ��� ��ü���� ������ ���� ��ü�� ���
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
// lombok�� �̿��ؼ� log�� ����ϴ� logger�� ������ ����
@Log4j
public class SampleTest {
	@Setter(onMethod_= {@Autowired})
	private Restaurant restaurant;
	
	@Test
	public void testExist() {
		// restaurant ������ null�� �ƴϾ�߸� �׽�Ʈ�� ����
		assertNotNull(restaurant);
		
		log.info(restaurant);
		log.info("------------------------");
		log.info(restaurant.getChef());
	}
}
