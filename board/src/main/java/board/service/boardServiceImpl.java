package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import board.dto.BoardDto;
import board.mapper.BoardMapper;

@Service
public class boardServiceImpl implements BoardService{

	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardList();
	}

	@Override
	public BoardDto boardDetail(BoardDto boardDto) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.boardDetail(boardDto);
	}

}
