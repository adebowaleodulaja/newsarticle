package com.news.article.controller;


import com.news.article.exception.ArticleNotFoundException;
import com.news.article.exception.AuthorNotFoundException;
import com.news.article.exception.InvalidArticleIDException;
import com.news.article.model.Article;
import com.news.article.model.Author;
import com.news.article.repository.ArticleRepository;
import com.news.article.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ArticleController {

    private ArticleRepository articleRepository;
    private AuthorRepository authorRepository;

    public ArticleController() {

    }

    @Autowired
    public ArticleController(ArticleRepository articleRepository, AuthorRepository authorRepository) {
        this.articleRepository = articleRepository;
        this.authorRepository = authorRepository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/newsletter/article")
    public ResponseEntity<Article> submitArticle(@RequestBody Article article) {

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();

        Author author = authorRepository.findByEmail(auth.getName());
        article.setAuthor(author);
        articleRepository.save(article);

        return new ResponseEntity<>(article, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/newsletter/articles")
    public List<Article> findAllArticles() {
        return articleRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/newsletter/publishedarticles")
    public List<Article> findAllPublishedArticles() {
        return articleRepository.findArticleByPublished();
    }

    @Transactional
    @RequestMapping(method = RequestMethod.DELETE, value = "/api/v1/newsletter/articles/{articleID}")
    public ResponseEntity<?> deleteUser(@PathVariable int articleID) {
        //Find the Author that wants to delete article
        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();

        Author author = authorRepository.findByEmail(auth.getName());

        if (author == null) {
            throw new AuthorNotFoundException("Author with id " + author.getAuthorID() + " not found!");
        }

        Article article = articleRepository.findById(articleID).get();
        if (article == null) {
            throw new ArticleNotFoundException("Article not found.");
        }

        if (article.getAuthor().getAuthorID() == author.getAuthorID()) {
            articleRepository.deleteArticleById(article.getId());
        }


        return new ResponseEntity<>(article, HttpStatus.NO_CONTENT);
    }

    @Transactional
    @RequestMapping(method = RequestMethod.PUT, value = "/api/v1/newsletter/articles/{articleID}")
    public ResponseEntity<?> editArticle(@PathVariable int articleID, @RequestBody Article article) {

        SecurityContext ctx = SecurityContextHolder.getContext();
        Authentication auth = ctx.getAuthentication();

        Author author = authorRepository.findByEmail(auth.getName());

        if (author == null) {
            throw new AuthorNotFoundException("Author with id " + author.getAuthorID() + " not found!");
        }

         Article article1 = articleRepository.findById(articleID).get();
        if (article == null) {
            throw new ArticleNotFoundException("Article not found.");
        } else {
            if (article1.getAuthor().getAuthorID() == author.getAuthorID()) {
                article.setId(articleID);
                article.setAuthor(author);
                articleRepository.save(article);
                return new ResponseEntity<>(article, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(new InvalidArticleIDException("Invalid Article ID"), HttpStatus.OK);
            }
        }

    }
}
