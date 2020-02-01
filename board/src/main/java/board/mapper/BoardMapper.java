package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import board.dto.BoardDto;

@Repository
@Mapper
public interface BoardMapper {

	List<BoardDto> selectBoardList() throws Exception;
}
