package com.example.spring_basics.service.book_copy;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookCopyServiceImpl implements BookCopyService {

    private final BookCopyRepository bookCopyRepository;
    private final BookCopyMapper bookCopyMapper;
    private final CopyCodeGenerator copyCodeGenerator;
    private final BookRepository bookRepository;

    @Override
    public BookCopyResponseDTO createBookCopy(CreateBookCopyDTO createBookCopyDTO) {
        Book book = bookRepository.findById(createBookCopyDTO.bookId())
                .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));

        CopyStatus status = CopyStatus.AVAILABLE;
        String classificationCode = copyCodeGenerator.generateBookCopyCode(book);

        BookCopy bookCopy = bookCopyMapper.toEntity(createBookCopyDTO, book, classificationCode, status);
        bookCopy = bookCopyRepository.save(bookCopy);
        return bookCopyMapper.toResponseDTO(bookCopy);
    }

    @Override
    public Page<BookCopyResponseDTO> getBookCopies(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<BookCopy> bookCopies = bookCopyRepository.findAll(pageable);
        return bookCopyMapper.toResponseDTOList(bookCopies);
    }

}
