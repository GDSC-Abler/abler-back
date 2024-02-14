package com.abler.controller;

import com.abler.domain.comment.Comment;
import com.abler.dto.CommentListDTO;
import com.abler.dto.CommentSaveDTO;
import com.abler.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4000")

@RestController
@Lazy
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/test")
    public String test(){
        return "test!";
    }

    @GetMapping("/list")
    public List<CommentListDTO> getCommentList(){
        return commentService.findAllDesc();
    }

    @PostMapping("/save")
    public Comment saveComment(@RequestBody CommentSaveDTO comment){
        return commentService.saveComment(comment);
    }
}
