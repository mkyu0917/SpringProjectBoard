package org.board.persistence;

//DAO : data acess object로 데이터 접근 객체, 커넥션같은 것을 하나만 두고 여러 사용자가 DAO인터페이스를 사용하여 필요한 자료에 접근 하도록 하는 것이 DAO의 개념.

import java.util.List;

import org.board.domain.BoardVO;

public interface BoardDAO {
	
	public void create(BoardVO vo) throws Exception;
	
	public BoardVO read(Integer bno) throws Exception;
	
	public void update(BoardVO vo) throws Exception;
	
	public void delete(Integer bno)throws Exception;
	
	public List<BoardVO> listAll()throws Exception;
}
