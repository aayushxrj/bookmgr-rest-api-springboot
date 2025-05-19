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

//    @GetMapping("/api/books")
//    public List<Book> getBooks(){
//        return books;
//    }

    @GetMapping()
    public List<Book> getBooks(@RequestParam(required = false) String category){

        if(category == null) return books;

        List<Book> filteredBooks = new ArrayList<>();

        for(Book book : books){
            if(book.getCategory().equalsIgnoreCase(category)){
                filteredBooks.add(book);
            }
        }

//        return books.stream()
//                .filter(book -> book.getCategory().equalsIgnoreCase(category))
//                .toList();

        return filteredBooks;
    }

    @GetMapping("/id/{id}")
    public Book getBookByIndex(@PathVariable int id){
        return books.get(id-1);
    }

    @GetMapping("/title/{title}")
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

    @PostMapping()
    public void createBook(@RequestBody Book newBook){
        for(Book book: books){
            if(book.getTitle().equalsIgnoreCase(newBook.getTitle())){
                return;
            }
        }
        books.add(newBook);

//        boolean isNewBook = books.
//                stream()
//                .noneMatch(book -> book.getTitle().equalsIgnoreCase(newBook.getTitle()));
//        if(isNewBook) books.add(newBook);
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
