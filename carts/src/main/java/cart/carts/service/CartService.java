package cart.carts.service;

import cart.carts.dto.BookDTO;
import cart.carts.dto.ResponseDTO;
import cart.carts.exception.CartException;
import cart.carts.model.BookData;
import cart.carts.model.CartData;
import cart.carts.model.UserData;
import cart.carts.repository.CartRepository;
import cart.carts.util.UserUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements  ICartService{
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserUtility utility;

    /**
     * @param quantity book qusntity
     * @param bookId book id
     * @param token token to verify
     * @return ResponseDTO to return status if data not present
     * @throws Exception if data not exists then throw exception
     */
    @Override
    public ResponseEntity<ResponseDTO> saveBooksToCart(Integer quantity, Integer bookId, String token) throws Exception {
        BookData bookData  = restTemplate.getForObject("http://localhost:8082/book/get/" + bookId, BookData.class);
        UserData userData  = restTemplate.getForObject("http://localhost:8081/user/getById?token=" + token, UserData.class);
        if(bookData == null || userData == null){
            throw new CartException("the user obj is "+ userData + "the book obj is "+bookData, CartException.ExceptionType.ENDPOINT_INVALID_OR_NULL);
        }
        CartData cartData = new CartData(bookData, userData, quantity);
        if(quantity > Integer.parseInt(bookData.getQuantity()))
            throw new CartException("the book quantity is grater", CartException.ExceptionType.QUANTITY_IS_GRATER);
        cartRepository.save(cartData);
        ResponseDTO responseDTO = new ResponseDTO("save success", cartData,"");
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param id to get data with the id
     * @param bookDto to update the book
     * @return ResponseDTO to return status if data present
     */
    @Override
    public ResponseEntity<ResponseDTO> updateCart(int id, BookDTO bookDto) {
        System.out.println("in ser service");
        BookData bookData_cart = new BookData(bookDto);
        Optional<CartData> cartData = cartRepository.findById(id);
        if (bookData_cart == null || cartData.isEmpty())
            throw new CartException("the cart obj is"+ cartData + "the book obj is "+bookData_cart, CartException.ExceptionType.ENDPOINT_INVALID_OR_NULL);
        int i_d = cartData.get().getBook().getId();
        bookData_cart.setId(i_d);
        cartData.get().setBook(bookData_cart);
        ResponseDTO responseDTO = new ResponseDTO("update book data success", cartData, "");
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     * @param id to get the all the data with this id
     * @return ResponseDTO to return status if data present
     */
    @Override
    public ResponseEntity<ResponseDTO> getById(int id) {
        Optional<CartData> cartData = cartRepository.findById(id);
        if (cartData.isEmpty()){
            throw new CartException("the cart data is empty", CartException.ExceptionType.ENDPOINT_INVALID_OR_NULL);
        }
        ResponseDTO responseDTO = new ResponseDTO("get success", cartData,"");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @return the list of cart data if exists
     */
    @Override
    public ResponseEntity<ResponseDTO> getAllDataInCart() {
        List<CartData> cartData = cartRepository.findAll();
        if (cartData.isEmpty()){
            throw new CartException("No data is registered", CartException.ExceptionType.NO_DATA_REGISTERED);
        }
        ResponseDTO responseDTO = new ResponseDTO("ger all success",cartData, "");
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }

    /**
     *
     * @param bookCartId get the book
     * @param quantity to update the quantity
     * @return updated books cart data if exists
     */
    @Override
    public ResponseEntity<ResponseDTO> updateQuantity(Integer bookCartId, Integer quantity) {
        Optional<CartData> cartData = cartRepository.findById(bookCartId);
        if(cartData.isEmpty())
            throw new CartException("data is not present", CartException.ExceptionType.NO_DATA_REGISTERED);
        cartData.get().setQuantity(quantity);
        cartRepository.save(cartData.get());
        ResponseDTO responseDTO = new ResponseDTO("updated quantity", cartData, "");
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    /**
     *
     * @param id to delete the cart data with this id
     * @return id of the cart data wich deleted
     */
    @Override
    public ResponseEntity<ResponseDTO> deleteBookFromCart(int id) {
        if (cartRepository.findById(id).isPresent()){
            cartRepository.deleteById(id);
            ResponseDTO responseDTO = new ResponseDTO("delete success",id, "");
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        }
        throw new CartException("given id not found", CartException.ExceptionType.NOT_VALID_CART_DATA);
    }
}
