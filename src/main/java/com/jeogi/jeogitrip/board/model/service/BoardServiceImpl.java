package com.jeogi.jeogitrip.board.model.service;

import com.jeogi.jeogitrip.board.model.Board;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

import com.jeogi.jeogitrip.board.model.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Slf4j
@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardMapper boardMapper;

    @Override
    public Map<String, Object> listBoard() {
        List<Board> boardList = boardMapper.listBoard();
        Map<String, Object> result = new HashMap<>();
        result.put("boardList", boardList);
        return result;
    }

    @Override
    public int registBoard(Board board) {
        return boardMapper.insertBoard(board);
    }

    @Override
    public int updateBoard(Board board) {
        return boardMapper.updateBoard(board);
    }

    @Override
    public int removeBoard(int boardNo) {
        return boardMapper.deleteBoard(boardNo);
    }

    @Override
    public Board getBoardByNo(int boardNo) {
        return boardMapper.getBoardByNo(boardNo);
    }

}
