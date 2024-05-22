package soren.rahimi.Capstone.Project.service.customer;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import soren.rahimi.Capstone.Project.dto.ProductDTO;
import soren.rahimi.Capstone.Project.dto.ProductDetailDTO;
import soren.rahimi.Capstone.Project.entities.FAQ;
import soren.rahimi.Capstone.Project.entities.Product;
import soren.rahimi.Capstone.Project.entities.Review;
import soren.rahimi.Capstone.Project.repository.FAQRepository;
import soren.rahimi.Capstone.Project.repository.ProductRepository;
import soren.rahimi.Capstone.Project.repository.ReviewRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements CustomerProductService {

    private final ProductRepository productRepository;

    private final FAQRepository faqRepository;

    private final ReviewRepository reviewRepository;


    @Transactional
    public List<ProductDTO> getAllProducts(){
        List<Product> products = productRepository.findAll();
        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    @Transactional
    public List<ProductDTO> searchProductByTitle(String name){
        List<Product> products = productRepository.findAllByNameContaining(name);
        return products.stream().map(Product::getDTO).collect(Collectors.toList());
    }

    @Transactional
    public ProductDetailDTO getProductDetailById(Long productId){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()){
            List<FAQ> faqList = faqRepository.findAllByProductId(productId);
            List<Review> reviewsList = reviewRepository.findAllByProductId(productId);

            ProductDetailDTO productDetailDTO = new ProductDetailDTO();

            productDetailDTO.setProductDTO(optionalProduct.get().getDTO());
            productDetailDTO.setFaqDTOList(faqList.stream().map(FAQ::getFAQDTO).collect(Collectors.toList()));
            productDetailDTO.setReviewDTOList(reviewsList.stream().map(Review::getDTO).collect(Collectors.toList()));

            return productDetailDTO;
        }
        return null;
    }
}
