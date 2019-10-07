package com.bodhisatan.readinglist;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "t_book")
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String reader;
    private String isbn;
    private String title;
    private String author;
    private String description;

}
