package com.codingrecipe.myboard.entity;

import com.codingrecipe.myboard.dto.CommentReqDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CommentEntityTest {

    @Test
    @DisplayName("CommentDTO를 CommentEntity로 변환하는지 확인하는 테스트")
    void toSaveEntity() {
        // Given
        CommentReqDto commentDTO = new CommentReqDto();
        commentDTO.setCommentWriter("Test Writer");
        commentDTO.setCommentContents("Test Contents");

        BoardEntity boardEntity = new BoardEntity();

        // When
        CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);

        // Then
        assertNotNull(commentEntity);
        assertEquals(commentDTO.getCommentWriter(), commentEntity.getCommentWriter());
        assertEquals(commentDTO.getCommentContents(), commentEntity.getCommentContents());
        assertEquals(boardEntity, commentEntity.getBoardEntity());
    }

    @Test
    @DisplayName("CommentEntity의 모든 속성이 정확히 설정되는지 확인하는 테스트")
    void toSaveEntity_AllPropertiesSetCorrectly() {
        // Given
        CommentReqDto commentDTO = new CommentReqDto();
        commentDTO.setCommentWriter("Test Writer");
        commentDTO.setCommentContents("Test Contents");

        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId(1L);
        boardEntity.setBoardTitle("Test Title");
        boardEntity.setBoardContents("Test Contents");

        // When
        CommentEntity commentEntity = CommentEntity.toSaveEntity(commentDTO, boardEntity);

        // Then
        assertNotNull(commentEntity);
        assertEquals(commentDTO.getCommentWriter(), commentEntity.getCommentWriter());
        assertEquals(commentDTO.getCommentContents(), commentEntity.getCommentContents());
        assertEquals(boardEntity, commentEntity.getBoardEntity());
    }

}