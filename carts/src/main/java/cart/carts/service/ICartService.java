package cart.carts.service;

import cart.carts.dto.BookDTO;
import cart.carts.dto.ResponseDTO;
import org.springframework.http.ResponseEntity;

public interface ICartService {
    ResponseEntity<ResponseDTO> getAllDataInCart();
    ResponseEntity<ResponseDTO> saveBooksToCart(Integer quantity, Integer bookId, String token) throws Exception;
    ResponseEntity<ResponseDTO> updateQuantity(Integer bookCartId, Integer quantity);
    ResponseEntity<ResponseDTO> deleteBookFromCart(int id);
    ResponseEntity<ResponseDTO> updateCart(int id, BookDTO bookDTO);
    ResponseEntity<ResponseDTO> getById(int id);
}
