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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@RequestMapping(value="/listAll", method= RequestMethod.GET) // 리스트 
	public void listAll(Model model) throws Exception {
		logger.info("show all list.............");
		model.addAttribute("list",service.listALL()); // 모델 에트리뷰트를 통해서 리스트라는 이름으로 서비스에있는 객체를 전달  
	}
	
	//RequestParam 애노테이션은 서블릿에서 request.getParamete()의 효과와 유사, 차이점은 문자열, 숫자, 날짜등의 형변환이 가능
	@RequestMapping(value="/read", method=RequestMethod.GET)
	public void read(@RequestParam("bno") int bno, Model model) throws Exception{ //requestParam을 통해서 값을외부에서 받아옴.
		
		model.addAttribute(service.read(bno));
	}
	
	@RequestMapping(value="/remove", method=RequestMethod.POST)
	public String remove(@RequestParam("bno")int bno, RedirectAttributes rttr) throws Exception {
		
		service.remove(bno);
		
		rttr.addFlashAttribute("msg","SUCCESS"); // 삭제 결과표시  addFlashAttribute는 url뒤에 파라미터가 붙지 않음, 일회성이라 데이터가 소멸됨
		
		return "redirect:/board/listAll"; // 삭제가 되면 리다이렉트로 리스트페이지로 이동 
		
	
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.GET)
	public void modifyGET(int bno, Model model) throws Exception {
		model.addAttribute(service.read(bno)); //
	}
	
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public String modifyPOST(BoardVO board, RedirectAttributes rttr)throws Exception{
		
		logger.info("mod post.........");
		
		service.modify(board);
		
		rttr.addFlashAttribute("msg","SUCCESS");
		
		return "redirect:/board/listAll";
	}
}
