package com.EXO1.demo.controller;

import com.EXO1.demo.model.Comment;
import com.EXO1.demo.repository.CommentRepository;
import com.EXO1.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleService articleService;

    @PostMapping("/add/{articleId}")
    public String addComment(@PathVariable Long articleId,
                             @ModelAttribute Comment comment) {
        comment.setArticle(articleService.getArticleById(articleId));
        commentRepository.save(comment);
        return "redirect:/articles/" + articleId;
    }
}