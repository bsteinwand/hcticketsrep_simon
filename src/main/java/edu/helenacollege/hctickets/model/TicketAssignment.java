package edu.helenacollege.hctickets.model;

import java.time.OffsetDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.ToString;


@Entity
@Table(name = "TicketAssignment", schema = "hctickets")
//@ToString
//@NoArgsConstructor
//@EqualsAndHashCode
//@Data
public class TicketAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TicketId", nullable = false)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TechnicianId", nullable = false)
    private User technician;

    @Column(name = "ActiveDate", nullable = false)
    private OffsetDateTime activeDate;

    @Column(name = "InactiveDate")
    private OffsetDateTime inactiveDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AssignedBy", nullable = false)
    private User assignedBy;
    
	public TicketAssignment() {
		super();
	}

	public TicketAssignment(Integer id, Ticket ticket, User technician, OffsetDateTime activeDate,
			OffsetDateTime inactiveDate, User assignedBy) {
		super();
		this.id = id;
		this.ticket = ticket;
		this.technician = technician;
		this.activeDate = activeDate;
		this.inactiveDate = inactiveDate;
		this.assignedBy = assignedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getTechnician() {
		return technician;
	}

	public void setTechnician(User technician) {
		this.technician = technician;
	}

	public OffsetDateTime getActiveDate() {
		return activeDate;
	}

	public void setActiveDate(OffsetDateTime activeDate) {
		this.activeDate = activeDate;
	}

	public OffsetDateTime getInactiveDate() {
		return inactiveDate;
	}

	public void setInactiveDate(OffsetDateTime inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public User getAssignedBy() {
		return assignedBy;
	}

	public void setAssignedBy(User assignedBy) {
		this.assignedBy = assignedBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(activeDate, assignedBy, id, inactiveDate, technician, ticket);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketAssignment other = (TicketAssignment) obj;
		return Objects.equals(activeDate, other.activeDate) && Objects.equals(assignedBy, other.assignedBy)
				&& Objects.equals(id, other.id) && Objects.equals(inactiveDate, other.inactiveDate)
				&& Objects.equals(technician, other.technician) && Objects.equals(ticket, other.ticket);
	}

	@Override
	public String toString() {
		return "TicketAssignment [id=" + id + ", ticket=" + ticket + ", technician=" + technician + ", activeDate="
				+ activeDate + ", inactiveDate=" + inactiveDate + ", assignedBy=" + assignedBy + "]";
	}

}