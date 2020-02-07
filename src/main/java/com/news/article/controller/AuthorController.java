package com.news.article.controller;

import com.news.article.config.SecurityConfig;
import com.news.article.model.Author;
import com.news.article.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AuthorController {


    private AuthorRepository authorRepository;
    private SecurityConfig passwordEncoder;

    public AuthorController(){
    }

    @Autowired
    public AuthorController(AuthorRepository authorRepository, SecurityConfig securityConfig){
        this.authorRepository = authorRepository;
        this.passwordEncoder = securityConfig;
    }

//    @Autowired
//    AuthorRepository authorRepository;

    @PostMapping("/author")
    public ResponseEntity<Author> addNewAuthor(@RequestBody Author author) {

        String password = author.getVpassword();
        PasswordEncoder pe = passwordEncoder.passwordEncoder();
        String encodedPassword = pe.encode(password);
        author.setVpassword(encodedPassword);
        authorRepository.save(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @GetMapping("/authors")
    public List<Author> findAllAuthors(){
        return authorRepository.findAll();
    }



}
