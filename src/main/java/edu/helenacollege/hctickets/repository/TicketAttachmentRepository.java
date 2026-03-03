package edu.helenacollege.hctickets.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.helenacollege.hctickets.model.TicketAttachment;
public interface TicketAttachmentRepository extends JpaRepository<TicketAttachment, Integer> {
}