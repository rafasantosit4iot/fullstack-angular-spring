package com.example.spring_basics.service.book_copy;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_basics.dto.request.book_copy.CreateBookCopyDTO;
import com.example.spring_basics.dto.response.book_copy.BookCopyResponseDTO;
import com.example.spring_basics.mapper.book_copy.BookCopyMapper;
import com.example.spring_basics.model.Book;
import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.enums.CopyStatus;
import com.example.spring_basics.repository.BookCopyRepository;
import com.example.spring_basics.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BookCopyServiceImpl implements BookCopyService {

    @Autowired
    BookCopyRepository bookCopyRepository;
    BookCopyMapper bookCopyMapper;
    BookRepository bookRepository;

    @Override
    public BookCopyResponseDTO createBookCopy(CreateBookCopyDTO createBookCopyDTO) {
        Book book = bookRepository.findById(createBookCopyDTO.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        CopyStatus status = CopyStatus.AVAILABLE;
        String classificationCode = "codteste";

        BookCopy bookCopy = bookCopyMapper.toEntity(createBookCopyDTO, book, classificationCode, status);
        bookCopy = bookCopyRepository.save(bookCopy);
        return bookCopyMapper.toResponseDTO(bookCopy);
    }

    @Override
    public List<BookCopyResponseDTO> getAllBookCopies() {
        List<BookCopy> bookCopies = bookCopyRepository.findAll();
        return bookCopyMapper.toResponseDTOList(bookCopies);
    }

}
