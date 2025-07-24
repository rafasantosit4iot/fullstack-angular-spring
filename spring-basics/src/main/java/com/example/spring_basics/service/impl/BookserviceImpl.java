package com.example.spring_basics.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.book.CreateBookDTO;
import com.example.spring_basics.dto.response.book.BookResponseDTO;
import com.example.spring_basics.mapper.book.BookMapper;
import com.example.spring_basics.model.Author;
import com.example.spring_basics.model.Book;
import com.example.spring_basics.model.Publisher;
import com.example.spring_basics.repository.AuthorRepository;
import com.example.spring_basics.repository.BookRepository;
import com.example.spring_basics.repository.PublisherRepository;
import com.example.spring_basics.service.BookService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;

    public BookServiceImpl(BookRepository bookRepository, BookMapper bookMapper, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public List<BookResponseDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return bookMapper.toResponseListDTO(books);
    }

    @Override
    public BookResponseDTO createBook(CreateBookDTO createBookDTO) {
        Author author = authorRepository.findById(createBookDTO.authorId())
                .orElseThrow(() -> new EntityNotFoundException("Autor não encontrado"));

        Publisher publisher = publisherRepository.findById(createBookDTO.publisherId())
            .orElseThrow(() -> new EntityNotFoundException("Editora não encontrada"));

        Book book = bookMapper.toEntity(createBookDTO, author, publisher);
        book = bookRepository.save(book);
        return bookMapper.toResponseDTO(book);
    }
}
