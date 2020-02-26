package com.pdurasek.demo.mapper;

import com.pdurasek.demo.dto.BookDTO;
import com.pdurasek.demo.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public static BookDTO bookToBookDTOWithoutRentedUsers(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setGenre(book.getGenre());
        bookDTO.setISBN(book.getISBN());
        bookDTO.setLanguage(book.getLanguage());
        bookDTO.setNumberOfPages(book.getNumberOfPages());
        bookDTO.setPublisher(book.getPublisher());
        bookDTO.setCopies(book.getCopies());
        bookDTO.setCopiesAvailable(book.getCopiesAvailable());

        return bookDTO;
    }

    public static Book bookDTOToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setGenre(bookDTO.getGenre());
        book.setISBN(bookDTO.getISBN());
        book.setLanguage(bookDTO.getLanguage());
        book.setNumberOfPages(bookDTO.getNumberOfPages());
        book.setPublisher(bookDTO.getPublisher());
        book.setCopies(bookDTO.getCopies());
        book.setCopiesAvailable(bookDTO.getCopiesAvailable());

        return book;
    }

}
