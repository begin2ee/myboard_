package com.codingrecipe.myboard.entity;

import com.codingrecipe.myboard.dto.CommentReqDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "comment_table")
public class CommentEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false) // 컬럼명, nullable 추가
    private Long id;

    @Column(name = "comment_writer", length = 20, nullable = false)
    private String commentWriter;

    @Column(name = "comment_contents", length = 255, nullable = true) // 컬럼명, nullable, length 추가
    private String commentContents;

    /* Board:Comment = 1:N */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private BoardEntity boardEntity;

    // request를 통해서 entity 반환하거나 method를 만들필요 없음
    public static CommentEntity toSaveEntity(CommentReqDto commentDTO, BoardEntity boardEntity) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setCommentWriter(commentDTO.getCommentWriter());
        commentEntity.setCommentContents(commentDTO.getCommentContents());
        commentEntity.setBoardEntity(boardEntity);
        return commentEntity;
    }
}