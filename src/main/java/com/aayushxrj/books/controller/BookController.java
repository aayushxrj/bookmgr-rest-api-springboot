package com.aayushxrj.books.controller;

import com.aayushxrj.books.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/api/books/id/{id}")
    public Book getBookByIndex(@PathVariable int id){
        return books.get(id-1);
    }

    @GetMapping("/api/books/title/{title}")
    public Book getBookByTitle(@PathVariable String title){
//        for(Book book : books){
//            if(book.getTitle().equalsIgnoreCase(title)){
//                return book;
//            }
//        }
//        return null;
        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);
    }


//    @GetMapping("/api")
//    public String firstAPI(){
//        return "Hello, Aayush!";
//    }

}
