package com.example.apitest.domain;

public record ReplyDto(
        String childCommentContent
) {

    public static ReplyDto create(String contents) {
        return new ReplyDto(contents);
    }
}
