package com.book.books.model;

import com.book.books.dto.BookDTO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "booktable")
public class BookData {
    @Id
    @GeneratedValue
    private  int id;
    private String bookName;
    private String authorName;
    private String bookDiscription;
    private String bookImageSource;
    private int price;
    private String quantity;

    public BookData(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.authorName = bookDTO.authorName;
        this.bookDiscription = bookDTO.bookDiscription;
        this.bookImageSource = bookDTO.bookImageSource;
        this.price = bookDTO.price;
        this.quantity = bookDTO.quantity;
    }

    public BookData(){

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getBookDiscription() {
        return bookDiscription;
    }

    public void setBookDiscription(String bookDiscription) {
        this.bookDiscription = bookDiscription;
    }

    public String getBookImageSource() {
        return bookImageSource;
    }

    public void setBookImageSource(String bookImageSource) {
        this.bookImageSource = bookImageSource;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "BookData{" +
                "id=" + id +
                ", bookName='" + bookName + '\'' +
                ", authorName='" + authorName + '\'' +
                ", bookDiscription='" + bookDiscription + '\'' +
                ", bookImageSource='" + bookImageSource + '\'' +
                ", price=" + price +
                ", quantity='" + quantity + '\'' +
                '}';
    }
}
