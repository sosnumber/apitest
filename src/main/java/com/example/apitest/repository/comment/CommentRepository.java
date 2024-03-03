package com.example.apitest.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.apitest.domain.comment.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
