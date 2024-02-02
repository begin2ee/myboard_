package com.codingrecipe.myboard.dto;

import com.codingrecipe.myboard.entity.CommentEntity;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class CommentDTOTest {

    @Test
    @DisplayName("멤버가 생성되는지 확인하는 테스트")
    void toCommentDTO() {
        // Given
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setId(1L);
        commentEntity.setCommentWriter("Test Writer");
        commentEntity.setCommentContents("Test Contents");
        Long boardId = 2L;

        // When
        CommentReqDto commentDTO = CommentReqDto.toCommentDTO(commentEntity, boardId);

        // Then
        assertNotNull(commentDTO);
        assertEquals(commentEntity.getId(), commentDTO.getId());
        assertEquals(commentEntity.getCommentWriter(), commentDTO.getCommentWriter());
        assertEquals(commentEntity.getCommentContents(), commentDTO.getCommentContents());
        assertEquals(commentEntity.getCreatedTime(), commentDTO.getCommentCreatedTime());
        assertEquals(boardId, commentDTO.getBoardId());
    }
}
