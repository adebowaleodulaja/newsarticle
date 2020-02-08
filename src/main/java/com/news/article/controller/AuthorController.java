package com.news.article.controller;

import com.news.article.config.SecurityConfig;
import com.news.article.exception.AuthorNotFoundException;
import com.news.article.model.AuthenticationResponse;
import com.news.article.model.Author;
import com.news.article.repository.AuthorRepository;
import com.news.article.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {


    private AuthorRepository authorRepository;
    private SecurityConfig passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtUtil jwtTokenUtil;

    public AuthorController() {
    }

    @Autowired
    public AuthorController(AuthorRepository authorRepository, SecurityConfig securityConfig,
                            AuthenticationManager authenticationManager, JwtUtil jwtTokenUtil) {
        this.authorRepository = authorRepository;
        this.passwordEncoder = securityConfig;
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/newsletter/author")
    public ResponseEntity<Author> addNewAuthor(@RequestBody Author author) {

        String password = author.getVpassword();
        PasswordEncoder pe = passwordEncoder.passwordEncoder();
        String encodedPassword = pe.encode(password);
        author.setVpassword(encodedPassword);
        authorRepository.save(author);
        return new ResponseEntity<>(author, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/v1/newsletter/author/authenticate")
    public ResponseEntity<?> authenticateAuthor(@RequestBody Author author) {

        Author checkForAuthor = authorRepository.findByEmail(author.getEmail());

        //Check Authors' existence
        if (checkForAuthor == null) {
            throw new AuthorNotFoundException("Author with email " + author.getEmail() + " does not exist");
        }else{

            if (verifyPassword(author.getVpassword(), checkForAuthor.getVpassword())){
                try{
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(author.getEmail(), author.getVpassword()));
                }catch (BadCredentialsException bcex){throw new AuthorNotFoundException("Invalid username and/or password", bcex); }

                final Author authorDetails = authorRepository.findByEmail(author.getEmail());

                final String jwt = jwtTokenUtil.generateToken(authorDetails);

                return ResponseEntity.ok(new AuthenticationResponse(jwt));
            }else {
                throw new AuthorNotFoundException("Invalid username and/or password");
            }
        }

    }

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/newsletter/authors")
    public List<Author> findAllAuthors() {
        return authorRepository.findAll();
    }


    private boolean verifyPassword(String rawPassword, String encodedPassword){
        PasswordEncoder pe = passwordEncoder.passwordEncoder();

        return pe.matches(rawPassword, encodedPassword);

    }

}
