package com.news.article.exception;

public class InvalidArticleIDException extends RuntimeException {

    public InvalidArticleIDException(String message) {
        super(message);
    }

    public InvalidArticleIDException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidArticleIDException(Throwable cause) {
        super(cause);
    }
}