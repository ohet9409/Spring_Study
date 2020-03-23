package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {org.zerock.config.RootConfig.class, org.zerock.config.ServletConfig.class})
@Log4j
public class BoardMapperTests {

	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;

	// @Test
	public void testGetList() {
		mapper.getList().forEach(board1 -> log.info(board1));
	}

	// @Test
	public void testInsert() {

		BoardVO board = new BoardVO();
		board.setTitle("�����ۼ��ϴ±� 200315");
		board.setContent("�����ۼ��ϴ³��� 200315");
		board.setWriter("newbie");

		mapper.insert(board);

		log.info(board);
	}

	//@Test
	public void testInsertSelectKey() {

		BoardVO board = new BoardVO();
		board.setTitle("�����ۼ��ϴ±� 200315 select key");
		board.setContent("�����ۼ��ϴ³��� 200315 select key");
		board.setWriter("newbie");

		mapper.insertSelectKey(board);

		log.info(board);
	}
	
	//@Test
	public void testRead() {
		
		// �����ϴ� �Խù� ��ȣ�� �׽�Ʈ
		BoardVO board = mapper.read(23822581L);
		
		log.info(board);
	}
	
	//@Test
	public void testDelte() {
		log.info("DELETE COUNT: " + mapper.delete(23822582L));
	}
	
	//@Test
	public void testUpdate() {
		
		BoardVO board = new BoardVO();
		// ������ �����ϴ� ��ȣ���� Ȯ���� ��
		board.setBno(23822581L);
		board.setTitle("������ ���� 200315");
		board.setContent("������ ���� 200315");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT: " + count);
	}
}