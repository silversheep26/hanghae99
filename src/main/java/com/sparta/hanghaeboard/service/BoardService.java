package com.sparta.hanghaeboard.service;

import com.sparta.hanghaeboard.dto.BoardRequestDto;
import com.sparta.hanghaeboard.dto.BoardResponseDto;
import com.sparta.hanghaeboard.entity.Board;
import com.sparta.hanghaeboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

//import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository = new BoardRepository();
    // 게시물 저장
    public String createBoard( BoardRequestDto requestDto){
        // 브라우저에서 받아온 데이터 저장하기 위해서 Board 객체로 변환 - 그래서 리턴을 board 로 (BoardRepository 가 받아간다)
        Board board = new Board(requestDto);
        return boardRepository.createBoard(board);
    }

    // 게시물 전체 보기
    public List<BoardResponseDto> getBoardList() {  // 데이터 베이스에 저장 된 전체 게시물 전부다 가져오는 API
        // 테이블에 저장 되어 있는 모든 게시물 목록 조회
        return boardRepository.getBoardList();
    }

    // 게시물 하나만 보기
    public BoardResponseDto getBoard(Long id)  { // 똑같은 id 를 갖고 있는 것을 가져올 것
        // 조회하기 위해 받아온 course 의 id 를 사용하여 해당 course 인스턴스가 테이블에 존재하는지 확인하고 가져오기
        Board board = boardRepository.getBoard(id);

        if (board != null){  // 게시물이 있다면
            return new BoardResponseDto(board);  // board를 사용하여 board~Dto 타입으로 변환하여 반환 : 왜냐 위에서 Board board 로 반환타입을 Board 정했기 때문에
        } else {
            return new BoardResponseDto(); // 아무것도 없는 경우 빈 벌 반환
        }
    }

    // 게시물 수정  = post + get(게시물하나보기)
    public BoardResponseDto updateBoard( Long id, BoardRequestDto requestDto){
        // 수정하기 위해 받아온 board 의 id 를 사용하여 해당 board 인스턴스가 존재하는지 확인 후 가져오기
        Board board = boardRepository.getBoard(id);

        if (board != null){  // 수정하고 싶으면 해당 게시물이 반드시 있어야한다.
            board.update(requestDto);
            return new BoardResponseDto(board);
        } else {
            return new BoardResponseDto();
        }
    }

    //게시물 삭제
    public String deleteBoard(Long id) {
        // 삭제하기 위해 받아온 board 의 id 를 사용하여 해당 board 인스턴스가 존재하는지 확인 후 가져오기
        Board board = boardRepository.getBoard(id);  // 삭제하기 위해 테이블에 있는지 확인 먼저

        if (board != null){
            boardRepository.deleteBoard(id);

            return "게시물 삭제에 성공했습니다. ";
        } else {
            return "삭제할 게시물이 없습니다.";
        }
    }
}
