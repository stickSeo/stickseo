package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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
		
		return mv;
		
	}
	
	
	@RequestMapping("/board")
	public ModelAndView BoardList() throws Exception{
		
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
		
	}
	
	@RequestMapping("/boardDetail")
	public ModelAndView boardDetail(BoardDto boardDto) throws Exception{
		
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		
		BoardDto board = boardService.boardDetail(boardDto);
		mv.addObject("board", board);
		
		return mv;
		
	}
	
	@RequestMapping("/boardWrite")
	public ModelAndView boardWrite() throws Exception{
		
		ModelAndView mv = new ModelAndView("/board/boardWrite");
		
//		List<BoardDto> list = boardService.selectBoardList();
//		mv.addObject("list", list);
		
		return mv;
		
	}
	
	@RequestMapping("/boarUpdate")
	public ModelAndView boarUpdate() throws Exception{
		
		ModelAndView mv = new ModelAndView("/board/boarDelete");
		
//		List<BoardDto> list = boardService.selectBoardList();
//		mv.addObject("list", list);
		
		return mv;
		
	}
	
	@RequestMapping("/boarDelete")
	public ModelAndView boarDelete() throws Exception{
		
		ModelAndView mv = new ModelAndView("/board/boarDelete");
		
//		List<BoardDto> list = boardService.selectBoardList();
//		mv.addObject("list", list);
		
		return mv;
		
	}
}
