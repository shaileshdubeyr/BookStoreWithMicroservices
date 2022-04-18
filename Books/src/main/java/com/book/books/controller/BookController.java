package com.book.books.controller;

import com.book.books.dto.BookDTO;
import com.book.books.dto.ResponseDTO;
import com.book.books.model.BookData;
import com.book.books.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    BookService bookService;

    /**
     *
     * @param bookDTO for registration object
     * @return user status
     * @Purpose to call the save method of the service
     */
    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> saveBook(@RequestBody @Valid BookDTO bookDTO){
        return  bookService.saveBooks(bookDTO);
    }

    /**
     *
     * no parameter passed to get book
     * @return ResponseDTO status to the user
     * @Puropse to retrive all the Books data from database
     */
    @GetMapping("/getallBooks")
    public ResponseEntity<ResponseDTO> getAllBooks(){
       return bookService.getAllBooksById();
    }

    /**
     *
     * @param id retrive book by id
     * @return ResponseDTO status to the user
     * @Puropse to retrive the book data from database by id
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<BookData> getBookById(@PathVariable("id") int id){
        System.out.println(id);
        return bookService.getBookById(id);
    }

    /**
     *
     * @param id and bookDTO for update the book data
     * @return ResponseDTO status to the user
     * @Purpose to update the book data with the book id
     */
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> upDateBook(@RequestParam("id") int id, @RequestBody @Valid BookDTO bookDTO){
        return  bookService.UpdateBookData(id, bookDTO);
    }

    /**
     *
     * @param name of the book to retrive the same
     * @return ResponseDTO status to the user
     * @Puropse to fetching the book data from database by name
     */
    @GetMapping("/byname")
    public ResponseEntity<ResponseDTO> searchByName(@RequestParam String name){
        return bookService.searchByName(name);
    }

    /**
     *
     * @param id to verify the user
     * quantity contains updated quantity of the book
     * @return ResponseDTO to return the status of the user
     */
    @GetMapping("/updateQuantity/{id}")
    public ResponseEntity<ResponseDTO> updateQuantity(@PathVariable int id, @RequestParam String quantity){
       return bookService.UpdateQuantity(id, quantity);
    }

    /**
     *
     * @param id deleting the book with the id
     * @return ResponseDTO to the user
     * @purpose to delete the particular book if id is present
     */
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable int id){
       return bookService.deleteBook(id);
    }
}
