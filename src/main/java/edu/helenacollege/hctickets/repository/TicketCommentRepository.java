package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.TicketComment;
public interface TicketCommentRepository extends JpaRepository<TicketComment, Integer> {
}
