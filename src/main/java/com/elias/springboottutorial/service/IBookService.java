package com.elias.springboottutorial.service;

import com.elias.springboottutorial.entity.Book;

import java.util.List;

public interface IBookService {

    List<Book> getAllBooks();
}