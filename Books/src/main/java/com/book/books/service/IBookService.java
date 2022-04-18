package com.book.books.service;

import com.book.books.dto.BookDTO;
import com.book.books.dto.ResponseDTO;
import com.book.books.model.BookData;
import org.springframework.http.ResponseEntity;

public interface IBookService {
    public ResponseEntity<ResponseDTO> saveBooks(BookDTO bookDTO);
    public ResponseEntity<BookData> getBookById(int token);
    public ResponseEntity<ResponseDTO> UpdateBookData(int id, BookDTO bookDTO);
    public ResponseEntity<ResponseDTO> getAllBooksById();
    public ResponseEntity<ResponseDTO> searchByName(String name);
    public ResponseEntity<ResponseDTO> UpdateQuantity(int id, String quantity);
    ResponseEntity<ResponseDTO> deleteBook(int id);
}
