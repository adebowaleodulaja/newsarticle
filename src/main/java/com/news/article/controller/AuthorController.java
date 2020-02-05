package com.news.article.controller;

import com.news.article.model.Author;
import com.news.article.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthorController {


    public AuthorController(){

    }

    @Autowired
    AuthorRepository authorRepository;

    @PostMapping("/author")
    public ResponseEntity<Author> addNewAuthor(@RequestBody Author author) {

        authorRepository.save(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }



}
