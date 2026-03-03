package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.TicketAssignment;
public interface TicketAssignmentRepository extends JpaRepository<TicketAssignment, Integer> {
}
