package com.abler.service;

import com.abler.domain.comment.Comment;
import com.abler.dto.CommentListDTO;
import com.abler.dto.CommentSaveDTO;
import org.springframework.context.annotation.Lazy;

import java.util.List;

@Lazy
public interface CommentService {
    List<CommentListDTO> findAllDesc();

    Comment saveComment(CommentSaveDTO entity);
}
