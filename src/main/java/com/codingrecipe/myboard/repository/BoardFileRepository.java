package com.codingrecipe.myboard.repository;

import com.codingrecipe.myboard.entity.BoardFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardFileRepository extends JpaRepository<BoardFileEntity, Long> {
}