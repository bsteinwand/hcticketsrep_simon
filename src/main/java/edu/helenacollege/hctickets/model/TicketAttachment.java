package edu.helenacollege.hctickets.model;

import java.time.OffsetDateTime;
import java.util.Objects;
import java.util.UUID;

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
@Table(name = "TicketAttachment", schema = "hctickets")
//@ToString
//@NoArgsConstructor
//@EqualsAndHashCode
//@Data
public class TicketAttachment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TicketId", nullable = false)
    private Ticket ticket;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId", nullable = false)
    private User user;

    @Column(name = "FileName", nullable = false, length = 75)
    private String fileName;

    @Column(name = "FilePath", nullable = false, length = 184)
    private String filePath;

    @Column(name = "FileType", nullable = false, length = 30)
    private String fileType;

    @Column(name = "FileHash", nullable = false, length = 255)
    private String fileHash;

    @Column(name = "Title", nullable = false, length = 40)
    private String title;

    @Column(name = "GUID", nullable = false)
    private UUID guid;

    @Column(name = "DateUpdated", nullable = false)
    private OffsetDateTime dateUpdated;
    
	public TicketAttachment() {
		super();
	}

	public TicketAttachment(Integer id, Ticket ticket, User user, String fileName, String filePath, String fileType,
			String fileHash, String title, UUID guid, OffsetDateTime dateUpdated) {
		super();
		this.id = id;
		this.ticket = ticket;
		this.user = user;
		this.fileName = fileName;
		this.filePath = filePath;
		this.fileType = fileType;
		this.fileHash = fileHash;
		this.title = title;
		this.guid = guid;
		this.dateUpdated = dateUpdated;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileHash() {
		return fileHash;
	}

	public void setFileHash(String fileHash) {
		this.fileHash = fileHash;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public UUID getGuid() {
		return guid;
	}

	public void setGuid(UUID guid) {
		this.guid = guid;
	}

	public OffsetDateTime getDateUpdated() {
		return dateUpdated;
	}

	public void setDateUpdated(OffsetDateTime dateUpdated) {
		this.dateUpdated = dateUpdated;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateUpdated, fileHash, fileName, filePath, fileType, guid, id, ticket, title, user);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TicketAttachment other = (TicketAttachment) obj;
		return Objects.equals(dateUpdated, other.dateUpdated) && Objects.equals(fileHash, other.fileHash)
				&& Objects.equals(fileName, other.fileName) && Objects.equals(filePath, other.filePath)
				&& Objects.equals(fileType, other.fileType) && Objects.equals(guid, other.guid)
				&& Objects.equals(id, other.id) && Objects.equals(ticket, other.ticket)
				&& Objects.equals(title, other.title) && Objects.equals(user, other.user);
	}

	@Override
	public String toString() {
		return "TicketAttachment [id=" + id + ", ticket=" + ticket + ", user=" + user + ", fileName=" + fileName
				+ ", filePath=" + filePath + ", fileType=" + fileType + ", fileHash=" + fileHash + ", title=" + title
				+ ", guid=" + guid + ", dateUpdated=" + dateUpdated + "]";
	}

}