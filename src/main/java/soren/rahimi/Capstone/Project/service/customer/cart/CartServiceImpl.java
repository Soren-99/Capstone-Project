package soren.rahimi.Capstone.Project.service.customer.cart;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import soren.rahimi.Capstone.Project.dto.AddProductInCartDTO;
import soren.rahimi.Capstone.Project.dto.AddToCartResponse;
import soren.rahimi.Capstone.Project.dto.CartItemsDTO;
import soren.rahimi.Capstone.Project.dto.OrderDTO;
import soren.rahimi.Capstone.Project.entities.CartItems;
import soren.rahimi.Capstone.Project.entities.Order;
import soren.rahimi.Capstone.Project.entities.Product;
import soren.rahimi.Capstone.Project.entities.User;
import soren.rahimi.Capstone.Project.enums.OrderStatus;
import soren.rahimi.Capstone.Project.repository.CartItemsRepository;
import soren.rahimi.Capstone.Project.repository.OrderRepository;
import soren.rahimi.Capstone.Project.repository.ProductRepository;
import soren.rahimi.Capstone.Project.repository.UserRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private ProductRepository productRepository;


    @Transactional
    public ResponseEntity<?> addProductToCart(AddProductInCartDTO addProductInCartDTO){
        AddToCartResponse addToCartResponse = new AddToCartResponse("Product add to the cart");
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(addProductInCartDTO.getUserId(), OrderStatus.Pending);
        if (activeOrder == null){
            User user = userRepository.findById(addProductInCartDTO.getUserId()).get();
            activeOrder = new Order();
            activeOrder.setOrderStatus(OrderStatus.Pending);
            activeOrder.setUser(user);
            activeOrder.setDate(LocalDate.now());
            activeOrder.setOrderDescription("order");
            orderRepository.save(activeOrder);
        }
        Optional<CartItems> optionalCartItems = cartItemsRepository.findByProductIdAndOrderIdAndUserId
                (addProductInCartDTO.getProductId(), activeOrder.getId(), addProductInCartDTO.getUserId());


        if(optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.OK).body(new AddToCartResponse("Increase quantity"));
        }else{
            Optional<Product> optionalProduct = productRepository.findById(addProductInCartDTO.getProductId());
            Optional<User> optionalUser = userRepository.findById(addProductInCartDTO.getUserId());

            if(optionalProduct.isPresent() && optionalUser.isPresent()){
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);
                CartItems updatedCart = cartItemsRepository.save(cart);

                activeOrder.setTotalAmount(activeOrder.getTotalAmount() + cart.getPrice());
                activeOrder.setAmount(activeOrder.getAmount() + cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepository.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(addToCartResponse);

            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }
    }



    @Transactional
    public OrderDTO getCartByUserId(Long userId){
        Order activeOrder = orderRepository.findByUserIdAndOrderStatus(userId, OrderStatus.Pending);
        List<CartItemsDTO> cartItemsDTOList = activeOrder.getCartItems().stream().map(CartItems::getCartDTO).collect(Collectors.toList());
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAmount(activeOrder.getAmount());
        orderDTO.setId(activeOrder.getId());
        orderDTO.setOrderStatus(activeOrder.getOrderStatus());
        orderDTO.setDiscount(activeOrder.getDiscount());
        orderDTO.setTotalAmount(activeOrder.getTotalAmount());
        orderDTO.setCartItems(cartItemsDTOList);

        return orderDTO;
    }
}
