package soren.rahimi.Capstone.Project.service.customer;

import soren.rahimi.Capstone.Project.dto.ProductDTO;

import java.util.List;

public interface CustomerProductService {

    List<ProductDTO> searchProductByTitle(String title);
    List<ProductDTO> getAllProducts();
}
