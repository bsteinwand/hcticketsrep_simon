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
@Table(name = "User", schema = "hctickets")
//@ToString
//@NoArgsConstructor
//@EqualsAndHashCode
//@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "FirstName", nullable = false, length = 40)
    private String firstName;

    @Column(name = "LastName", nullable = false, length = 40)
    private String lastName;

    @Column(name = "Email", nullable = false, length = 60)
    private String email;

    @Column(name = "SMSNumber", length = 20)
    private String smsNumber;

    @Column(name = "CreationDate", nullable = false)
    private OffsetDateTime creationDate;

    @Column(name = "InactiveDate")
    private OffsetDateTime inactiveDate;

    @Column(name = "Status", nullable = false, length = 20)
    private String status;

    @Column(name = "Salt", nullable = false, length = 255)
    private String salt;

    @Column(name = "PasswordHash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "NotificationPreference", nullable = false, length = 20)
    private String notificationPreference;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RoleId", nullable = false)
    private Role role;

	public User() {
		super();
	}

	public User(Integer id, String firstName, String lastName, String email, String smsNumber,
			OffsetDateTime creationDate, OffsetDateTime inactiveDate, String status, String salt, String passwordHash,
			String notificationPreference, Role role) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.smsNumber = smsNumber;
		this.creationDate = creationDate;
		this.inactiveDate = inactiveDate;
		this.status = status;
		this.salt = salt;
		this.passwordHash = passwordHash;
		this.notificationPreference = notificationPreference;
		this.role = role;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSmsNumber() {
		return smsNumber;
	}

	public void setSmsNumber(String smsNumber) {
		this.smsNumber = smsNumber;
	}

	public OffsetDateTime getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(OffsetDateTime creationDate) {
		this.creationDate = creationDate;
	}

	public OffsetDateTime getInactiveDate() {
		return inactiveDate;
	}

	public void setInactiveDate(OffsetDateTime inactiveDate) {
		this.inactiveDate = inactiveDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}

	public String getNotificationPreference() {
		return notificationPreference;
	}

	public void setNotificationPreference(String notificationPreference) {
		this.notificationPreference = notificationPreference;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	

	@Override
	public int hashCode() {
		return Objects.hash(creationDate, email, firstName, id, inactiveDate, lastName, notificationPreference,
				passwordHash, role, salt, smsNumber, status);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(creationDate, other.creationDate) && Objects.equals(email, other.email)
				&& Objects.equals(firstName, other.firstName) && Objects.equals(id, other.id)
				&& Objects.equals(inactiveDate, other.inactiveDate) && Objects.equals(lastName, other.lastName)
				&& Objects.equals(notificationPreference, other.notificationPreference)
				&& Objects.equals(passwordHash, other.passwordHash) && Objects.equals(role, other.role)
				&& Objects.equals(salt, other.salt) && Objects.equals(smsNumber, other.smsNumber)
				&& Objects.equals(status, other.status);
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", status=" + status + ", role=" + role + "]";
	}

	
    
}