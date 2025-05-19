package com.aayushxrj.books.controller;

import com.aayushxrj.books.entity.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
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

    @GetMapping()
    public List<Book> getBooks(@RequestParam(required = false) String category){

        if(category == null) return books;

        return books.stream()
                .filter(book -> book.getCategory().equalsIgnoreCase(category))
                .toList();

    }

    @GetMapping("/title/{title}")
    public Book getBookByTitle(@PathVariable String title){

        return books.stream()
                .filter(book -> book.getTitle().equalsIgnoreCase(title))
                .findFirst()
                .orElse(null);

    }

    @PostMapping()
    public void createBook(@RequestBody Book newBook){

        boolean isNewBook = books.stream()
                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
        if(isNewBook) books.add(newBook);

    }

    @PutMapping("/{title}")
    public void updateBook(@PathVariable String title, @RequestBody Book updatedBook){
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle().equalsIgnoreCase(title)) {
                books.set(i, updatedBook);
                return;
            }
        }
    }

    @DeleteMapping("/{title}")
    public void deleteBook(@PathVariable String title) {
        books.removeIf(book -> book.getTitle().equalsIgnoreCase(title));
    }

}
