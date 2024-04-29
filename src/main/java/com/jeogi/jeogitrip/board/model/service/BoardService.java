package com.jeogi.jeogitrip.board.model.service;


import java.util.Map;

import com.jeogi.jeogitrip.board.model.Board;

public interface BoardService {
    Map<String, Object> listBoard();
    int registBoard(Board board);
    int updateBoard(Board board);
    int removeBoard(int boardNo);
    Board getBoardByNo(int boardNo);
}
