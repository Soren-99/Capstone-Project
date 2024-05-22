package soren.rahimi.Capstone.Project.dto;

import lombok.Data;
import org.springframework.security.core.parameters.P;

import java.util.List;

@Data
public class OrderedProductsResponseDTO {

    private List<ProductDTO> productDTOList;

    private Long orderAmount;
}
