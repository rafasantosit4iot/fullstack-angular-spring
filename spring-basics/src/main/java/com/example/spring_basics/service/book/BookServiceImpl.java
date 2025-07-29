package com.example.spring_basics.service.book;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorRepository authorRepository;
    private final PublisherRepository publisherRepository;
    private final GenreRepository genreRepository;
    
    @Override
    public Page<BookResponseDTO> getBooks(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Book> books = bookRepository.findAll(pageable);
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
