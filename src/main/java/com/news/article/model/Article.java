package com.news.article.model;

import javax.persistence.*;

@Entity
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String title;
    private String content;
    private int published;

    //@ManyToOne(cascade = CascadeType.ALL)
    //@JoinColumn(name = "authorID")
    private int authorID;

    public Article() {
    }

    public Article(int id, String title, String content, int published, int authorID) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.published = published;
        this.authorID = authorID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPublished() {
        return published;
    }

    public void setPublished(int published) {
        this.published = published;
    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }
}
