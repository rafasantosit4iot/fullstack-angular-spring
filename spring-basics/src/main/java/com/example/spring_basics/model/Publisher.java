package com.example.spring_basics.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "publishers")
public class Publisher {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(length = 100, nullable = false, unique=true)
    private String name;

    @Column(length = 100, nullable = false)
    private String founder;

    @Column(nullable = false)
    private Integer foundationYear;

    @OneToMany(mappedBy = "publisher", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Headquarter> headquarters = new HashSet<>();
}
