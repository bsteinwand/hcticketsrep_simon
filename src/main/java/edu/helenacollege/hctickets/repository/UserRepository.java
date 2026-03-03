package edu.helenacollege.hctickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.helenacollege.hctickets.model.User;
public interface UserRepository extends JpaRepository<User, Integer> {
	@Query(value = "SELECT u FROM User u JOIN FETCH u.role WHERE u.id = :id", nativeQuery = false)
    // Return a custom DTO or a List<Tuple> for specific column retrieval
    User findUserWithRole(@Param("id") int id);
}
