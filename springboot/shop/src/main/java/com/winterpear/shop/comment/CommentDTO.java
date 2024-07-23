package com.winterpear.shop.comment;

import lombok.Getter;

@Getter
public class CommentDTO {
    private final String username;
    private final String commentDate;
    private final String content;

    public CommentDTO(String username, String commentDate, String content) {
        this.username = username;
        this.commentDate = commentDate;
        this.content = content;
    }
}
