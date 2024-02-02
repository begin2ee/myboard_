package com.codingrecipe.myboard.controller;

import com.codingrecipe.myboard.dto.CommentReqDto;
import com.codingrecipe.myboard.repository.CommentRepository;
import com.codingrecipe.myboard.service.CommentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest()
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    protected MockMvc mockMvc;
    @Mock
    private CommentService commentService;
    @InjectMocks
    private CommentController commentController;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private CommentRepository commentRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this); // MockitoAnnotations.initMocks()를 호출하여 목 객체를 초기화합니다.
    }


    @Test
    @DisplayName("유효한 댓글이 저장될 때 올바른 HTTP 상태 코드가 반환되는지 확인하는 테스트")
    void save_ValidComment_ReturnsOK() throws Exception {
        // db 삭제
//        Long id = 1L;
//        commentRepository.deleteById(id);
        // db 삭제 확인 0을 예상
//        assertThat(commentRepository.existsById(id)).isFalse();
        // Given
        CommentReqDto commentDTO = new CommentReqDto();
//        commentDTO.setId(id);
        commentDTO.setCommentWriter("John Doe");
        commentDTO.setCommentContents("This is a test comment.");
//        commentDTO.setBoardId(123L);
        commentDTO.setBoardId(3L);
        commentDTO.setCommentCreatedTime(LocalDateTime.now());
        String url = "/comment/save";

        // When
        final String requestBody = objectMapper.writeValueAsString(commentDTO);

        ResultActions result = mockMvc.perform(post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody)
        );

        result
                // Then
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName(" 유효하지 않은 댓글이 저장되었을 때 적절한 예외 처리가 되는지 확인하는 테스트")
    void save_InvalidComment_ReturnsNotFound() {
        // Given
        CommentReqDto commentDTO = new CommentReqDto();
        doThrow(new IllegalArgumentException()).when(commentService).save(any(CommentReqDto.class));

        // When
        ResponseEntity response = commentController.save(commentDTO);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    @DisplayName("유효하지 않은 댓글이 저장될 때 에러 메시지가 반환되는지 확인하는 테스트")
    void save_InvalidComment_ReturnsErrorMessage() {
        // Given
        CommentReqDto commentDTO = new CommentReqDto();
        doThrow(new IllegalArgumentException()).when(commentService).save(any(CommentReqDto.class));

        // When
        ResponseEntity response = commentController.save(commentDTO);

        // Then
        assertEquals("해당 게시글이 존재하지 않습니다.", response.getBody());
    }
}