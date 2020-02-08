package com.news.article.service;

import com.news.article.model.Author;
import com.news.article.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    AuthorRepository authorRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Author author = authorRepository.findByEmail(s);
        if(author == null){
            throw new UsernameNotFoundException("Author not found.");
        }
        UserDetails userDetails =  new User(author.getEmail(), author.getVpassword(),
                new ArrayList<>());
        return userDetails;
    }
}
