package com.codingrecipe.myboard.service;

import com.codingrecipe.myboard.dto.CommentReqDto;
import com.codingrecipe.myboard.entity.BoardEntity;
import com.codingrecipe.myboard.entity.CommentEntity;
import com.codingrecipe.myboard.repository.BoardRepository;
import com.codingrecipe.myboard.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class CommentServiceTest {

    @Mock
    private CommentRepository commentRepository;

    @Mock
    private BoardRepository boardRepository;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void save_ValidComment_ReturnsCommentId() {
        // Given
        CommentReqDto commentDTO = new CommentReqDto();
        commentDTO.setBoardId(1L);
        BoardEntity boardEntity = new BoardEntity();
        when(boardRepository.findById(1L)).thenReturn(Optional.of(boardEntity));

        when(commentRepository.save(any(CommentEntity.class))).thenReturn(new CommentEntity());

        // When
        Long commentId = commentService.save(commentDTO);

        // Then
        assertNotNull(commentId);
    }

    @Test
    void save_InvalidComment_ThrowsIllegalArgumentException() {
        // Given
        CommentReqDto commentDTO = new CommentReqDto();
        commentDTO.setBoardId(2L); // 존재하지 않는 boardId
        when(boardRepository.findById(2L)).thenReturn(Optional.empty());

        // When, Then
        assertThrows(IllegalArgumentException.class, () -> {
            commentService.save(commentDTO);
        });
    }

    @Test
    void findAll_CommentsExist_ReturnsCommentDTOList() {
        // Given
        Long boardId = 1L;
        BoardEntity boardEntity = new BoardEntity();
        when(boardRepository.findById(boardId)).thenReturn(Optional.of(boardEntity));

        List<CommentEntity> commentEntityList = new ArrayList<>();
        commentEntityList.add(new CommentEntity());
        when(commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity)).thenReturn(commentEntityList);

        // When
        List<CommentReqDto> commentDTOList = commentService.findAll(boardId);

        // Then
        assertFalse(commentDTOList.isEmpty());
    }

    @Test
    void findAll_CommentsNotExist_ReturnsEmptyList() {
        // Given
        Long boardId = 1L;
        BoardEntity boardEntity = new BoardEntity();
        when(boardRepository.findById(boardId)).thenReturn(Optional.of(boardEntity));

        List<CommentEntity> commentEntityList = new ArrayList<>();
        when(commentRepository.findAllByBoardEntityOrderByIdDesc(boardEntity)).thenReturn(commentEntityList);

        // When
        List<CommentReqDto> commentDTOList = commentService.findAll(boardId);

        // Then
        assertTrue(commentDTOList.isEmpty());
    }
}