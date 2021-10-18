package com.example.bookstore.yuan;

import com.example.bookstore.Book;

public class Content {
    private String contentname;
    Book book;
    public Content(String contentname, Book book)
    {
        this.contentname=contentname;
        this.book=book;
    }

    public String getContentname() {
        return contentname;
    }

    public void setContentname(String contentname) {
        this.contentname = contentname;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
