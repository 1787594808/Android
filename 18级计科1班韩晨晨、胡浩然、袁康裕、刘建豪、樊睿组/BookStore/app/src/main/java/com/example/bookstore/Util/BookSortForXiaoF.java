package com.example.bookstore.Util;

import com.example.bookstore.Book;

import java.util.Comparator;

public class BookSortForXiaoF implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return o2.getXiao()-o1.getXiao();
    }
}
