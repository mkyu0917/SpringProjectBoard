package org.board.controller;

import javax.inject.Inject;

import org.board.domain.BoardVO;
import org.board.service.BoardService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/board/*") // "/board/*" board라는 경로뒤에오는 페이지이름으로 주소값을 맵핑.
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	@Inject //객체주입
	private BoardService service;
	
	@RequestMapping(value="/register", method = RequestMethod.GET) //등록화면 GET 방식 글을 작성할때 
	public void registerGET(BoardVO board, Model model) throws Exception {
		
		logger.info("register get.....");
	}
	
	@RequestMapping(value="/register", method= RequestMethod.POST) //등록화면 POST방식 글을 등록할때
	public String registPOST(BoardVO board, Model model) throws Exception {
		
		logger.info("regist post.......");
		logger.info(board.toString());
		
		service.regist(board);
		
		model.addAttribute("result", "success"); // value객체를 name이름으로 추가한다. 뷰코드에서는 namedmfh 지정한 이름을 통해서 value를 사용한다.
		
		//return "/board/success"; //성공했을때 나오는 화면 
		return "redirect:/board/listAll"; //화면에 글을 성공적으로 작성하고 새로고침할시에 새로운 게시글이 계속생성됨
										  //반복되는 문제들을 해결하기 위해서 다른페이지 변경함.
	}
	@RequestMapping(value="/listAll", method= RequestMethod.GET)
	public void listAll(Model model) throws Exception {
		logger.info("show all list.............");
	}
	
}
