package com.jeogi.jeogitrip.board.model.mapper;


import com.jeogi.jeogitrip.board.model.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {

	List<Board> listBoard();
	int insertBoard(Board board);
	int updateBoard(Board board);
	int deleteBoard(int boardNo);
	Board getBoardByNo(int boardNo);
}