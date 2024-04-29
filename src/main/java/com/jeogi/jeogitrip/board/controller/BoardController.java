package com.jeogi.jeogitrip.board.controller;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import com.jeogi.jeogitrip.board.model.Board;
import com.jeogi.jeogitrip.board.model.service.BoardService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin("*")
@Tag(name = "Board", description = "게시판 목록과 상세보기, 등록, 수정, 삭제 등 전반적인 게시판 관리를 처리하는 클래스")
@RequestMapping("/api/board")
public class BoardController {
	
	private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }


    @GetMapping({ "/", "/index" })
    public ResponseEntity<String> showRoot() {
        return ResponseEntity.ok("Welcome to the board!");
    }
    
    @Operation(summary = "게시글 목록", description = "게시글의 <big>전체 목록</big>을 반환해 줍니다.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "게시글 목록 OK!!"),
			@ApiResponse(responseCode = "404", description = "페이지없어!!"),
			@ApiResponse(responseCode = "500", description = "서버에러!!") })
    @GetMapping("/list")
    public ResponseEntity<?> listBoard() {
    	try {
		 	Map<String, Object> board = boardService.listBoard();
		 	if (board != null && !board.isEmpty()) {
		 		HttpHeaders headers = new HttpHeaders();
				headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
				return ResponseEntity.ok().headers(headers).body(board);
		 	} else {
		 		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		 	}
		 } catch (Exception e) {
		 	return exceptionHandling(e);
		 }
    }
    
    private ResponseEntity<String> exceptionHandling(Exception e) {
		e.printStackTrace();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error : " + e.getMessage());
	}
    
    @Operation(summary = "게시글 등록", description = "게시글 정보를 받아 데이터베이스에 등록.")
	@PostMapping("/regist")
    public ResponseEntity<?> registBoard(
    		@RequestBody(description = "등록할 게시글 정보.", required = true, content = @Content(schema = @Schema(implementation = Board.class))) @org.springframework.web.bind.annotation.RequestBody Board board) {
    	try {
    		boardService.registBoard(board);
			Map<String, Object> list = boardService.listBoard();
    		HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
    	} catch (Exception e) {
			return exceptionHandling(e);
		}
    }
    
    @Operation(summary = "게시글 삭제", description = "게시글을 삭제.")
    @DeleteMapping("/{isbn}")
    public ResponseEntity<?> deleteBook(
    		@Parameter(required = true, description = "삭제할 게시글 번호") @PathVariable("boardNo") int boardNo) {
        try {
        	boardService.removeBoard(boardNo);
            Map<String, Object> list = boardService.listBoard();
            HttpHeaders headers = new HttpHeaders();
    		headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
    		return ResponseEntity.ok().headers(headers).body(list);
        } catch (Exception e) {
			return exceptionHandling(e);
		}
    }
    
    @Operation(summary = "게시글 수정", description = "게시글 정보를 수정.")
    @PutMapping("/{isbn}")
    public ResponseEntity<?> updateBook(
    		@RequestBody(description = "수정할 게시글 정보.", required = true, content = @Content(schema = @Schema(implementation = Board.class))) @org.springframework.web.bind.annotation.RequestBody Board board) {
    	try {
			boardService.updateBoard(board);
			Map<String, Object> list = boardService.listBoard();
//			return new ResponseEntity<List<MemberDto>>(list, HttpStatus.OK);
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(new MediaType("application", "json", StandardCharsets.UTF_8));
			return ResponseEntity.ok().headers(headers).body(list);
		} catch (Exception e) {
			return exceptionHandling(e);
		}
    }
    
    
    
    
    
    
    

}
