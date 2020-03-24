package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	
	// XML�� sql���� ó���Ǿ��� ��� �ּ�ó��
	//@Select("select * from tbl_board where bno > 0 and rownum <=5")
	public List<BoardVO> getList();
	
	// ����¡ ó���� ���� �޼���
	public List<BoardVO> getListWithPaging(Criteria cri);
	
	public void insert(BoardVO board);
	
	public void insertSelectKey(BoardVO board);
	
	public BoardVO read(Long bno);
	
	public int	delete(Long bno);
	
	public int update(BoardVO board);
	
	// ��ü �������� ���� ó��
	public int getTotalCount(Criteria cri);
}
