
// 실제 비지니스 로직을 생성, 비지니스 로직이 들어가는 부분, 컨트롤러에서 요청한 메소드를 선택하여 실행,
package org.board.service;

import java.util.List;

import org.board.domain.BoardVO;

public interface BoardService {
	
	public void regist(BoardVO board)throws Exception;
	
	public BoardVO read(Integer bno)throws Exception;
	
	public void modify(BoardVO board)throws Exception;
	
	public void remove(Integer bno)throws Exception;
	
	public List<BoardVO> listALL() throws Exception;
	
}
