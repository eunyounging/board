package com.example.board.repository;

import com.example.board.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByMemberId(Long memberId);

    default Board findByIdOrElseThrow(Long id) {
        return findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Does noe exist id = " + id));
    }
}
