package com.news.article.repository;

import com.news.article.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findByEmail(String email);
    Author findByAuthorID(int authorID);

}
