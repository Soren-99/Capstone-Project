package soren.rahimi.Capstone.Project.service.admin.faq;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import soren.rahimi.Capstone.Project.dto.FAQDTO;
import soren.rahimi.Capstone.Project.entities.FAQ;
import soren.rahimi.Capstone.Project.entities.Product;
import soren.rahimi.Capstone.Project.repository.FAQRepository;
import soren.rahimi.Capstone.Project.repository.ProductRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FAQServiceImpl implements FAQService{

    private final FAQRepository faqRepository;

    private final ProductRepository productRepository;

    public FAQDTO postFAQ(Long productId, FAQDTO faqDTO){
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if (optionalProduct.isPresent()){
            FAQ faq = new FAQ();

            faq.setQuestion(faqDTO.getQuestion());
            faq.setAnswer(faqDTO.getAnswer());
            faq.setProduct(optionalProduct.get());

            return faqRepository.save(faq).getFAQDTO();
        }
        return null;
    }

}
