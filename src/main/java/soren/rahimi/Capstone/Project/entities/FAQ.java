package soren.rahimi.Capstone.Project.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import soren.rahimi.Capstone.Project.dto.FAQDTO;

@Entity
@Data
public class FAQ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;
    private String answer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Product product;

    public FAQDTO getFAQDTO(){
        FAQDTO faqDTO = new FAQDTO();
        faqDTO.setId(id);
        faqDTO.setQuestion(question);
        faqDTO.setAnswer(answer);
        faqDTO.setProductId(product.getId());

        return faqDTO;
    }

}
