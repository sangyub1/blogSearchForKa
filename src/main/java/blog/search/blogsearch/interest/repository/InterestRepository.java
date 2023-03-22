package blog.search.blogsearch.interest.repository;

import blog.search.blogsearch.interest.entity.InterestEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<InterestEntity,String> {
}
