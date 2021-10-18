package com.example.bookstore.Util;

import com.example.bookstore.Book;

import java.util.Comparator;

public class BookSortForJiaF implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return (int)(o2.getBvalue()-o1.getBvalue());
    }
}
