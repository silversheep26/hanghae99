package com.sparta.hanghaeboard.repository;

import com.sparta.hanghaeboard.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositoryInter extends JpaRepository<Board, Long> {
//    List<Board> findAllByOrderByModifiedAtDesc();


}
