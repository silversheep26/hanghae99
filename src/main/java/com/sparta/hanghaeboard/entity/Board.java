package com.sparta.hanghaeboard.entity;

import com.sparta.hanghaeboard.dto.BoardRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
// 이 정보대로 데이터 베이스에 저장 된다.
@Getter
@Entity
@NoArgsConstructor
public class Board extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String contents;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String password;

    public void setId(Long id) {
        this.id = id;
    }

    public Board(BoardRequestDto requestDto){
        this.username = requestDto.getUserName();
        this.contents = requestDto.getContent();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }

    public void update(BoardRequestDto requestDto) {
        this.username = requestDto.getUserName();
        this.contents = requestDto.getContent();
        this.password = requestDto.getPassword();
        this.title = requestDto.getTitle();
    }
}
