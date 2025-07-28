package com.example.spring_basics.service.book;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.book.CreateBookDTO;
import com.example.spring_basics.dto.response.book.BookResponseDTO;
import com.example.spring_basics.mapper.book.BookMapper;
import com.example.spring_basics.model.Author;
import com.example.spring_basics.model.Book;
import com.example.spring_basics.model.Genre;
import com.example.spring_basics.model.Publisher;
import com.example.spring_basics.repository.AuthorRepository;
import com.example.spring_basics.repository.BookRepository;
import com.example.spring_basics.repository.GenreRepository;
import com.example.spring_basics.repository.PublisherRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;
    private BookMapper bookMapper;
    private AuthorRepository authorRepository;
    private PublisherRepository publisherRepository;
    private GenreRepository genreRepository;

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

        Genre genre = genreRepository.findById(createBookDTO.genreId())
                .orElseThrow(() -> new EntityNotFoundException("Gênero não encontrado"));

        Book book = bookMapper.toEntity(createBookDTO, author, publisher, genre);
        book = bookRepository.save(book);
        return bookMapper.toResponseDTO(book);
    }
}
