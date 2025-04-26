package com.bookstore.bookmanagement.controller;

import com.bookstore.bookmanagement.dto.BookRequestDto;
import com.bookstore.bookmanagement.dto.BookResponseDto;
import com.bookstore.bookmanagement.model.Book;
import com.bookstore.bookmanagement.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController
{

    private final BookService bookService;

    //Constructor injection
    public BookController(BookService bookService)
    {
        this.bookService=bookService;
    }

    @PostMapping
    public ResponseEntity<BookResponseDto> createBook(@RequestBody BookRequestDto bookRequestDto)
    {
        BookResponseDto bookResponseDto = bookService.saveBook(bookRequestDto);
        return ResponseEntity.ok(bookResponseDto);
    }

    @GetMapping
    public ResponseEntity<List<BookResponseDto>> getBooks( )
    {
        List<BookResponseDto> book = bookService.getAllBooks();
        return ResponseEntity.ok(book);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDto> getBookById(@PathVariable Long id)
    {
        BookResponseDto book = bookService.getBookById(id);
        return ResponseEntity.ok(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDto> updateBook(@PathVariable Long id, @RequestBody BookRequestDto bookRequestDto)
    {
        BookResponseDto updatedBook = bookService.updateBook(id,bookRequestDto);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        boolean deleted = bookService.deleteBookById(id);

        if (deleted) {
            return ResponseEntity.ok("Book deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found!");
        }
    }

}
