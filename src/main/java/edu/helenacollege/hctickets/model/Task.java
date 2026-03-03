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
@Table(name = "Task", schema = "hctickets")
//@ToString
//@NoArgsConstructor
//@EqualsAndHashCode
//@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Name", nullable = false, length = 60)
    private String name;

    @Column(name = "Details", nullable = false, length = 1000)
    private String details;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TicketId", nullable = false)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @Column(name = "CreationDate", nullable = false)
    private OffsetDateTime creationDate;

    @Column(name = "CompletedDate")
    private OffsetDateTime completedDate;
    
	public Task() {
		super();
	}

	public Task(Integer id, String name, String details, Ticket ticket, User user, String status,
			OffsetDateTime creationDate, OffsetDateTime completedDate) {
		super();
		this.id = id;
		this.name = name;
		this.details = details;
		this.ticket = ticket;
		this.user = user;
		this.status = status;
		this.creationDate = creationDate;
		this.completedDate = completedDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public OffsetDateTime getCompletedDate() {
		return completedDate;
	}

	public void setCompletedDate(OffsetDateTime completedDate) {
		this.completedDate = completedDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(completedDate, creationDate, details, id, name, status, ticket, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Task other = (Task) obj;
		return Objects.equals(completedDate, other.completedDate) && Objects.equals(creationDate, other.creationDate)
				&& Objects.equals(details, other.details) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(status, other.status)
				&& Objects.equals(ticket, other.ticket) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", name=" + name + ", details=" + details + ", ticket=" + ticket + ", user=" + user
				+ ", status=" + status + ", creationDate=" + creationDate + ", completedDate=" + completedDate + "]";
	}
	
}