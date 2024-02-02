package com.codingrecipe.myboard.service;

import com.codingrecipe.myboard.dto.CommentReqDto;
import com.codingrecipe.myboard.entity.BoardEntity;
import com.codingrecipe.myboard.entity.CommentEntity;
import com.codingrecipe.myboard.repository.BoardRepository;
import com.codingrecipe.myboard.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;

    public Long save(CommentReqDto commentDTO) {
        /* 부모엔티티(BoardEntity) 조회 */
        Optional<BoardEntity> optionalBoardEntity = boardRepository.findById(commentDTO.getBoardId());

        if (optionalBoardEntity.isPresent()) {
            BoardEntity boardEntity = optionalBoardEntity.get();

            CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);

            return commentRepository.save(commentEntity).getId();

        } else {
            throw new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다."); // custom 예외로
//            return null; //null 사용 지양하기, 예외 발생으로
        }
    }

    public List<CommentReqDto> findAll(Long boardId) {
        BoardEntity boardEntity = boardRepository.findById(boardId).get();
        List<CommentEntity> commentEntityList = commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity);
        /* EntityList -> DTOList */
        List<CommentReqDto> commentDTOList = new ArrayList<>();
        for (CommentEntity commentEntity : commentEntityList) {
            CommentReqDto commentDTO = CommentReqDto.toCommentDTO(commentEntity, boardId);
            commentDTOList.add(commentDTO);
        }
        return commentDTOList;
    }

    public void deleteAll() {
        commentRepository.deleteAll();
    }
}