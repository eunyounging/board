package com.example.board.controller;

import com.example.board.dto.BoardResponseDto;
import com.example.board.dto.BoardWithAgeResponseDto;
import com.example.board.dto.CreateBoardRequestDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<BoardResponseDto>> getBoardsByMemberId(@PathVariable Long memberId) {

        List<Board> boards = boardService.getBoardsByMemberId(memberId);

        List<BoardResponseDto> boardResponseDtos = boards.stream().map(board -> new BoardResponseDto(
                board.getId(),
                board.getMember().getUsername(),
                board.getContents()))
                .toList();

        return new ResponseEntity<>(boardResponseDtos, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BoardResponseDto> save(@RequestBody CreateBoardRequestDto requestDto) {

        BoardResponseDto boardResponseDto = boardService.save(requestDto.getTitle(), requestDto.getContents(), requestDto.getUsername());

        return new ResponseEntity<>(boardResponseDto, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<BoardResponseDto>> findAll() {

        List<BoardResponseDto> boardResponseDtoList = boardService.findAll();

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BoardWithAgeResponseDto> findByID(@PathVariable Long id) {

        BoardWithAgeResponseDto boardWithAgeResponseDto = boardService.findById(id);

        return new ResponseEntity<>(boardWithAgeResponseDto, HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        boardService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
