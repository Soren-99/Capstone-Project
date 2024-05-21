package soren.rahimi.Capstone.Project.service.admin.faq;

import soren.rahimi.Capstone.Project.dto.FAQDTO;

public interface FAQService {
    FAQDTO postFAQ(Long productId, FAQDTO faqDTO);
}
