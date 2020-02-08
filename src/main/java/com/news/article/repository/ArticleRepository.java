package com.news.article.repository;

import com.news.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {

    void deleteArticleById(int id);

    @Query("SELECT a FROM Article a WHERE a.published = 1")
    List<Article> findArticleByPublished();
}
