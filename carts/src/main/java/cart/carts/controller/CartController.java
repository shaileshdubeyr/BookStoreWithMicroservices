package cart.carts.controller;

import cart.carts.dto.BookDTO;
import cart.carts.dto.ResponseDTO;
import cart.carts.service.ICartService;
import cart.carts.util.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @Autowired
    UserUtility utility;

    /**
     * @param quantity,bookId and userId to add in the cart
     * @return cart status with responseDTO
     * @Purpose to save the all the entities
     */
    @PostMapping("/create/{quantity}")
    public ResponseEntity<ResponseDTO> saveCart(@PathVariable int quantity, @RequestParam int bookId, @RequestParam("id") int userId) throws Exception {
        String token = utility.createToken(userId);
        return cartService.saveBooksToCart(quantity, bookId, token);
    }

    /**
     * @param id to update the user
     * @param bookDTO contains updated data of the book
     * @return ResponseDTO to return the status of the user
     */
    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> updateCart(@RequestParam int id, @RequestBody BookDTO bookDTO) {
        return  cartService.updateCart(id, bookDTO);
    }

    /**
     * @param bookCartId to update the user
     * @param quantity contains updated quantity of the book
     * @return ResponseDTO to return the status of the book and user
     */
    @PutMapping("/cart/{bookCartId}/{quantity}")
    public ResponseEntity updateQuantity(@PathVariable Integer bookCartId, @PathVariable Integer quantity) {
        return  cartService.updateQuantity(bookCartId, quantity);
    }

    /**
     * @return the list the cart data if exists
     */
    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllCartData(){
        return cartService.getAllDataInCart();
    }

    /**
     * @param id for verification user
     * @return ResponseDTO status to the cart
     * @Puropse to fetch the user data and book from database by cart id
     */
    @GetMapping("/getbyid")
    public ResponseEntity<ResponseDTO> getById(@RequestParam int id){
        return cartService.getById(id);
    }

    /**
     * @param id deleting the cartData with the id
     * @return ResponseDTO to the user
     * @purpose to delete the cart if id is present
     */
    @DeleteMapping("/delete/")
    public ResponseEntity<ResponseDTO> deleteFromCart(@RequestParam int id) {
        return cartService.deleteBookFromCart(id);
    }
}
