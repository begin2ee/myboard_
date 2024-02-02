package com.codingrecipe.myboard.controller;

import com.codingrecipe.myboard.dto.CommentReqDto;
import com.codingrecipe.myboard.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
@Validated
public class CommentController {
    private final CommentService commentService;

    @PostMapping("/save")
    // TODO : <Response> 으로 객체 타입을 명시
    public ResponseEntity save(@Valid @RequestBody @ModelAttribute CommentReqDto commentDTO) { // 유효성 검증 추가
        System.out.println("commentDTO = " + commentDTO);
//        Long saveResult = commentService.save(commentDTO);
//        if (saveResult != null) {
//            List<CommentDTO> commentDTOList = commentService.findAll(commentDTO.getBoardId()); //변수를 commentDTOList -> commentDTO
//            return new ResponseEntity<>(commentDTOList, HttpStatus.OK);
//        } else {
//            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND); //custom 예외 리턴
//        }

//        try {
        Long saveResult = commentService.save(commentDTO);
        List<CommentReqDto> commentDTOList = commentService.findAll(commentDTO.getBoardId());
        return new ResponseEntity<>(commentDTOList, HttpStatus.OK);

        // TODO : 커스텀 예외 처리 하기
//        } catch (DataAccessException e) { // persistance 영속성
//            return new ResponseEntity<>("데이터베이스 작업 중 오류가 발생했습니다.", HttpStatus.INTERNAL_SERVER_ERROR);
//        } catch (IllegalArgumentException e) {
//            return new ResponseEntity<>("해당 게시글이 존재하지 않습니다.", HttpStatus.NOT_FOUND);
//        }
        // jpa만의 런타임 exception이 발생한다면?
        // findall, save 각각 따로
    }
}