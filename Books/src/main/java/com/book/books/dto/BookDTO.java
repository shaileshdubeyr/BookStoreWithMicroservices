package com.book.books.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class BookDTO {
    @NotBlank(message = "book name not blank")
    @Size(min = 5, max = 30, message = "minimum character of the Book name is 5")
    public String bookName;
    @NotBlank(message = "book name not blank")
    @Size(min = 5, max = 30, message = "minimum character of the Author name is 5")
    public String authorName;
    public String bookDiscription;
    public String bookImageSource;
    public int price;
    public String quantity;
}
