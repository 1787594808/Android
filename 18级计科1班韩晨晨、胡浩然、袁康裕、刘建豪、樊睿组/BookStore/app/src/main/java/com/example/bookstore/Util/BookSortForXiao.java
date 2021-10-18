package com.example.bookstore.Util;

import com.example.bookstore.Book;

import java.util.Comparator;

public class BookSortForXiao implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o1.getXiao()-o2.getXiao();
    }
}
