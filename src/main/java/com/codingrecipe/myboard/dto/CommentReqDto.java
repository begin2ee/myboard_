package com.codingrecipe.myboard.dto;

import com.codingrecipe.myboard.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class CommentReqDto { //CommentRequestDTO
    private Long id;
    private String commentWriter;
    private String commentContents;
    private Long boardId;
    private LocalDateTime commentCreatedTime;

    public static CommentReqDto toCommentDTO(CommentEntity commentEntity, Long boardId) {
        CommentReqDto commentDTO = new CommentReqDto();
        commentDTO.setId(commentEntity.getId());
        commentDTO.setCommentWriter(commentEntity.getCommentWriter());
        commentDTO.setCommentContents(commentEntity.getCommentContents());
        commentDTO.setCommentCreatedTime(commentEntity.getCreatedTime());
        commentDTO.setBoardId(boardId);
        return commentDTO;
    }
}