package com.bookstore.bookmanagement.dto;

import com.bookstore.bookmanagement.model.enums.TypeOfBook;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookRequestDto //client->Server
{

    private Long isbnNumber;


    private String title;

    private String author;
    private String genre;
    private TypeOfBook type;
    private Double rating;
}
/*
why no id?
because client does not supply the id, the DB generates it on it's own when creating a new book*/