package org.zerock.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;

// 로그를 보기위해 사용
@Log4j
// 계층 구조상 주로 비즈니스 영역을 담당하는 객채임을 표시
@Service
// 모든 파라미터를 이용하는 생성자를 만들기위해 사용
@AllArgsConstructor
public class BoardServiceImpl implements BoardService{

	@Setter(onMethod_= @Autowired)
	private BoardMapper mapper;
	
	@Override
	public void resister(BoardVO board) {
		
		log.info("register......." + board);
		
		mapper.insertSelectKey(board);
	}

	@Override
	public BoardVO get(Long bno) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean modify(BoardVO board) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Long bno) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<BoardVO> getList() {
		// TODO Auto-generated method stub
		return null;
	}

}
