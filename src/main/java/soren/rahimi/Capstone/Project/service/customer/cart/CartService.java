package soren.rahimi.Capstone.Project.service.customer.cart;

import org.springframework.http.ResponseEntity;
import soren.rahimi.Capstone.Project.dto.AddProductInCartDTO;
import soren.rahimi.Capstone.Project.dto.OrderDTO;
import soren.rahimi.Capstone.Project.dto.PlaceOrderDTO;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDTO addProductInCartDTO);

    OrderDTO getCartByUserId(Long userId);

    OrderDTO applyCoupon (Long userId, String code);

    OrderDTO increaseProductQuantity(AddProductInCartDTO addProductInCartDTO);

    OrderDTO decreaseProductQuantity(AddProductInCartDTO addProductInCartDTO);

    OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO);
}
