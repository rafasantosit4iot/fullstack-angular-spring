package com.example.spring_basics.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.spring_basics.service.user.UserLibraryCode;

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
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Autowired
    UserLibraryCode userLibraryCode;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable=false)
    private String name;

    @Column(unique = true)
    private String email;

    @Column(unique = true, nullable=false)
    private String libraryUserCode;
    
    private Integer maxActiveLoans;
    private Boolean active;

    @OneToMany(mappedBy = "user")
    private Set<Loan> loans = new HashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<Reservation> reservations = new HashSet<>();

    public User(String name, String email) {
        this.name = name;
        this.email = email;
        this.libraryUserCode = userLibraryCode.generateLibraryUserCode();
        this.maxActiveLoans = 5;
        this.active = true;
    }
}
