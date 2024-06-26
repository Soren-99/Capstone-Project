package soren.rahimi.Capstone.Project.service.customer.cart;

import org.springframework.http.ResponseEntity;
import soren.rahimi.Capstone.Project.dto.AddProductInCartDTO;
import soren.rahimi.Capstone.Project.dto.OrderDTO;
import soren.rahimi.Capstone.Project.dto.PlaceOrderDTO;

import java.util.List;
import java.util.UUID;

public interface CartService {

    ResponseEntity<?> addProductToCart(AddProductInCartDTO addProductInCartDTO);

    OrderDTO getCartByUserId(Long userId);

    OrderDTO applyCoupon (Long userId, String code);

    OrderDTO increaseProductQuantity(AddProductInCartDTO addProductInCartDTO);

    OrderDTO decreaseProductQuantity(AddProductInCartDTO addProductInCartDTO);

    OrderDTO placeOrder(PlaceOrderDTO placeOrderDTO);

    List<OrderDTO> getMyPlacedOrders(Long userId);

    OrderDTO searchOrderByTrackingId(UUID trackingId);
}
