package com.book.books.service;

import com.book.books.dto.BookDTO;
import com.book.books.dto.ResponseDTO;
import com.book.books.excption.BookException;
import com.book.books.model.BookData;
import com.book.books.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    BookRepository bookRepository;

    /**
     *
     * @param bookDTO to save book data
     * @return ResponseDto as a status
     * @Purpose to save the book data into the database
     */
    @Override
    public ResponseEntity<ResponseDTO> saveBooks(BookDTO bookDTO) {
        if(bookDTO.bookName.equals(bookRepository.findByBookName(bookDTO.bookName))){
            throw new BookException("book already exists plaease rename some thing", BookException.ExceptionType.BOOK_ALREADY_EXISTS);
        }
        BookData bookData = bookRepository.save(new BookData(bookDTO));
        ResponseDTO responseDTO = new ResponseDTO("save success", bookData,"no token required");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @param id to verify the user
     * @return response of the user with confirmation message
     * @purpose to getting book by  if exists
     */
    @Override
    public ResponseEntity<BookData> getBookById(int id) {
        Optional<BookData> bookData = bookRepository.findById(id);
        if (bookData.isEmpty()){
            throw new BookException("book not exists", BookException.ExceptionType.BOOK_NOT_FOUND);
        }
        ResponseDTO responseDTO = new ResponseDTO("get call success", bookData, "" );
        return new ResponseEntity<BookData>(bookData.get(), HttpStatus.OK);
    }

    /**
     *
     * @param id to verify the book
     * @param bookDTO metadata of the book
     * @return the responseDTO if book data updated
     * @purpose update book data if exists
     */
    @Override
    public ResponseEntity<ResponseDTO> UpdateBookData(int id, BookDTO bookDTO) {
        BookData bookData = new BookData(bookDTO);
        if(bookData.equals(null)){
            throw new BookException("book record is not found", BookException.ExceptionType.BOOK_INVALID);
        }
        bookData.setId(id);
        bookRepository.save(bookData);
        ResponseDTO responseDTO = new ResponseDTO("update success", bookData, "");
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @NoParam  is passing
     * @return the responseDTO if all user found
     * @Purpose to fetching the all the books registered if exists
     */
    @Override
    public ResponseEntity<ResponseDTO> getAllBooksById() {
        List<BookData> bookData = bookRepository.findAll();
        if (bookData.isEmpty()){
            throw new BookException("No one record is found", BookException.ExceptionType.NO_BOOK_REGISTERED);
        }
        ResponseDTO responseDTO = new ResponseDTO("Gell call success", bookData, "");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @param name to verify the book
     * @return response of the user with confirmation message
     * @purpose getting book by name if exists
     */
    @Override
    public ResponseEntity<ResponseDTO> searchByName(String name) {
       BookData bookData = bookRepository.findByBookName(name);
       if (bookData.equals(null)){
           throw new BookException("No one book present with this name", BookException.ExceptionType.NOT_VALID_BOOK);
       }
       ResponseDTO responseDTO = new ResponseDTO("search success", bookData, " ");
       return new ResponseEntity<ResponseDTO> (responseDTO, HttpStatus.OK);
    }
    /**
     *
     * @param id to verify the book
     * @return response of the user with confirmation message
     * @purpose updating book quantity if book exists
     */
    @Override
    public ResponseEntity<ResponseDTO> UpdateQuantity(int id, String quantity) {
        Optional<BookData> bookData = bookRepository.findById(id);
        if (bookData.isEmpty()){
            throw new BookException("no books are found", BookException.ExceptionType.INVALID_DATA);
        }
        bookData.get().setQuantity(quantity);
        bookRepository.save(bookData.get());
        ResponseDTO responseDTO = new ResponseDTO("updated quantity", bookData, "");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
    /**
     *
     * @param id to delete the book
     * @return the ResponseDTO if user deleted
     * @Purpose is to delete the book if exists
     */
    @Override
    public ResponseEntity<ResponseDTO> deleteBook(int id) {
        if(bookRepository.findById(id).isEmpty()){
            throw new BookException("No record are found", BookException.ExceptionType.BOOK_NOT_FOUND);
        }
        bookRepository.deleteById(id);
        ResponseDTO responseDTO = new ResponseDTO("delete success", id, "");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }
}
