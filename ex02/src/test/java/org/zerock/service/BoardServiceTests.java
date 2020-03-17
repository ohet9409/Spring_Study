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
		// null이 아님을 확인하는 용도
		assertNotNull(service);
	}
	
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 200317");
		board.setContent("새로 작성하는 내용 200317");
		board.setWriter("newbie");
		
		service.resister(board);
		
		log.info("생성된 게시물의 번호: " + board.getBno());
	}
}
