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
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public void registerGET(BoardVO board, Model model) throws Exception {
		
		logger.info("register get.....");
	}
	
	@RequestMapping(value="/register", method= RequestMethod.POST)
	public String registPOST(BoardVO board, Model model) throws Exception {
		
		logger.info("regist post.......");
		logger.info(board.toString());
		
		service.regist(board);
		
		model.addAttribute("result", "success"); // 
		
		return "/board/success";
	}
	
}
