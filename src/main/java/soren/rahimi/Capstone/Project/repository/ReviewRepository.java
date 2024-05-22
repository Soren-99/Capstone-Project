package soren.rahimi.Capstone.Project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import soren.rahimi.Capstone.Project.entities.Review;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long>  {
}
