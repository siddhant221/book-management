package com.bookstore.bookmanagement.dto;

import com.bookstore.bookmanagement.model.enums.TypeOfBook;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponseDto //Server -> client
{
    private Long id;

    private String title;
    private String author;
    private String genre;
   // private String type;
    private Long isbnNumber;
    private Double rating;
    private TypeOfBook type;
}
