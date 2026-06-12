package com.EXO1.demo.controller;

import com.EXO1.demo.model.Article;
import com.EXO1.demo.model.Comment;
import com.EXO1.demo.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    // Liste des articles
    @GetMapping
    public String list(Model model) {
        model.addAttribute("articles", articleService.getAllArticles());
        return "articles-list";
    }

    // Formulaire nouvel article
    @GetMapping("/new")
    public String newForm(Model model) {
        model.addAttribute("article", new Article());
        return "article-form";
    }

    // Enregistrer un nouvel article
    @PostMapping
    public String create(@ModelAttribute Article article) {
        articleService.saveArticle(article);
        return "redirect:/articles";
    }

    // Détail d'un article + commentaires
    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        model.addAttribute("article", articleService.getArticleById(id));
        model.addAttribute("newComment", new Comment());
        return "article-detail";
    }

    // Supprimer un article
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        articleService.deleteArticle(id);
        return "redirect:/articles";
    }
}