package soren.rahimi.Capstone.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soren.rahimi.Capstone.Project.entities.FAQ;
import soren.rahimi.Capstone.Project.entities.Review;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>  {

    List<Review> findAllByProductId(Long productsId);
}
