package soren.rahimi.Capstone.Project.dto;

import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;
import soren.rahimi.Capstone.Project.entities.Product;
import soren.rahimi.Capstone.Project.entities.User;

@Data
public class ReviewDTO {

    private Long id;

    private Long rating;


    private String description;

    private MultipartFile img;

    private byte[] returnedImg;


    private Long userId;


    private Long productId;

    private String username;
}
