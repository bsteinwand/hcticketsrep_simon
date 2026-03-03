package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.Ticket;
public interface TicketRepository extends JpaRepository<Ticket, Integer> {
}
