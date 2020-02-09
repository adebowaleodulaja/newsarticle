package com.news.article.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Set;

@JsonIdentityInfo(generator=ObjectIdGenerators.UUIDGenerator.class)
@Entity
@Table(name = "authors")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int authorID;
    private String name;
    private String email;
    private String vpassword;
    private String phoneNumber;
    private String maritalStatus;
    private String dateOfBirth;//dd-mm-yyyy

   @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@Transient
    private java.util.List<Article> articles;


    public Author() {
    }

    public Author(int authorID, String name, String email, String vpassword, String phoneNumber, String maritalStatus, String dateOfBirth) {
        this.authorID = authorID;
        this.name = name;
        this.email = email;
        this.vpassword = vpassword;
        this.phoneNumber = phoneNumber;
        this.maritalStatus = maritalStatus;
        this.dateOfBirth = dateOfBirth;

    }

    public int getAuthorID() {
        return authorID;
    }

    public void setAuthorID(int authorID) {
        this.authorID = authorID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVpassword() {
        return vpassword;
    }

    public void setVpassword(String vpassword) {
        this.vpassword = vpassword;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(String maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public java.util.List<Article> getArticles() {
        return articles;
    }

    public void setArticles(java.util.List<Article> articles) {
        this.articles = articles;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorID=" + authorID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", vpassword='" + vpassword + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", maritalStatus='" + maritalStatus + '\'' +
                ", dateOfBirth='" + dateOfBirth + '\'' +
                '}';
    }
}

