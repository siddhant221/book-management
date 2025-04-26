package com.bookstore.bookmanagement.model;

import com.bookstore.bookmanagement.model.enums.TypeOfBook;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigInteger;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="Books")
public class Book
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double rating;

    @NotNull
    @Column(nullable = false,unique = true)
    private Long isbnNumber;

    private String genre;

    private String author;

    @Column(nullable = false)
    @NotBlank
    private String title;

    @Enumerated(EnumType.STRING)
    private TypeOfBook type;


}
