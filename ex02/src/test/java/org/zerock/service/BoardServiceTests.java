package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {

	@Setter(onMethod_= @Autowired)
	private BoardService service;
	
	//@Test
	public void testExist() {
		
		log.info(service);
		// null�� �ƴ��� Ȯ���ϴ� �뵵
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("���� �ۼ��ϴ� �� 200317");
		board.setContent("���� �ۼ��ϴ� ���� 200317");
		board.setWriter("newbie");
		
		service.resister(board);
		
		log.info("������ �Խù��� ��ȣ: " + board.getBno());
	}
}
