package com.example.spring_basics.model;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 120, nullable = false)
    private String title;

    @Column(name = "edition")
    private Integer editionNumber;
    private String synopsis;

    @Column(length = 13, nullable = false)
    private String isbnCode;

    @Column(nullable = false)
    private Integer yearOfRelease;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "authorid", referencedColumnName = "id", nullable = false)
    private Author author;
}
