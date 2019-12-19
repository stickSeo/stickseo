package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDto;
import board.service.BoardService;

@RestController
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	
	@RequestMapping("/main")
	public ModelAndView root() throws Exception{
		
		ModelAndView mv = new ModelAndView("/board/main");
		
//		List<BoardDto> list = boardService.selectBoardList();
//		mv.addObject("list", list);
		
		return mv;
		
	}
	
	
	@RequestMapping("/board/boardList.do")
	public ModelAndView BoardList() throws Exception{
		
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
		
	}
}
