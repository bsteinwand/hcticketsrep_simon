package edu.helenacollege.hctickets.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import edu.helenacollege.hctickets.model.Status;

public interface StatusRepository extends JpaRepository<Status, Integer> {
	public Status findByCode(String code);
	public List<Status> findByActive(boolean active);
	public List<Status> findByStatusType(String statusType);
	
}
