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
@Table(name = "UserApplicationRole", schema = "hctickets")
//@ToString
//@NoArgsConstructor
//@EqualsAndHashCode
//@Data
public class UserApplicationRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AppRoleId", nullable = false)
    private ApplicationRole applicationRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AppId", nullable = false)
    private Application application;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @Column(name = "InactiveDate")
    private OffsetDateTime inactiveDate;

    
    
	public UserApplicationRole() {
		super();
	}

	public UserApplicationRole(Integer id, ApplicationRole applicationRole, User user, Application application,
			String status, OffsetDateTime inactiveDate) {
		super();
		this.id = id;
		this.applicationRole = applicationRole;
		this.user = user;
		this.application = application;
		this.status = status;
		this.inactiveDate = inactiveDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ApplicationRole getApplicationRole() {
		return applicationRole;
	}

	public void setApplicationRole(ApplicationRole applicationRole) {
		this.applicationRole = applicationRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OffsetDateTime getInactiveDate() {
		return inactiveDate;
	}

	public void setInactiveDate(OffsetDateTime inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	@Override
	public int hashCode() {
		return Objects.hash(application, applicationRole, id, inactiveDate, status, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserApplicationRole other = (UserApplicationRole) obj;
		return Objects.equals(application, other.application) && Objects.equals(applicationRole, other.applicationRole)
				&& Objects.equals(id, other.id) && Objects.equals(inactiveDate, other.inactiveDate)
				&& Objects.equals(status, other.status) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "UserApplicationRole [id=" + id + ", applicationRole=" + applicationRole + ", user=" + user
				+ ", application=" + application + ", status=" + status + ", inactiveDate=" + inactiveDate + "]";
	}
    
}