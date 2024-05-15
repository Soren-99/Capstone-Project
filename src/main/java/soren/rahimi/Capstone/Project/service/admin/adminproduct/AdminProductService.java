package soren.rahimi.Capstone.Project.service.admin.adminproduct;

import soren.rahimi.Capstone.Project.dto.ProductDTO;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDTO addProduct(ProductDTO productDTO) throws IOException;

    List<ProductDTO> getAllProducts();

    List<ProductDTO> getAllProductByName(String name);

    boolean deleteProduct(Long id);
}
