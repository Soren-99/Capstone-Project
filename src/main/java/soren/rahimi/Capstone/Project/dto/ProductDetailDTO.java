package soren.rahimi.Capstone.Project.dto;


import lombok.Data;

import java.util.List;

@Data
public class ProductDetailDTO {

    private ProductDTO productDTO;

    private List<ReviewDTO> reviewDTOList;

    private List<FAQDTO> faqDTOList;
}
