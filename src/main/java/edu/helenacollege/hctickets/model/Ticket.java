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
@Table(name = "Ticket", schema = "hctickets")
//@ToString
//@NoArgsConstructor
//@EqualsAndHashCode
//@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "Title", nullable = false, length = 40)
    private String title;

    @Column(name = "Description", nullable = false, length = 600)
    private String description;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CreatedBy", nullable = false)
    private User createdBy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ApplicationId", nullable = false)
    private Application application;

    @Column(name = "CreationDate", nullable = false)
    private OffsetDateTime creationDate;

    @Column(name = "Visibility", nullable = false, length = 20)
    private String visibility;

    @Column(name = "ClosedDate")
    private OffsetDateTime closedDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ClosedBy", nullable = false)
    private User closedBy;

    
	public Ticket() {
		super();
	}

	public Ticket(Integer id, String title, String description, String status, User createdBy, Application application,
			OffsetDateTime creationDate, String visibility, OffsetDateTime closedDate, User closedBy) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.status = status;
		this.createdBy = createdBy;
		this.application = application;
		this.creationDate = creationDate;
		this.visibility = visibility;
		this.closedDate = closedDate;
		this.closedBy = closedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public String getVisibility() {
		return visibility;
	}

	public void setVisibility(String visibility) {
		this.visibility = visibility;
	}

	public OffsetDateTime getClosedDate() {
		return closedDate;
	}

	public void setClosedDate(OffsetDateTime closedDate) {
		this.closedDate = closedDate;
	}

	public User getClosedBy() {
		return closedBy;
	}

	public void setClosedBy(User closedBy) {
		this.closedBy = closedBy;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, closedBy, closedDate, createdBy, creationDate, description, id, status, title,
				visibility);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ticket other = (Ticket) obj;
		return Objects.equals(application, other.application) && Objects.equals(closedBy, other.closedBy)
				&& Objects.equals(closedDate, other.closedDate) && Objects.equals(createdBy, other.createdBy)
				&& Objects.equals(creationDate, other.creationDate) && Objects.equals(description, other.description)
				&& Objects.equals(id, other.id) && Objects.equals(status, other.status)
				&& Objects.equals(title, other.title) && Objects.equals(visibility, other.visibility);
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", title=" + title + ", description=" + description + ", status=" + status
				+ ", createdBy=" + createdBy + ", application=" + application + ", creationDate=" + creationDate
				+ ", visibility=" + visibility + ", closedDate=" + closedDate + ", closedBy=" + closedBy + "]";
	}


}