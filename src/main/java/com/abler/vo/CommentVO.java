package com.abler.vo;

import com.abler.domain.comment.Comment;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
@Setter
@Getter
public class CommentVO {
    private Long id;
    private LocalDateTime datetime;
    private Long postId;
    private Long userId;
    private Long relationCm;
    private String description;

    public CommentVO(Comment comment){
        this.id = comment.getId();
        this.datetime = comment.getDatetime();
        this.postId = comment.getPostId();
        this.userId = comment.getUserId();
        this.relationCm = comment.getRelationCm();
        this.description = comment.getDescription();
    }
}
