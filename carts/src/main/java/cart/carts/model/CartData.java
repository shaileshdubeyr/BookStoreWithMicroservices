package cart.carts.model;

import javax.persistence.*;

@Entity
@Table(name = "cartData")
public class CartData {
    @Id
    @GeneratedValue
    private int id;
    @ManyToOne
    @JoinColumn(name = "book_ID")
    private BookData book;
    @ManyToOne
    @JoinColumn(name = "user_ID")
    private UserData user;
    private int quantity;

    public CartData() {

    }

    public CartData(BookData book, UserData user, int quantity) {
        this.book = book;
        this.user = user;
        this.quantity = quantity;
    }

    public BookData getBook() {
        return book;
    }

    public void setBook(BookData book) {
        this.book = book;
    }

    public UserData getUser() {
        return user;
    }

    public void setUser(UserData user) {
        this.user = user;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
