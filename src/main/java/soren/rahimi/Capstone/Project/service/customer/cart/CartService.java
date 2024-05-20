package soren.rahimi.Capstone.Project.service.customer.cart;

import org.springframework.http.ResponseEntity;
import soren.rahimi.Capstone.Project.dto.AddProductInCartDTO;
import soren.rahimi.Capstone.Project.dto.OrderDTO;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDTO addProductInCartDTO);

    OrderDTO getCartByUserId(Long userId);
}
