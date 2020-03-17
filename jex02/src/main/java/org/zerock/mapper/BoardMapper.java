package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;

public interface BoardMapper {
	
	// XML에 sql문이 처리되었을 경우 주석처리
	//@Select("select * from tbl_board where bno > 0 and rownum <=5")
	public List<BoardVO> getList();
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int	delete(Long bno);
	
	public int update(BoardVO board);
}
