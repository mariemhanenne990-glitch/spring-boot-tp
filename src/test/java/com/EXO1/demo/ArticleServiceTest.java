package com.EXO1.demo;

import com.EXO1.demo.model.Article;
import com.EXO1.demo.repository.ArticleRepository;
import com.EXO1.demo.service.ArticleService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @Mock
    private ArticleRepository articleRepository;

    @InjectMocks
    private ArticleService articleService;

    @Test
    void testGetAllArticles() {
        Article a1 = new Article();
        a1.setTitle("Mon premier article");

        when(articleRepository.findAll()).thenReturn(List.of(a1));

        List<Article> result = articleService.getAllArticles();

        assertEquals(1, result.size());
        assertEquals("Mon premier article", result.get(0).getTitle());
    }

    @Test
    void testSaveArticle() {
        Article article = new Article();
        article.setTitle("Nouvel article");

        when(articleRepository.save(article)).thenReturn(article);

        Article saved = articleService.saveArticle(article);

        assertNotNull(saved);
        assertEquals("Nouvel article", saved.getTitle());
        verify(articleRepository, times(1)).save(article);
    }

    @Test
    void testDeleteArticle() {
        articleService.deleteArticle(1L);
        verify(articleRepository, times(1)).deleteById(1L);
    }
}