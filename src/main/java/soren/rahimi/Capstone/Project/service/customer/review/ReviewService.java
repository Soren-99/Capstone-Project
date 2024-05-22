package soren.rahimi.Capstone.Project.service.customer.review;

import soren.rahimi.Capstone.Project.dto.OrderedProductsResponseDTO;
import soren.rahimi.Capstone.Project.dto.ReviewDTO;

import java.io.IOException;

public interface ReviewService {
    OrderedProductsResponseDTO getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDTO giveReview(ReviewDTO reviewDTO) throws IOException;
}
