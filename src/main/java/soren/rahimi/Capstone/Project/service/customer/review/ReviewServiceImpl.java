package soren.rahimi.Capstone.Project.service.customer.review;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soren.rahimi.Capstone.Project.dto.OrderedProductsResponseDTO;
import soren.rahimi.Capstone.Project.dto.ProductDTO;
import soren.rahimi.Capstone.Project.dto.ReviewDTO;
import soren.rahimi.Capstone.Project.entities.*;
import soren.rahimi.Capstone.Project.repository.OrderRepository;
import soren.rahimi.Capstone.Project.repository.ProductRepository;
import soren.rahimi.Capstone.Project.repository.ReviewRepository;
import soren.rahimi.Capstone.Project.repository.UserRepository;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;

    private final UserRepository userRepository;

    private final ReviewRepository reviewRepository;

    @Transactional
    public OrderedProductsResponseDTO getOrderedProductsDetailsByOrderId(Long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        OrderedProductsResponseDTO orderedProductsResponseDTO = new OrderedProductsResponseDTO();
        if(optionalOrder.isPresent()){
            orderedProductsResponseDTO.setOrderAmount(optionalOrder.get().getAmount());

            List<ProductDTO> productDTOList = new ArrayList<>();
            for (CartItems cartItems: optionalOrder.get().getCartItems()){
                ProductDTO productDTO = new ProductDTO();

                productDTO.setId(cartItems.getProduct().getId());
                productDTO.setName(cartItems.getProduct().getName());
                productDTO.setPrice(cartItems.getPrice());
                productDTO.setQuantity(cartItems.getQuantity());

                productDTO.setByteImg(cartItems.getProduct().getImg());

                productDTOList.add(productDTO);
            }
            orderedProductsResponseDTO.setProductDTOList(productDTOList);
        }
        return orderedProductsResponseDTO;
    }

    @Transactional
    public ReviewDTO giveReview(ReviewDTO reviewDTO) throws IOException {
        Optional<Product> optionalProduct = productRepository.findById(reviewDTO.getProductId());
        Optional<User> optionalUser = userRepository.findById(reviewDTO.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Review review = new Review();

            review.setRating(reviewDTO.getRating());
            review.setDescription(reviewDTO.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDTO.getImg().getBytes());

            return reviewRepository.save(review).getDTO();
        }
        return null;
    }
}
