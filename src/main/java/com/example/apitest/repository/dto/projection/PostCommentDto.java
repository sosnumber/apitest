package com.example.apitest.repository.dto.projection;

public record PostCommentDto(
        Long commentId,
        Long parentCommentId,
        String contents
) {

    public static PostCommentDto create(String contents) {
        return new PostCommentDto(null, null, contents);
    }
}
