package com.example.spring_basics.service.book_copy;

import org.springframework.stereotype.Service;

import com.example.spring_basics.model.BookCopy;
import com.example.spring_basics.model.enums.CopyStatus;
import com.example.spring_basics.repository.BookCopyRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CopyValidator {
    private final BookCopyRepository bookCopyRepository;

    public void markBookCopyAsBorrowed(BookCopy bookCopy) {
        bookCopy.setStatus(CopyStatus.LOANED);
        bookCopyRepository.save(bookCopy);
    }
    
    public void markBookCopyAsReserved(BookCopy bookCopy) {
        bookCopy.setStatus(CopyStatus.RESERVED);
        bookCopyRepository.save(bookCopy);
    }
    
    public void markBookCopyAsAvailable(BookCopy bookCopy) {
        bookCopy.setStatus(CopyStatus.AVAILABLE);
        bookCopyRepository.save(bookCopy);
    }
    
    public void markBookCopyInMaintenance(BookCopy bookCopy) {
        bookCopy.setStatus(CopyStatus.UNDER_MAINTENANCE);
        bookCopyRepository.save(bookCopy);
    }
}
