package com.bookstore.bookmanagement.utility;

import com.bookstore.bookmanagement.dto.BookRequestDto;
import com.bookstore.bookmanagement.dto.BookResponseDto;
import com.bookstore.bookmanagement.model.Book;
import org.springframework.stereotype.Service;

@Service
public class BookMapper
{
    public Book mapToEntity(BookRequestDto bookRequestDto)
    {
        Book book = new Book();
        book.setIsbnNumber(bookRequestDto.getIsbnNumber());
        book.setTitle(bookRequestDto.getTitle());
        book.setAuthor(bookRequestDto.getAuthor());
        book.setGenre(bookRequestDto.getGenre());
        book.setRating(bookRequestDto.getRating());
        book.setType(bookRequestDto.getType());
        return book;
    }

    public BookResponseDto mapToResponse(Book book)
    {
        return BookResponseDto.builder()
                .id(book.getId())
                .isbnNumber(book.getIsbnNumber())
                .title(book.getTitle())
                .author(book.getAuthor())
                .genre(book.getGenre())
                .type(book.getType())
                .rating(book.getRating())
                .build();
    }
}
