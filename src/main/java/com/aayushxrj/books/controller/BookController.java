package com.aayushxrj.books.controller;

import com.aayushxrj.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BookController {

    private final List<Book> books = new ArrayList<>();

    public BookController(){
        initialiseBooks();
    }

    private void initialiseBooks(){
        books.addAll(List.of(
                new Book("Title one", "Author one", "Science"),
                new Book("Title two", "Author two", "Science"),
                new Book("Title three", "Author three", "History"),
                new Book("Title four", "Author four", "Maths"),
                new Book("Title five", "Author five", "Maths"),
                new Book("Title six", "Author six", "Maths")
        ));
    }

    @GetMapping("/api/books")
    public List<Book> getBooks(){
        return books;
    }

//    @GetMapping("/api")
//    public String firstAPI(){
//        return "Hello, Aayush!";
//    }

}
